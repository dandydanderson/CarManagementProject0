package user;

import java.io.Serializable;
import java.util.ArrayList;

import carlot.Car;
import carlot.CarLot;
import daos.CarDao;

//this is the umbrella user class that all user types extend
public class User implements Serializable{
	
	
	
	private int userId;
	private String name;
	private String type;
	private String password;
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User() {
		
	}
	
	public User(String name) {
		super();
		this.name = name;
	}
	
	public User(String name, String password, String type) {
		this.name = name;
		this.password = password;
		this.type = type;
	}
	

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

	
}
