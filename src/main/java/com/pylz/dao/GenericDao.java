package com.pylz.dao;

import java.util.List;

public interface GenericDao<T> {
	T find(Long id);

	List<T> findAll();

	void save(T entity);

	void update(T entity);

	void delete(T entity);
}
