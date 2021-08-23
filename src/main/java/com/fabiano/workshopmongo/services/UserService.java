package com.fabiano.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiano.workshopmongo.domain.User;
import com.fabiano.workshopmongo.dto.UserDTO;
import com.fabiano.workshopmongo.repository.UserRepository;
import com.fabiano.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	
	@Autowired
	private UserRepository repo;
	
	public List<User> findall(){
	 return repo.findAll();
	 
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
