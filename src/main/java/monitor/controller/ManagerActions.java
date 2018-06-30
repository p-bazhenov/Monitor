package monitor.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import monitor.domain.Role;
import monitor.domain.User;
import monitor.service.StatsParser;
import monitor.service.UserService;

@Controller
@PreAuthorize("hasAuthority('MANAGER')")
public class ManagerActions {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StatsParser statsParser;	
	
	@GetMapping("/adduser")
	public String addUser(
			@AuthenticationPrincipal User user,
			Model model
	) {
		User u = new User();
		u.setRoles(Collections.singleton(Role.PLAYER));
		model.addAttribute("user", user);
		model.addAttribute("newbie", u);
		return "registration";
	}

	@PostMapping("/adduser")
	public String saveNewUser(
			@RequestParam (name = "role") String role,
            @Valid User regUser,
            BindingResult bindingResult,
            RedirectAttributes redirect
	) {
		boolean newUser = userService.addUser(regUser, role);
		if (newUser) {
			redirect.addAttribute("message", "Registration was ended succesfully");
			return "redirect:/home";
		}
		
		redirect.addAttribute("message", "User alredy exist");
		return "redirect:/home";
	}

	@GetMapping("/edit/{username}")
	public String edit(
			@AuthenticationPrincipal User principal,
			@PathVariable String username, 
			Model model, 
			RedirectAttributes redirect
	) {
		User user = userService.findUserByUsername(username);
		if (user == null) {
			redirect.addAttribute("message", String.format("User %s not found",username));
			return "redirect:/home";
		}
		model.addAttribute("user", principal);
		model.addAttribute("newbie", user);
		return "registration";
	}
	
	@PostMapping("/saveuserchanges")
	public String saveChanges(
			@RequestParam (name = "role") String role,
            User changed,
            RedirectAttributes redirect
	) {
		boolean edit = userService.saveChangesOfUser(changed, role);
		if (!edit) {
			redirect.addFlashAttribute("class", "alert-danger");
			redirect.addFlashAttribute("message", String.format("User %s not found",changed.getFullname()));
			return "redirect:/home";
		}
		redirect.addFlashAttribute("class", "alert-success");
		redirect.addFlashAttribute("message", String.format("A changes for %s was saved.",changed.getFullname()));
		return "redirect:/home";
	}
	
	@GetMapping("/activity/{gameid}")
	public String viewUserProfile(
			@AuthenticationPrincipal User user,
			@PathVariable (name="gameid") Long targetUserId, 
			Model model 
		) {
		
		model.addAttribute("wot", statsParser.getShortResults("wot", targetUserId));
		model.addAttribute("wowp", statsParser.getShortResults("wowp", targetUserId));
		model.addAttribute("wows", statsParser.getShortResults("wows", targetUserId));
		model.addAttribute("wotb", statsParser.getShortResults("wotb", targetUserId));
		model.addAttribute("targetId", targetUserId);
		model.addAttribute("user", user);

		return "player";

	}
	
}
