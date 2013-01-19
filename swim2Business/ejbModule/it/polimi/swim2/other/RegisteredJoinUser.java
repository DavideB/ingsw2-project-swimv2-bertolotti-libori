package it.polimi.swim2.other;

import java.util.Date;
import it.polimi.swim2.persistence.User;
import it.polimi.swim2.persistence.Registered;

public class RegisteredJoinUser {

	public RegisteredJoinUser(User u, Registered r) {
/*		email = u.getEmail();
		password = u.getPassword();
		name = r.getName();
		surname = r.getSurname();
		birthdate =  r.getBirthdate();
*/		
	};
	
//	private int u_id;   

	private String email;

	private String password;
	
//	private int r_id;
	
	private Date birthdate;

//	private String imageUrl;

	private String name;

	private String surname;
 

/*	public int getId() {
		return this.u_id;
	}
*/

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

/*	public String getImageUrl() {
		return this.imageUrl;
	}
*/

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}
}
