package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the HELPREQUEST database table.
 * 
 */
@Entity
@Table(name="HELPREQUEST")
@NamedQueries({
	@NamedQuery(name="Helprequest.findAll",
	query="SELECT h FROM Helprequest h"),
	@NamedQuery(name="Helprequest.findYourFriendsRequests",
	query="SELECT h FROM Helprequest h, Registered r, Skill s, Friendshiprequest fr," +
			"RegisteredSkill rs where r.id = :id AND rs.id.id=r.id AND rs.id.skillId = s.id AND " +
			"h.skillId=s.id AND ((fr.ans_id=r.id AND fr.sent_id=h.sen_id)" +
			"OR (fr.sent_id=r.id AND fr.ans_id=h.sen_id))"),
	@NamedQuery(name="Helprequest.findOthersRequests",
	query="SELECT h FROM Helprequest h, Registered r, Skill s, Friendshiprequest fr," +
			"RegisteredSkill rs where r.id = :id AND rs.id.id=r.id AND rs.id.skillId = s.id AND " +
			"h.skillId=s.id AND ((fr.ans_id<>r.id AND fr.sent_id<>h.sen_id)" +
			"OR (fr.sent_id<>r.id AND fr.ans_id<>h.sen_id))"),
	@NamedQuery(name="Helprequest.findHelprequestById",
	query="SELECT h FROM Helprequest h where h.id = :hr_id"),
	@NamedQuery(name="Helprequest.findHelprequestByUserId",
	query="SELECT h FROM Helprequest h where h.sen_id = :user_id"),
	@NamedQuery(name="Helprequest.findHelprequestBySkillId",
	query="SELECT h FROM Helprequest h where h.skillId = :skill_id")
})

public class Helprequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Ans_id")
	private int ans_id;

    @Temporal( TemporalType.DATE)
	private Date ansdate;

	private byte isForFriend;

	private String message;

	@Column(name="Sen_id")
	private int sen_id;

    @Temporal( TemporalType.DATE)
	private Date sentdate;

	@Column(name="skill_id")
	private int skillId;

    public Helprequest() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAns_id() {
		return this.ans_id;
	}

	public void setAns_id(int ans_id) {
		this.ans_id = ans_id;
	}

	public Date getAnsdate() {
		return this.ansdate;
	}

	public void setAnsdate(Date ansdate) {
		this.ansdate = ansdate;
	}

	public byte getIsForFriend() {
		return this.isForFriend;
	}

	public void setIsForFriend(byte isForFriend) {
		this.isForFriend = isForFriend;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSen_id() {
		return this.sen_id;
	}

	public void setSen_id(int sen_id) {
		this.sen_id = sen_id;
	}

	public Date getSentdate() {
		return this.sentdate;
	}

	public void setSentdate(Date sentdate) {
		this.sentdate = sentdate;
	}

	public int getSkillId() {
		return this.skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

}