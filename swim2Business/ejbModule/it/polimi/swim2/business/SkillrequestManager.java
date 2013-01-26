package it.polimi.swim2.business;


import it.polimi.swim2.interfaces.StatelessEJBSkillrequest;

import it.polimi.swim2.persistence.Newabilityrequest;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless(name="SkillrequestManager")
@Remote(StatelessEJBSkillrequest.class)
public class SkillrequestManager implements StatelessEJBSkillrequest {
  @PersistenceContext(unitName="persistencetier") EntityManager em;
  

  @Override
  @TransactionAttribute
  public boolean createSkillrequest(int mak_id, Date mak_date, String message) {
	  Newabilityrequest newabilityrequest = new Newabilityrequest();
	  newabilityrequest.setMak_id(mak_id);
	  newabilityrequest.setMakdate(mak_date);
	  newabilityrequest.setMessage(message);
	  em.persist(newabilityrequest); 
	  return true;
  }
  
  public int deleteSkillrequest(int req_id) {
	  Query q = em.createNamedQuery("Newabilityrequest.DeleteById");
	  q.setParameter("req_id", req_id);
	  return q.executeUpdate();
        }
     
  @TransactionAttribute
  public boolean createSkillrequest(int mak_id, String message) {
	  Newabilityrequest newabilityrequest = new Newabilityrequest();
	  newabilityrequest.setMak_id(mak_id);
	  newabilityrequest.setMessage(message);
	  em.persist(newabilityrequest); 
	  return true;
  }
    

	public List<Newabilityrequest> getAllSkillrequests() {
	    ArrayList<Newabilityrequest> toReturn = new ArrayList<Newabilityrequest>();
	    Query q = em.createNamedQuery("Newabilityrequest.findAll"); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Newabilityrequest) po);
	    }
	    return toReturn;
	  }

	
	public List<Newabilityrequest> getUnansweredSkillrequests(Date mak_date) {
	    ArrayList<Newabilityrequest> toReturn = new ArrayList<Newabilityrequest>();
	    Query q = em.createNamedQuery("Newabilityrequest.findUnanswered");
	    q.setParameter("mak_date", mak_date);
	    for (Object po : q.getResultList()) {
	      toReturn.add((Newabilityrequest) po);
	    }
	    return toReturn;
	  }
	
	public List<Newabilityrequest> getUnansweredSkillrequests() {
	    ArrayList<Newabilityrequest> toReturn = new ArrayList<Newabilityrequest>();
	    Query q = em.createNamedQuery("Newabilityrequest.findUnanswered");
	    for (Object po : q.getResultList()) {
	      toReturn.add((Newabilityrequest) po);
	    }
	    return toReturn;
	  }
	
	
  public Newabilityrequest getSkillrequest(int mak_id) {
	  Query q = em.createNamedQuery("Newabilityrequest.findById").setParameter("id", mak_id);
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (Newabilityrequest) toReturn.get(0);
      
      return null;
  } 
}