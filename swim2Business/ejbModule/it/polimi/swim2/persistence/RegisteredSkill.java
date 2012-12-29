package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RegisteredSkill database table.
 * 
 */
@Entity
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