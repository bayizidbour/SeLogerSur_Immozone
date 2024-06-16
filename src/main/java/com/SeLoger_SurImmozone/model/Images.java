package com.SeLoger_SurImmozone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idImage;
	
	private String nomImage;
	
	@ManyToOne
	@JoinColumn(name = "id_propriete")
	private Propriete propriete;
	
	
	
}
