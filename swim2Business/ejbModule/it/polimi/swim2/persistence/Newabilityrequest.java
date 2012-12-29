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