package services;

import java.util.ArrayList;

import java.util.List;

import carlot.Car;
import carlot.CarLot;
import carlot.Offer;
import daos.CarDao;
import daos.UserDao;
import user.User;

public class UserServices {
	
	private boolean finished = false;
	CarDao userCarDao = new CarDao();//CarDao for the user
	private User user = new User();


	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public UserServices() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public void printLotList() {//Print out all the cars on the lot directly from file
		ArrayList<Car> carList = (ArrayList<Car>) userCarDao.read().getCars();
		
		for (int i = 0; i<carList.size();i++) {
			System.out.println(carList.get(i).toString());
		}
		
	}
	
	public void printOfferList() {//Print out the offers on the lot directly from file
		ArrayList<Offer> offerList = (ArrayList<Offer>) userCarDao.read().getOffers();
		
		for (int i = 0; i<offerList.size();i++) {
			System.out.println(offerList.get(i).toString());
		}
		
	}
	
	public void printUserList() {//Print out the offers on the lot directly from file
		ArrayList<User> userList = (ArrayList<User>) userCarDao.read().getUsers();
		
		for (int i = 0; i<userList.size();i++) {
			System.out.println(userList.get(i).toString());
		}
		
	}
	
	
	public CarLot getCarLot() {//get the Current Car Lot
		return userCarDao.read();
	}
	
	
	public void saveCarLot(CarLot carLot) {//save changes to the car lot
		userCarDao.write(carLot);
	}


}
