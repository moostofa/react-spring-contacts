package com.example.api.contacts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;
	private String username;
	private String email;
	private String address;
	private String phone;
	private String company;

	public Contact() {
	}

	public Contact(String name, String username, String email, String address, String phone,
			String company) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.company = company;
	}

	// Converting a @ResponseBody Contact object into an @Entity Contact object
	public Contact(ContactRequestModel details) {
		this.name = details.getName();
		this.username = details.getUsername();
		this.email = details.getEmail();
		this.address = details.getAddress();
		this.phone = details.getPhone();
		this.company = details.getCompany();
	}

	@Override
	public String toString() {
		return "Contact [\n" + "\t address=" + address + ",\n\t company=" + company + ",\n\t email="
				+ email + ",\n\t name=" + name + ",\n\t phone=" + phone + ",\n\t username="
				+ username + "\n]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
