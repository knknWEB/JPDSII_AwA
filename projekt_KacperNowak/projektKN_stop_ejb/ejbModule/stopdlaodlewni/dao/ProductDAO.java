package stopdlaodlewni.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import stopdlaodlewni.entities.Product;

//DAO - Data Access Object for Product entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class ProductDAO{

	// Dependency injection (no setter method is needed)
	@PersistenceContext//(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Product product) {
		em.persist(product);
	}

	public Product merge(Product product) {
		return em.merge(product);
	}

	public void remove(Product product) {
		em.remove(em.merge(product));
	}

	public Product find(Object id) {
		return em.find(Product.class, id);
	}

	public List<Product> getFullList() {
		List<Product> list = null;

		Query query = em.createQuery("select p from Product p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Product> getList(Map<String, Object> searchParams) {
		List<Product> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Product p ";
		String where = "";
		String orderby = "order by p.productName asc";

		// search for surname
		String productName = (String) searchParams.get("productName");
		if (productName != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.productName like :productName ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);

		if (productName != null) {
			query.setParameter("productName", productName+"%");
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
