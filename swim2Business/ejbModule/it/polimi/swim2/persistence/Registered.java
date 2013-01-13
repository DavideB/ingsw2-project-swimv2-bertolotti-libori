package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;


import java.util.Date;


/**
 * The persistent class for the REGISTERED database table.
 * 
 */
@Entity
@Table(name="REGISTERED")
@NamedQueries( {
@NamedQuery(name="Registered.getUserData", query="Select r from User u, Registered r where u.id = r.user_id and u.email= :email" ),
@NamedQuery(name="Registered.findAll", 
query="SELECT r FROM Registered r "),
@NamedQuery(name="Registered.getUser",
query="SELECT r from User u, Registered r where u.id = r.user_id and u.email= :email and u.password= :password" ),
@NamedQuery(name="Registered.findAllRegisteredJoinUser",
query="SELECT NEW it.polimi.swim2.other.RegisteredJoinUser(u, r) from User u, Registered r where u.id = r.user_id"),
@NamedQuery(name="Registered.findRegistered",
query="SELECT r FROM Registered r where r.user_id = :user_id")
})

public class Registered implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @Temporal( TemporalType.DATE)
	private Date birthdate;

	private String imageUrl;

	private String name;

	private String surname;

	@Column(name="User_id")
	private int user_id;

    public Registered() {
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

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
