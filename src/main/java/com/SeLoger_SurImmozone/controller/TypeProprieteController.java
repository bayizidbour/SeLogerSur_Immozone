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

import com.SeLoger_SurImmozone.model.TypePropriete;
import com.SeLoger_SurImmozone.service.TypeProprieteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/type/admin")
public class TypeProprieteController {

	@Autowired
	TypeProprieteService typePrService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("types", typePrService.getAlltypes());
		return "typePropriete/index";
	}
	
	@GetMapping("/new")
	public String addNewType(Model model) {
		model.addAttribute("type", new TypePropriete());
		
		return "typePropriete/new";
	}
	
	@PostMapping
	public String createType(@Valid TypePropriete typeP, BindingResult result, RedirectAttributes ra) {
		
		if(result.hasErrors()) {
			return "typePropriete/new";
		}
		
		if(typeP.getId() != null) {
			typePrService.update(typeP.getId(), typeP);
			ra.addFlashAttribute("success", "Type de propriété modifié avec succès");
		}else {
			typePrService.createtype(typeP);
			ra.addFlashAttribute("success", "Type de propriété ajouté avec succès!");			
		}
		
		return"redirect:/type/admin";
	}
	
	@GetMapping("/update/{id}")
	public String update( @PathVariable int id, Model model, RedirectAttributes ra) {
		
		if(typePrService.getTypeById(id) == null) {
			ra.addFlashAttribute("warning", "Ce type de propriété n'existe pas!");
			return "redirect:/type/admin";
		}
		
		model.addAttribute("type", typePrService.getTypeById(id));
		return "typePropriete/new";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes ra) {
		
		if(typePrService.getTypeById(id) == null) {
			ra.addFlashAttribute("warning", "Ce type de proprieté n'existe pas");
			return "redirect:/type/admin";
		}
		
		typePrService.deletetypeById(id);
		ra.addFlashAttribute("success", "Type de propriété supprimé avec succès!");
		
		return "redirect:/type/admin";
	}
}
