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
	
	/**
	 * METHODE POUR L'AJOUT D'UN AGENCE EN BASE DE DONNEE
	 * @param agence
	 * @return
	 */
	public Agence createAgence(Agence agence) {
		return agenceRepo.save(agence);
	}
	
	
	/**
	 * METHODE QUI PERMET DE LISTER TOUS LES AGENCES ENREGISTREE EN BASE DE DONNEE
	 * @return un Array list de tous les agences se trouvant en base de donnée
	 */
	public List<Agence> getAllAgences(){
		return agenceRepo.findAll();
	}
	
	
	/**
	 * METHODE PERMETTANT DE RECHERCHER UNE AGENCE GRACE A SON Id
	 * @param id
	 * @return un agence s'il existe en base de donnée sinon retourne null
	 */
	public Agence getAgenceById(Integer id) {
		Optional<Agence> optAg = agenceRepo.findById(id);
		
		return optAg.isPresent() ? optAg.get() : null;
	}
	
	
	/**
	 * METHODE PERMETTANT DE RECHERCHER UN AGENCE VIA SON Id, DE LE MODIFIER ET DE L'AJOUTER EN BASE DE DONNEE
	 * @param id
	 * @param agToUp
	 * @return
	 */
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
	
	/**
	 * METHODE POUR LA SUPPRESSION D'UNE AGENCE VIA SON Id
	 * @param id
	 */
	public void deleteAgById(Integer id) {
		agenceRepo.deleteById(id);
	}
	
	
	/**
	 * 
	 * @param agence
	 */
	public void deleteAgence(Agence agence) {
		agenceRepo.delete(agence);
	}
	
	
}
