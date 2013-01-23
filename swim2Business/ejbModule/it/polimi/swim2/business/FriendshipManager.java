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
	  @PersistenceContext(unitName="persistencetier") EntityManager em;

    /**
     * Default constructor. 
     */
    public FriendshipManager() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Registered> getAllFriends(Registered r) {
		Query q = em.createNamedQuery("Friendshiprequest.getRespondents"); 
		Query q1 = em.createNamedQuery("Friendshiprequest.getAskers"); 
		q.setParameter("id", r.getId());
		q1.setParameter("id", r.getId());
		List tmp = q.getResultList();  
		List<Registered> toReturn = new ArrayList<Registered>();
		if (tmp != null) tmp.addAll(q1.getResultList());
		else tmp = q1.getResultList();
	    if (tmp != null && !tmp.isEmpty()) { 
	    	for (Object s : q.getResultList()) {
	    		toReturn.add((Registered) s);
		    }
	    	return toReturn;
	    }
		return null;
	}

	@Override
	public void addFriend(int targetId, int myId, String message ) {
		Query q = em.createNamedQuery("Registered.find"); 
		q.setParameter("id", targetId);
		List toReturn = q.getResultList();  
	    if (toReturn != null && !toReturn.isEmpty()) { 
		   Registered r = (Registered) toReturn.get(0);
		   Friendshiprequest f = new Friendshiprequest();
		   f.setSent_id(myId);
		   java.sql.Date sqlDate = new java.sql.Date(new java.util.GregorianCalendar().getTimeInMillis());
		   f.setSentdate(sqlDate);
		   f.setAns_id(r.getId());
		   f.setMessage(message);
		   em.persist(f);
		   	    }
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
	public void acceptFriendship(String email) {
		Query q = em.createNamedQuery("Registered.getUserData"); 
		q.setParameter("email", email);
		List toReturn = q.getResultList();  
	    if (toReturn != null && !toReturn.isEmpty()) { 
	    	Registered r = (Registered) toReturn.get(0);
			Query q1 = em.createNamedQuery("Friendshiprequest.getAskers"); 
	    	q1.setParameter("id", r.getId());
	    	List toRet = q.getResultList();  
		    if (toRet != null && !toReturn.isEmpty()) { 
		    	Friendshiprequest f = (Friendshiprequest) toRet.get(0);
		    	java.sql.Date sqlDate = new java.sql.Date(new java.util.GregorianCalendar().getTimeInMillis());
		    	f.setAnsdate(sqlDate);
		    	return;
		    }	
	    }
	}
	    
}
