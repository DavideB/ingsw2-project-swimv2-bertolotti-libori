package it.polimi.swim2.other;

import java.util.Date;
import it.polimi.swim2.persistence.User;
import it.polimi.swim2.persistence.Admin;

public class AdminJoinUser {

	private int id;

    public AdminJoinUser(User u, Admin a) {
    	
    };

	private String email;

	private String password;
	
	private Date birthdate;

	private String imageUrl;

	private String name;

	private String surname;
 

	public int getId() {
		return this.id;
	}


	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
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


	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}
}
