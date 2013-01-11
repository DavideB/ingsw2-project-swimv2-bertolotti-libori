package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.User;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessFriendshipBean {
	public List<User> getAllFriends();
	public void removeFriend(String email);
	void acceptFriendship(String email);
	void addFriend(String email, int myId, String message);
}
