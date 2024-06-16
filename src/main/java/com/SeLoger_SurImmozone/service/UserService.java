package com.SeLoger_SurImmozone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeLoger_SurImmozone.model.User;
import com.SeLoger_SurImmozone.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
	UserRepository userRepo;
	
	public User addUser(User user) {
		return userRepo.save(user);
	}
	
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	
	public User getUserById(Integer id) {
		Optional<User> u = userRepo.findById(id);
		return u.isPresent()? u.get():null;
	}
	
	public User updateUser(Integer id, User userToUp) {
		Optional<User> optUser = userRepo.findById(id);
		
		if(optUser.isPresent()) {
			User userInBd = optUser.get();
			userInBd.setNom(userToUp.getNom());
			userInBd.setPrenom(userToUp.getPrenom());
			userInBd.setEmail(userToUp.getEmail());
			userInBd.setLogin(userToUp.getLogin());
			userInBd.setMdp(userToUp.getMdp());
			userInBd.setTel(userToUp.getTel());
			userInBd.setAdresse(userToUp.getAdresse());
			userInBd.setCp(userToUp.getCp());
			userInBd.setPays(userToUp.getPays());
			userInBd.setStatut(userToUp.getStatut());
			
			return userRepo.save(userInBd);
		}
		
		return null;
	}
	
	public void deleteUserById(Integer id) {
		this.userRepo.deleteById(id);
	}
	
	public void deleteUser(User user) {
		userRepo.delete(user);
	}
	
	
}
