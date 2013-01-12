package it.polimi.swim2.business;

import it.polimi.swim2.interfaces.StatelessSkillBean;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;
import it.polimi.swim2.persistence.User;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class SkillManager
 */
@Stateless(name="SkillManager")
@Remote( StatelessSkillBean.class)
public class SkillManager implements StatelessSkillBean {
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

}
