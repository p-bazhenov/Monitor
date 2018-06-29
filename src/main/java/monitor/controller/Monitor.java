package monitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import monitor.domain.User;
import monitor.repos.UserRepo;
import monitor.service.StatsParser;
import monitor.service.UserService;

@Controller
public class Monitor {
	
	@Autowired
	private UserService userService;
			
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
			List<User> players = userService.findAll();
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
	
	@GetMapping("/passwordreset")
	public String getPasswordResetPage(
			@AuthenticationPrincipal User user, 
			Model model
		) {
		model.addAttribute("user", user);
		return "passwordreset";
	}
	
	@PostMapping("/passwordreset")
	public String doPasswordReset(
			@AuthenticationPrincipal User user, 
			@RequestParam (name="password") String password,
			@RequestParam (name="confirm") String confirm,
			Model model, 
			RedirectAttributes redirect
		) {
		
		boolean isSucces = userService.passwordUpdate();
		
		redirect.addAttribute("message", "Password was updated");
		redirect.addAttribute("user", user);
		return "redirect:/home";
	}

	
}