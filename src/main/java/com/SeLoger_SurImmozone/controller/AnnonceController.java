package com.SeLoger_SurImmozone.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SeLoger_SurImmozone.model.Annonce;
import com.SeLoger_SurImmozone.model.Propriete;
import com.SeLoger_SurImmozone.model.User;
import com.SeLoger_SurImmozone.service.AnnonceService;
import com.SeLoger_SurImmozone.service.ProprieteService;
import com.SeLoger_SurImmozone.service.UserService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/agent/annonce")
public class AnnonceController {

	@Autowired
	AnnonceService annonceService;
	
	@Autowired
	ProprieteService proprieteService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("annonces", annonceService.getAllAnnonces());
		return"index2";
	}
	
	
	
	@GetMapping("/newAnnonce/{id}")
	public String createAnnonce(@PathVariable Integer id, Model model, HttpServletRequest request) {
		
//		HttpSession session  = request.getSession();
		Propriete propriete = proprieteService.getProprieteById(id);
		
//		System.out.println(session);
		
		model.addAttribute("date_now", LocalDate.now());
//		model.addAttribute("session", session);
		model.addAttribute("propriete", propriete);
		model.addAttribute("annonce", new Annonce());
		
		return "annonce/new";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam("idU") Integer idU, @RequestParam("idP") Integer idP ) {
		
		Annonce annonce = new Annonce();
		
		
		
		User user = userService.getUserById(idU);
		Propriete prop = proprieteService.getProprieteById(idP);
		
		annonce.setUser(user);
		annonce.setPropriete(prop);
		annonce.setDateAnnonce(LocalDate.now());
		
		annonceService.createAnnonce(annonce);
		
		
		return"redirect:/propriete/admin";
		
	} 
	
	
	
	
}
