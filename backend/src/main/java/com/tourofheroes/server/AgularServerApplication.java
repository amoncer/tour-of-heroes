package com.tourofheroes.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tourofheroes.server.dao.HeroRepository;
import com.tourofheroes.server.model.Hero;

@SpringBootApplication
public class AgularServerApplication {
	
	@Autowired
	private HeroRepository heroRepository; 

	public static void main(String[] args) {
		SpringApplication.run(AgularServerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner createCLRunner() {
		return (args) -> {
			List<Hero> heroes = new ArrayList<>();
			heroes.add(new Hero("Dr Nice"));
			heroes.add(new Hero("Narco"));
			heroes.add(new Hero("Bombasto"));
			heroes.add(new Hero("Celeritas"));
			heroes.add(new Hero("Magneta"));
			heroes.add(new Hero("RubberMan"));
			heroes.add(new Hero("Dynama"));
			heroes.add(new Hero("Dr IQ"));
			heroes.add(new Hero("Magma"));
			heroes.add(new Hero("Tornado"));
			heroes.forEach(h -> heroRepository.save(h));
		};
	}

}
