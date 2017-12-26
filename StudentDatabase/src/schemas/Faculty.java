package schemas;

import util.DatabaseException;
import util.Validation;

public class Faculty {

	private int id;
	private String name, address;
	private boolean active;

	public Faculty(int id, String name, String address) {
		this.setId(id);
		this.setName(name);
		this.setAddress(address);
		this.setActive(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String toString() {
		return this.getName();
	}
	
	public void validate() throws DatabaseException {
		Validation.validateLongName(this.getName());
		Validation.validateAddress(this.getAddress());
	}

}
