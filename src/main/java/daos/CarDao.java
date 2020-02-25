package daos;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import carlot.Car;
import carlot.CarLot;
import carlot.Offer;
import finance.Payment;
import user.User;
import util.ConnectionFactory;




public class CarDao implements Dao {
	
	private static Logger log = Logger.getRootLogger();
	
	
	//Connectivity
	
	private static String url = "jdbc:postgresql://localhost:5432/CarLot";
	private static String username = "postgres";
	private static String password = "admin";
	
	

	@Override
	public void addCar(Car car) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO cars values(?,?,?,?,?,?)");//vin_number,make,model,color,price,owner
			
			ps.setString(1, car.getVinNum());
			ps.setString(2, car.getMake());
			ps.setString(3, car.getModel());
			ps.setString(4, car.getColor());
			ps.setDouble(5, car.getPrice());
			ps.setInt(6, 1);//any car added to the lot is initially owned by the lot.
			
			ps.execute();
			
			log.info("Car added to the lot: " + car.toString());
					
		}catch (SQLException e) {
			e.printStackTrace();
			log.warn("The car was not added to the lot.");
		
		}
		
	}

	@Override
	public void addOffer(Offer offer) {
		
		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO offers(user_id, vin_number, offer_amount, active) values(?,?,?,?)");//offer_id, user_id, vin_number, amount, accepted
			
			ps.setInt(1, offer.getUserId());
			ps.setString(2, offer.getVinNumber());
			ps.setDouble(3, offer.getAmount());
			ps.setBoolean(4, true);
			
			ps.execute();
			
			log.info("Offer added: " + offer.toString());
					
		}catch (SQLException e) {
			e.printStackTrace();
			log.warn("The car was not added to the lot.");
		
		}
				
	}

	@Override
	public void addUser(User user) {

		try (Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users(username,password, user_type) values(?,?,?)");//user_id, username, password, user_type
			
			ps.setString(1, user.getName());//value entered doesn't matter, it's int serial pk
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getType());
			
			
			ps.execute();
			
			log.info("User added: " + user.toString());
					
		}catch (SQLException e) {
			e.printStackTrace();
			log.warn("A new user was not able to be added");
		
		}
		
	}

	@Override
	public Car getCar(String vinNumber) {	
		
	Car c = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Select * from cars where (vin_number = ?)");
			ps.setString(1, vinNumber);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				c = new Car();
				c.setVinNum(rs.getString(1));
				c.setMake(rs.getString(2));
				c.setModel(rs.getString(3));
				c.setColor(rs.getString(4));
				c.setPrice(rs.getDouble(5));
				c.setOwner(rs.getInt(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public List<Car> getAllCars() {
		
		List<Car> carList = new ArrayList<Car>();
		Car c = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Select * from cars where owner = 1");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				c = new Car();
				c.setVinNum(rs.getString(1));
				c.setMake(rs.getString(2));
				c.setModel(rs.getString(3));
				c.setColor(rs.getString(4));
				c.setPrice(rs.getDouble(5));
				c.setOwner(rs.getInt(6));
				
				carList.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carList;
	}

	@Override
	public Offer getOffer(int offerid) {//need
		
		Offer o = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Select * from offers where (offer_id = ?)");
			ps.setInt(1, offerid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				o = new Offer();
				o.setOfferId(rs.getInt(1));
				o.setUserId(rs.getInt(2));
				o.setVinNumber(rs.getString(3));
				o.setAmount(rs.getDouble(4));
				o.setActive(rs.getBoolean(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.warn("Unable to find that offer.");
		}
		
		return o;
		
	}

	@Override
	public List<Offer> getAllOffers() {

	Offer o = null;
	List<Offer> offerList = new ArrayList<Offer>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Select * from offers order by active desc");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				o = new Offer();
				o.setOfferId(rs.getInt(1));
				o.setUserId(rs.getInt(2));
				o.setVinNumber(rs.getString(3));
				o.setAmount(rs.getDouble(4));
				o.setActive(rs.getBoolean(5));
				
				offerList.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return offerList;
	}

	@Override
	public User getUser(String username, String password) {
		
		User u = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Select * from users where (username = ? and password = ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				u = new User();
				u.setUserId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setType(rs.getString(4));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public List<User> getAllUsers() {//skip for now
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rejectOffer(int offerId) {

		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("delete from offers where offer_id = ?");
			ps.setInt(1, offerId);
			ps.executeUpdate();
			
			log.info("Offer Rejected");
			
		} catch (SQLException e) {
			log.warn("Unable to remove an offer from the list");
		}
		
	}

	@Override
	public void acceptOffer(Offer offer) {

		try(Connection conn = ConnectionFactory.getConnection()){//this changes the car ownership from the lot to the user, and sets the price
			
			PreparedStatement ps = conn.prepareStatement("update cars set owner = ?, price = ? where vin_number = ?" );
			ps.setInt(1, offer.getUserId());
			ps.setDouble(2, offer.getAmount());
			ps.setString(3, offer.getVinNumber());
			
			ps.executeUpdate();
			
			log.info("Offer accepted, ownership transferred to user#: "+offer.getUserId() );
				
		} catch (SQLException e) {
			log.warn("Unable to accept offer at this time.");
			e.printStackTrace();
		}
		
		
		try(Connection conn = ConnectionFactory.getConnection()){//this sets all other offers of the same vin to false
			
			PreparedStatement ps = conn.prepareStatement("update offers set active = false where vin_number = ?");
			ps.setString(1, offer.getVinNumber());
			ps.executeUpdate();
			
			log.info("All other offers removed from that car");
			
		} catch (SQLException e) {
			log.warn("Unable reject all offers on this car at this time.");
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeCar(String vinNumber) {


		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("delete from cars where vin_number = ?");
			ps.setString(1, vinNumber);
			ps.executeUpdate();
			
			log.info("Car removed from the lot: " + vinNumber);
			
		} catch (SQLException e) {
			log.warn("Unable to remove car from the lot");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Offer> getAllMyOffers(int userId) {
		Offer o = null;
		List<Offer> offerList = new ArrayList<Offer>();
			
			try(Connection conn = ConnectionFactory.getConnection()){
				
				PreparedStatement ps = conn.prepareStatement("Select * from offers where user_id = ?");
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					o = new Offer();
					o.setOfferId(rs.getInt(1));
					o.setUserId(rs.getInt(2));
					o.setVinNumber(rs.getString(3));
					o.setAmount(rs.getDouble(4));
					o.setActive(rs.getBoolean(5));
					
					offerList.add(o);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return offerList;
		
	}

	@Override
	public List<Car> getAllMyCars(int userId) {
		Car c = null;
		List<Car> carList = new ArrayList<Car>();
			
			try(Connection conn = ConnectionFactory.getConnection()){
				
				PreparedStatement ps = conn.prepareStatement("Select * from cars where owner = ?");
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					c = new Car();
					
					c.setVinNum(rs.getString(1));
					c.setMake(rs.getString(2));
					c.setModel(rs.getString(3));
					c.setColor(rs.getString(4));
					c.setPrice(rs.getDouble(5));
					c.setOwner(rs.getInt(6));
					
					carList.add(c);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return carList;
		
	}

	@Override
	public void populatePayments(Car car) {

		try (Connection conn = ConnectionFactory.getConnection()){
			
			LocalDate today = LocalDate.now();
			
			for(int i = 0;i<60;i++) {
				
			PreparedStatement ps = conn.prepareStatement("INSERT INTO payments(vin_number,user_id, due_date, price) values(?,?,?,?)");//vin_number, user_id, date, amount
			
			ps.setString(1, car.getVinNum());//value entered doesn't matter, it's int serial pk
			ps.setInt(2, car.getOwner());
			ps.setString(3, today.plusMonths(i).toString());
			ps.setDouble(4, car.getPrice()/60);
			
						
			ps.execute();
			
			}
					
		}catch (SQLException e) {
			e.printStackTrace();
			log.warn("Could not populate payments");
		
		}
			
		
		
	}

	@Override
	public List<Payment> getAllMyPayments(int userId) {
		Payment p = null;
		List<Payment> paymentList = new ArrayList<Payment>();
			
			try(Connection conn = ConnectionFactory.getConnection()){
				
				PreparedStatement ps = conn.prepareStatement("Select * from payments where user_id = ? order by due_date");
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					p = new Payment();
					
					p.setPaymentId(rs.getInt(1));
					p.setVinNumber(rs.getString(2));
					p.setOwner(rs.getInt(3));
					p.setDueDate(rs.getString(4));
					p.setAmount(rs.getDouble(5));
					
					paymentList.add(p);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
		}
			
			return paymentList;
	}

	@Override
	public List<Payment> getAllPayments() {
		Payment p = null;
		List<Payment> paymentList = new ArrayList<Payment>();
			
			try(Connection conn = ConnectionFactory.getConnection()){
				
				PreparedStatement ps = conn.prepareStatement("Select * from payments order by due_date, user_id");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next() && paymentList.size()<100) {
					p = new Payment();
					
					p.setPaymentId(rs.getInt(1));
					p.setVinNumber(rs.getString(2));
					p.setOwner(rs.getInt(3));
					p.setDueDate(rs.getString(4));
					p.setAmount(rs.getDouble(5));
					
					paymentList.add(p);
				}
				
				
			} catch (SQLException e) {
				log.warn("Unable to retrieve payments from the database");
				e.printStackTrace();
		}
			
			return paymentList;
	}

	@Override
	public void sundaySundaySunday() {//takes 20% of the price of cars on the lot
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			CallableStatement cs = conn.prepareCall("call sundaySundaySunday()");
			cs.execute();
			log.info("Sale began.");
			
		}catch(SQLException e) {
			e.printStackTrace();
			log.warn("The sale was not able to be started");
		}
		
	}

	@Override
	public void mondayMondayMonday() {//returns the cars to their original price
	
		try(Connection conn = ConnectionFactory.getConnection()){
			
			CallableStatement cs = conn.prepareCall("call mondayMondayMonday()");
			cs.execute();
			log.info("Sale ended");
			
		}catch(SQLException e) {
			e.printStackTrace();
			log.warn("The sale was not able to be removed.");
		}
		
		
		
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	ALL DEPRECATED FROM SERIALIZATION
//	
//	public void write(Object carLot) {
//
//		String filename;
//		filename = "carLot" + ".dat";
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		
//		try {
//			fos = new FileOutputStream(filename);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(carLot);
//		}catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				oos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				fos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
//
//	public CarLot read() {
//		
//		String filename = "carLot" + ".dat";
//		CarLot carLot = null;
//		try (FileInputStream fis = new FileInputStream(filename);
//		ObjectInputStream ois = new ObjectInputStream(fis);){
//			carLot = (CarLot) ois.readObject();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return carLot;
//	}


}
