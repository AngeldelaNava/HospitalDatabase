package hospitaldatabase.db.ifaces;

import java.util.List;

import hospitaldatabase.db.pojos.users.Role;
import hospitaldatabase.db.pojos.users.User;

public interface HospitalUserManager {

	public void connect();
	public void disconnect();
	public void newUser(User u);
	public void newRole(Role r);
	public Role getRole(int id);
	public List<Role> getRoles();
	public User checkPassword(String email, String password);
	public List<User> getAdmins();
}
