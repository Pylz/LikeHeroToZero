package com.pylz.dao;

import com.pylz.entities.EmissionData;
import java.util.List;

public interface EmissionDataDao extends GenericDao<EmissionData> {
    List<String> findDistinctCountries();
    void deleteAll();
    boolean existsByYearAndEmissions(int year, double emissions);
}
