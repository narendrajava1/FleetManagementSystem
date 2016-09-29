package fleet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class ReturnVehicle {
	
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	Main m=new Main();
	public void acceptInfo()
	{	
		TakeCar forcarnumber = new TakeCar();
		BookAVehicle getInfo =new BookAVehicle();
		int bookingIDOfemp= getInfo.returnBookingId();
		if(forcarnumber.getCarNumber(bookingIDOfemp) != null){
			System.out.println("Are you sure you want to return the last car ("+forcarnumber.getCarNumber(bookingIDOfemp) +") that you haved booked?");
			char response=userInput.next().charAt(0);
			if(response=='y' || response=='Y')
			{
				retrieveBookingData(bookingIDOfemp);
				System.out.println("Success!!");
			}else{
				System.out.println("Try again please");
				m.viewMenu();
			}
		}else{
			System.out.println("No cars to be returned");
		}
	}
	
	private void retrieveBookingData(int bookingId) {
		session.beginTransaction();
		Booking retrievedBookingData=(Booking)session.get(Booking.class,bookingId);
		setActualReturnDate(retrievedBookingData);		
	}

	private void fillQuestionnaire(Booking retrievedBookingData) {
		try{
			System.out.print("Enter the amount of fuel filled during the journey (in litres) : ");
			int amountOfFuelFilled=userInput.nextInt();
			//assuming first full tank =100 litres
			int fuelConsumed=100+amountOfFuelFilled;
			System.out.print("Rate your experience with the vehicle assigned to you (1 :best - 5: worst) : ");
			int vehicleRating=userInput.nextInt();
			TasksAtVehicleReturnTime vehicleDet=new TasksAtVehicleReturnTime();
			vehicleDet.updateRecordsInDatabase(retrievedBookingData, fuelConsumed);
			vehicleDet.setVehicleRatingUsingFeedback(retrievedBookingData.getCarNumber(),vehicleRating);
		}catch(Exception e){
			System.out.println("Please try again");
		}
		
	}

	private void setActualReturnDate(Booking retrievedBookingData) {
		if(!(retrievedBookingData == null))
		{
			retrievedBookingData.setActualReturnDate((Calendar.getInstance()));
			session.update(retrievedBookingData);
			session.getTransaction().commit();
			fillQuestionnaire(retrievedBookingData);
		}
		else
		{
			System.out.println("The booking id you entered is invalid.");
		}

	}
	
	private boolean checkIfthisbookingWasMadeByThisEmployee(int bookingId){
		
		Criteria cr = session.createCriteria(Booking.class);
		cr.add(Restrictions.eq("empId", m.empId));
		cr.add(Restrictions.eq("bookingId", bookingId));
		//cr.add(Restrictions.eq("actualReturnDate",null));
		cr.add(Restrictions.isNull("actualReturnDate"));
		List results = cr.list();
		
		if(results.isEmpty()){
			//System.out.println("no");
			return false;
		}else{
			//System.out.println("yes");
			return true;
		}
	}

}
