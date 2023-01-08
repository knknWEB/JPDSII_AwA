package stopdlaodlewni.app.administration;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import stopdlaodlewni.dao.UserDAO;
import stopdlaodlewni.entities.User;

@Named
@ViewScoped
public class UserEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_INFO_LIST = "userList?faces-redirect=true";
	private static final String PAGE_USER_LIST = "panel?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private User user = new User();
	private User loaded = null;

	@EJB
	UserDAO userDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;
	
	FacesContext fCtx=FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
	RemoteClient<User> r = RemoteClient.load(session);


	public User getUser() {
		return user;
	}

	public void onLoad() throws IOException {
		loaded = (User) flash.get("user");

		if (loaded != null) {
			user = loaded;
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			
		}

	}

	public String saveData() {
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (user == null) {
				userDAO.create(user);
				
			} else {
				userDAO.merge(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		if(r.getDetails().getRole().equals("admin")) {
			return PAGE_INFO_LIST;
		}
		else {
			return PAGE_USER_LIST;
		}
	}
}
