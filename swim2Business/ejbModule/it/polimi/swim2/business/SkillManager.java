package it.polimi.swim2.business;

import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.RegisteredSkill;
import it.polimi.swim2.persistence.RegisteredSkillPK;
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
    public SkillManager() {}

	/**
     * @see StatelessSkillBean#getAllSkills()
     */
    @Override
    public List<Skill> getAllSkills() {
    	ArrayList<Skill> toReturn = new ArrayList<Skill>();
    	Query q = em.createNamedQuery("Skill.findAll"); 
    	for (Object s : q.getResultList()) {
    		toReturn.add((Skill) s);
	    }
	    return toReturn;
    }
    
    @Override
    public List<Skill> getAllOtherSkills(Registered r) {
    	ArrayList<Skill> toReturn = new ArrayList<Skill>();
    	Query q = em.createNamedQuery("RegisteredSkill.findOtherSkills"); 
    	q.setParameter("id", r.getId());
    	for (Object s : q.getResultList()) {
    		toReturn.add((Skill) s);
	    }
	    return toReturn;
    }

    
        /**
     * @see StatelessSkillBean#getUserSkills(String)
     */
    @Override
    public List<Skill> getUserSkills(Registered r) {
        ArrayList<Skill> toReturn = new ArrayList<Skill>();
        Query q = em.createNamedQuery("RegisteredSkill.findUserSkills");
        q.setParameter("id", r.getId());
       
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
  	  Query q = em.createNamedQuery("Skill.findSkillByName");
  	  q.setParameter("name", name);
  	  List toReturn = q.getResultList();  
        if (toReturn != null && !toReturn.isEmpty())
      	  return (Skill) toReturn.get(0);
        
        return null;
    }
    
    @Override
    public void linkSkill(Registered r, Skill s) {
            RegisteredSkill rs = new RegisteredSkill();
            RegisteredSkillPK pk = new RegisteredSkillPK();
            pk.setId(r.getId());
            pk.setSkillId(s.getId());
            rs.setId(pk);
            em.persist(rs);
    }
   
    @Override
    public void unLinkSkill(Registered r, Skill s) {
            RegisteredSkillPK pk = new RegisteredSkillPK();
            pk.setId(r.getId());
            pk.setSkillId(s.getId());
            em.remove(em.find(RegisteredSkill.class, pk));
    }

    

	@Override
	public List<Skill> getAllRegSkills() {
		ArrayList<Skill> toReturn = new ArrayList<Skill>();
    	Query q = em.createNamedQuery("Skill.findAllRegistered"); 
    	for (Object s : q.getResultList()) {
    		toReturn.add((Skill) s);
	    }
	    return toReturn;
	}

//	@Override
//	public ArrayList<Skill> getUserSkills(String email) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
