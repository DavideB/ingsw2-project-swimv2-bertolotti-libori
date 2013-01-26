package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.Newabilityrequest;
import it.polimi.swim2.persistence.Skill;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessEJBSkillrequest {
	public boolean createSkillrequest(int mak_id, Date mak_date, String message);
	public int deleteSkillrequest(int req_id);
	public List<Newabilityrequest> getAllSkillrequests();
	public List<Newabilityrequest> getUnansweredSkillrequests();
	public List<Newabilityrequest> getUnansweredSkillrequests(Date mak_date);
	public Newabilityrequest getSkillrequest(int mak_id);

}
