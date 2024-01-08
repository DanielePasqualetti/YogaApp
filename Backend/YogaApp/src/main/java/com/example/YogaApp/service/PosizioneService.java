package com.example.YogaApp.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.YogaApp.entity.EDifficulty;
import com.example.YogaApp.entity.EPracticeCategory;
import com.example.YogaApp.entity.Posizione;
import com.example.YogaApp.repository.PosizioneRepository;

@Service
public class PosizioneService {

	@Autowired PosizioneRepository posizioneRepo;
	
	@Autowired @Qualifier("posizioneBean") private ObjectProvider<Posizione> posizioneProvider;
	
	public Posizione createPosizione(
			String englishName,
			String sanskritName,
			EDifficulty difficulty,
			EPracticeCategory practiceCategory,
			String style,
			Set<String> chakras,
			Set<String> elements
			) {
		Posizione p = posizioneProvider.getObject().builder()
				.englishName(englishName)
				.sanskritName(sanskritName)
				.difficulty(difficulty)
				.practiceCategory(practiceCategory)
				.style(style)
				.chakras(chakras)
				.elements(elements)
				.build();
		posizioneRepo.save(p);
		System.out.println("Nuova posizione aggiunta al DB.");
		return p;
	}
	
	public List<Posizione> findAll() {
		List<Posizione> listaPosizioni = posizioneRepo.findAll();
		return listaPosizioni;
	}
	
	public Posizione findById(Long id) {
		return posizioneRepo.findById(id).get();
	}
	
	public Posizione findByEnglishName(String englishName) {
		return posizioneRepo.findByEnglishName(englishName);
	}
	
	public Posizione findBySanskritName(String sanskritName) {
		return posizioneRepo.findBySanskritName(sanskritName);
	}
	
	public List<Posizione> findByDifficulty(EDifficulty difficulty) {
		List<Posizione> listaPosizioni = posizioneRepo.findByDifficulty(difficulty);
		return listaPosizioni;
	}
	
	public List<Posizione> findByPracticeCategory(EPracticeCategory practiceCategory) {
		List<Posizione> listaPosizioni = posizioneRepo.findByPracticeCategory(practiceCategory);
		return listaPosizioni;
	}
	
	public List<Posizione> findByStyle(String style) {
		List<Posizione> listaPosizioni = posizioneRepo.findByStyle(style);
		return listaPosizioni;
	}
	
	public List<Posizione> findByChakras(String chakras) {
		List<Posizione> listaPosizioni = posizioneRepo.findByChakras(chakras);
		return listaPosizioni;
	}
	
	public List<Posizione> findByElements(String elements) {
		List<Posizione> listaPosizioni = posizioneRepo.findByElements(elements);
		return listaPosizioni;
	}
	
	public void deletePosizione(Long id) {
		posizioneRepo.deleteById(id);
	}
	
	public Posizione editPosizione(Posizione posizione) {
		return posizioneRepo.save(posizione);
	}
}
