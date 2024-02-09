package com.pylz.managedbean;

import com.pylz.entities.User;
import com.pylz.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private UserService userService;

	// Standard-Konstruktor ohne Argumente
	public LoginBean() {
		// Bleibt leer
	}

	@Inject
	public LoginBean(UserService userService) {
		this.userService = userService;
	}

	// Getter und Setter
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		String loginFailed = "loginFailed?faces-redirect=true";

		if (userService == null) {
			// Behandlung des Falls, wenn userService nicht injiziert wurde
			// Dies kann das Protokollieren eines Fehlers oder Weiterleiten auf eine Fehlerseite sein
			return loginFailed;
		}

		List<User> users = userService.findAllUsers();

		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				// Überprüfen der Benutzerrolle
				String role = user.getRole(); // Unter der Annahme, dass die Methode getRole() in der User-Klasse existiert

				if ("SCIENTIST".equals(role)) {
					// Weiterleitung zum Dashboard für Wissenschaftler
					return "dashboardScientist?faces-redirect=true";
				} else if ("ADMIN".equals(role)) {
					// Weiterleitung zum Dashboard für Administratoren
					return "dashboardAdmin?faces-redirect=true";
				} else {
					// Behandlung anderer Rollen oder Szenarien, falls erforderlich
					// Zum Beispiel Weiterleitung zu einer anderen Seite oder Anzeigen einer Fehlermeldung
					return loginFailed;
				}
			}
		}
		// Anmeldung fehlgeschlagen (kein passender Benutzer gefunden)
		return loginFailed;
	}

}
