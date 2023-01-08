package stopdlaodlewni.app.administration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.servlet.http.HttpSession;

import stopdlaodlewni.dao.UserDAO;
import stopdlaodlewni.entities.User;

@Named
@RequestScoped
public class UserListBB {
	private static final String PAGE_NEWS_EDIT = "userEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
	
	FacesContext fCtx = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
	RemoteClient<User> r = RemoteClient.load(session);
	
		
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<User> getLogged()
	{
		
		List<User> list = null;
		
		list = userDAO.getList(r.getDetails().getLogin());
		
		return list;
	}


	
	public List<User> getFullList(){
		return 	userDAO.getFullList();
	}


	public String newUser(){
		User user = new User();

		flash.put("user", user);
		
		return PAGE_NEWS_EDIT;
	}

	public String editUser(User user){

		flash.put("user", user);
		
		return PAGE_NEWS_EDIT;
	}

	public String deleteUser(User user){
		userDAO.remove(user);
		return PAGE_STAY_AT_THE_SAME;
	}
	
	
}
