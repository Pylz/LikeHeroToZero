package com.pylz.service;

import com.pylz.entities.User;
import java.util.List;

public interface UserService {
	User findById(Long id); // Methode zur Suche eines Benutzers anhand der ID

	List<User> findAllUsers(); // Methode zur Suche aller Benutzer

	void saveUser(User user); // Methode zum Speichern eines Benutzers

	User findByUsername(String username); // Methode zur Suche eines Benutzers anhand des Benutzernamens
}
