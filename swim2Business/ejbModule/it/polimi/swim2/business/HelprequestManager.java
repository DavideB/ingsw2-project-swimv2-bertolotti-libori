package it.polimi.swim2.business;


import it.polimi.swim2.interfaces.StatelessEJBHelprequest;

import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Registered;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless(name="HelprequestManager")
@Remote(StatelessEJBHelprequest.class)
public class HelprequestManager implements StatelessEJBHelprequest {
  @PersistenceContext(unitName="persistencetier") EntityManager em;
  
 
/*
		;
 */
  @Override
  @TransactionAttribute
  public boolean createHelprequest(int ans_id, Date ansdate, byte isForFriend, String message, int sen_id, Date sentdate, int skillId ) {
	  Helprequest Helprequest = new Helprequest();
	  Helprequest.setAns_id(ans_id);
	  Helprequest.setAnsdate(ansdate);
	  Helprequest.setIsForFriend(isForFriend);
	  Helprequest.setMessage(message);
	  Helprequest.setSen_id(sen_id);
	  Helprequest.setSentdate(sentdate);
	  Helprequest.setSkillId(skillId);
	  em.persist(Helprequest); 
	  return true;
  }
  
  @Override
  @TransactionAttribute
  public boolean createHelprequest(byte isForFriend, String message, int sen_id, Date sentdate, int skillId ) {
	  Helprequest Helprequest = new Helprequest();
	  Helprequest.setIsForFriend(isForFriend);
	  Helprequest.setMessage(message);
	  Helprequest.setSen_id(sen_id);
	  Helprequest.setSentdate(sentdate);
	  Helprequest.setSkillId(skillId);
	  em.persist(Helprequest); 
	  return true;
  }
     
  public List<Helprequest> getAllHelprequests() {
	    ArrayList<Helprequest> toReturn = new ArrayList<Helprequest>();
	    Query q = em.createNamedQuery("Helprequest.findAll"); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Helprequest) po);
	    }
	    return toReturn;
	  }
  
  public Helprequest getHelprequest(int hr_id) {
	  Query q = em.createNamedQuery("Helprequest.findHelprequestById").setParameter("id", hr_id);
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (Helprequest) toReturn.get(0);
      
      return null;
  }

  public List<Helprequest> getUserHelprequests(int user_id) {
	    ArrayList<Helprequest> toReturn = new ArrayList<Helprequest>();
	    Query q = em.createNamedQuery("Helprequest.findHelprequestByUserId").setParameter("id", user_id); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Helprequest) po);
	    }
	    return toReturn;
	  }
  
  public List<Helprequest> getSkillHelprequests(int skill_id) {
	    ArrayList<Helprequest> toReturn = new ArrayList<Helprequest>();
	    Query q = em.createNamedQuery("Helprequest.findHelprequestBySkillId").setParameter("id", skill_id); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Helprequest) po);
	    }
	    return toReturn;
	  } 

  
  @Override
  public List<Helprequest> getYourFriendsHelprequests(Registered r) {
          ArrayList<Helprequest> toReturn = new ArrayList<Helprequest>();
          Query q = em.createNamedQuery("Helprequest.findYourFriendsRequests")
                          .setParameter("id", r.getId());
          for (Object po : q.getResultList()) {
                  toReturn.add((Helprequest) po);
          }
          return toReturn;
  }

  @Override
  public List<Helprequest> getOthersHelprequests(Registered r) {
          ArrayList<Helprequest> toReturn = new ArrayList<Helprequest>();
          Query q = em.createNamedQuery("Helprequest.findOthersRequests")
                          .setParameter("id", r.getId());
          for (Object po : q.getResultList()) {
                  toReturn.add((Helprequest) po);
          }
          return toReturn;
  }
  
}