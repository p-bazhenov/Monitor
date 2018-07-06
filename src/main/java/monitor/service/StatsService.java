package monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import monitor.domain.ShortResults;
import monitor.domain.Statistic;
import monitor.repos.StatsRepo;


@Component
public class StatsService {

	@Autowired
	private StatsRepo statsRepo;	
	
	public ShortResults getLastDayStats(String projectName, Long userid) {
		Statistic statistic = statsRepo.findFirstByProjectNameAndGameIdOrderByIdDesc(projectName, userid);
		return parseStatistic(statistic, projectName);		
	}
	
	public List<Statistic> getLast3DaysStats(String projectName, Long userid){
		List<Statistic> stats= statsRepo.findTop3ByProjectNameAndGameIdOrderByIdDesc(projectName, userid);
		return stats;
	}

	public ShortResults getResultsByDateProjectGameid(String datestring, String projectName, Long userid) {
		Statistic statistic = statsRepo.findByGameIdAndDatestampAndProjectName(userid, datestring, projectName);
		return parseStatistic(statistic, projectName);
	}
	
	private ShortResults parseStatistic(Statistic statistic, String projectName) {
		
		if (statistic.getStatisticJSON() == null){
			return new ShortResults(0, 0, "0");
		}
		
		JsonObject st = new JsonParser()
				.parse(statistic.getStatisticJSON()).getAsJsonObject();
		
		Long lastBattleTime = st.get("last_battle_time").getAsLong();
		int battlesCount = 0;
		JsonElement stat = st.get("statistics");
		if (!(stat instanceof JsonNull)) {
			if(projectName.equals("wows")) {
				battlesCount = stat.getAsJsonObject()
						.get("pvp").getAsJsonObject()
						.get("battles").getAsInt();
			}else {
				battlesCount = stat.getAsJsonObject()
						.get("all").getAsJsonObject()
						.get("battles").getAsInt();
			}
		}
		
		return new ShortResults(battlesCount, lastBattleTime, statistic.getDatestamp());		
	}
		
}
