package dao;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import carlot.Car;
import carlot.CarLot;
import carlot.Offer;
import daos.CarDao;
import finance.Payment;
import services.UserServices;
import user.User;

public class CarDaoTest {

	UserServices userService = new UserServices();
	CarDao dao = userService.getUserCarDao();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
//	//CREATE
//		public void addCar(Car car, boolean active), public Car getCar(String vinNumber, boolean active),public void removeCar(String vinNumber, boolean active);
	
	@Test
	public void addCarTest() {
		User e = new User();
		e.setUserId(1);
		e.setName("employee");
		e.setPassword("employee");
		userService.setUser(e);
		

		
		Car car = new Car("Chevrolet", "Sonic", "Red", "REDSONIC", 12000);
		dao.addCar(car, false);
		
		assertEquals("This should add the Red Sonic to the lot", car, dao.getCar("REDSONIC", false));
		
		dao.removeCar("REDSONIC", false);
		
	}	
	
//		public void addOffer(Offer offer, boolean active), public Offer getOffer(int offerid, boolean active);
	
		@Test
		public void addOfferTest() {
			User e = new User();
			e.setUserId(2);
			e.setName("customer");
			e.setPassword("customer");
			userService.setUser(e);
			
			Car car = new Car("Chevrolet", "Sonic", "Red", "BLUESONIC", 12000);
			
			
			
			Offer offer = new Offer();
			offer.setActive(true);
			offer.setAmount(10000);
			offer.setUserId(e.getUserId());
			offer.setVinNumber(car.getVinNum());
			//dao.addOffer(offer, false);
			
			assertEquals("This should add an offer to the offer table", offer.getVinNumber(), dao.getOffer(2, false).getVinNumber());			
			
		}
	
	
//		public void addUser(User user, boolean active), public User getUser(String username, String password, boolean active);
		
		@Test
		public void addUserTest() {
			User e = new User();
			e.setUserId(3);
			e.setName("customer1");
			e.setPassword("customer1");
			e.setType("customer");
			userService.setUser(e);
			dao.addUser(e, false);
			
			assertEquals("This should add the user to the user table",dao.getUser("customer1", "customer1", false).getName(), "customer1");
			
		}
		
		
//		public void populatePayments(Car car, boolean active);
//		
//		//READ
//		
		
		
		
//		public List<Car> getAllCars(boolean active);
		
		@Test
		public void getAllCarsTest() {//ensures that it actually returns a list from the dao
			
			assertEquals("This method should return a list", true, dao.getAllCars(false) instanceof List<?>);
			
		}
		
		
//		
//		public List<Offer> getAllOffers(boolean active);
		
		@Test
		public void getAllOffersTest() {//ensures that it actually returns a list from the dao
			
			assertEquals("This method should return a list", true, dao.getAllOffers(false) instanceof List<?>);
			
		}
		
		
		
//		public List<Offer> getAllMyOffers(int userId, boolean active);
		
		@Test
		public void getAllMyOffersTest() {//ensures that it actually returns a list from the dao
			
			assertEquals("This method should return a list", true, dao.getAllMyOffers(2,false) instanceof List<?>);
			
		}
		
		
//		public List<Payment> getAllPayments(boolean active);
//		
		@Test
		public void getAllPaymentsTest() {//ensures that it actually returns a list from the dao
			
			assertEquals("This method should return a list", true, dao.getAllPayments(false) instanceof List<?>);
			
		}
		
		
//		
//		//UPDATE
//		
//		public void acceptOffer(Offer offer, boolean active);		
//		
//		
//		//DELETE
//		public void rejectOffer(int offerId, boolean active);//this needs to also change ownership to the offer's user
//		

		

		@Test
		public void getAllMyCarsTest() {//List<Car> getAllMyCars(int userId, boolean active);
			
			assertEquals("This method should return a list", true, dao.getAllMyCars(2, false) instanceof List<?>);
			
		}
		
		
		
//		List<Payment> getAllMyPayments(int userId, boolean active);
//		
		@Test
		public void getAllMyPaymentsTest() {//ensures that it actually returns a list from the dao
			
			assertEquals("This method should return a list", true, dao.getAllMyPayments(2, false) instanceof List<?>);
			
		}
		
//		
//		//FUNCTIONS AND PROCEDURES
//		
//		public void sundaySundaySunday(boolean active);
		
		@Test
		public void sundaySundaySundayTest() {
			
			Car car = new Car("Chevrolet", "Sonic", "Red", "REDSONIC", 12000);
			dao.addCar(car, false);
			
			Car carPreSale = dao.getCar("REDSONIC", false);
			dao.sundaySundaySunday(false);
			Car carPostSale = dao.getCar("REDSONIC", false);
			
			assertNotEquals("The prices should not match", carPreSale.getPrice(), carPostSale.getPrice());
			
			
			dao.removeCar("REDSONIC", false);
			
		}
		
		
//		public void mondayMondayMonday(boolean active);

		@Test
		public void mondayMondayMondayTest() {
		
		Car car = new Car("Chevrolet", "Sonic", "Red", "REDSONIC", 12000);
		dao.addCar(car, false);
		
		Car carPreSale = dao.getCar("REDSONIC", false);
		dao.mondayMondayMonday(false);
		Car carPostSale = dao.getCar("REDSONIC", false);
		
		assertNotEquals("The prices should not match", carPreSale.getPrice(), carPostSale.getPrice());
		
		
		dao.removeCar("REDSONIC", false);
		}
	

}
