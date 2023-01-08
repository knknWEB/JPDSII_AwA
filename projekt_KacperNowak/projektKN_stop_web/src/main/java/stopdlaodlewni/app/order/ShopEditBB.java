package stopdlaodlewni.app.order;

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

import org.primefaces.event.RowEditEvent;

import stopdlaodlewni.dao.ShopDAO;
import stopdlaodlewni.entities.Product;
import stopdlaodlewni.entities.Shop;
import stopdlaodlewni.entities.User;

@Named
@ViewScoped
public class ShopEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_INFO_LIST = "orderList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Shop shop = new Shop();
	private Shop loaded = null;

	@EJB
	ShopDAO shopDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;
	private double price;

	private Product productId;
	

	private User usersId;

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	


	public Shop getShop() {
		return shop;
	}

	public void onLoad() throws IOException {
		
		loaded = (Shop) flash.get("shop");
		
		if (loaded != null) {
			shop = loaded;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}

	public String saveData() {
		if (loaded == null) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Dokonano zmian@!", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (shop == null) {
				
				shopDAO.create(shop);
				
			} else {
				shopDAO.merge(shop);
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Edycja zakończona sukcesem!", null));
				return PAGE_INFO_LIST;
				
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