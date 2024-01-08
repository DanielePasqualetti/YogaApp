package com.example.YogaApp.entity;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "posizioni")
public class Posizione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String englishName;
	@Column(nullable = false)
	private String sanskritName;
	@Enumerated(EnumType.STRING)
	private EDifficulty difficulty;
	@Enumerated(EnumType.STRING)
	private EPracticeCategory practiceCategory;
	@Column(nullable = false)
	private String style;
	@ElementCollection
	@CollectionTable(name = "posizione_chakras", joinColumns = @JoinColumn(name = "posizione_id"))
	@Column(name = "chakra")
	private Set<String> chakras;
	@ElementCollection
    @CollectionTable(name = "posizione_elements", joinColumns = @JoinColumn(name = "posizione_id"))
    @Column(name = "element")
	private Set<String> elements;
	
}
