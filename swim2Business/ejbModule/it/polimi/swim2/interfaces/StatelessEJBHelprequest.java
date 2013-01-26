package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessEJBHelprequest {
	public boolean createHelprequest(int ans_id, Date ansdate, byte isForFriend,  String message, int sen_id, Date sentdate, int skillId);
	public boolean createHelprequest(byte isForFriend, String message, int sen_id, Date sentdate, int skillId);
	public List<Helprequest> getAllHelprequests();
	public List<Helprequest> getUserHelprequests(int userId);
	public List<Helprequest> getSkillHelprequests(int skillId);
	public Helprequest getHelprequest(int id);
	List<Helprequest> getYourFriendsHelprequests(Registered r);
    List<Helprequest> getOthersHelprequests(Registered r);
}
