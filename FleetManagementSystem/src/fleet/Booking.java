package fleet;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Booking")
public class Booking {
	@Id
	private int bookingId;
	private int empId;
	private String source;
	private String destiantion;
	private double distance;
	private Calendar bookingDate;
	private Calendar expectedReturnDate;
	private String carNumber;
	private Calendar actualReturnDate;
	
	@Column(name="bookingId")
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	@Column(name="empId")
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	@Column(name="source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@Column(name="destiantion")
	public String getDestiantion() {
		return destiantion;
	}
	public void setDestiantion(String destination) {
		this.destiantion = destination;
	}
	
	@Column(name="distance")
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	@Column(name="bookingDate")
	public Calendar getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Calendar bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	@Column(name="expectedReturnDate")
	public Calendar getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(Calendar expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	
	@Column(name="carNumber")
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNo) {
		this.carNumber = carNo;
	}
	
	@Column(name="actualReturnDate")
	public Calendar getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(Calendar actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	
	

}


