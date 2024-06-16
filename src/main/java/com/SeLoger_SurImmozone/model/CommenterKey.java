package com.SeLoger_SurImmozone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class CommenterKey {

	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "annonce_id")
	private Integer annonce_id;
}
