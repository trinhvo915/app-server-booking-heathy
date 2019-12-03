package com.foody.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.foody.entities.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, String>, CrudRepository<Price, String> {
	@Query(value ="Select * from price where  price.id_clinic = :id_clinic", nativeQuery=true)
	List<Price> getPricesClinic(@Param("id_clinic") String id_clinic);
}
