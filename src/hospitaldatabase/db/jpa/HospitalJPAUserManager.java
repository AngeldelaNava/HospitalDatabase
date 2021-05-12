package hospitaldatabase.db.jpa;

import java.util.List;

import hospitaldatabase.db.ifaces.HospitalUserManager;
import hospitaldatabase.db.pojos.users.Role;
import hospitaldatabase.db.pojos.users.User;

public class HospitalJPAUserManager implements HospitalUserManager {

	@Override
	public void connect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newRole(Role r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role getRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User checkPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
