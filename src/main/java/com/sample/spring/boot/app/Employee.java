package com.sample.spring.boot.app;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Class used to send employee information to the client in xml format.
 * 
 * @author Srilalitha Nittala
 *
 */
@XmlRootElement(name="employee")
@XmlType(propOrder={"name", "address"})
public class Employee {

	private int id;
	
	private String name;
	
	private String address;

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
}
