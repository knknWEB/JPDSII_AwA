package stopdlaodlewni.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import stopdlaodlewni.entities.User;

@Stateless
public class UserDAO {
	// simulate finding user in DB
	@PersistenceContext

	protected EntityManager em;

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}
	
	
	
	public List<User> getList(String Login) {
		List<User> list = null;
		
		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "order by u.login asc";
		
		if (Login != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.login like :Login ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);

		if (Login != null) {
			query.setParameter("Login", Login+"%");
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("select u from User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	

	public User getUserFromDatabase(String login, String pass) {
		User u = null;

		Query query = em.createQuery("select u FROM User u where u.login=:login");
		query.setParameter("login", login);

		try {
			User user = (User) query.getSingleResult();

			if (login.equals(user.getLogin()) && pass.equals(user.getPassword())) {
				u = new User();
				u.setUserId(user.getUserId());
				u.setLogin(login);
				u.setPassword(pass);
				u.setRole(user.getRole());
				u.setName(user.getName());
				u.setSurname(user.getSurname());
				u.setMail(user.getMail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;
	}
	

	// simulate retrieving roles of a User from DB
	public List<String> getUserRolesFromDatabase(User user) {

		ArrayList<String> roles = new ArrayList<String>();

		 if (user.getRole().equals("user")) { 
			 roles.add("user"); 
			 }
		 else if(user.getRole().equals("admin")) { 
			 roles.add("admin");
		  }	
		return roles;
	}
	
	public User getUserByLogin(String login) {
		User user = null;

		Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login");
		query.setParameter("login", login);

		try {
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return user;
	}
	
	
	
}
