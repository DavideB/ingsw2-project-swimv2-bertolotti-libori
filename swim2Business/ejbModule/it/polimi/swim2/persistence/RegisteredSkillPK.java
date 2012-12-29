package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RegisteredSkill database table.
 * 
 */
@Embeddable
public class RegisteredSkillPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="skill_id")
	private int skillId;

    public RegisteredSkillPK() {
    }
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSkillId() {
		return this.skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RegisteredSkillPK)) {
			return false;
		}
		RegisteredSkillPK castOther = (RegisteredSkillPK)other;
		return 
			(this.id == castOther.id)
			&& (this.skillId == castOther.skillId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.skillId;
		
		return hash;
    }
}