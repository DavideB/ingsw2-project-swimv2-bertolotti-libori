package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RegisteredSkill database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="RegisteredSkill.findUserSkills",query="SELECT s FROM Skill s, RegisteredSkill r where r.id.skillId = s.id and r.id.id = :id"),
	@NamedQuery(name="RegisteredSkill.findOtherSkills",query="select s1 from Skill s1 where s1.id not in (SELECT s.id FROM Skill s, RegisteredSkill r where r.id.skillId = s.id and r.id.id <> :id)")
})
public class RegisteredSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RegisteredSkillPK id;

    public RegisteredSkill() {
    }

	public RegisteredSkillPK getId() {
		return this.id;
	}

	public void setId(RegisteredSkillPK id) {
		this.id = id;
	}
	
}