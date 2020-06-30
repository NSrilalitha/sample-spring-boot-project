package com.sample.spring.boot.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "person")
public class PersonDetails {
	
	private String firstName;
	
	private String lastName;
	
	private String nativePlace;

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

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	@Override
	public String toString() {
		return "PersonDetails [firstName=" + firstName + ", lastName=" + lastName + ", nativePlace=" + nativePlace
				+ "]";
	}
	
	

}
