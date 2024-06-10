package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	List<Item>findByNameLike(String prod);
	
	List<Item>findByPriceLessThanEqual(Integer maxPrice);	
	
	List<Item>findByNameLikeAndPriceLessThanEqual(String prod, Integer maxPrice );
	
	

}
