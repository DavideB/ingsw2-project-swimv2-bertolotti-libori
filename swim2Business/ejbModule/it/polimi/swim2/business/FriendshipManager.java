package it.polimi.swim2.business;

import java.util.ArrayList;
import java.util.List;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessFriendshipBean;
import it.polimi.swim2.persistence.Friendshiprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;
import it.polimi.swim2.persistence.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class FriendshipManager
 */
@Stateless
@Remote(StatelessFriendshipBean.class)
public class FriendshipManager implements StatelessFriendshipBean {
	@PersistenceContext(unitName = "persistencetier")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public FriendshipManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Registered> getAllFriends(Registered r) {
		Query q = em.createNamedQuery("Friendshiprequest.getFriends1");
		Query q1 = em.createNamedQuery("Friendshiprequest.getFriends2");
		q.setParameter("id", r.getId());
		q1.setParameter("id", r.getId());
		List tmp = q.getResultList();
		List<Registered> toReturn = new ArrayList<Registered>();
		if (tmp != null)
			tmp.addAll(q1.getResultList());
		else
			tmp = q1.getResultList();
		if (tmp != null && !tmp.isEmpty()) {
			for (Object s : tmp) {
				// controlla che la richiesta sia stata effettivamente accettata
				toReturn.add((Registered) s);
			}
			return toReturn;
		}
		return null;
	}

	@Override
	public void addFriend(int targetId, int myId, String message) throws Exception {
		Registered me = em.find(Registered.class, myId);
		Registered you = em.find(Registered.class, targetId);
		List<Registered> friends = getAllFriends(me);
		if (friends!=null && friends.contains(you)) throw new Exception("Siete già amici!");
		//controlla che tu non abbia già inviato una richiesta d'amicizia
		List<Friendshiprequest> fr = getAllRequests(you.getId());
		if (fr!=null) {
			for (Friendshiprequest tmp : fr) {
				if (tmp.getSent_id()==myId) throw new Exception("Non puoi inviare più di una richiesta d'amicizia alla stessa persona!");
			}
		}
		Friendshiprequest f = new Friendshiprequest();
		f.setSent_id(myId);
		java.sql.Date sqlDate = new java.sql.Date(
				new java.util.GregorianCalendar().getTimeInMillis());
		f.setSentdate(sqlDate);
		f.setAns_id(you.getId());
		f.setMessage(message);
		em.persist(f);
		return;
	}

	@Override
	public void removeFriend(String email) {
		Query q = em.createNamedQuery("Registered.getUserData");
		q.setParameter("email", email);
		List toReturn = q.getResultList();
		if (toReturn != null && !toReturn.isEmpty()) {
			Registered r = (Registered) toReturn.get(0);
			Query q1 = em.createNamedQuery("Friendshiprequest.getRespondents");
			q1.setParameter("id", r.getId());
			List toRet = q.getResultList();
			if (toRet != null && !toReturn.isEmpty()) {
				Friendshiprequest f = (Friendshiprequest) toRet.get(0);
				em.remove(f);
				return;
			}
			q1 = em.createNamedQuery("Friendshiprequest.getAskers");
			q1.setParameter("id", r.getId());
			toRet = q.getResultList();
			if (toRet != null && !toRet.isEmpty()) {
				Friendshiprequest f = (Friendshiprequest) toRet.get(0);
				em.remove(f);
			}

		}
		return;
	}

	@Override
	public void rejectFriendship(int reqId) {
		em.remove(em.find(Friendshiprequest.class, reqId));
	}

	@Override
	public void acceptFriendship(int target) {
		Friendshiprequest f = (Friendshiprequest) em.find(
				Friendshiprequest.class, target);
		java.sql.Date sqlDate = new java.sql.Date(
				new java.util.GregorianCalendar().getTimeInMillis());
		f.setAnsdate(sqlDate);
		return;
	}

	@Override
	public void removeFriend(int myId, int targetId) {
		Query q1 = em.createNamedQuery("Friendshiprequest.getFriendsRequests");
		q1.setParameter("id", myId);
		for (Object o : q1.getResultList()) {
			Friendshiprequest f = (Friendshiprequest) o;
			if ( (f.getAns_id()==myId && f.getSent_id()==targetId) | (f.getAns_id()==targetId && f.getSent_id()==myId)) {
				em.remove(f);
				return;
			}

		}

	}

	@Override
	public List<Friendshiprequest> getAllRequests(int target) {
		ArrayList<Friendshiprequest> toReturn = new ArrayList<Friendshiprequest>();
		Query q = em.createNamedQuery("Friendshiprequest.getRequests");
		q.setParameter("id", target);
		for (Object s : q.getResultList()) {
			toReturn.add((Friendshiprequest) s);
		}
		return toReturn;
	}

}
