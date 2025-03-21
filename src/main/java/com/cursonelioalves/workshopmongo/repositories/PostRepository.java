package com.cursonelioalves.workshopmongo.repositories;
 
 import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cursonelioalves.workshopmongo.domain.Post;
 
 @Repository
 public interface PostRepository extends MongoRepository<Post, String> {
 
 }
