package com.pylz.dao.impl;

import com.pylz.dao.EmissionDataDao;
import com.pylz.entities.EmissionData;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EmissionDataDaoImpl extends GenericDaoImpl<EmissionData> implements EmissionDataDao {

    @PersistenceContext
    private EntityManager em;

    // Standard-Konstruktor ohne Argumente für CDI
    public EmissionDataDaoImpl() {
        super(EmissionData.class);
    }

    @Override
    public List<String> findDistinctCountries() {
        // Diese Methode gibt eine Liste von eindeutigen Ländernamen aus der EmissionData-Entität zurück.
        return em.createQuery("SELECT DISTINCT e.countryName FROM EmissionData e", String.class)
                 .getResultList();
    }

    @Override
    public void deleteAll() {
        // Löscht alle Datensätze aus der EmissionData-Entität.
        em.createQuery("DELETE FROM EmissionData").executeUpdate();
    }
    
    @Override
    public boolean existsByYearAndEmissions(int year, double emissions) {
        // Überprüft, ob ein Datensatz mit dem angegebenen Jahr und den angegebenen Emissionen in der Datenbank existiert.
        String queryStr = "SELECT COUNT(e) FROM EmissionData e WHERE e.date = :year AND e.amountValue = :emissions";
        Long count = em.createQuery(queryStr, Long.class)
                       .setParameter("year", year)
                       .setParameter("emissions", emissions)
                       .getSingleResult();
        return count > 0;
    }
}
