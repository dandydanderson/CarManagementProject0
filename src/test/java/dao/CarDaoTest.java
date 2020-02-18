package dao;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import carlot.Car;
import carlot.CarLot;
import daos.CarDao;

public class CarDaoTest {

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
	public void writeCarLot() {//this will not pass everytime because of it's reading/writing files, but if it does sometimes...
		CarLot testCarLot = new CarLot("Test Car Lot");
		Car testCar = new Car("Chevrolet", "Sonic LT", "Red", "1G1YG2DW4D5106591");
		testCarLot.addCar(testCar);
		CarDao carDao = new CarDao();
		carDao.write(testCarLot);
		
		assertEquals("This should create a file with a CarLot object with a Car named carLot.dat", true, (carDao.read() instanceof CarLot));
		
	}

}
