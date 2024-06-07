package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="items")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(name="category_id")
	private Integer categoryId; 
	
	private String name;
	private Integer price;
	
	public Item() {
		
	}
	
	public Item(String name, Integer price) {
		this.name = name;
		this.price = price;
	}
	
	public Item(Integer categoryId, String name, Integer price) {
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
	}
	
	public Item(Integer id, Integer categoryId, String name, Integer price) {
		this.id = id;
		this.categoryId=categoryId;
		this.name = name;
		this.price = price;
	}



	
	
	

}
