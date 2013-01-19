package it.polimi.swim2.interfaces;

import it.polimi.swim2.other.RegisteredJoinUser;
import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;
import it.polimi.swim2.persistence.User;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessEJB {
	public void createTestData();
	public List<User> getAllUsers();
	public void createUser(String firstName, String lastName, String email, String password, Date birthDate);
	public Registered loginReg(String username, String password);
	public Admin loginAdmin(String username, String password);
	public Registered getUserData(String userName);
	public boolean verifyOrCreateUser(String email);
	public boolean registerUser(String firstName, String lastName, String email, String password, Date birthDate);
	public List<Registered> getAllRegistered();
	public User getUser(int id);
	public User getUser(String email);
	public List<Admin> getAllAdmin();
	public Admin getAdmin(int user_id);
	public Admin getAdmin(User user);
	public List<RegisteredJoinUser> getAllRegisteredJoinUser();
	void changeImg(Registered r, String url);
	List<Registered> getAllOtherRegistered();
}
