package com.muhammet.repository;

import com.muhammet.document.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String> {
}
