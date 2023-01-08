package stopdlaodlewni.app.news;

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
		loaded = (Info) flash.get("info");

		if (loaded != null) {
			info = loaded;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));

		}

	}

	public String saveData() {
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (info == null) {
				infoDAO.create(info);
			} else {
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
