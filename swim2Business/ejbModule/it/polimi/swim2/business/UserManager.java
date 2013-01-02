package it.polimi.swim2.business;


import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.User;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless(name="UserManager")
@Remote(StatelessEJB.class)
public class UserManager implements StatelessEJB {
  @PersistenceContext(unitName="persistencetier") EntityManager em;
  
  public List<User> getAllUsers() {
    ArrayList<User> toReturn = new ArrayList<User>();
    Query q = em.createNamedQuery("findAllUser"); 
    for (Object po : q.getResultList()) {
      toReturn.add((User) po);
    }
    return toReturn;
  }
  
  public List<Registered> getAllRegistered() {
	    ArrayList<Registered> toReturn = new ArrayList<Registered>();
	    Query q = em.createNamedQuery("findAllRegistered"); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Registered) po);
	    }
	    return toReturn;
  }
  
  @Override
  public Registered loginReg(String username, String password) {
	  Query q = em.createNamedQuery("Registered.getUser"); 
	  q.setParameter("email", username);
	  q.setParameter("password", password);
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty()) { 
	     return (Registered) toReturn.get(0);
      }
	  return null;
  }
  
  @Override
  public Admin loginAdmin(String username, String password) {
	    Query q = em.createNamedQuery("Admin.getUser"); 
	    q.setParameter("email", username);
		q.setParameter("password", password);
	    List toReturn = q.getResultList();  
	      if (toReturn != null && !toReturn.isEmpty()) { 
		     return (Admin) toReturn.get(0);
	      }
		  return null;
}
  
  @Override
  @TransactionAttribute
  public void createUser(String firstName, String lastName, String email, String password, Date birthDate) {
	  User user = new User();
	  user.setEmail(email);
	  user.setPassword(password);
	  //aggiungo la riga alla tabella per ottenere un id valido
	  em.persist(user);
	  Registered reg = new Registered();
	  reg.setName(firstName);
	  reg.setSurname(lastName);
	  reg.setBirthdate(birthDate);
	  reg.setUser_id(user.getId());
	  em.persist(reg);
  }
  
  
  public void createTestData() {
    
    
    // create mr blair
    
    User user = new User();
    user.setEmail("blair@polimi.it");
    em.persist(user);
    Registered registered = new Registered();
    registered.setUser_id(user.getId());
    registered.setName("Tony");
    registered.setSurname("Blair");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    try {
		registered.setBirthdate(sdf.parse("05/11/1966"));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    em.persist(registered);  
    
  }
  
  public void deleteSomeData() {
     

    }   
    
}