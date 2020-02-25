package carlot;

import java.io.Serializable;

import user.Customer;
import user.User;

public class Offer implements Serializable{

	private int offerId;
	private int userId;
	private String vinNumber;
	private double amount;
	private boolean active;
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getVinNumber() {
		return vinNumber;
	}
	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", userId=" + userId + ", vinNumber=" + vinNumber + ", amount=" + amount
				+ ", active=" + active + "]";
	}
	public Offer(int userId, String vinNumber, double amount, boolean active) {
		super();
		this.userId = userId;
		this.vinNumber = vinNumber;
		this.amount = amount;
		this.active = active;
	}
	public Offer() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
}
