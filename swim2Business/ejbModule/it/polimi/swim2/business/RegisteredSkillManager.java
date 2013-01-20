package it.polimi.swim2.business;

import it.polimi.swim2.interfaces.StatelessEJBRegisteredSkill;
import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.RegisteredSkill;
import it.polimi.swim2.persistence.RegisteredSkillPK;
import it.polimi.swim2.persistence.Skill;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class RegisteredSkillManager
 */
@Stateless(name="RegisteredSkillManager")
@Remote( StatelessEJBRegisteredSkill.class)
public class RegisteredSkillManager implements StatelessEJBRegisteredSkill {
	 @PersistenceContext(unitName="persistencetier") EntityManager em;

    /**
     * Default constructor. 
     */
    public RegisteredSkillManager() {}

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

}
