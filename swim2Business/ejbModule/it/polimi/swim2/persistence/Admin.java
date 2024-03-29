package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the ADMIN database table.
 * 
 */
@Entity
@Table(name="ADMIN")
@NamedQueries( {
	@NamedQuery(name="Admin.getUser", 
			query="Select a from User u, Admin a where u.id = a.user_id and u.email=:email and u.password=:password" ),
	@NamedQuery(name="Admin.findAll", 
			query="SELECT a FROM Admin a "),
	@NamedQuery(name="Admin.findAllAdminJoinUser",
			query="SELECT NEW it.polimi.swim2.other.AdminJoinUser(u, a) from User u, Admin a where u.id = a.user_id"),
	@NamedQuery(name="Admin.findAdmin",
			query="SELECT a FROM Admin a where a.user_id = :user_id"),
 })

public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @Temporal( TemporalType.DATE)
	private Date birthdate;

	private String name;

	private String surname;

	@Column(name="User_id")
	private int user_id;

    public Admin() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getUser_id() {
		return this.user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}