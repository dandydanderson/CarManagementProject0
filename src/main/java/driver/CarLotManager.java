package driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import carlot.Car;
import carlot.CarLot;
import carlot.Offer;
import daos.CarDao;
import services.UserServices;
import user.Customer;
import user.Employee;
import user.User;
import util.BlockPrinter;

public class CarLotManager {
	
private static Logger log = Logger.getRootLogger();
	
	
	public static void main(String[] args) {
		
		UserServices userService = new UserServices();
		CarDao dao = userService.getUserCarDao();
		
    	//CarLot carLot = userService.getCarLot();     	//this will be the way it's done once all methods are finalized
    	log.info("Beginning of user session for: " + userService.getUser().toString());
    	
    	String input = "N";
    	
		CarLot carLot = new CarLot("David's Deals");
//		Employee bossman  = new Employee("Bossman");
//		userService.setUser(bossman);
//		carLot.addUser(bossman);
		//Customer david = new Customer("David");
	
		//carLot.addUser(david);
//	
//		Car testCar = new Car("Chevrolet", "Sonic LT", "Blue", "ABC123");
//		testCar.setPrice(6000);
//		testCar.populatePayments();
//		
//		Car testCar1 = new Car("Tesla", "Model 3", "White", "2");
//		testCar1.setPrice(40000.0);
//		testCar.populatePayments();
		//testCar.printPayments();
		
//		carLot.addCar(testCar);
//		carLot.addCar(testCar1);
//		
//		
//		List<Car> cars = carLot.getCars();
//		
//		carLot.addOffer(new Offer(david, 5000.0,true), cars.get(0).getVinNum());//change the input parameters to double string
//		carLot.addOffer(new Offer(david, 6500.0, true), cars.get(1).getVinNum());
//		carLot.addOffer(new Offer(david, 5600.0,true), cars.get(0).getVinNum());
//		
//		List<Offer> offers = carLot.getOffers();
//		
//		david.addCar(cars.get(1));
		
//System.out.println(carLot.getOffers().get(0));
		
		BlockPrinter header = new BlockPrinter();
		System.out.println("\n\n");
		System.out.println("Please enter your username (Case Sensitive): ");
		System.out.println("\n\n");
		
		Scanner sc = new Scanner(System.in);
		String un = sc.nextLine();
		
		System.out.println("\n\n");
		System.out.println("Please enter your password (Case Sensitive): ");
		System.out.println("\n\n");
		
		String pw = sc.nextLine();
		
		userService.setUser(userService.getUserCarDao().getUser(un, pw));
		
		
		
		if (userService.getUserCarDao().getUser(un, pw) instanceof User){//already have an account
			System.out.println("User Found!");
			
		}
			
			
		else {//don't have an account, sign up process
				
				System.out.println("\n"+"Seems like you're a new user! We'll get you added to our customer list.");
				
				System.out.println("\n\n");
				System.out.println("Please enter your username (Case Sensitive): ");
				System.out.println("\n\n");
				
				un = sc.nextLine();
				
				System.out.println("\n\n");
				System.out.println("Please enter your password (Case Sensitive): ");
				System.out.println("\n\n");
				
				pw = sc.nextLine();
				
				User u = new User(un, pw, "customer");
				
				dao.addUser(u);
				userService.setUser(u);
				
			}
		
		while(!userService.isFinished()) {
			
			header = new BlockPrinter();
		
			//////////////////////////////
			///Customer Driver Logic
			//////////////////////////////
			
			if(userService.getUser().getType().equals("customer")) {
			
			System.out.println("\n"+"Good to have you back, " + userService.getUser().getName() + "!");
			System.out.println("What can we help you with today (Type the number only)?: "
					+"\n"+"1. Look at the car list."
					+"\n"+"2. Check my offers."
					+"\n"+"3. Make an offer on a car"
					+"\n"+"4. View my owned cars"
					+"\n"+"5. View all payments due"
					+"\n"+"6. Log out"
					
					);
		
			
			input = sc.nextLine();
				
				
			switch (input) {
			
			case "1"://print out the lot list -done
				System.out.println("---------------------------------------------------------------------");
				System.out.println("These are the cars available: ");
				System.out.println("---------------------------------------------------------------------");
				
				userService.printLotList(dao.getAllCars());				
				break;
			case "2"://check that individual users offers -done
				System.out.println("\n" + "These are your offers:");
				userService.printOfferList(dao.getAllMyOffers(userService.getUser().getUserId()));
				break;
			case "3"://add an offer on a car  -done
				
//				ps.setInt(1, offer.getOfferId());//value entered doesn't matter, it's int serial pk
//				ps.setInt(2, offer.getUserId());
//				ps.setString(3, offer.getVinNumber());
//				ps.setDouble(4, offer.getAmount());
//				ps.setBoolean(5, false);
				
				String vinNum;
				Double amount;
				
				System.out.println("\n" + "What is the VIN Number of the car?:");
				vinNum = sc.nextLine();
				
				System.out.println("\n"+"These are the current offers on this car:");

				
				System.out.println("\n" + "What is your offer?");
				
				amount = Double.valueOf(sc.nextLine());
				
				Offer offer = new Offer(userService.getUser().getUserId(), vinNum, amount, false);
				
				dao.addOffer(offer);
				
				
				
				
				
			
				break;
			case "4"://view owned cars -done
				userService.printLotList(dao.getAllMyCars(userService.getUser().getUserId()));
				break;
			case "5"://view all payments due -done
				userService.printPaymentList(dao.getAllMyPayments(userService.getUser().getUserId()));

			break;
			case "6"://logout -done
				
				userService.setFinished(true);
				
				break;
			default:
				System.out.println("That was not a valid input.");
				break;
			}
		    
		}
			
			////////////////////////////////////////
			/////////Employee Driver Logic
			/////////////////////////////////////
			
			
			if(userService.getUser().getType().equals("employee")) {
				
				
				
				System.out.println("What task would you like to complete (Type the number only)?: "
						+"\n"+"1. Look at the car list."
						+"\n"+"2. Check all offers."
						+"\n"+"3. Add a car to the lot"
						+"\n"+"4. Remove a car from the lot."
						+"\n"+"6. View all payments due."
						+"\n"+"7. Accept a vehicle offer."
						+"\n"+"8. Reject a vehicle offer."
						+"\n"+"9. Log out"
						);
	
				
			     input = sc.nextLine();
					
					
				switch (input) {
				
				case "1"://print out the lot list -done
					System.out.println("\n" + "All cars on the lot: ");
					userService.printLotList(dao.getAllCars());		
					break;
				case "2"://check all offers -done
					System.out.println("\n" + "All offers:");
					userService.printOfferList(dao.getAllOffers());
					break;
				case "3"://add a car to the lot public Car(String make, String model, String color, String vinNum)  -done
					String make;
					String model;
					String color;
					String vinNum;
					double price;
					System.out.println("\n" + "What is the make of the car?");
					make = sc.nextLine();
					System.out.println("\n" + "What is the model of the car?");
					model = sc.nextLine();
					System.out.println("\n" + "What is the color of the car?");
					color = sc.nextLine();
					System.out.println("\n" + "What is the vin number of the car?");
					vinNum = sc.nextLine();
					System.out.println("\n" + "What is the list price of the car?");
					price = Double.valueOf(sc.nextLine());
					
					Car newCar = new Car(make, model, color, vinNum, price);
					dao.addCar(newCar);
					
					
					break;
				case "4"://remove a car from the lot -done
					System.out.println("\n" + "What is the vin numer of the car you would like to remove?");
					vinNum = sc.nextLine();
					
					dao.removeCar(vinNum);
					break;
				case "5"://view all offers on a car
					System.out.println("\n" + "What is the vin numer of the car you would like to to see offers on?");
					vinNum = sc.nextLine();
					carLot.printCarOffers(vinNum);
					break;
				case "6"://view all cars and their payment history-done
					
					userService.printPaymentList(dao.getAllPayments());
					
					
					break;
				case "7"://Accept a vehicle offer-done
					
					userService.printOfferList(dao.getAllOffers());
					
					System.out.println("\n" + "What is the offer number you would like to accept?");
					
					int offerid = Integer.valueOf(sc.nextLine());
					
					Car c = dao.getCar(dao.getOffer(offerid).getVinNumber());
					c.setOwner(dao.getOffer(offerid).getUserId());
					dao.acceptOffer(dao.getOffer(offerid));
					dao.populatePayments(c);
					
					
	
					
					 
					break;
				case "8"://Reject a vehicle offer-done
					System.out.println("\n" + "What is the Offer ID you would like to reject?");
					int offerId = Integer.valueOf(sc.nextLine());

				
					dao.rejectOffer(offerId);
					
					break;
				case "9"://logout-done
					
					userService.setFinished(true);
					
					break;
				default:
					System.out.println("That was not a valid input.");
					break;
				}
			
			
			
			}
		 }
		
		
		
	
		//userService.saveCarLot(carLot);//always save any updates at the end
		log.info("End of user session for: " + userService.getUser().toString());
	}
	}



