package fleet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tracking")
public class TrackingDetails {
	
	@Id
	private String carNumber;
	private String currentLocation;
	private int isOccupied;
	private int bookingId;
	
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public int getIsOccupied() {
		return isOccupied;
	}
	public void setIsOccupied(int isOccupied) {
		this.isOccupied = isOccupied;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

}
