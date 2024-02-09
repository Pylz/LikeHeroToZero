package com.pylz.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.pylz.dao.GenericDao;

public class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T find(Long id) {
        // Findet und gibt ein Objekt der angegebenen Entity-Klasse anhand seiner ID zurück.
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        // Gibt eine Liste aller Objekte der angegebenen Entity-Klasse zurück.
        return entityManager.createQuery("SELECT e FROM " + entityClass.getName() + " e", entityClass).getResultList();
    }

    @Override
    public void save(T entity) {
        // Speichert ein Objekt der angegebenen Entity-Klasse in der Datenbank.
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        // Aktualisiert ein bereits in der Datenbank vorhandenes Objekt der angegebenen Entity-Klasse.
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        // Löscht ein Objekt der angegebenen Entity-Klasse aus der Datenbank.
        entityManager.remove(entity);
    }
}
