package com.pylz.dao.impl;

import com.pylz.dao.UserDao;
import com.pylz.entities.User;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    // Standard-Konstruktor ohne Argumente für CDI
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        try {
            // Sucht nach einem Benutzer anhand seines Benutzernamens und gibt das entsprechende Benutzerobjekt zurück.
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                                .setParameter("username", username)
                                .getSingleResult();
        } catch (NoResultException e) {
            // Kein Benutzer mit diesem Benutzernamen gefunden.
            logger.info("Kein Benutzer gefunden mit Benutzernamen: {}", username);
            return null;
        } catch (Exception e) {
            // Fehler beim Suchen des Benutzers nach Benutzernamen.
            logger.error("Fehler beim Suchen des Benutzers nach Benutzernamen: {}", username, e);
            return null;
        }
    }
}
