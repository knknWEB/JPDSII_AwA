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
	@PersistenceContext//(unitName = UNIT_NAME)

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

	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("select p from User p");

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
				u.setLogin(login);
				u.setPassword(pass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;
	}

	// simulate retrieving roles of a User from DB
	public List<String> getUserRolesFromDatabase(User user) {
	
		
		
		ArrayList<String> roles = new ArrayList<String>();

		if (user.getLogin().equals("user1")) {
			roles.add("user");
		}
		if (user.getLogin().equals("user2")) {
			roles.add("manager");
		}
		if (user.getLogin().equals("admin")) {
			roles.add("admin");
		}
		return roles;
	}
}
