package carlot;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.TestCase;
import user.Customer;
import user.User;

@RunWith(MockitoJUnitRunner.class)
public class CarLotTest {
	

	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before //create a CarLot before each example
	public void setUp() throws Exception {
		//CarLot testLot = new CarLot("Test Car Lot");//Not sure why this isn't working
		System.out.println("@Before is being called");
	}

	@After
	public void tearDown() throws Exception {
	}

	
	////////////////////////////////////////
	////////////CarLot unit test ------------------no long in use
	//////////////////////////////////
	
//	@Test
//	public void checkUserListForUserExistsTest() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		testLot.addUser(new User("John"));//do I need mockito if the object is a pojo?
//		assertEquals("checkUserListForUser should return true for 'John'",true, testLot.checkUserListForUser("John"));
//	}
//	
//	@Test
//	public void checkUserListForUserDNETest() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		testLot.addUser(new User("John"));//do I need mockito if the object is a pojo?
//		assertEquals("checkUserListForUser should return true for 'Fred'",false, testLot.checkUserListForUser("Fred"));
//	}
//	
//	@Test
//	public void addCarToCustomer() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car car = new Car("Chevrolet", "Sonic", "Red", "REDSONIC");//public Car(String make, String model, String color, String vinNum)
//		User customer = new Customer("TestCust");
//		testLot.addUser(customer);
//		testLot.addCarToCustomer(car, (Customer)customer);
//		
//		assertEquals("This method should add a Car object to a customer objects car list.",car, ((Customer) customer).getCars().get(0));
//	}
//	
//	@Test
//	public void getCustomerPaymentsTest() {//NOT A FUNCTIONING TEST
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car car = new Car("Chevrolet", "Sonic", "Red", "REDSONIC");//public Car(String make, String model, String color, String vinNum)
//		car.setPrice(5000);
//		car.populatePayments();
//		User customer = new Customer("TestCust");
//		testLot.addUser(customer);
//		testLot.addCarToCustomer(car, (Customer)customer);
//		//not sure how to test a printer actually. Maybe check the output somehow?
//		assertEquals("This test does not function yet", true, false);
//	}
//	
//	
//	@Test
//	public void getUserTest() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		User test = new User("testUser");
//		testLot.addUser(test);
//		assertEquals("Return a user by name from the Car Lot user list- ", test, testLot.getUser("testUser"));
//	}
//	
//	@Test
//	public void getUserNoMatch() {//sort of passes. This is bad logic on my part, as it shouldn't return a user with null string if they aren't in there
//		CarLot testLot = new CarLot("Test Car Lot");
//		assertEquals("Return a user by name from the Car Lot user list- ", new User(null), testLot.getUser("testUser"));
//	}
//	
//	@Test
//	public void emptyLotTest() {//This tests the empty lot check method, emptyLot()
//		
//		CarLot testLot = new CarLot("Test Car Lot");
//		assertEquals("A new lot should start empty", true, testLot.emptyLot());	
//	}
//	
//	@Test
//	public void addCarTest() {//tests that the add car method actually adds a car
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
//		testLot.addCar(testCar);
//		assertEquals("This should add a car to the lot", false, testLot.emptyLot());
//	}
//	
//	@Test
//	public void testRemoveCar() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
//		testLot.addCar(testCar);
//		testLot.removeCar("1G1YG2DW4D5106591");
//		assertEquals("The lot should be empty after the car is removed from the lot", true, testLot.getCars().isEmpty());
//	}
//	
//	@Test
//	public void testGetCar() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
//		testLot.addCar(testCar);
//		assertEquals("This should return a specific car from the lot by VIN- ", testCar, testLot.getCar("1G1YG2DW4D5106591"));
//	}
//	
//	@Test
//	public void testTransferCarToCustomer() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car car = new Car("Chevrolet", "Sonic", "Red", "REDSONIC");//public Car(String make, String model, String color, String vinNum)
//		User customer = new Customer("TestCust");
//		testLot.addUser(customer);
//		testLot.transferCarToCustomer(car, (Customer)customer);
//		
//		assertEquals("This method should add a Car object to a customer objects car list.",car, ((Customer) customer).getCars().get(0));
//	}
//	
//	@Test
//	public void testAddOffer() {//public void addOffer(Offer offer, String vinNum) {
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
//		testLot.addCar(testCar);
//		User customer = new Customer("TestCust");
//		Offer offer = new Offer((Customer) customer, 5000, true);
//		testLot.addOffer(offer, "1G1YG2DW4D5106591");
//		
//		assertEquals("This method should add an offer to the cars offer list- ", offer, testCar.getOffers().get(0));
//		
//	}
//	
//	
//	@Test
//	public void testGetAcceptedOffer() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
//		testLot.addCar(testCar);
//		User customer = new Customer("TestCust");
//		Offer offer = new Offer((Customer) customer, 5000, true);
//		testLot.addOffer(offer, "1G1YG2DW4D5106591");
//		
//		assertEquals("Accepting an offer should return the offer accepted given vin and offerID- ", offer, testLot.getAcceptedOffer("1G1YG2DW4D5106591", "0"));
//		
//	}
//	
//	
//	
//	@Test
//	public void testRejectedOffer() {
//		CarLot testLot = new CarLot("Test Car Lot");
//		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
//		testLot.addCar(testCar);
//		User customer = new Customer("TestCust");
//		Offer offer = new Offer((Customer) customer, 5000, true);
//		testLot.addOffer(offer, "1G1YG2DW4D5106591");
//		testLot.rejectOffer("1G1YG2DW4D5106591", "0");
//		
//		assertEquals("There should be no offers in the cars offer list- ", true, testLot.getCar("1G1YG2DW4D5106591").getOffers().isEmpty());
//		
//	}

}
