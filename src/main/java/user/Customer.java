package user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import carlot.Car;
import carlot.Offer;

public class Customer extends User implements Serializable{
	
	
	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	private List<Offer> offers = new ArrayList<Offer>();//this will be populated by financialServices
	private List<Car> ownedCars = new ArrayList<Car>();//handled by carDao
	
	
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List offers) {
		this.offers = offers;
	}
	public List<Car> getCars() {
		return ownedCars;
	}
	public void setCars(List cars) {
		this.ownedCars = cars;
	}
	public void addCar(Car car) {
		ownedCars.add(car);
	}
	public void printOwnedCars() {
		for(int i = 0;i<ownedCars.size();i++) {
			System.out.println(ownedCars.get(i).toString());
		}
	}

	
}
