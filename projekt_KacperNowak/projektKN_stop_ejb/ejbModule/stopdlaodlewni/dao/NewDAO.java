package stopdlaodlewni.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import stopdlaodlewni.entities.New;

//DAO - Data Access Object for New entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class NewDAO{

	// Dependency injection (no setter method is needed)
	@PersistenceContext//(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(New news) {
		em.persist(news);
	}

	public New merge(New news) {
		return em.merge(news);
	}

	public void remove(New news) {
		em.remove(em.merge(news));
	}

	public New find(Object id) {
		return em.find(New.class, id);
	}

	public List<New> getFullList() {
		List<New> list = null;

		Query query = em.createQuery("select p from New p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<New> getList(Map<String, Object> searchParams) {
		List<New> list = null;

		// 1. Build query string with parameters
		String select = "select n ";
		String from = "from New n ";
		String where = "";
		String orderby = "order by n.title asc";

		// search for surname
		String title = (String) searchParams.get("title");
		if (title != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "n.title like :title ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (title != null) {
			query.setParameter("title", title+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of New objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
