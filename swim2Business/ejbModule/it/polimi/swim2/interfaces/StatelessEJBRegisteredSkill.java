package it.polimi.swim2.interfaces;

import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface StatelessEJBRegisteredSkill {
	void linkSkill(Registered r, Skill s);

	void unLinkSkill(Registered r, Skill s);
}

