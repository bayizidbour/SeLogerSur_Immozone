package com.SeLoger_SurImmozone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Appartement extends Propriete {

	@Min(1)
	private int nbPiece;
	
	@Min(1)
	private int nbSalleDeBain;
	
	@Column( length = 10 )
	private String ascensseur;
}
