package monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import monitor.domain.ShortResults;
import monitor.domain.User;
import monitor.service.StatsService;

@RestController
public class RestfullController {

	@Autowired
	private StatsService statsService;

	@GetMapping("/api/{targetid}/{project}/{date}")
	public ShortResults getData(
			@AuthenticationPrincipal User user,
			@PathVariable(name = "targetid") Long targetid,
			@PathVariable(name = "project") String project,
			@PathVariable(name = "date") String dt
	) {
		ShortResults sr = statsService.getResultsByDateProjectGameid(dt, project, targetid);
		return sr;
	}
		
}
