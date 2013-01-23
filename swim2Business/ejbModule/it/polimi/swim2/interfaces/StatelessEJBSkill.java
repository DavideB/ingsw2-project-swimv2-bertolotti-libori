package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessEJBSkill {
	public boolean createSkill(String name, Date defdate, String adminansw,  int lead_id );
	public boolean createSkill(String name);
	public List<Skill> getAllSkills();
	public Skill getSkill(int id);
	public Skill getSkill(String name);
	List<Skill> getUserSkills(Registered r);
	List<Skill> getAllOtherSkills(Registered r);
}
