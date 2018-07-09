package monitor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;

import monitor.domain.GameProjects;
import monitor.domain.Statistic;
import monitor.domain.User;
import monitor.repos.StatsRepo;

@Component
public class Task {
	
    private static final Logger log = LoggerFactory.getLogger(Task.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private final String USER_AGENT = "Mozilla/5.0";

    @Autowired
    private UserService userService;
    
    @Autowired
    private StatsRepo statsRepo;
    
	@Scheduled(cron="0 10 01 * * *", zone="Europe/Minsk") 
	public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        List<User> users = userService.findAll();
    	GameProjects[] projects = GameProjects.values();
    	String ld = LocalDate.now().toString();
        for (User u : users) {
        	for (GameProjects project : projects) {
	        	try {
	        		Statistic st = new Statistic();
	        		st.setDatestamp(ld);
	        		st.setGameId(u.getGameid());
	        		st.setProjectName(project.name());
					String stat = getStatistic(u.getGameid(), project.name().toLowerCase());
					st.setStatisticJSON(stat);
					System.out.println(stat);
					statsRepo.save(st);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    }
	
	
	public String getStatistic(Long gameId, String projectName) {

		String url = getUrl(gameId, projectName);
		String apiData = apiCall(url);

		JsonElement output = new JsonParser().parse(apiData).getAsJsonObject()
				.get("data");
		if (output instanceof JsonNull) {
			return null;
		}
				
		return output.getAsJsonObject()
				.get(gameId.toString())
				.getAsJsonObject()
				.toString();
	}	
	
	private String getUrl(Long gameId, String projectName) {
		String url = null;
		String params = "application_id=4ab8f2e5a25bbc48084fcf6e97663256&account_id="+gameId;
			
		switch (projectName) {
			case "wot":
				url = "https://api.worldoftanks.ru/wot/account/info/?";
				break;
			case "wowp":
				url = "https://api.worldofwarplanes.ru/wowp/account/info2/?";
				break;
			case "wows":
				url = "https://api.worldofwarships.ru/wows/account/info/?";
				break;
			case "wotb":
				url = "https://api.wotblitz.ru/wotb/account/info/?";
		}
		
		return url + params;
	}
	
	private String apiCall(String url) {
		String data = null;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestProperty("User-Agent", USER_AGENT);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			data = response.toString();
		} catch (MalformedURLException e) {
			return data;
		} catch (IOException e) {
			return data;
		}
		return data;
	}
}