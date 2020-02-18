package carlot;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import finance.Payment;

public class Car implements Serializable{
	
	private double price;
	private String make;
	private String model;
	private String color;
	private String vinNum;//could eventually check arguments on this
	private List<Offer> offers = new ArrayList<Offer>(); //this will be customer name and either offer amount or an "offer" object depending on necessary complexity
	private List<Payment> payments = new ArrayList<Payment>();
	
	public Car(String make, String model, String color, String vinNum) {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
		this.vinNum = vinNum;
	}
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List offers) {
		this.offers = offers;
	}
	
	public void addOffer(Offer offer) {
		offer.setCar(this);
		offers.add(offer);
	}
	
	public void removeOffer(Offer offer) {
		offers.remove(offer);
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVinNum() {
		return vinNum;
	}
	public void setVinNum(String vinNum) {
		this.vinNum = vinNum;
	}
	public void printCarOffers() {
		for(int i = 0;i<offers.size();i++) {
			System.out.println(offers.get(i).toString());
		}
	}
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void populatePayments() {
		
		for(int i = 0; i<60;i++) {
			Payment payment = new Payment();
			payment.setDueDate(LocalDate.now().plusMonths(i));
			payment.setAmount(price/60.0);
			payments.add(payment);
		}
	}
	
	public void printPayments() {
		for(int i = 0;i<payments.size();i++) {
			System.out.println(payments.get(i).toString());
		}
	}
	
	
	@Override
	public String toString() {
		return vinNum + " " + color + " " + make + " " + model ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vinNum == null) ? 0 : vinNum.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {//this method checks the vin only, as that should be unique to every car and will be how we search for them
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (vinNum == null) {
			if (other.vinNum != null)
				return false;
		} else if (!vinNum.equals(other.vinNum))
			return false;
		return true;
	}
	

}
