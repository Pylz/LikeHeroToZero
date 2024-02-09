package com.pylz.service.impl;

import com.pylz.dao.EmissionDataDao;
import com.pylz.entities.EmissionData;
import com.pylz.service.EmissionDataService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
@Transactional
public class EmissionDataServiceImpl implements EmissionDataService {

    private static final Logger logger = LogManager.getLogger(EmissionDataServiceImpl.class);
    private EmissionDataDao emissionDataDao;

    protected EmissionDataServiceImpl() {
        // Für die Proxy-Erstellung durch CDI
    }

    @Inject
    public EmissionDataServiceImpl(EmissionDataDao emissionDataDao) {
        this.emissionDataDao = emissionDataDao;
    }

    @Override
    public EmissionData findById(Long id) {
        try {
            return emissionDataDao.find(id);
        } catch (Exception e) {
            logger.error("Fehler beim Suchen von Emissionsdaten mit der ID: {}", id, e);
            return null;
        }
    }

    @Override
    public List<EmissionData> findAllEmissions() {
        try {
            return emissionDataDao.findAll();
        } catch (Exception e) {
            logger.error("Fehler beim Suchen aller Emissionsdaten", e);
            return Collections.emptyList();
        }
    }

    @Override
    public void saveEmission(EmissionData emissionData) {
        try {
            emissionDataDao.save(emissionData);
        } catch (Exception e) {
            logger.error("Fehler beim Speichern von Emissionsdaten: {}", emissionData, e);
        }
    }
    
    @Override
    public List<String> findDistinctCountries() {
        try {
            return emissionDataDao.findDistinctCountries();
        } catch (Exception e) {
            logger.error("Fehler beim Suchen von verschiedenen Ländern", e);
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteAllEmissions() {
        try {
            emissionDataDao.deleteAll(); // Implementieren der Löschfunktion
        } catch (Exception e) {
            logger.error("Fehler beim Löschen aller Emissionsdaten", e);
        }
    }
    
    @Override
    public boolean existsByYearAndEmissions(int year, double emissions) {
        try {
            return emissionDataDao.existsByYearAndEmissions(year, emissions);
        } catch (Exception e) {
            logger.error("Fehler beim Überprüfen der Existenz von Emissionsdaten für das Jahr: {} und die Emissionen: {}", year, emissions, e);
            return false; // Im Falle einer Ausnahme annehmen, dass sie nicht existiert
        }
    }
}
