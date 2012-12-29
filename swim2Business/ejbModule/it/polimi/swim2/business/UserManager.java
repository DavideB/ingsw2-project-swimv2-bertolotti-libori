package it.polimi.swim2.business;


import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.User;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.math.BigDecimal;
import java.util.*;

@Stateless(name="UserManager")
@Remote(StatelessEJB.class)
public class UserManager implements StatelessEJB {
  @PersistenceContext(unitName="persistencetier") EntityManager em;
  
  public List<User> getAllUsers() {
    ArrayList<User> toReturn = new ArrayList<User>();
    Query q = em.createNamedQuery("findAll"); 
    for (Object po : q.getResultList()) {
      toReturn.add((User) po);
    }
    return toReturn;
  }
  
  
  public void createTestData() {
    
    
    // create mr blair
    
    User user = new User();
    user.setEmail("blair@polimi.it");
    em.persist(user);
    Registered registered = new Registered();
    registered.setId(user.getId());
    registered.setName("Tony");
    registered.setSurname("Blair");
   
    em.persist(user);  
 
    em.refresh(user);
   
  }
  
  public void deleteSomeData() {
     

    }   
    
}