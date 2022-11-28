package stopdlaodlewni.app;

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

import stopdlaodlewni.dao.NewDAO;
import stopdlaodlewni.entities.New;

@Named
@ViewScoped
public class NewEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_NEWS_LIST = "newList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private New news = new New();
	private New loaded = null;

	@EJB
	NewDAO newsDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public New getNews() {
		return news;
	}

	public void onLoad() throws IOException {
		// 1. load news passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (New) session.getAttribute("news");

		// 2. load news passed through flash
		loaded = (New) flash.get("news");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			news = loaded;
			// session.removeAttribute("news");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no New object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (news.getIdNews() == null) {
				// new record
				newsDAO.create(news);
			} else {
				// existing record
				newsDAO.merge(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_NEWS_LIST;
	}
}
