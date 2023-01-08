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
	private Query query;

	Info queryFilter = new Info();
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
	public Info getQueryFilter() {
		return queryFilter;
	}


	public List<Info> getFullList() {
		List<Info> list = null;

		Query query = em.createQuery("select i from Info i ORDER BY i.date DESC");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
		

}
