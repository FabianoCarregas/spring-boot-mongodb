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
import com.fabiano.workshopmongo.dto.CommentDTO;
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
		
		CommentDTO c1 = new CommentDTO("Have a good trip mate!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Enjoy", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Nice mate", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c3));
		post2.getComments().add(c2);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		
		
	}
	
	

}
