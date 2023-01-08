package stopdlaodlewni.app.product;

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

import stopdlaodlewni.dao.ProductDAO;
import stopdlaodlewni.entities.Product;

@Named
@RequestScoped
public class ProductListBB {
	private static final String PAGE_PRODUCT_EDIT = "productEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String productName;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	ProductDAO productDAO;
		
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Product> getFullList(){
		return productDAO.getFullList();
	}

	public List<Product> getList(){
		List<Product> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (productName != null && productName.length() > 0){
			searchParams.put("productName", productName);
		}
		
		//2. Get list
		list = productDAO.getList(searchParams);
		
		return list;
	}

	public String newProduct(){
		Product product = new Product();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("product", product);
		
		//2. Pass object through flash	
		flash.put("product", product);
		
		return PAGE_PRODUCT_EDIT;
	}

	public String editProduct(Product product){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("product", product);
		
		//2. Pass object through flash 
		flash.put("product", product);
		
		return PAGE_PRODUCT_EDIT;
	}

	public String deleteProduct(Product product){
		productDAO.remove(product);
		return PAGE_STAY_AT_THE_SAME;
	}
	
	
	public String deleteLast(Product product){
		
		
		
		
		
		productDAO.remove(product);
		return PAGE_STAY_AT_THE_SAME;
	}
}
