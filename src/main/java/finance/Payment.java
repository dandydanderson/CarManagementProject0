package finance;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Payment implements Serializable{
	
	private LocalDate dueDate;
	private double amount;
	private boolean paid;
	
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate localDate) {
		this.dueDate = localDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	@Override
	public String toString() {
		return "Payment [dueDate=" + dueDate + ", amount=" + amount + ", paid=" + paid + "]";
	}
	
	

}
