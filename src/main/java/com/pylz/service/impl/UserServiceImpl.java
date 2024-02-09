package com.pylz.service.impl;

import com.pylz.dao.UserDao;
import com.pylz.entities.User;
import com.pylz.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	private UserDao userDao;

	protected UserServiceImpl() {
		// Für die Proxy-Erstellung durch CDI
	}

	@Inject
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findById(Long id) {
		try {
			return userDao.find(id);
		} catch (Exception e) {
			logger.error("Fehler beim Suchen des Benutzers mit der ID: {}", id, e);
			return null;
		}
	}

	@Override
	public List<User> findAllUsers() {
		try {
			return userDao.findAll();
		} catch (Exception e) {
			logger.error("Fehler beim Suchen aller Benutzer", e);
			return Collections.emptyList();
		}
	}

	@Override
	public void saveUser(User user) {
		try {
			userDao.save(user);
		} catch (Exception e) {
			logger.error("Fehler beim Speichern des Benutzers: {}", user, e);
		}
	}

	@Override
	public User findByUsername(String username) {
		try {
			return userDao.findByUsername(username);
		} catch (Exception e) {
			logger.error("Fehler beim Suchen des Benutzers anhand des Benutzernamens: {}", username, e);
			return null;
		}
	}
}
