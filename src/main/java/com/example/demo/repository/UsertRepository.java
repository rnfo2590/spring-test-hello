package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usert;

@Repository
public interface UsertRepository extends JpaRepository<Usert, Integer> {
	
	
	Optional<Usert>findByNameAndId(String name, Integer id );
	
	

}
