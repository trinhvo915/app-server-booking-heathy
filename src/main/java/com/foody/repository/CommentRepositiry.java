package com.foody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Comment;

@Repository
public interface CommentRepositiry extends JpaRepository<Comment, String>,CrudRepository<Comment, String>{

}