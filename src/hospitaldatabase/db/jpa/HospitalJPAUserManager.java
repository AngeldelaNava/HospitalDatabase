package hospitaldatabase.db.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hospitaldatabase.db.ifaces.HospitalUserManager;
import hospitaldatabase.db.pojos.users.Role;
import hospitaldatabase.db.pojos.users.User;

public class HospitalJPAUserManager implements HospitalUserManager {

	private EntityManager em;
	@Override
	public void connect() {
		em = Persistence.createEntityManagerFactory("user-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreing_keys=ON").executeUpdate();
		em.getTransaction().commit();
		List<Role> existingRoles = this.getRoles();
		if (existingRoles.size() < 4) {
			this.newRole(new Role("administrator"));
			this.newRole(new Role("biomedical engineer"));
			this.newRole(new Role("hospital staff"));
			this.newRole(new Role("patient"));
		}
		//List<User> existingUsers = this.getUsers();
		//if(existingUsers.size() < 1){
			//this.newUser(new User("admin", "admin", existingRoles.get(0)));
		//}
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void newUser(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void newRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(int id) {
		Query q = em.createNativeQuery("SELECT * FROM Role WHERE id = ?", Role.class);
		q.setParameter(1, id);
		return (Role) q.getSingleResult();
	}

	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM Role", Role.class);
		return (List<Role>) q.getResultList();
	}

	@Override
	public User checkPassword(String email, String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash = md.digest();
			Query q = em.createNativeQuery("SELECT * FROM User WHERE email = ? AND password = ?", User.class);
			q.setParameter(1, email);
			q.setParameter(2, hash);
			return (User) q.getSingleResult();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

}
