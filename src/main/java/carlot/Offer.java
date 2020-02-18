package carlot;

import java.io.Serializable;

import user.Customer;

public class Offer implements Serializable{

	private String offerId;
	private Customer owner;
	private double amount;
	private boolean active;
	private Car car;
	
	
	public Offer(Customer owner, double amount, boolean active) {
		super();
		this.owner = owner;
		this.amount = amount;
		this.active = active;
	}
	
	@Override
	public String toString() {
		return  "Offer ID: " + offerId + " " + car.toString() + " Customer: " + owner.getName() + " Offer Amount: " + amount;
	}

	
	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Customer getOwner() {
		return owner;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	public double getAmount() {
		return amount;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
	
	
}
