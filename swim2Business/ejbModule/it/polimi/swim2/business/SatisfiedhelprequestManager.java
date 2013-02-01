package it.polimi.swim2.business;


import it.polimi.swim2.interfaces.StatelessEJBSatisfiedhelprequest;

import it.polimi.swim2.persistence.Satisfiedhelprequest;
import it.polimi.swim2.persistence.Registered;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless(name="SatisfiedhelprequestManager")
@Remote(StatelessEJBSatisfiedhelprequest.class)
public class SatisfiedhelprequestManager implements StatelessEJBSatisfiedhelprequest {
  @PersistenceContext(unitName="persistencetier") EntityManager em;

  @Override
  @TransactionAttribute
  public boolean createSatisfiedhelprequest(int reqId, Date date, int feedback ) {
	  Satisfiedhelprequest Satisfiedhelprequest = new Satisfiedhelprequest();
	  Satisfiedhelprequest.setReqId(reqId);
	  Satisfiedhelprequest.setDate(date);
	  Satisfiedhelprequest.setFeedback(feedback);
	  em.merge(Satisfiedhelprequest); 
	  return true;
  }
      
  public List<Satisfiedhelprequest> getAllSatisfiedhelprequests() {
	    ArrayList<Satisfiedhelprequest> toReturn = new ArrayList<Satisfiedhelprequest>();
	    Query q = em.createNamedQuery("Satisfiedhelprequest.findAll"); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Satisfiedhelprequest) po);
	    }
	    return toReturn;
	  }
  
  public Satisfiedhelprequest getSatisfiedhelprequest(int hr_id) {
	  Query q = em.createNamedQuery("Satisfiedhelprequest.findSatisfiedhelprequestById").setParameter("id", hr_id);
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (Satisfiedhelprequest) toReturn.get(0);
      
      return null;
  }

  public List<Satisfiedhelprequest> getUserSatisfiedhelprequests(int user_id) {
	    ArrayList<Satisfiedhelprequest> toReturn = new ArrayList<Satisfiedhelprequest>();
	    Query q = em.createNamedQuery("Satisfiedhelprequest.findSatisfiedhelprequestByUserId").setParameter("id", user_id); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Satisfiedhelprequest) po);
	    }
	    return toReturn;
	  }
  
  public List<Satisfiedhelprequest> getSkillSatisfiedhelprequests(int skill_id) {
	    ArrayList<Satisfiedhelprequest> toReturn = new ArrayList<Satisfiedhelprequest>();
	    Query q = em.createNamedQuery("Satisfiedhelprequest.findSatisfiedhelprequestBySkillId").setParameter("id", skill_id); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Satisfiedhelprequest) po);
	    }
	    return toReturn;
	  } 
  
  @Override 
  public void setFeedback(int reqId, int feedback) {
	  em.find(Satisfiedhelprequest.class, reqId).setFeedback(feedback);
  }

/* 
  @Override
  public List<Satisfiedhelprequest> getYourFriendsSatisfiedhelprequests(Registered r) {
          ArrayList<Satisfiedhelprequest> toReturn = new ArrayList<Satisfiedhelprequest>();
          Query q = em.createNamedQuery("Satisfiedhelprequest.findYourFriendsRequests")
                          .setParameter("id", r.getId());
          for (Object po : q.getResultList()) {
                  toReturn.add((Satisfiedhelprequest) po);
          }
          return toReturn;
  }

  @Override
  public List<Satisfiedhelprequest> getOthersSatisfiedhelprequests(Registered r) {
          ArrayList<Satisfiedhelprequest> toReturn = new ArrayList<Satisfiedhelprequest>();
          Query q = em.createNamedQuery("Satisfiedhelprequest.findOthersRequests")
                          .setParameter("id", r.getId());
          for (Object po : q.getResultList()) {
                  toReturn.add((Satisfiedhelprequest) po);
          }
          return toReturn;
  }
*/ 
}