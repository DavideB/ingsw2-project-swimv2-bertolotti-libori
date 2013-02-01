package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Satisfiedhelprequest;

import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessEJBSatisfiedhelprequest {
	public boolean createSatisfiedhelprequest(int reqId, Date date, int feedback);
	public List<Satisfiedhelprequest> getAllSatisfiedhelprequests();
	public List<Satisfiedhelprequest> getUserSatisfiedhelprequests(int userId);
	public List<Satisfiedhelprequest> getSkillSatisfiedhelprequests(int skillId);
	public Satisfiedhelprequest getSatisfiedhelprequest(int id);
	//public List<Satisfiedhelprequest> getYourFriendsSatisfiedhelprequest(Registered r);
    //public List<Satisfiedhelprequest> getOthersSatisfiedhelprequest(Registered r);
	void setFeedback(int reqId, int feedback);
}
