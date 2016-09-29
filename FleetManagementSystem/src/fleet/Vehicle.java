package fleet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vehicle")
public class Vehicle {
	
	private int carId;
	@Id
	private String carNumber;
	private double carMilage;
	private int carRating;
	private int carCostPrice;
	private Integer carSellingPrice;
	private int isOccupied;
	private int servicingFlag;
	
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public double getCarMilage() {
		return carMilage;
	}
	public void setCarMilage(double carMilage) {
		this.carMilage = carMilage;
	}
	public int getCarRating() {
		return carRating;
	}
	public void setCarRating(int carRating) {
		this.carRating = carRating;
	}
	public int getCarCostPrice() {
		return carCostPrice;
	}
	public void setCarCostPrice(int carCostPrice) {
		this.carCostPrice = carCostPrice;
	}
	@Column(nullable=true)
	public int getCarSellingPrice() {
		return carSellingPrice;
	}
	public void setCarSellingPrice(int carSellingPrice) {
		this.carSellingPrice = carSellingPrice;
	}
	public int getIsOccupied() {
		return isOccupied;
	}
	public void setIsOccupied(int isOccupied) {
		this.isOccupied = isOccupied;
	}
	public int getServicingFlag() {
		return servicingFlag;
	}
	public void setServicingFlag(int servicingFlag) {
		this.servicingFlag = servicingFlag;
	}
	
	
}
