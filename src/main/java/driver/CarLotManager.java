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

public class CarLotManager {
	
private static Logger log = Logger.getRootLogger();
	
	
	public static void main(String[] args) {
		
		UserServices userService = new UserServices();
    	CarLot carLot = userService.getCarLot();     	//this will be the way it's done once all methods are finalized
    	log.info("Beginning of user session for: " + userService.getUser().toString());
    	
//		CarLot carLot = new CarLot("David's Deals");
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
		System.out.println("Hello, Welcome to " + carLot.getCarLotName());
		System.out.println("\n\n");
		System.out.println("Please enter your username (Case Sensitive): ");
		System.out.println("\n\n");
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		if (carLot.checkUserListForUser(input)){//already have an account

			userService.setUser(carLot.getUser(input));
		}
			
			
		else {//don't have an account, sign up process
				
				System.out.println("\n"+"Seems like you're a new user! We'll get you added to our customer list.");
				userService.setUser(new Customer(input));
				carLot.addUser(userService.getUser());
				
				log.info("New User added: " + input);
			}
		
		while(!userService.isFinished()) {
		
			//////////////////////////////
			///Customer Driver Logic
			//////////////////////////////
			
			if(userService.getUser() instanceof Customer) {
			
			System.out.println("\n"+"Good to have you back, " + userService.getUser().getName() + "!");
			System.out.println("What can we help you with today (Type the number only)?: "
					+"\n"+"1. Look at the car list."
					+"\n"+"2. Check my offers."
					+"\n"+"3. Make an offer on a car"
					+"\n"+"4. View my owned cars"
					+"\n"+"5. View my payments due on a car"
					);
		
			
			input = sc.nextLine();
				
				
			switch (input) {
			
			case "1"://print out the lot list
				carLot.printLotList();				
				break;
			case "2"://check that individual users offers
				System.out.println("\n" + "These are your offers:");
				carLot.printMyOffers((Customer)userService.getUser());
				break;
			case "3"://add an offer on a car //public Offer(Customer owner, double amount, boolean active) {
				String vinNum;
				Double amount;
				System.out.println("\n" + "What is the VIN Number of the car?:");
				vinNum = sc.nextLine();
				System.out.println("\n"+"These are the current offers on this car:");
				carLot.printCarOffers(vinNum);
				System.out.println("\n" + "What is your offer?");
				amount = Double.valueOf(sc.nextLine());
				carLot.addOffer(new Offer((Customer) userService.getUser(), amount, true), vinNum);
			
				break;
			case "4"://view owned cars
				((Customer) userService.getUser()).printOwnedCars();
				break;
			case "5":
				String customer = userService.getUser().getName();
				((Customer) carLot.getUser(customer)).printOwnedCars();
				System.out.println("\n" + "Enter the VIN that you'd like to check payments on.");
				vinNum = sc.nextLine();
				
				carLot.getCustomerPayments(customer, vinNum);
				break;
			default:
				System.out.println("That was not a valid input.");
				break;
			}
			
			System.out.println("\n"+"Can we help you with anything else today? Y/N"+"\n");
			
		    input = sc.nextLine();
		    
			}
			
			////////////////////////////////////////
			/////////Employee Driver Logic
			/////////////////////////////////////
			
			
			if(userService.getUser() instanceof Employee) {
				
				
				
				System.out.println("What task would you like to complete (Type the number only)?: "
						+"\n"+"1. Look at the car list."
						+"\n"+"2. Check all offers."
						+"\n"+"3. Add a car to the lot"
						+"\n"+"4. Remove a car from the lot."
						+"\n"+"5. View all offers on a car."
						+"\n"+"6. View all cars and their payment history."
						+"\n"+"7. Accept a vehicle offer."
						+"\n"+"8. Reject a vehicle offer."
						+"\n"+"9. Check payments on a car."
						);
	
				
				input = sc.nextLine();
					
					
				switch (input) {
				
				case "1"://print out the lot list
					System.out.println("\n" + "All cars on the lot: ");
					carLot.printLotList();				
					break;
				case "2"://check all offers
					System.out.println("\n" + "All offers:");
					carLot.printOfferList();
					break;
				case "3"://add a car to the lot public Car(String make, String model, String color, String vinNum) 
					String make;
					String model;
					String color;
					String vinNum;
					System.out.println("\n" + "What is the make of the car?");
					make = sc.nextLine();
					System.out.println("\n" + "What is the model of the car?");
					model = sc.nextLine();
					System.out.println("\n" + "What is the color of the car?");
					color = sc.nextLine();
					System.out.println("\n" + "What is the vin numer of the car?");
					vinNum = sc.nextLine();
					
					Car newCar = new Car(make, model, color, vinNum);
					carLot.addCar(newCar);
					
					log.info("Car added to the lot: " + vinNum);
					System.out.println("\n" + "New car added to the lot: "+"\n"+newCar.toString());
					
					break;
				case "4"://remove a car from the lot
					System.out.println("\n" + "What is the vin numer of the car you would like to remove?");
					vinNum = sc.nextLine();
					carLot.removeCar(vinNum);
					log.info("Car Removed: " + vinNum);
				case "5"://view all offers on a car
					System.out.println("\n" + "What is the vin numer of the car you would like to to see offers on?");
					vinNum = sc.nextLine();
					carLot.printCarOffers(vinNum);
					break;
				case "6"://view all cars and their payment history
					
					break;
				case "7"://Accept a vehicle offer
					System.out.println("\n" + "What is the vin numer of the car you would like to accept an offer on?");
					vinNum = sc.nextLine();
					System.out.println("\n" + "What is the offer number for that car?");
					 String offerNum = sc.nextLine();
						log.info("Vehicle offer accepted on: " + vinNum);
			
					//add the car to customer car list
					 Offer offer = carLot.getAcceptedOffer(vinNum,offerNum);
					 System.out.println(offer.toString());
					 Car tempCar = offer.getCar();
					 tempCar.setPrice(offer.getAmount());
					 tempCar.populatePayments();
					 tempCar.printPayments();
					 offer.setCar(tempCar);
					 carLot.addCarToCustomer(offer.getCar(), offer.getOwner());
					 carLot.removeCar(vinNum);
					
					 
					break;
				case "8"://Reject a vehicle offer
					System.out.println("\n" + "What is the vin numer of the car you would like to reject an offer on?");
					vinNum = sc.nextLine();
					System.out.println("\n" + "What is the offer number for that car?");
					offerNum = sc.nextLine();
				
					carLot.rejectOffer(vinNum, offerNum);
					
					log.info("Offer rejected on : " + vinNum);
					
					System.out.println("Remaining offers after removal: ");
					carLot.printOfferList();
					break;
				case "9"://check Payments on a customers car
					carLot.printUserList();
					System.out.println("\n" + "Which customer would you like to check vehicle payments on?");
					String customer = sc.nextLine();
					
					((Customer) carLot.getUser(customer)).printOwnedCars();
					
					System.out.println("\n" + "Which car would you like to check payments on?");
					vinNum = sc.nextLine();
					carLot.getCustomerPayments(customer, vinNum);
					break;
				default:
					System.out.println("That was not a valid input.");
					break;
				}
			
			
				
				
				System.out.println("\n"+"Any more tasks to complete?: Y/N"+"\n");
				
			    input = sc.nextLine();
			}
		    
			//////////////////////////////
			///Program end logic
			//////////////////////////
			
		    if(!input.equals("Y")) {
		    	userService.setFinished(true);
		    	
		    	System.out.println("\n" + "Have a wonderful day!");
		    }
		    else {}
			
			
		}
		
		
		
	
		userService.saveCarLot(carLot);//always save any updates at the end
		log.info("End of user session for: " + userService.getUser().toString());
	}
	}



