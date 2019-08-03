package com.tourofheroes.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tourofheroes.server.dao.HeroRepository;
import com.tourofheroes.server.exception.HeroNotFoundException;
import com.tourofheroes.server.model.Hero;

@RestController
public class HeroController implements ControllerInterface {
	
	HeroRepository heroRepository;
	
	public HeroController(HeroRepository heroRepository) {
		this.heroRepository = heroRepository;
	}
	
	@GetMapping(value = "/heroes")
	public List<Hero> getAllHeroes(){
		return heroRepository.findAll();
	}
	
	@GetMapping(value = "/heroes/{id}")
	public Hero getHero(@PathVariable Long id) throws HeroNotFoundException {
		return heroRepository.findById(id).orElseThrow(() -> new HeroNotFoundException("Hero avec id : " + id + " n'existe pas"));
	}
	
	
	@PostMapping(value = "/heroes")
	public Hero createHero(@RequestBody Hero hero) {
		return heroRepository.save(hero);
	}
	
	@PutMapping(value = "/heroes/{id}")
	public void updateHero(@PathVariable Long id, @RequestBody Hero hero) throws HeroNotFoundException {
		Hero savedHero = heroRepository.findById(id).orElseThrow(() -> new HeroNotFoundException("Hero avec id : " + id + " n'existe pas"));
		savedHero.setName(hero.getName());
		heroRepository.save(savedHero);
	}
	
	@DeleteMapping(value = "/heroes/{id}")
	public void deleteHero(@PathVariable Long id) {
		heroRepository.deleteById(id);
	}
	
	@GetMapping(value = "/heroes/search")
	public List<Hero> searchHeroes(@PathParam(value = "name") String name){
		String[] terms = name.split(" ");
		List<Hero> result = new ArrayList<Hero>();
		Stream.of(terms).forEach(term -> result.addAll(heroRepository.findByNameIgnoreCaseContaining(term)));
		return result;
	}

}