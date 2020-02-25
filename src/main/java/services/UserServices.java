package services;

import java.util.ArrayList;

import java.util.List;

import carlot.Car;
import carlot.CarLot;
import carlot.Offer;
import daos.CarDao;
import finance.Payment;
import user.User;

public class UserServices {
	
	private boolean sale = false;
	private boolean finished = false;
	private static CarDao userCarDao = new CarDao();//CarDao for the user
	private User user = new User();


	
	public boolean saleActive() {
		return sale;
	}

	public void setSale(boolean sale) {
		this.sale = sale;
	}

	public CarDao getUserCarDao() {
		return userCarDao;
	}

	public void setUserCarDao(CarDao userCarDao) {
		this.userCarDao = userCarDao;
	}

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

	
	
	public void printLotList(List<Car> carList) {//Print out all the cars on the lot directly from file

		for(int i = 0; i<carList.size();i++) {
			System.out.println("------------------------------");
			System.out.println(carList.get(i).toString());
		}
		
	}
	
	public void printOfferList(List<Offer> offerList) {//Print out the offers on the lot directly from file ///CarLot offerList is now where offers live
		for(int i = 0; i<offerList.size();i++) {
			System.out.println("------------------------------");
			System.out.println(offerList.get(i).toString());
		}
		
	}
	
	public void printPaymentList(List<Payment> paymentList) {//Print out the offers on the lot directly from file ///CarLot offerList is now where offers live
		for(int i = 0; i<paymentList.size();i++) {
			System.out.println("------------------------------");
			System.out.println(paymentList.get(i).toString());
		}
		
	}
	
	public void printUserList() {//Print out the offers on the lot directly from file
//		ArrayList<User> userList = (ArrayList<User>) userCarDao.read().getUsers();
//		
//		for (int i = 0; i<userList.size();i++) {
//			System.out.println(userList.get(i).toString());
//		}
		
	}
	
	
//	public CarLot getCarLot() {//get the Current Car Lot
//		return userCarDao.read();
//	}
//	
	
//	public void saveCarLot(CarLot carLot) {//save changes to the car lot
//		userCarDao.write(carLot);
//	}


}
