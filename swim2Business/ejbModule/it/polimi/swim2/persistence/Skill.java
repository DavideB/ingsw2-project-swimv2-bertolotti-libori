package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the SKILL database table.
 * 
 */
@Entity
@Table(name="SKILL")
@NamedQueries({
	@NamedQuery(name="Skill.findAll",
			query="SELECT s FROM Skill s"),
	@NamedQuery(name="Skill.findAllRegistered",
	query="SELECT distinct s FROM Skill s, RegisteredSkill rs where s.id in (select rs.id.skillId from RegisteredSkill rs)"),
	@NamedQuery(name="Skill.findSkillById",
	query="SELECT s FROM Skill s where s.id = :id"),
	@NamedQuery(name="Skill.findSkillByName",
	query="SELECT s FROM Skill s where s.name = :name")
})
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @Lob()
	private String adminansw;

    @Temporal( TemporalType.DATE)
	private Date defdate;

	@Column(name="Lead_id")
	private int lead_id;

	private String name;

    public Skill() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminansw() {
		return this.adminansw;
	}

	public void setAdminansw(String adminansw) {
		this.adminansw = adminansw;
	}

	public Date getDefdate() {
		return this.defdate;
	}

	public void setDefdate(Date defdate) {
		this.defdate = defdate;
	}

	public int getLead_id() {
		return this.lead_id;
	}

	public void setLead_id(int lead_id) {
		this.lead_id = lead_id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}