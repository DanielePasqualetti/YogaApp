package com.example.YogaApp.controller;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.YogaApp.entity.EDifficulty;
import com.example.YogaApp.entity.EPracticeCategory;
import com.example.YogaApp.entity.Posizione;
import com.example.YogaApp.repository.PosizioneRepository;
import com.example.YogaApp.service.PosizioneService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class AppController {
	
	@Autowired
	PosizioneService pSvc;
	
	@Autowired
	PosizioneRepository posizioneRepo;
	
	@Autowired @Qualifier("posizioneBean")
	private ObjectProvider<Posizione> posizioneProvider;
	
	
	//
	// GET
	//
	@GetMapping("/posizioni/get")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Posizione>> findAll() {
		List<Posizione> posizioni = pSvc.findAll();
		return ResponseEntity.ok(posizioni);
	}
	
	@GetMapping("/posizioni/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Posizione> findById(@PathVariable Long id) {
		Posizione posizione = pSvc.findById(id);
		
		if (posizione != null) {
			return new ResponseEntity<>(posizione, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/posizioni/english-name/{englishName}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Posizione> findByEnglishName(@PathVariable String englishName) {
		Posizione posizione = pSvc.findByEnglishName(englishName);
		
		if (posizione != null) {
			return new ResponseEntity<>(posizione, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/posizioni/sanskrit-name/{sanskritName}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Posizione> findBySanskritName(@PathVariable String sanskritName) {
		Posizione posizione = pSvc.findBySanskritName(sanskritName);
		
		if (posizione != null) {
			return new ResponseEntity<>(posizione, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/posizioni/difficulty/{difficulty}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Posizione>> findByDifficulty(@PathVariable EDifficulty difficulty) {
	    List<Posizione> posizioni = pSvc.findByDifficulty(difficulty);
	    return ResponseEntity.ok(posizioni);
	}

	@GetMapping("/posizioni/practice-category/{practiceCategory}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Posizione>> findByPracticeCategory(@PathVariable EPracticeCategory practiceCategory) {
	    List<Posizione> posizioni = pSvc.findByPracticeCategory(practiceCategory);
	    return ResponseEntity.ok(posizioni);
	}

	@GetMapping("/posizioni/style/{style}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Posizione>> findByStyle(@PathVariable String style) {
	    List<Posizione> posizioni = pSvc.findByStyle(style);
	    return ResponseEntity.ok(posizioni);
	}
	
	@GetMapping("/posizioni/chakras/{chakras}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Posizione>> findByChakras(@PathVariable String chakras) {
		List<Posizione> posizioni = pSvc.findByChakras(chakras);
	    return ResponseEntity.ok(posizioni);
	}
	
	@GetMapping("/posizioni/elements/{elements}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Posizione>> findByElements(@PathVariable String elements) {
	    List<Posizione> posizioni = pSvc.findByElements(elements);
	    return ResponseEntity.ok(posizioni);
	}
	
	//
	//POST
	//
	@PostMapping("/posizioni/new")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Posizione> createPosizione(@RequestBody Posizione p) {
		Posizione posizione = pSvc.createPosizione(
				p.getEnglishName(),
				p.getSanskritName(),
				p.getDifficulty(),
				p.getPracticeCategory(),
				p.getStyle(),
				p.getChakras(),
				p.getElements());
		return new ResponseEntity<> (posizione, HttpStatus.CREATED);
	}
	
	//
	//DELETE
	//
	@DeleteMapping("/posizioni/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> deletePosizione(@PathVariable Long id) {
		Posizione posizione = pSvc.findById(id);
		
		if (posizione != null) {
			pSvc.deletePosizione(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//
	//PUT
	//
	@PutMapping("/posizioni/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Posizione> editPosizione(
			@PathVariable Long id,
			@RequestBody Posizione posizioneModificata) {
		Posizione posizioneEsistente = pSvc.findById(id);
		
		if (posizioneEsistente != null) {
			posizioneEsistente.setEnglishName(posizioneModificata.getEnglishName());
			posizioneEsistente.setSanskritName(posizioneModificata.getSanskritName());
			posizioneEsistente.setDifficulty(posizioneModificata.getDifficulty());
			posizioneEsistente.setPracticeCategory(posizioneModificata.getPracticeCategory());
			posizioneEsistente.setStyle(posizioneModificata.getStyle());
			posizioneEsistente.setChakras(posizioneModificata.getChakras());
			posizioneEsistente.setElements(posizioneModificata.getElements());
			
			Posizione posizioneAggiornata = pSvc.editPosizione(posizioneEsistente);
			
			return new ResponseEntity<>(posizioneAggiornata, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
