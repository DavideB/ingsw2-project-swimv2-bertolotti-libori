package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the SATISFIEDHELPREQUEST database table.
 * 
 */
@Entity
@Table(name="SATISFIEDHELPREQUEST")
@NamedQueries({
	@NamedQuery(name="Satisfiedhelprequest.findAll",
	query="SELECT s FROM Satisfiedhelprequest s"),
	@NamedQuery(name="Satisfiedhelprequest.findYourFriendsRequests",
	query="SELECT DISTINCT h FROM Helprequest h, Registered r, Skill s, Friendshiprequest fr," +
			"RegisteredSkill rs where r.id = :id AND rs.id.id=r.id AND rs.id.skillId = s.id AND " +
			"h.skillId=s.id AND ((fr.ans_id=r.id AND fr.sent_id=h.sen_id)" +
			"OR (fr.sent_id=r.id AND fr.ans_id=h.sen_id))" +
			"AND h.sen_id in (SELECT re.user_id FROM Registered re)"),
	@NamedQuery(name="Satisfiedhelprequest.findOthersRequests",
	query="SELECT DISTINCT h FROM Helprequest h, Registered r, Skill s, Friendshiprequest fr, " +
			"RegisteredSkill rs where r.id = :id AND rs.id.id= r.id AND rs.id.skillId = s.id AND " +
			"h.skillId=s.id AND NOT ((fr.ans_id=r.id AND fr.sent_id=h.sen_id)" +
			"OR (fr.sent_id=r.id AND fr.ans_id=h.sen_id))" +
			"AND h.sen_id in (SELECT re.user_id FROM Registered re)"),		
	@NamedQuery(name="Satisfiedhelprequest.findHelprequestById",
	query="SELECT s FROM Satisfiedhelprequest s where s.reqId = :hr_id"),
	@NamedQuery(name="Satisfiedhelprequest.findSatisfiedhelprequestByUserId",
	query="SELECT h FROM Helprequest h where h.sen_id = :id"),
	@NamedQuery(name="Satisfiedhelprequest.findHelprequestBySkillId",
	query="SELECT h FROM Helprequest h where h.skillId = :skill_id")
})

public class Satisfiedhelprequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="req_id")
	private int reqId;

    @Temporal( TemporalType.DATE)
	private Date date;

	private int feedback;

    public Satisfiedhelprequest() {
    }

	public int getReqId() {
		return this.reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getFeedback() {
		return this.feedback;
	}

	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}

}