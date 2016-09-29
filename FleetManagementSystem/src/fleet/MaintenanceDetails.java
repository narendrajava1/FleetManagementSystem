package fleet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="maintenence")
public class MaintenanceDetails {
	
	private String carNumber;
	private int currentKmReading;
	@Id
	private int bookingId;
	private int milage;
	private int fuleConsumed;
	private int fulePresent;
	private int sateOfCar;
	private String comments;
	private int isCompleted;
	private int costsForRepairOrServicing;
	
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public int getCurrentKmReading() {
		return currentKmReading;
	}
	public void setCurrentKmReading(int currentKmReading) {
		this.currentKmReading = currentKmReading;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getMilage() {
		return milage;
	}
	public void setMilage(int milage) {
		this.milage = milage;
	}
	public int getFuleConsumed() {
		return fuleConsumed;
	}
	public void setFuleConsumed(int fuleConsumed) {
		this.fuleConsumed = fuleConsumed;
	}
	public int getFulePresent() {
		return fulePresent;
	}
	public void setFulePresent(int fulePresent) {
		this.fulePresent = fulePresent;
	}
	public int getSateOfCar() {
		return sateOfCar;
	}
	public void setSateOfCar(int sateOfCar) {
		this.sateOfCar = sateOfCar;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(int isCompleted) {
		this.isCompleted = isCompleted;
	}
	@Column(nullable=true)
	public int getCostsForRepairOrServicing() {
		return costsForRepairOrServicing;
	}
	
	public void setCostsForRepairOrServicing(int costsForRepairOrServicing) {
		this.costsForRepairOrServicing = costsForRepairOrServicing;
	}
	
		
}

