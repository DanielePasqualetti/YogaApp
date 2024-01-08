package com.example.YogaApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.YogaApp.entity.Posizione;
import com.example.YogaApp.repository.PosizioneRepository;

@Configuration
public class AppConfig {
	
	@Autowired PosizioneRepository posizioneRepo;
	
	@Bean("posizioneBean")
	@Scope("prototype")
	public Posizione posizione() {
		return new Posizione();
	}

}
