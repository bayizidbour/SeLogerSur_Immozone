package com.SeLoger_SurImmozone.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Annonce {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAnnonce;
	
	
	private LocalDate dateAnnonce = LocalDate.now() ;
	
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "id_propriete")
	private Propriete propriete;
	
	@OneToMany(mappedBy = "annonce")
	private List<Commentaire> Commentaires;
	
	@OneToMany(mappedBy = "annonce")
	private List<Transaction> transactions;

	@Override
	public String toString() {
		return "Annonce [idAnnonce=" + idAnnonce + ", dateAnnonce=" + dateAnnonce + ", user=" + user + ", propriete="
				+ propriete + "]";
	}
	
	
	
}
