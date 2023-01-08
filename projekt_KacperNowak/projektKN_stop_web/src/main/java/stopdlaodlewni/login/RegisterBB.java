package stopdlaodlewni.login;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import stopdlaodlewni.dao.UserDAO;
import stopdlaodlewni.entities.User;

@Named
@RequestScoped
public class RegisterBB {
	private static final String PAGE_LOGIN = "/public/login?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private String name;
	private String surname;
	private String mail;
	private String login;
	private String password;
	private String password2;

	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	private User user = new User();

	public User getUser() {
		return user;
	}

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;

	@EJB
	UserDAO userDAO;

	@Inject
	FacesContext ctx;

	public String register() {
		if (userDAO.getUserByLogin(this.login)==null) {
			try {
				user.setPassword(this.password);
				user.setLogin(this.login);
				user.setMail(this.mail);
				user.setName(this.name);
				user.setSurname(this.surname);
				user.setRole("user");

				userDAO.create(user);
				
				
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pomyślnie dodano użytkownika. Teraz zaloguj się!", null));
				
				return PAGE_STAY_AT_THE_SAME;
				
			} 
			
			catch (Exception e) {
				e.printStackTrace();
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Błąd przy rejestracji konta!", null));
				return PAGE_STAY_AT_THE_SAME;
			}

		}
		else {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Użytkownik o podanym loginie już istnieje!", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}



	
}