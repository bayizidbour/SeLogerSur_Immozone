package com.SeLoger_SurImmozone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SeLoger_SurImmozone.model.Agence;
import com.SeLoger_SurImmozone.repository.AgenceRepository;
import com.SeLoger_SurImmozone.service.AgenceService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/agence/admin")
public class AgenceController {

	@Autowired
	AgenceRepository agenceRepo;
	
	@Autowired
	AgenceService agenceService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("agences", agenceService.getAllAgences() );
		
		return"agence/index";
	}
	
	@GetMapping("/new")
	public String addNewAgence(Model model) {
		model.addAttribute("agence", new Agence());
		
		return"agence/new";
	}
	
	@PostMapping
	public String createAgence(@Valid Agence agence, Model model, BindingResult result, RedirectAttributes ra) {
		
		if(result.hasErrors()) {
			return "agence/new";
		}
		
		if(agence.getIdAgence() != null) {
			agenceService.update(agence.getIdAgence(), agence);
			ra.addFlashAttribute("success", "Agence modifié avec succès !");			
		}else {
			agenceService.createAgence(agence);
			ra.addFlashAttribute("success", "Agence ajouté avec succès !");
		}
		
		
		return"redirect:/agence/admin";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes ra) {
		
		if (agenceService.getAgenceById(id) == null) {
			ra.addFlashAttribute("warning", "Cet agence n'existe pas!");
			return"redirect:/agence/admin";
		}
			
		model.addAttribute("agence", agenceService.getAgenceById(id));
		return"agence/new";
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes ra) {
		if (agenceService.getAgenceById(id) == null) {
			ra.addFlashAttribute("warning", "Cet agence n'existe pas!");
			return "redirect:/agence/admin";
		}
		
		agenceService.deleteAgById(id);
		ra.addFlashAttribute("success", "Agence supprimé avec succès !");
		
		return "redirect:/agence/admin";
	}
	
	
}
