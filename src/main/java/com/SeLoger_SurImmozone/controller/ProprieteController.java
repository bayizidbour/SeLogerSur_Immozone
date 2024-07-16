package com.SeLoger_SurImmozone.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SeLoger_SurImmozone.model.Appartement;
import com.SeLoger_SurImmozone.model.Maison;
import com.SeLoger_SurImmozone.model.Propriete;
import com.SeLoger_SurImmozone.model.Terrain;
import com.SeLoger_SurImmozone.service.AgenceService;
import com.SeLoger_SurImmozone.service.ProprieteService;
import com.SeLoger_SurImmozone.service.TypeProprieteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/propriete/admin")
public class ProprieteController {

	@Autowired
	ProprieteService proprieteService;
	
	@Autowired
	AgenceService agenceService;
	
	@Autowired
	TypeProprieteService typePropriete;
	
	final String     UPLOADDIR = "src\\main\\resources\\static\\img\\";
	
	/**
	 * LISTER TOUS LES TYPES DE PROPRIETE
	 * @param model
	 * @return
	 */
	@GetMapping
	public String index(Model model) {
		System.out.println("dans propriete/index");
		model.addAttribute("proprietes", proprieteService.getAllProprites());
		return"propriete/index";
	}
	
	/**
	 * <p class="text-danger">LISTER TOUS LES TERRAINS</p>
	 * @param model
	 * @return
	 */
	@GetMapping("/showListAppart")
	public String indexAppart (Model model) {
		model.addAttribute("proprietes", proprieteService.getAllProprites());
		return "propriete/showListAppart";
	}
	
	//	LISTER TOUTES LES MAISONS
	@GetMapping("/showListMaison")
	public String indexMaison (Model model) {
		model.addAttribute("proprietes", proprieteService.getAllProprites());
		return "propriete/showListMaison";
	}
	
	//	LISTER TOUS LES TERRAINS
	@GetMapping("/showListTerrain")
	public String indexTerrain (Model model) {
		model.addAttribute("proprietes", proprieteService.getAllProprites());
		return "propriete/showListTerrain";
	}
	
	
	//	RENVOIES VERS LE FORMULAIRE QUI PERMET DE CREER ET AJOUTER UNE PROPRIETE
	@GetMapping("/addPropriete/{id}")
	public String addNewPropriete( @PathVariable Integer id, Model model) {
		model.addAttribute("agences", agenceService.getAllAgences());
		model.addAttribute("types", typePropriete.getAlltypes());
		
		
		if(id == 1) {
			model.addAttribute("propriete", new Appartement());
			return "propriete/appart";
		}else if(id == 2) {
			model.addAttribute("propriete", new Maison());
			return "propriete/maison";
		}else {
			model.addAttribute("propriete", new Terrain());
			return "propriete/terrain";
		}
	
		
	}
	
	//	MODIFiER OU AJOUTER UNE PRORIETE DE TYPE APPARTEMENT EN BASE DE DONNEE
	@PostMapping("/appart")	
	public String insertAppart(@Valid Appartement appart, Model model, BindingResult result, RedirectAttributes ra,  @RequestParam( "photo" ) MultipartFile photo) throws IOException {
		if(result.hasErrors()) {
			model.addAttribute("agences", agenceService.getAllAgences());
			model.addAttribute("types", typePropriete.getAlltypes());
			return "propriete/appart";
		}
		
		 // NOM PHOTO UPLOADE
        String fileName = StringUtils.cleanPath( photo.getOriginalFilename() );

        // DOSSIER DESTINATION
        Path uploadPath = Paths.get( UPLOADDIR + appart.getType().getNom() + "\\" );

        // TEST SI LE DOSSIER DE DOSTINATION N'EXISTE PAS
        if ( !Files.exists( uploadPath ) ) {
            Files.createDirectories( uploadPath );
        }

        // COPIE DE LA PHOTO DANS SON REPERTOIRE
        InputStream inputStream = photo.getInputStream();
        Path filePath = uploadPath.resolve( fileName );
        Files.copy( inputStream, filePath, StandardCopyOption.REPLACE_EXISTING );

        appart.setImage( fileName );

		
		if(appart.getIdPropriete() != null) {
			proprieteService.update(appart.getIdPropriete(), appart);
			ra.addFlashAttribute("success", "Propriété de type Appartement modifié avec succès!");
		}else {
			proprieteService.createPropriete(appart);
			ra.addFlashAttribute("success", "Propriété de type Appartement ajouté avec succès!");
		}
		return "redirect:/propriete/admin";
	}
	
