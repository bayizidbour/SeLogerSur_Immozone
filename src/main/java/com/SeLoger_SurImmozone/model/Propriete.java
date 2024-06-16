package com.SeLoger_SurImmozone.model;

import java.util.List;

import com.SeLoger_SurImmozone.enums.Categorie;

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
public abstract class Propriete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPropriete;
	
	
	@Column(length = 30)
	@Size(min=7 , max=30)
	private String image;
	
	@Size( min = 60, max = 700 )
	private String description;
	
	
	private double surfaceHabitable;
	
	private boolean statutPropriete;
	
	@Min( 1 )
	private double prix;
	
	@Enumerated(EnumType.STRING)
	private Categorie venteOrLocate;
	
	@Column( length = 30 )
	private  String adresse;
	
	@Column( length = 5 )
	private int cp;
	
	@Column( length = 30 )
	private String ville;
	 
	@Column( length = 30 )
	private String pays;
	
	@ManyToOne
	@JoinColumn(name = "id_agence")
	private Agence agence;
	
	@OneToMany(mappedBy = "propriete")
	private List<Images> images;
	
	@ManyToOne
	@JoinColumn(name = "id_type")
	private TypePropriete type;
	
	@OneToMany(mappedBy = "propriete")
	private List<Annonce> annonces;
	
	public String getLogo() {
        return "/img/" + this.type.getNom() + "/" + this.image;
    }
}
