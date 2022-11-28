package stopdlaodlewni.app.administration;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import stopdlaodlewni.dao.InfoDAO;
import stopdlaodlewni.entities.Info;

@Named
@ViewScoped
public class InfoEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_INFO_LIST = "infoList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Info info = new Info();
	private Info loaded = null;

	@EJB
	InfoDAO infoDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Info getInfo() {
		return info;
	}

	public void onLoad() throws IOException {
		// 1. load info passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Info) session.getAttribute("info");

		// 2. load info passed through flash
		loaded = (Info) flash.get("info");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			info = loaded;
			// session.removeAttribute("info");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no Info object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (info == null) {
				// new record
				infoDAO.create(info);
			} else {
				// existing record
				infoDAO.merge(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_INFO_LIST;
	}
}
