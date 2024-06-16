package com.SeLoger_SurImmozone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeLoger_SurImmozone.model.Propriete;
import com.SeLoger_SurImmozone.repository.ProprieteRepository;

@Service
public class ProprieteService {

	@Autowired
	ProprieteRepository proprieteRepo;
	
	public Propriete createPropriete(Propriete pr) {
		return proprieteRepo.save(pr);
	}
	
	public List<Propriete> getAllProprites(){
		return proprieteRepo.findAll();
	}
	
	public Propriete getProprieteById(Integer id) {
		Optional<Propriete> optPr = proprieteRepo.findById(id);
		
		return optPr.isPresent()? optPr.get():null;
	}
	
	public Propriete update(Integer id, Propriete prToUp) {
		Optional<Propriete> optPr = proprieteRepo.findById(id);
		
		if(optPr.isPresent()) {
			Propriete prInBd = optPr.get();
			
			prInBd.setDescription(prToUp.getDescription());
			prInBd.setSurfaceHabitable(prToUp.getSurfaceHabitable());
			prInBd.setType(prToUp.getType());
			prInBd.setVenteOrLocate(prToUp.getVenteOrLocate());
			prInBd.setAdresse(prToUp.getAdresse());
			prInBd.setCp(prToUp.getCp());
			prInBd.setVille(prToUp.getVille());
			prInBd.setPays(prToUp.getPays());
			prInBd.setPrix(prToUp.getPrix());
			
		return proprieteRepo.save(prInBd);
		}
		return null;
	}
	
	public void deleteById(Integer id) {
		proprieteRepo.deleteById(id);
	}
	
	public void deleteP(Propriete pr) {
		proprieteRepo.delete(pr);
	}
}
