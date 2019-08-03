package com.tourofheroes.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hero {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	public Hero() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hero(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
