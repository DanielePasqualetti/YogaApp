package com.example.YogaApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.YogaApp.entity.EDifficulty;
import com.example.YogaApp.entity.EPracticeCategory;
import com.example.YogaApp.entity.Posizione;

public interface PosizioneRepository extends JpaRepository<Posizione, Long>{
	
	Posizione findByEnglishName(String englishName);
	
	Posizione findBySanskritName(String sanskritName);
	
	List<Posizione> findByDifficulty(EDifficulty difficulty);

	List<Posizione> findByPracticeCategory(EPracticeCategory practiceCategory);

	List<Posizione> findByStyle(String style);
	
	List<Posizione> findByChakras(String chakras);
	
	List<Posizione> findByElements(String elements);
}
