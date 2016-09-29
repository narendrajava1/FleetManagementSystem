package fleet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;



public class ChecksForBooking {
	int checking=10;
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	
	public boolean checkIfLaterThanToday(String dateOfTravel)
	{
		Date d=new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String todaysDate=df.format(d);
		try {
			Date UserDate = df.parse(dateOfTravel);
			Date TodaysDate =df.parse(todaysDate);
			if(UserDate.after(TodaysDate) || UserDate.equals(TodaysDate)){
				return false;
			}else{
				return true;
			}

		} catch (ParseException e) {
			//e.printStackTrace();
			System.out.println("Please enter date in formate of yyyy-MM-dd");
		}
		return true;
		//===========================================DATE CHECK CONDITION (todays date and the date entered)
	}

	public boolean compareDate(String dateOfTravel, String intededReturn) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date UserDateTravel = df.parse(dateOfTravel);
			Date UserDateReturn =df.parse(intededReturn);
			if(UserDateTravel.before(UserDateReturn) || UserDateTravel.equals(UserDateReturn)){
				return false;
			}else{
				return true;
			}

		} catch (ParseException e) {
			//e.printStackTrace();
			System.out.println("Please enter date in format of yyyy-MM-dd");
		}
		return true;
	}

	public boolean checkIfEmployeeAlreadyHasABookedCar(int empID) {

		try{
			//Booking bookingobj = new Booking();
			Criteria cr = session.createCriteria(Booking.class);
			cr.add(Restrictions.eq("empId", empID));
			//cr.add(Restrictions.eq("actualReturnDate",null));
			cr.add(Restrictions.isNull("actualReturnDate"));
			List results = cr.list();
			
			if(results.isEmpty()){
				//System.out.println(results);
				return true;
			}else{
				System.out.println("You have not yet returned your previous car");
				return false;
			}
			
			
		}catch(Exception e){
			if(checking == 1)
				e.printStackTrace();
			else
				System.out.println("Some error in the system");
			//System.out.println("true");
			return false;
			
		}	
	}
	
	public boolean checkIfVehicleIsAvailable()
	{
		
		try{
			Criteria cr = session.createCriteria(Vehicle.class);
			cr.add(Restrictions.eq("isOccupied", 0));
			List results = cr.list();
			//System.out.println("Total Salary: "  );
	        if(results.isEmpty()){
	        	System.out.println("No cars in parking lot at the moment");
				return true;
			}else{				
				return false;
			}
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("Some error in the system");
			return true;
		}
	}
}
