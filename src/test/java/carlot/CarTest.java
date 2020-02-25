package carlot;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import finance.Payment;

public class CarTest {

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

//	public void populatePayments() {
//		
//		for(int i = 0; i<60;i++) {
//			Payment payment = new Payment();
//			payment.setDueDate(LocalDate.now().plusMonths(i));
//			payment.setAmount(price/60.0);
//			payments.add(payment);
//		}
//	}
//	@Test
//	public void testPopulatePayments() {
//		
//		Car car = new Car("Chevrolet", "Sonic", "Red", "REDSONIC", 12000);
//		car.setPrice(6000);
//		car.populatePayments();
//		
//		assertEquals("This should populate the payments list in the car- ", false, car.getPayments().isEmpty());
//		
//	}
//	
//	
//	public void printPayments() {
//		for(int i = 0;i<payments.size();i++) {
//			System.out.println(payments.get(i).toString());
//		}
//	}
	

}
