package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.User;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessEJB {
	public void createTestData();
	public void deleteSomeData();
	public List<User> getAllUsers();
}
