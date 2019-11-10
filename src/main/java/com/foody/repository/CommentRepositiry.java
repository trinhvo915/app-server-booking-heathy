package com.foody.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.foody.entities.Comment;

@Repository
public interface CommentRepositiry extends JpaRepository<Comment, String>,CrudRepository<Comment, String>{
	
	@Query(value ="Select * from comment where  comment.id_clinic = :id_clinic and comment.id_expert = :id_expert", nativeQuery=true)
	Set<Comment> getCommnetsByIdClincAndIdExpert(@Param("id_clinic") String id_clinic, @Param("id_expert") String id_expert);
}
