package com.SeLoger_SurImmozone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeLoger_SurImmozone.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByLogin(String login);
	User findByEmail(String email);
	
	User findByLoginAndMdp(String login, String mdp);
}
