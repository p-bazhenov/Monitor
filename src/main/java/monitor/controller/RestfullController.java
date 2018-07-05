package monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import monitor.domain.ShortResults;
import monitor.repos.StatsRepo;

@RestController
public class RestfullController {

	@Autowired
	private StatsRepo statsRepo;
	
	@GetMapping("/api/{project}/{date}")
	public ShortResults getData(
			@PathVariable String project,
			@PathVariable String dt
	) {
		
	
		return new ShortResults();
	}
	
	
}
