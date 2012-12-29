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