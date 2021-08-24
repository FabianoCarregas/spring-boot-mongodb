package com.fabiano.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fabiano.workshopmongo.domain.Post;
import com.fabiano.workshopmongo.domain.User;
import com.fabiano.workshopmongo.dto.AuthorDTO;
import com.fabiano.workshopmongo.repository.PostRepository;
import com.fabiano.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria", "maria@gmail.com");
		User alex = new User(null, "Alex", "alex@gmail.com");
		User bob = new User(null, "Bob", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1= new Post(null, sdf.parse("21/02/2021"),"let's go to the trip!!", "I'm going to travel to Spain",new AuthorDTO(maria));
		Post post2= new Post(null, sdf.parse("23/02/2021"),"Good morning", "today is a beautiful day",new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
	
	

}
