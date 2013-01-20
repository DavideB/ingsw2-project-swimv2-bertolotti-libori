package it.polimi.swim2.decorators;

import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;

import org.displaytag.decorator.TableDecorator;

public class SkillDecorator extends TableDecorator implements Comparable {
	public String getAddAbilityLink() {
		Skill s = (Skill)getCurrentRowObject();
		String link = "<a href=\"AddSkill?target="+s.getId()+"\">"+"Aggiungi questa abilità al tuo curriculum"+ "</a>";
		return link;
	}
	public String getRemoveAbilityLink() {
		Skill s = (Skill)getCurrentRowObject();
		String link = "<a href=\"RemoveSkill?target="+s.getId()+"\">"+"Rimuovi"+ "</a>";
		return link;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
