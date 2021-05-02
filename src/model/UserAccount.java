package model;

// this model code is for creating accounts
public class UserAccount {

	public String name;
	public String DOB;
	public String address;
	public String email;
	public String type;

	public UserAccount() {
	}

	public UserAccount(String name, String DOB, String address, String email, String type) {
		this.name = name;
		this.DOB = DOB;
		this.address = address;
		this.email = email;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}