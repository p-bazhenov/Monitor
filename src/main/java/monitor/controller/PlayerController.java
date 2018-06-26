package monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import monitor.domain.User;
import monitor.service.StatsParser;

@Controller
public class PlayerController {

	@Autowired
	private StatsParser statsParser;
	
	@GetMapping("/details/{game}")
	public String getActivity(
			@AuthenticationPrincipal User user,
			@PathVariable("game") String game, 
			Model model
			) {
		
		model.addAttribute("game", statsParser.getLastWeekStats(game, user.getGameid()));
		model.addAttribute("user", user);

		return "/games/" + game;
	}

	
}
