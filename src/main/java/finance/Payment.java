package finance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Payment implements Serializable{
	
	private int paymentId;
	private String vinNumber;
	private int owner;
	private String dueDate;
	private double amount;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getVinNumber() {
		return vinNumber;
	}
	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", owner=" + owner + ", dueDate="
				+ dueDate + ", vinNumber=" + vinNumber + ", amount=" + amount + "]";
	}
	public Payment() {
		super();
	}
	
	

}
