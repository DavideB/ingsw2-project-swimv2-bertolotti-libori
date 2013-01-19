package it.polimi.swim2.business;

import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;
import it.polimi.swim2.persistence.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class SkillManager
 */
@Stateless(name="SkillManager")
@Remote( StatelessEJBSkill.class)
public class SkillManager implements StatelessEJBSkill {
	 @PersistenceContext(unitName="persistencetier") EntityManager em;

    /**
     * Default constructor. 
     */
    public SkillManager() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see StatelessSkillBean#getAllSkills()
     */
    public ArrayList<Skill> getAllSkills() {
    	ArrayList<Skill> toReturn = new ArrayList<Skill>();
    	Query q = em.createNamedQuery("Skill.findAll"); 
    	for (Object s : q.getResultList()) {
    		toReturn.add((Skill) s);
	    }
	    return toReturn;
    }

	/**
     * @see StatelessSkillBean#getUserSkills(String)
     */
    public ArrayList<Skill> getUserSkills(String email) {
    	ArrayList<Skill> toReturn = new ArrayList<Skill>();
    	Query q = em.createNamedQuery("RegisteredSkill.findUserSkills"); 
    	Query getUser = em.createNamedQuery("Registered.getUserData");
    	Registered user = (Registered) getUser.getSingleResult();
    	q.setParameter("id", user.getId());
    	for (Object s : q.getResultList()) {
    		toReturn.add((Skill) s);
	    }
	    return toReturn;
    }

	/**
     * @see StatelessSkillBean#addNewSkill(String)
     */
    public void addNewSkill(String name) {
        Skill s = new Skill();
        s.setName(name);
        em.persist(s);
        return;
    }
    
    @Override
    @TransactionAttribute
    public boolean createSkill(String name, Date defdate, String adminansw,  int lead_id ) {
  	  Skill skill = new Skill();
  	  skill.setName(name);
  	  skill.setDefdate(defdate);
  	  skill.setAdminansw(adminansw);
  	  skill.setLead_id(lead_id);
  	  em.persist(skill); 
  	  return true;
    }
    
       
    public boolean createSkill(String name ) {
  	  Skill skill = new Skill();
  	  skill.setName(name);
  	  em.persist(skill);
  	  return true;
    }

    public Skill getSkill(int id) {
  	  Query q = em.createNamedQuery("Skill.findSkillById").setParameter("id", id);
  	  List toReturn = q.getResultList();  
        if (toReturn != null && !toReturn.isEmpty())
      	  return (Skill) toReturn.get(0);
        
        return null;
    }

    public Skill getSkill(String name) {
  	  Query q = em.createNamedQuery("Skill.findSkillByName").setParameter("name", name);
  	  List toReturn = q.getResultList();  
        if (toReturn != null && !toReturn.isEmpty())
      	  return (Skill) toReturn.get(0);
        
        return null;
    }

}
