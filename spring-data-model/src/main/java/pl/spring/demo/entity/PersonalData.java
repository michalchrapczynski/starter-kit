package pl.spring.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonalData {

	@Column(nullable = false, length = 50)
	private String firstName;
	@Column(nullable = false, length = 50)
	private String lastName;

	public PersonalData() {
		// for hibernate
	}

	public PersonalData(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
