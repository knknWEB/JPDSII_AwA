package stopdlaodlewni.app.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.servlet.http.HttpSession;

import stopdlaodlewni.dao.ShopDAO;
import stopdlaodlewni.entities.Shop;
import stopdlaodlewni.entities.User;
import stopdlaodlewni.dao.ProductDAO;
import stopdlaodlewni.entities.Product;
import stopdlaodlewni.dao.UserDAO;
import stopdlaodlewni.entities.User;

@Named
@RequestScoped
public class ShopListBB {
	private static final String PAGE_NEWS_EDIT = "orderEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	ShopDAO shopDAO;
	
	@EJB
	ProductDAO productDAO;
	
	@EJB
	UserDAO userDAO;
	
	
	@Inject
	FacesContext ctx;
		
	FacesContext fCtx=FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(true);
	RemoteClient<User> r = RemoteClient.load(session);


	public List<Shop> getFullList(){
		
		return shopDAO.getFullList();
	}
	
	public List<Shop> getList(){
		User user = new User();
		user.setUserId(r.getDetails().getUserId());
		
		return shopDAO.getResultByLogin(user);
	}

	

	public String newShop(){
		Shop shop = new Shop();
	
		flash.put("shop", shop);
		
		return PAGE_NEWS_EDIT;
	}

	public String editShop(Shop shop){
		
		flash.put("shop", shop);
		
		return PAGE_NEWS_EDIT;
	}
	
	public String createShop(Product product){
		if(r!=null){
			User user = new User();
			user=userDAO.getUserByLogin(r.getDetails().getLogin());
			if((user.getCity()==null)||(user.getStreet().isEmpty())||(user.getPostcode().isEmpty())||(user.getNofHouse()==0)) {
				ctx.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aby złożyć zamówienie, musisz uzupełnić pełny adres w panelu użytkownika!", null));
				return PAGE_STAY_AT_THE_SAME;
			}
			else {
				
			
			Shop shop = new Shop();
			Double price = product.getPrice();
			String name = product.getProductName().toLowerCase();

				if(shopDAO.getResultByUser(user).isEmpty()) {
					try { 
						shop.setShopDate(new java.util.Date()); 
						shop.setPrice(price);
						shop.setProduct(product); 
						shop.setShopStatus("Nowe"); 
						shop.setUser(user);
					  
						shopDAO.create(shop);
					  
						ctx.addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Zakupiono "+name+" za "+price+" złotych!", null));
					  
					  return PAGE_STAY_AT_THE_SAME;
					  
					  }
					  
					  catch (Exception e) { e.printStackTrace();
					  
					  return PAGE_STAY_AT_THE_SAME;
					  }		
					}
				else {
					ctx.addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "W tym dniu złożono już zamówienie!!!", null));
				}
			return PAGE_STAY_AT_THE_SAME;
		}
		}
		else {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Zaloguj się aby złożyć zamówienie!", null));
			return PAGE_STAY_AT_THE_SAME;

		}
	
	}

	public String deleteShop(Shop shop){
		shopDAO.remove(shop);
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usunięto rekord, zamówienie nr "+shop.getShopId(), null));
		return PAGE_STAY_AT_THE_SAME;
	}
	

}
