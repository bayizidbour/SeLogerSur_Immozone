package com.SeLoger_SurImmozone.model;

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
public class Maison extends Propriete {

	@Min(1)
	private int nbSalleDeBain;
	
	@Min(1)
	private int nbPiece;
}
