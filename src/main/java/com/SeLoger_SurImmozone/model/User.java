package com.SeLoger_SurImmozone.model;

import java.util.List;

import com.SeLoger_SurImmozone.enums.Statut;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUser;
	
	@Column( length = 10 )
	private String sexe;
	
	
	@Size(min = 2, max=30)
	@Column(length = 30,nullable = false)
	private String prenom;
	
	
	@Size(min = 2, max=30)
	@Column(length = 30,nullable = false)
	private String nom;
	
	
	@Size(min =6,max=8)
	@Column(length = 8, unique = true, nullable = false)
	private String login;
	
	@Size(min =10,max=50)
	@Column(length = 50, unique=true, nullable = false)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String mdp;
	
	@Column(length = 12)
	private String tel;
	
	@Column( length = 30 )
	private  String adresse;
	
	@Column( length = 5 )
	private int cp;
	
	@Column( length = 30 )
	private String ville;
	 
	@Column( length = 30 )
	private String pays;
	
	@Enumerated(EnumType.STRING)
	private Statut statut;
	
	@OneToMany(mappedBy = "user")
	private List<Annonce> annonces;
	
	@OneToMany(mappedBy = "user")
	private List<Commentaire> commentaires;
	
	@ManyToOne
	@JoinColumn(name = "id_agence")
	private Agence agence;
	
	@OneToMany(mappedBy = "user")
	private List<Transaction> transactions;
}
