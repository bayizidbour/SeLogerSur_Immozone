package com.SeLoger_SurImmozone.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {

	@EmbeddedId
	private CommenterKey id;
	
	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name ="user_id")
	private User user;
	
	@ManyToOne
	@MapsId("annonce_id")
	@JoinColumn(name = "annonce_id")
	private Annonce annonce;
	
	@Size( min = 60, max = 700 )
	private String contenuComment;
	
	
	private LocalDate dateComment = LocalDate.now();
}
