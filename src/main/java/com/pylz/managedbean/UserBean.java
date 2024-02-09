package com.pylz.managedbean;

import com.pylz.entities.User;
import com.pylz.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserBean {

    private UserService userService;
    private User user;
    private List<User> users;

    // Standard-Konstruktor ohne Argumente
    public UserBean() {
        this.user = new User(); // Erstellt ein leeres Benutzerobjekt
    }

    @Inject
    public UserBean(UserService userService) {
        this(); // Ruft den Standard-Konstruktor ohne Argumente auf
        this.userService = userService;
    }

    // Methode zum Speichern eines Benutzers
    public String saveUser() {
        // Setzt die Rolle auf "SCIENTIST" vor dem Speichern
        user.setRole("SCIENTIST");
        userService.saveUser(user);
        user = new User(); // Setzt das Benutzerobjekt nach dem Speichern zurück
        return "successPage?faces-redirect=true"; // Navigiert zur successPage.xhtml
    }

    // Methode zum Laden aller Benutzer
    public List<User> getAllUsers() {
        if (users == null) {
            users = userService.findAllUsers();
        }
        return users;
    }

    // Getter und Setter für Benutzer und Benutzerliste
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
