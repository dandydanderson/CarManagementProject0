package user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import carlot.Car;
import carlot.CarLot;
import daos.CarDao;
import services.UserServices;

public class UserTest {

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

	
	
	@Test
	public void userGetLotTest() {//need to come back and mockito this guy instead of integration testing, but for now
		
		CarLot testCarLot = new CarLot("Test Car Lot");
		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
		testCarLot.addCar(testCar);
		CarDao carDao = new CarDao();
		carDao.write(testCarLot);
		testCarLot = carDao.read();
		User testUser = new User("Mr. Test");
		UserServices uS = new UserServices();
	
		assertEquals("This should allow the user to get the lot", testCarLot, uS.getCarLot());

}
}
