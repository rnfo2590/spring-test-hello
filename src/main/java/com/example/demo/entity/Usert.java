package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Users")
public class Usert {
	@Id
	private Integer id;
	
	private String name;
	private Integer age;


}

