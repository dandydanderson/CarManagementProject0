package carlot;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class CarLotTest {
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before //create a CarLot before each example
	public void setUp() throws Exception {
		//CarLot testLot = new CarLot("Test Car Lot");	//not sure why this isn't allowed
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addCarTest() {//tests that the add car method actually adds a car
		CarLot testLot = new CarLot("Test Car Lot");
		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
		testLot.addCar(testCar);
		assertEquals("This should add a car to the lot", false, testLot.emptyLot());
	}
	
	@Test
	public void emptyLotTest() {//This tests the empty lot check method, emptyLot()
		
		CarLot testLot = new CarLot("Test Car Lot");
		assertEquals("A new lot should start empty", true, testLot.emptyLot());	
	}

}
