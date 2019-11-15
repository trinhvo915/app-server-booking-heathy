package com.foody.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foody.entities.Post;;

@Repository
public interface PostRepository extends JpaRepository<Post, String>, CrudRepository<Post, String>{
	
	@Query(value ="Select * from post where  post.id_clinic = :id_clinic and post.id_post_type = :id_post_type", nativeQuery=true)
	Set<Post> getPostFollowTypePost(@Param("id_clinic") String id_clinic, @Param("id_post_type") String id_post_type);
}
