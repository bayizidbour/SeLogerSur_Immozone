package com.SeLoger_SurImmozone.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTransaction;
	
	
	private LocalDate dateTransaction = LocalDate.now();
	
	private String natureTransaction;
	
	@ManyToOne
	@JoinColumn(name = "id_annonce")
	private Annonce annonce;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	
}
