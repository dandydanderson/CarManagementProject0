package carlot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import user.Customer;
import user.User;

public class CarLot implements Serializable{
	

	private String carLotName;
	private List<Car> cars = new ArrayList<Car>();//these are all populated after UserServices loads the file
	private List<Offer> offers = new ArrayList<Offer>();//this needs to be removed, as it doesn't actually hold teh offers anymore
	private List<User> users = new ArrayList<User>();
	
	
/////////////////////////
	//User Section
	/////////////////////
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public boolean checkUserListForUser(String userName) {//tested
		
		boolean check = false;
		
		for(int i = 0; i < users.size();i++) {
			if(users.get(i).getName().equals(userName)) {check = true;}
		}
		
		return check;
	}
	
	public void addCarToCustomer(Car car, Customer cust) {
		
		for(int i =0;i<users.size();i++) {
			if(users.get(i).equals(cust)) {
				cust = (Customer)users.get(i);
				cust.addCar(car);
				users.set(i, cust);
				break;
			}
		}
	}
	public void getCustomerPayments(String customer, String vinNumber) {
		Customer temp = new Customer("Null");
		for(int i = 0;i<users.size();i++) {
			if(users.get(i).getName().equals(customer)) {
				temp = (Customer) users.get(i);
			}
		}
		for(int i =0;i<temp.getCars().size();i++) {
			if(temp.getCars().get(i).getVinNum().equals(vinNumber)) {
				temp.getCars().get(i).printPayments();
			}
		}
		
	}
	
	public User getUser(String name) {
		User temp = new User();
		for (int i =0;i<users.size();i++) {
			if(users.get(i).getName().equals(name)) {
				temp = users.get(i);
			}
		}
		return temp;
	}
	
	
	/////////////////////////
	///Car Lot Section
	///////////////////////

	public String getCarLotName() {
		return carLotName;
	}

	public void setCarLotName(String carLotName) {
		this.carLotName = carLotName;
	}
	public CarLot (String carLotName) {
		this.carLotName = carLotName;
	}
	public boolean emptyLot() {
		if(cars.isEmpty()) {
			return true;
		}
		else {return false;
		}
	}
	

	
	/////////////////////////
	///////Car Section
	///////////////////
	public List<Car> getCars() {
		return cars;
	}
	
	public void setCar(List<Car> car) {
		this.cars = car;
	}
	
	public void addCar(Car car) {//test this guy
		
		cars.add(car);
	}
	
	public void removeCar(String vinNumber) {
		for(int i = 0; i<cars.size();i++) {
			if(cars.get(i).getVinNum().equals(vinNumber)) {
				System.out.println("\n" + "Successfully removed car: " + cars.get(i).toString());
				cars.remove(i);
			}
		}
	}
	
	public Car getCar(String vinNum) {
		for(int i = 0; i<cars.size();i++) {
			if(cars.get(i).getVinNum().equals(vinNum)) {
				return cars.get(i);
			}
		}
			return null;
		
	}
	
	public void transferCarToCustomer(Car car, User customer) {
		((Customer) customer).addCar(car);
	}//this is a repeat method, but cleaner than the other. Need to refactor out the addCarToCustomer method and replace it with this
	public Customer getCustomerFromCarOffer(Car car, String offerNum) {
		Customer temp = new Customer("Null");
		for(int i = 0;i<car.getOffers().size();i++) {
		if(car.getOffers().get(i).getOfferId().equals(offerNum)) {
			return car.getOffers().get(i).getOwner();
		}
		}
		return temp;
	}
	
	
	/////////////////////////////
	//////Offers
	//////////////////
	public List<Offer> getOffers() {//this should create a new ArrayList and add all the offers instead of setting Lot offers to this. 
									//Lot offers isn't used anywhere else.
		
	if(offers.isEmpty()) {
		for(int i = 0; i<cars.size();i++) {
			for(int j = 0; j<cars.get(i).getOffers().size();j++) {
				offers.add((Offer) cars.get(i).getOffers().get(j));
			}
		}
		
		return offers;
	}
	else {return offers;}
	}
	
	public void addOffer(Offer offer, String vinNum) {
		
		for (int i = 0;i<cars.size();i++) {
			if(cars.get(i).getVinNum().equals(vinNum)) {
				offer.setOfferId(String.valueOf(cars.get(i).getOffers().size()));
				cars.get(i).addOffer(offer);
			}
		}
	}
//	public void removeOffer(Offer offer) {
//		for(int i = 0; i<offers.size();i++) {
//			if (offers.get(i).equals(offer)){
//				offers.remove(i);
//			}
//		}
//	}
	
	public void printMyOffers(Customer cust) {
		
		for(int i = 0; i<cars.size();i++) {
			for(int j = 0;j<cars.get(i).getOffers().size();j++) {
				if(cars.get(i).getOffers().get(j).getOwner().equals(cust)){
					System.out.println(cars.get(i).getOffers().get(j).toString());
				}
			}
		}
			
	}
	
	public void printCarOffers(String vinNum) {
		for(int i = 0; i<cars.size();i++) {
			if(cars.get(i).getVinNum().equals(vinNum)) {
				cars.get(i).printCarOffers();
			}
		}
	}
	
	
	public Offer getAcceptedOffer(String vinNum, String offerId) {
		for(int i = 0;i<cars.size();i++) {
			for(int j = 0;j<cars.get(i).getOffers().size();j++) {
				if(cars.get(i).getOffers().get(j).getOfferId().equals(offerId) && cars.get(i).getVinNum().equals(vinNum)) {//if the offer number and vin number match
				return cars.get(i).getOffers().get(j);
				}
			}
		}
		return null;
	}
	
	public void rejectOffer(String vinNum, String offerId) {
		for(int i = 0;i<cars.size();i++) {
			for(int j = 0;j<cars.get(i).getOffers().size();j++) {
				if(cars.get(i).getOffers().get(j).getOfferId().equals(offerId) && cars.get(i).getVinNum().equals(vinNum)) {//if the offer number and vin number match
				cars.get(i).getOffers().remove(cars.get(i).getOffers().get(j));
				}
			}
		}
	}
//	public void cleanOffers(String vinNum) {//no longer needed
//		for(int i = 0;i<offers.size();i++) {
//			if(offers.get(i).getCar().getVinNum().equals(vinNum)) {
//				offers.remove(i);
//				i-=1;
//			}
//		}
//	}
	/////////////////////////////////////
	/////Table Printers
	/////////////////////
	public void printLotList() {//Print out all the cars on the lot directly from file
		
		for (int i = 0; i<cars.size();i++) {
			System.out.println(cars.get(i).toString());
		}
		
	}
	
	public void printOfferList() {//Print out the offers on the lot directly from file
		
		for (int i = 0; i<cars.size();i++) {
			for(int j=0;j<cars.get(i).getOffers().size();j++){
				
			System.out.println(cars.get(i).getOffers().get(j).toString());
			}
		}
		}
		
	
	public void printUserList() {//Print out the offers on the lot directly from file
		
		for (int i = 0; i<users.size();i++) {
			System.out.println(users.get(i).toString());
			}
		}

	

	@Override
	public String toString() {
		return "CarLot [carLotName=" + carLotName + "]";
	}
	
	
	
}
