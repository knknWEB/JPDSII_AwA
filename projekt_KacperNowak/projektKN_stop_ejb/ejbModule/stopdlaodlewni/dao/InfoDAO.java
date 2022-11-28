package stopdlaodlewni.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import stopdlaodlewni.entities.Info;

//DAO - Data Access Object for Info entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class InfoDAO{

	// Dependency injection (no setter method is needed)
	@PersistenceContext//(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Info info) {
		em.persist(info);
	}

	public Info merge(Info info) {
		return em.merge(info);
	}

	public void remove(Info info) {
		em.remove(em.merge(info));
	}

	public Info find(Object id) {
		return em.find(Info.class, id);
	}

	public List<Info> getFullList() {
		List<Info> list = null;

		Query query = em.createQuery("select p from Info p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Info> getList(Map<String, Object> searchParams) {
		List<Info> list = null;

		// 1. Build query string with parameters
		String select = "select i ";
		String from = "from Info i ";
		String where = "";
		String orderby = "order by i.title asc";

		// search for surname
		String title = (String) searchParams.get("title");
		if (title != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "i.title like :title ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (title != null) {
			query.setParameter("title", title+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Info objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
