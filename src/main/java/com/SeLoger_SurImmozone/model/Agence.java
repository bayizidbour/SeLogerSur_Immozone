package com.SeLoger_SurImmozone.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Agence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAgence;
	
	
	@Size(min = 2, max=30)
	@Column(length = 30,nullable = false)
	private String nomAg;
	
	@Size(min =10,max=50)
	@Column(length = 50, unique=true)
	private String email;
	
	@Size(min =10,max=50)
	@Column(length = 50)
	private  String adresseAg;
	
	@Column( length = 5 )
	private int cp;
	
	@Size(min =3,max=20)
	@Column(length = 20, unique=true)
	private String ville;
	
	@Size(min =3,max=20)
	@Column(length = 50)
	private String pays;
	
	@OneToMany(mappedBy = "agence")
	private List<Propriete> propriete;	
	
	@OneToMany(mappedBy = "agence")
	private List<User> users;
}
