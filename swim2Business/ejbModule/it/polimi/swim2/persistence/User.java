package it.polimi.swim2.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="USER")
@NamedQueries({
	@NamedQuery(name="User.findAll",
	query="SELECT u FROM User u "),
	@NamedQuery(name="User.findUserByEmail",
	query="SELECT u FROM User u where u.email = :email"),
	@NamedQuery(name="User.findUserById",
	query="SELECT u FROM User u where u.id = :id"),
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String password;

    public User() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}