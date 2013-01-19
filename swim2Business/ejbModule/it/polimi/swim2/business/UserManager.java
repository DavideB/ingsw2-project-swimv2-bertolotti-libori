package it.polimi.swim2.business;


import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.User;
import it.polimi.swim2.other.RegisteredJoinUser;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless(name="UserManager")
@Remote(StatelessEJB.class)
public class UserManager implements StatelessEJB {
  @PersistenceContext(unitName="persistencetier") EntityManager em;
  
  public List<User> getAllUsers() {
    ArrayList<User> toReturn = new ArrayList<User>();
    Query q = em.createNamedQuery("User.findAll"); 
    for (Object po : q.getResultList()) {
      toReturn.add((User) po);
    }
    return toReturn;
  }
  
  public List<Registered> getAllRegistered() {
	    ArrayList<Registered> toReturn = new ArrayList<Registered>();
	    Query q = em.createNamedQuery("Registered.findAll"); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Registered) po);
	    }
	    return toReturn;
  }

  @Override
  public List<Registered> getAllOtherRegistered(String email) {
	    ArrayList<Registered> toReturn = new ArrayList<Registered>();
	    Query q = em.createNamedQuery("Registered.findAllOthers"); 
		q.setParameter("id", getUserData(email).getId());
	    for (Object po : q.getResultList()) {
	      toReturn.add((Registered) po);
	    }
	    return toReturn;
}
  public List<Admin> getAllAdmin() {
	    ArrayList<Admin> toReturn = new ArrayList<Admin>();
	    Query q = em.createNamedQuery("Admin.findAll"); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((Admin) po);
	    }
	    return toReturn;
  }
  
  public List<RegisteredJoinUser> getAllRegisteredJoinUser() {
	    ArrayList<RegisteredJoinUser> toReturn = new ArrayList<RegisteredJoinUser>();
	    Query q = em.createNamedQuery("Registered.findAllRegisteredJoinUser"); 
	    for (Object po : q.getResultList()) {
	      toReturn.add((RegisteredJoinUser) po);
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
   
  @Override
  @TransactionAttribute
  public boolean registerUser(String firstName, String lastName, String email, String password, Date birthDate) {
	  User user = getUser(email);
	  //user.setEmail(email);
	  user.setPassword(password);
	  //aggiorno la tupla nella tabella con la password dello user 
	  em.persist(user);
	  Registered reg = new Registered();
	  reg.setName(firstName);
	  reg.setSurname(lastName);
	  reg.setBirthdate(birthDate);
	  reg.setUser_id(user.getId());
	  em.persist(reg);
	  return true;
  }
  
  public boolean createUser(String email) {
	  User user = new User();
	  user.setEmail(email);
	  em.persist(user);
	  return true;
  }
  
  public User getUser(String email) {
	  Query q = em.createNamedQuery("User.findUserByEmail").setParameter("email", email);
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (User) toReturn.get(0);
      
      return null;
  }
  
  public User getUser(int id) {
	  Query q = em.createNamedQuery("User.findUserById").setParameter("id", id);
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (User) toReturn.get(0);
      
      return null;
  }
  
  public Registered getRegistered(int user_id) {
	  Query q = em.createNamedQuery("Registered.findRegistered").setParameter("user_id", user_id);
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (Registered) toReturn.get(0);
      
      return null;
  }
  
  public Admin getAdmin(int user_id) {
	  Query q = em.createNamedQuery("Admin.findAdmin").setParameter("user_id", user_id);
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (Admin) toReturn.get(0);
      
      return null;
  }
  
  public Registered getRegistered(User user) {
	  Query q = em.createNamedQuery("Registered.findRegistered").setParameter("user_id", user.getId());
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (Registered) toReturn.get(0);
      
      return null;
  }
 
  public Admin getAdmin(User user) {
	  Query q = em.createNamedQuery("Admin.findAdmin").setParameter("user_id", user.getId());
	  List toReturn = q.getResultList();  
      if (toReturn != null && !toReturn.isEmpty())
    	  return (Admin) toReturn.get(0);
      
      return null;
  }
  
  public boolean verifyOrCreateUser(String email) {
	  User u = getUser(email);
	  if ( u == null )
		  return createUser(email);
	  if ( u != null && getRegistered(u) == null && getAdmin(u) == null) 
		  return true;
	  return false;
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
  

@Override
public Registered getUserData(String username) {
	Query q = em.createNamedQuery("Registered.getUserData"); 
	q.setParameter("email", username);
	List toReturn = q.getResultList();  
    if (toReturn != null && !toReturn.isEmpty()) { 
	   return (Registered) toReturn.get(0);
    }
	return null;
}   

	@Override
	public void changeImg(Registered r, String url) {
		r.setImageUrl(url);
		em.merge(r);
	}
    
}
