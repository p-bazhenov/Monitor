package monitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import monitor.domain.User;
import monitor.repos.UserRepo;
import monitor.service.StatsParser;

@Controller
public class Monitor {
	
	@Autowired
	private UserRepo userRepo;
			
	@Autowired
	private StatsParser statsParser;	
	
	@GetMapping("/")
	public String start(@AuthenticationPrincipal User user) {
		if (user != null) {
			return "redirect:/home";
		}
		return "login";
	}

	@GetMapping("/home")
	public String getHome(
		@AuthenticationPrincipal User user, 
		Model model
			) {
		if(user.isManager()) {
			List<User> players = userRepo.findAll();
			model.addAttribute("players", players);
			model.addAttribute("user", user);
			return "manager";
		}
		
		model.addAttribute("wot", statsParser.getShortResults("wot", user.getGameid()));
		model.addAttribute("wowp", statsParser.getShortResults("wowp", user.getGameid()));
		model.addAttribute("wows", statsParser.getShortResults("wows", user.getGameid()));
		model.addAttribute("wotb", statsParser.getShortResults("wotb", user.getGameid()));
		model.addAttribute("user", user);

		return "player";
	}
		
}