	//	MODIFiER OU AJOUTER UNE PRORIETE DE TYPE MAISON EN BASE DE DONNEE
	@PostMapping("/maison")
	public String insertMaison(@Valid Maison maison, Model model, BindingResult result, RedirectAttributes ra, @RequestParam("photo") MultipartFile photo) throws IOException {
		if(result.hasErrors()) {
			model.addAttribute("agences", agenceService.getAllAgences());
			model.addAttribute("types", typePropriete.getAlltypes());
			return "propriete/maison";
		}
		
		// NOM PHOTO UPLOADE
        String fileName = StringUtils.cleanPath( photo.getOriginalFilename() );

        // DOSSIER DESTINATION
        Path uploadPath = Paths.get( UPLOADDIR + maison.getType().getNom() + "\\" );

        // TEST SI LE DOSSIER DE DOSTINATION N'EXISTE PAS
        if ( !Files.exists( uploadPath ) ) {
            Files.createDirectories( uploadPath );
        }

        // COPIE DE LA PHOTO DANS SON REPERTOIRE
        InputStream inputStream = photo.getInputStream();
        Path filePath = uploadPath.resolve( fileName );
        Files.copy( inputStream, filePath, StandardCopyOption.REPLACE_EXISTING );

        maison.setImage( fileName );
		
		if(maison.getIdPropriete() != null) {
			proprieteService.update(maison.getIdPropriete(), maison);
			ra.addFlashAttribute("success", "Propriété de type Maison modifié avec succès!");
		}else {
			proprieteService.createPropriete(maison);
			ra.addFlashAttribute("success", "Propriété de type Maison ajouté avec succès!");
		}
		
		return "redirect:/propriete/admin";
	}
	
	//	MODIFiER OU AJOUTER UNE PRORIETE DE TYPE TERRAIN EN BASE DE DONNEE
	@PostMapping("/terrain")
	public String insertTerrain(@Valid Terrain terrain, Model model, BindingResult result, RedirectAttributes ra, @RequestParam("photo") MultipartFile photo) throws IOException {
		if(result.hasErrors()) {
			model.addAttribute("agences", agenceService.getAllAgences());
			model.addAttribute("types", typePropriete.getAlltypes());
			return "propriete/terrain";
		}
		
		// NOM PHOTO UPLOADE
        String fileName = StringUtils.cleanPath( photo.getOriginalFilename() );

        // DOSSIER DESTINATION
        Path uploadPath = Paths.get( UPLOADDIR + terrain.getType().getNom() + "\\" );

        // TEST SI LE DOSSIER DE DOSTINATION N'EXISTE PAS
        if ( !Files.exists( uploadPath ) ) {
            Files.createDirectories( uploadPath );
        }

        // COPIE DE LA PHOTO DANS SON REPERTOIRE
        InputStream inputStream = photo.getInputStream();
        Path filePath = uploadPath.resolve( fileName );
        Files.copy( inputStream, filePath, StandardCopyOption.REPLACE_EXISTING );

        terrain.setImage( fileName );
		
		if(terrain.getIdPropriete() != null) {
			proprieteService.update(terrain.getIdPropriete(), terrain);
			ra.addFlashAttribute("success", "Propriété de type Terrain modifié avec succès!");
		}else {
			proprieteService.createPropriete(terrain);
			ra.addFlashAttribute("success", "Propriété de type Terrain ajouté avec succès!");
		}
		
		return "redirect:/propriete/admin";
	}
	
	
	//	Modifier une propriété
	@GetMapping("/update/{id}")
	public String update( @PathVariable int id, RedirectAttributes ra, Model model) {
		if(proprieteService.getProprieteById(id) == null) {
			ra.addFlashAttribute("warning", "Cette propriete n'existe pas !");
			return "redirect:/propriete/admin";
		}
		
		Propriete propriete = proprieteService.getProprieteById(id);
		
		model.addAttribute("agences", agenceService.getAllAgences());
		model.addAttribute("types", typePropriete.getAlltypes());
		model.addAttribute("propriete", proprieteService.getProprieteById(id));
		
		if(propriete instanceof Maison) {
			return "propriete/maison";
		}else if (propriete instanceof Appartement) {
			return "propriete/appart";
		}else {
			return"propriete/terrain";
		}
	
	}
	
	
	//	Supprimer une propriété
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes ra) {
		
		if(proprieteService.getProprieteById(id) == null) {
			ra.addFlashAttribute("warning", "Cette propriete n'existe pas !");
			return "redirect:/propriete/admin";
		}
		
		proprieteService.deleteById(id);
		ra.addFlashAttribute("success", "Proprieté supprimée avec succès!");
		return "redirect:/propriete/admin";
	}
}
