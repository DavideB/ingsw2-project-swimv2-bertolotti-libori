package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the NEWABILITYREQUEST database table.
 * 
 */
@Entity
@Table(name="NEWABILITYREQUEST")
@NamedQueries({
	@NamedQuery(name="Newabilityrequest.findAll",
	query="SELECT n FROM Newabilityrequest n"),
	@NamedQuery(name="Newabilityrequest.findByDate",
	query="SELECT n FROM Newabilityrequest n where n.makdate = :mak_date"),
	@NamedQuery(name="Newabilityrequest.findUnanswered",
	query="SELECT DISTINCT n FROM Newabilityrequest n where (n.message not in (SELECT s.name FROM Skill s))"),
	@NamedQuery(name="Newabilityrequest.findById",
	query="SELECT n FROM Newabilityrequest n where n.mak_id = :mak_id"),
	@NamedQuery(name="Newabilityrequest.DeleteById",
	query="DELETE FROM Newabilityrequest n where n.id = :req_id")
})
public class Newabilityrequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Mak_id")
	private int mak_id;

    @Temporal( TemporalType.DATE)
	private Date makdate;

	private String message;

    public Newabilityrequest() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMak_id() {
		return this.mak_id;
	}

	public void setMak_id(int mak_id) {
		this.mak_id = mak_id;
	}

	public Date getMakdate() {
		return this.makdate;
	}

	public void setMakdate(Date makdate) {
		this.makdate = makdate;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}