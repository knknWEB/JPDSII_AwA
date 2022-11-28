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

import stopdlaodlewni.dao.NewDAO;
import stopdlaodlewni.entities.New;

@Named
@RequestScoped
public class NewListBB {
	private static final String PAGE_NEWS_EDIT = "newEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String title;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	NewDAO newsDAO;
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<New> getFullList(){
		return newsDAO.getFullList();
	}

	public List<New> getList(){
		List<New> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (title != null && title.length() > 0){
			searchParams.put("title", title);
		}
		
		//2. Get list
		list = newsDAO.getList(searchParams);
		
		return list;
	}

	public String newNews(){
		New news = new New();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("news", news);
		
		//2. Pass object through flash	
		flash.put("news", news);
		
		return PAGE_NEWS_EDIT;
	}

	public String editNews(New news){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("news", news);
		
		//2. Pass object through flash 
		flash.put("news", news);
		
		return PAGE_NEWS_EDIT;
	}

	public String deleteNews(New news){
		newsDAO.remove(news);
		return PAGE_STAY_AT_THE_SAME;
	}
}
