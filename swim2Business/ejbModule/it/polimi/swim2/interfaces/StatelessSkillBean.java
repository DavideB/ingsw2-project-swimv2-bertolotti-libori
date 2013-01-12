package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.Skill;
import it.polimi.swim2.persistence.User;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessSkillBean {
	public ArrayList<Skill> getAllSkills();
	public void addNewSkill(String name);
	public ArrayList<Skill> getUserSkills(String email);
}
