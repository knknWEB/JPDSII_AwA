package stopdlaodlewni.app;

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
import javax.servlet.http.HttpSession;

import stopdlaodlewni.dao.InfoDAO;
import stopdlaodlewni.entities.Info;

@Named
@RequestScoped
public class InfoListBB {
	private static final String PAGE_NEWS_EDIT = "infoEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String title;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	InfoDAO infoDAO;
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Info> getFullList(){
		return infoDAO.getFullList();
	}

	public List<Info> getList(){
		List<Info> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (title != null && title.length() > 0){
			searchParams.put("title", title);
		}
		
		//2. Get list
		list = infoDAO.getList(searchParams);
		
		return list;
	}

	public String newInfo(){
		Info info = new Info();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("info", info);
		
		//2. Pass object through flash	
		flash.put("info", info);
		
		return PAGE_NEWS_EDIT;
	}

	public String editInfo(Info info){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("info", info);
		
		//2. Pass object through flash 
		flash.put("info", info);
		
		return PAGE_NEWS_EDIT;
	}

	public String deleteInfo(Info info){
		infoDAO.remove(info);
		return PAGE_STAY_AT_THE_SAME;
	}
}
