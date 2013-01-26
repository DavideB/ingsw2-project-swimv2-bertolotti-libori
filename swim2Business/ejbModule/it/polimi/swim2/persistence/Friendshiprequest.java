package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the FRIENDSHIPREQUEST database table.
 * 
 */
@Entity
@Table(name="FRIENDSHIPREQUEST")
@NamedQueries( {
	@NamedQuery(name="Friendshiprequest.getFriendsRequests", query="Select f from Friendshiprequest f where f.ans_id = :id and f.ansdate is not null" ),
	@NamedQuery(name="Friendshiprequest.getRequests", query="Select f from Friendshiprequest f where f.ans_id = :id and f.ansdate is null" ),
	@NamedQuery(name="Friendshiprequest.getFriends1", query="Select r from Friendshiprequest f, Registered r where r.id = f.ans_id and f.sent_id = :id and f.ansdate is not null" ),
	@NamedQuery(name="Friendshiprequest.getFriends2", query="Select r from Friendshiprequest f, Registered r where f.sent_id=r.id and f.ans_id=:id and f.ansdate is not null" ),
	@NamedQuery(name="Friendshiprequest.getRespondents", query="Select r from Friendshiprequest f, Registered r where r.id = f.ans_id and f.sent_id = :id and f.ansdate is null" ),
	@NamedQuery(name="Friendshiprequest.getAskers", query="Select r from Friendshiprequest f, Registered r where :id = f.ans_id and f.sent_id = r.id and f.ansdate is null" )
	//,@NamedQuery(name="Friendshiprequest.accept", query="update Friendshiprequest set ")
})
public class Friendshiprequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Ans_id")
	private int ans_id;

    @Temporal( TemporalType.DATE)
	private Date ansdate;

	private String message;

	@Column(name="Sent_id")
	private int sent_id;

    @Temporal( TemporalType.DATE)
	private Date sentdate;

    public Friendshiprequest() {
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSent_id() {
		return this.sent_id;
	}

	public void setSent_id(int sent_id) {
		this.sent_id = sent_id;
	}

	public Date getSentdate() {
		return this.sentdate;
	}

	public void setSentdate(Date sentdate) {
		this.sentdate = sentdate;
	}

}