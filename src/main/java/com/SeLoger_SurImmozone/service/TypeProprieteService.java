package com.SeLoger_SurImmozone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeLoger_SurImmozone.model.TypePropriete;
import com.SeLoger_SurImmozone.repository.TypeProprieteRepository;

@Service
public class TypeProprieteService {

	@Autowired
	TypeProprieteRepository typePrRepo;
	
	public TypePropriete createtype(TypePropriete t) {
		return typePrRepo.save(t);
	}
	
	public List<TypePropriete> getAlltypes(){
		return typePrRepo.findAll();
	}
	
	public TypePropriete getTypeById(Integer id) {
		Optional<TypePropriete> optType = typePrRepo.findById(id);
		
		return optType.isPresent()? optType.get():null;
	}
	
	public TypePropriete update(Integer id, TypePropriete typeToUp) {
		Optional<TypePropriete> optT = typePrRepo.findById(id);
		
		if(optT.isPresent()) {
			TypePropriete typeInBd = optT.get();
			
			typeInBd.setNom(typeToUp.getNom());
			return typePrRepo.save(typeInBd);
		}
		
		return null;
	}
	
	public void deletetypeById(Integer id) {
		typePrRepo.deleteById(id);
	}
	
	public void deleteType( TypePropriete type) {
		typePrRepo.delete(type);
	}
	
	
}
