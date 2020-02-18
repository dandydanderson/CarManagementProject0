package user;

import java.io.Serializable;
import java.util.ArrayList;

import carlot.Car;
import carlot.CarLot;
import daos.CarDao;

//this is the umbrella user class that all user types extend
public class User implements Serializable{
	
	
	
	
	private String name;
	private String type;

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
	

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

	
	
}
