package com.tourofheroes.server.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourofheroes.server.model.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
	
	public List<Hero> findByNameIgnoreCaseContaining(String name);

}
