package stopdlaodlewni.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import stopdlaodlewni.entities.Shop;
import stopdlaodlewni.entities.User;

//DAO - Data Access Object for Order entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class ShopDAO{

	// Dependency injection (no setter method is needed)
	@PersistenceContext//(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Shop shop) {		
		em.persist(shop);
	}

	public Shop merge(Shop shop) {
		
		return em.merge(shop);
	}

	public void remove(Shop shop) {
		em.remove(em.merge(shop));
	}

	public Shop find(Object id) {
		return em.find(Shop.class, id);
	}

	public List<Shop> getFullList() {
		List<Shop> list = null;

		Query query = em.createQuery("SELECT s FROM Shop s ORDER BY s.shopDate DESC");
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Shop> getResultByUser(User user) {
		List<Shop> list = null;
		Query query = em.createQuery("SELECT s FROM Shop s WHERE s.shopDate=:shopDate AND user=:u");
		query.setParameter("shopDate", new java.util.Date());
		query.setParameter("u", user);

		try {
			list = query.getResultList();		
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return list;
	}
	
	public List<Shop> getResultByLogin(User user) {
		List<Shop> list = null;

		Query query = em.createQuery("SELECT s FROM Shop s WHERE user=:u");
		//SELECT s FROM Shop s JOIN User u WHERE u.userId = :usersId AND s.shopDate=:shopDate
		query.setParameter("u", user);
		//query.setParameter("usersId", userId);
		//query.setParameter("shopDate", new java.util.Date());

		try {
			list = query.getResultList();			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return list;
	}
	
	

}
