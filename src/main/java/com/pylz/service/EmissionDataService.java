package com.pylz.service;

import com.pylz.entities.EmissionData;
import java.util.List;

public interface EmissionDataService {
	EmissionData findById(Long id); // Methode zur Suche von Emissionsdaten anhand der ID

	List<EmissionData> findAllEmissions(); // Methode zur Suche aller Emissionsdaten

	void saveEmission(EmissionData emissionData); // Methode zum Speichern von Emissionsdaten

	void deleteAllEmissions(); // Neue Methode zum Löschen aller Emissionsdaten

	List<String> findDistinctCountries(); // Methode zum Suchen verschiedener Länder

	boolean existsByYearAndEmissions(int year, double emissions); // Methode zur Überprüfung der Existenz von
																	// Emissionsdaten für ein Jahr und Emissionen
}
