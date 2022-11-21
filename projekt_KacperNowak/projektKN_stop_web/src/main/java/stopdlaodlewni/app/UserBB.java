package stopdlaodlewni.app;

import javax.inject.Inject;
import javax.inject.Named;

import stopdlaodlewni.dao.UserDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@Named
@RequestScoped
//@SessionScoped
public class UserBB {
	

	private String login;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
		
	public String getSurname() {
		return login;
	}

	public void setSurname(String login) {
		this.login = login;
	}


	@Inject
	FacesContext ctx;

	
	public String info() {
		return "info";
	}
}
