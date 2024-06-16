package com.SeLoger_SurImmozone.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SeLoger_SurImmozone.enums.Statut;
import com.SeLoger_SurImmozone.model.User;
import com.SeLoger_SurImmozone.repository.UserRepository;
import com.SeLoger_SurImmozone.service.AgenceService;
import com.SeLoger_SurImmozone.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AgenceService agenceService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String login () {
		return "user/login";
	}
	
	@GetMapping("/admin/listUser")
	public String listUser(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "user/listUser";
	}
	
	@GetMapping("/admin/newUser")
	public String ajoutNewUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("agences", agenceService.getAllAgences());
		return "user/newUser";
	}
	
	@GetMapping("/logon")
	public String logon(Model model){
		model.addAttribute("user", new User());
		return "user/logon";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/user/login";
	}
	
	@PostMapping("/logon")
	public String userLogon(@Valid User user, BindingResult result, Model model, RedirectAttributes ra) {
		
		String email = user.getEmail();
		String login = user.getLogin();
				
		if(result.hasErrors()) {
			System.out.println("dans userHasError");
//			model.addAttribute("user", user);
			return "user/logon";
		}
		
		if(userRepo.findByLogin(login) != null || userRepo.findByEmail(email) != null) {
			model.addAttribute("warning", "1");
//			model.addAttribute("user", user);
			return "user/logon";
		}
		System.out.println(user);
		user.setStatut(Statut.CLIENT);
		userService.addUser(user);
		 ra.addFlashAttribute("success", "Inscription éffectué avec succès ! ");
		return "redirect:/index2";
	}
	
	@PostMapping("/admin/ajoutUser")
	public String insertUser(@Valid User user, 
			BindingResult result, 
			Model model, 
			RedirectAttributes ra) {
		System.out.println("dans post mapping ajout user");
		String email = user.getEmail();
		String login = user.getLogin();
		
		if(result.hasErrors()) {
			model.addAttribute("agences", agenceService.getAllAgences());
			return "user/newUser";
		}
		
		if(user.getIdUser() != null) {
			userService.updateUser(user.getIdUser(), user);
			ra.addFlashAttribute("success", "Utilisateur modifié avec success");
		}else {
			if(userRepo.findByLogin(login) != null || userRepo.findByEmail(email) != null) {
				model.addAttribute("warning", "1");
//				model.addAttribute("user", user);
				return "user/logon";
			}
			userService.addUser(user);
			ra.addFlashAttribute("success", "User ajouté avec succès! ");
		}
		
		return "redirect:/user/admin/listUser";
		
	}
	
	
	@PostMapping("/login")
	public String login(
			@RequestParam("login") String login, 
			@RequestParam("mdp") String mdp, 
			HttpSession session)  {
		
		User user = userRepo.findByLoginAndMdp(login, mdp);
		
		session.setAttribute("user", user);
		
		
		System.out.println("login "+ login + "mdp "+ mdp );
		return "user/login";
	}
	
	@GetMapping( "/update/{id}" )
	public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
		if (userService.getUserById(id) == null) {
			ra.addAttribute("warning", "Cet utilisateur n'existe pas!");
			return "redirect:/user/admin/listUser";
		}
		
		model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("agences", agenceService.getAllAgences());
		return "user/newUser";
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes ra) {
		if(userService.getUserById(id) == null) {
			ra.addFlashAttribute("warning", "cet utilisateur n'existe pas");
			return "redirect:/user/admin/listUser";
		}
		
		userService.deleteUserById(id);
		ra.addFlashAttribute("success", "Utilisateur supprimé avec succès!");
		return "redirect:/user/admin/listUser";
	}
}
