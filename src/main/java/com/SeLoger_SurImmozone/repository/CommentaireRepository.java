package com.SeLoger_SurImmozone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeLoger_SurImmozone.model.Commentaire;
import com.SeLoger_SurImmozone.model.CommenterKey;

public interface CommentaireRepository extends JpaRepository<Commentaire, CommenterKey> {

}
