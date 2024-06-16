package com.SeLoger_SurImmozone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeLoger_SurImmozone.model.Agence;
import com.SeLoger_SurImmozone.repository.AgenceRepository;

@Service
public class AgenceService {

	@Autowired
	AgenceRepository agenceRepo;
	
	public Agence createAgence(Agence agence) {
		return agenceRepo.save(agence);
	}
	
	public List<Agence> getAllAgences(){
		return agenceRepo.findAll();
	}
	
	public Agence getAgenceById(Integer id) {
		Optional<Agence> optAg = agenceRepo.findById(id);
		
		return optAg.isPresent() ? optAg.get() : null;
	}
	
	public Agence update(Integer id, Agence agToUp) {
		
		Optional<Agence> optAg = agenceRepo.findById(id);
		
		if ( optAg.isPresent()) {
			Agence agInBd = optAg.get();
			
			agInBd.setNomAg(agToUp.getNomAg());
			agInBd.setEmail(agToUp.getEmail());
			agInBd.setAdresseAg(agToUp.getAdresseAg());
			agInBd.setCp(agToUp.getCp());
			agInBd.setVille(agToUp.getVille());
			agInBd.setPays(agToUp.getPays());
			
			return agenceRepo.save(agInBd);
		}
		
		return null;
	}
	
	
	public void deleteAgById(Integer id) {
		agenceRepo.deleteById(id);
	}
	
	public void deleteAgence(Agence agence) {
		agenceRepo.delete(agence);
	}
	
	
}
