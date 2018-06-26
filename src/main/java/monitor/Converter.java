package monitor;


public class Converter {

}

/*
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.google.gson.JsonParser;

import monitor.domain.Statistic;
import monitor.domain.User;
import monitor.repos.StatsRepo;
import monitor.service.UserService;

@Component
public class Converter {

	@Autowired
	private UserService userService;
	
	@Autowired
	private StatsRepo statsRepo;
	/*@EventListener(ApplicationReadyEvent.class)
	public void changeUsersInDB() {
		
		List<User> users = userService.findAll();
		
		for (User u : users) {
			userService.addUser(u, "PLAYER");
		}
		
	    System.out.println("Update finish");
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void changeStatistic(){
		List<Statistic> stats = statsRepo.findAll();
		for (Statistic st : stats) {
			String data = st.getStatisticJSON();
			try {
			String stata = new JsonParser().parse(data).getAsJsonObject()
					.get("data").getAsJsonObject()
					.get(st.getGameId().toString()).getAsJsonObject()
					.toString();
			st.setStatisticJSON(stata);
			statsRepo.save(st);
			} catch (Exception e) {
				
			}
		}
		
	    System.out.println("Update finish");
	}
	
	
}*/