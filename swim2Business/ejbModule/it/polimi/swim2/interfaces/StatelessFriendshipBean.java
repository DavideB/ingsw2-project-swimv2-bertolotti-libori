package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.Friendshiprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.User;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessFriendshipBean {
	//public List<User> getAllFriends();
	public void removeFriend(String email);
	void addFriend(int targetId, int myId, String message) throws Exception;
	List<Registered> getAllFriends(Registered r);
	List<Friendshiprequest> getAllRequests(int target);
	void rejectFriendship(int reqId);
	public void acceptFriendship(int target);
	void removeFriend(int myId, int targetId);
}
