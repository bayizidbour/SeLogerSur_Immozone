package com.SeLoger_SurImmozone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeLoger_SurImmozone.model.Annonce;
import com.SeLoger_SurImmozone.repository.AnnonceRepository;

@Service
public class AnnonceService {

	@Autowired
	AnnonceRepository annonceRepo;
	
	public Annonce createAnnonce(Annonce annonce) {
		return annonceRepo.save(annonce);
	}
	
	public List<Annonce> getAllAnnonces(){
		return annonceRepo.findAll();
	}
	
	public Annonce getAnnonceById(Integer id) {
		Optional<Annonce> optAnnonce = annonceRepo.findById(id);
		
		return optAnnonce.isPresent()? optAnnonce.get() : null;
	}
	
	
	public void deleteAnnonceById(Integer id) {
		annonceRepo.deleteById(id);
	}
	
	
	public void delete(Annonce annonce) {
		annonceRepo.delete(annonce);
	}
	
	public Annonce update(Integer id, Annonce annToUp) {
		Optional<Annonce> optAnn = annonceRepo.findById(id);
		
		if (optAnn.isPresent()) {
			Annonce annInBd = optAnn.get();
			
			annInBd.setDateAnnonce(annToUp.getDateAnnonce());
			annInBd.setPropriete(annToUp.getPropriete());
			annInBd.setUser(annToUp.getUser());
			
			return annonceRepo.save(annInBd);
		}
		
		return null;
	}
	
}
