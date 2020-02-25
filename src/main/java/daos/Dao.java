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
	public void addCar(Car car);
	public void addOffer(Offer offer);
	public void addUser(User user);
	public void populatePayments(Car car);
	
	//READ
	public Car getCar(String vinNumber);
	public List<Car> getAllCars();
	public Offer getOffer(int offerid);
	public List<Offer> getAllOffers();
	public List<Offer> getAllMyOffers(int userId);
	public User getUser(String username, String password);
	public List<User> getAllUsers();
	public List<Payment> getAllPayments();
	
	
	//UPDATE
	
	public void acceptOffer(Offer offer);
	
	
	//DELETE
	public void rejectOffer(int offerId);//this needs to also change ownership to the offer's user
	public void removeCar(String vinNumber);
	List<Car> getAllMyCars(int userId);
	List<Payment> getAllMyPayments(int userId);
	
	
	//FUNCTIONS AND PROCEDURES
	
	public void sundaySundaySunday();
	public void mondayMondayMonday();
	
}
