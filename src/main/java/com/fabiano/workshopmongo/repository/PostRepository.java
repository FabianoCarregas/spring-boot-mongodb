package com.fabiano.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabiano.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{'title': {$regex: ?0, $options: '<options>'}}")
	List<Post> findByTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [{date: {$gte: ?1}}, { date: { $lte: ?2} } , { $or: [ {'title': {$regex: ?0, $options: '<options>'} }, {'body': {$regex: ?0, $options: '<options>'}},{'comments.text': {$regex: ?0, $options: '<options>'}} ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
