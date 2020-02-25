package daos;

import java.util.List;

import carlot.Car;
import carlot.Offer;
import finance.Payment;
import user.User;

//every dao object will need to be able to load and save at a minimum

public interface Dao {

// DEPRECATED FROM SERIALIZATION MODEL
//	
//	public void write(Object object); 
//	
//	public Object read();
	
	//CREATE
	public void addCar(Car car, boolean active);
	public void addOffer(Offer offer, boolean active);
	public void addUser(User user, boolean active);
	public void populatePayments(Car car, boolean active);
	
	//READ
	public Car getCar(String vinNumber, boolean active);
	public List<Car> getAllCars(boolean active);
	public Offer getOffer(int offerid, boolean active);
	public List<Offer> getAllOffers(boolean active);
	public List<Offer> getAllMyOffers(int userId, boolean active);
	public User getUser(String username, String password, boolean active);
	public List<User> getAllUsers(boolean active);
	public List<Payment> getAllPayments(boolean active);
	
	
	//UPDATE
	
	public void acceptOffer(Offer offer, boolean active);
	
	
	//DELETE
	public void rejectOffer(int offerId, boolean active);//this needs to also change ownership to the offer's user
	public void removeCar(String vinNumber, boolean active);
	List<Car> getAllMyCars(int userId, boolean active);
	List<Payment> getAllMyPayments(int userId, boolean active);
	
	
	//FUNCTIONS AND PROCEDURES
	
	public void sundaySundaySunday(boolean active);
	public void mondayMondayMonday(boolean active);
	
}
