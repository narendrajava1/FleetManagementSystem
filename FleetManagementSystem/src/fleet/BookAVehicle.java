package fleet;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

public class BookAVehicle {

	int checking = 0;
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Main m =new Main ();
	public void allocateCar(int EmpId)
	{

		double finalCalculatedDistance;
		//Employee empObj=getUserDetails(EmpId);//
		
		Main m =new Main();
		//System.out.println(empObj.getEmpID());
		checkPrerequisites(m.empId);
		
		RequestUserValues requestForBookingDetails=new RequestUserValues();
		Booking bookingData=(Booking)requestForBookingDetails.getBookingValues();
		bookingData.setEmpId(m.empId);
		CalculateDistance cal=new CalculateDistance();
		try {
			finalCalculatedDistance=cal.getDistance(bookingData.getDestiantion())* 1.609344;
			finalCalculatedDistance=(int)finalCalculatedDistance;
			bookingData.setDistance(finalCalculatedDistance);
		} 
		catch (Exception e) {
			if(checking ==1)
				e.printStackTrace();
			//error
		}
		
		int empRating=m.empRating;
		String carNumber;
		do{
			try{
				List vehiclesInPool=session.createCriteria(Vehicle.class).add(Restrictions.eq("isOccupied", 0)).add(Restrictions.between("carRating",empRating,5)).addOrder(Property.forName("carRating").asc()).addOrder(Property.forName("carMilage").desc()).list();
				Iterator i=vehiclesInPool.iterator();
				Vehicle records=(Vehicle)i.next();
				carNumber=records.getCarNumber();
				bookingData.setCarNumber(carNumber);
				//System.out.println(carNumber);
				if(carNumber != null)
					break;
			}catch(Exception e){
				//e.printStackTrace();
				empRating--;
			}
		}while(true);
		bookingData.setSource("HEIDELBERG");
		saveBookingDetails(bookingData);
		markVehicleBooked(bookingData.getCarNumber());
		//getBookingId(carNumber);
	}

	private void markVehicleBooked(String carNumber) {
		
		try{
			session.beginTransaction();
			Vehicle vehicleData=(Vehicle)session.get(Vehicle.class, carNumber);
			vehicleData.setIsOccupied(1);
			session.update(vehicleData);
			session.getTransaction().commit();
			System.out.println("Car "+carNumber+" has been allocated succesfully");
		}catch(Exception e){
			if(checking ==1)
				e.printStackTrace();
		}
		
		
	}

	private Employee getUserDetails(int EmpId) {
		
		session.beginTransaction();
		Employee empDetails=(Employee)session.get(Employee.class, EmpId);
		return empDetails;
	}

	private void saveBookingDetails(Booking bookingData) {
	
		session.beginTransaction();
		session.save(bookingData);
		session.getTransaction().commit();
		
	}

	private void checkPrerequisites(int empID) {
		
		ChecksForBooking checkObj=new ChecksForBooking();
		Main m = new Main();
		if(!(checkObj.checkIfEmployeeAlreadyHasABookedCar(empID)))
			m.viewMenu();
		
		if(checkObj.checkIfVehicleIsAvailable())
			m.viewMenu();
		
	}

/*	private void getBookingId(String carNumber) {
		try{
			List bookingObj=session.createCriteria(Booking.class).add(Restrictions.eq("carNumber", carNumber)).add(Restrictions.isNull("actualReturnDate")).list();
			Iterator i=bookingObj.iterator();
			if(!(bookingObj.isEmpty()))
			{
					Booking bookedCar=(Booking)i.next();
					//System.out.println("Your booking id is :"+bookedCar.getBookingId());
			}
			
		}catch(Exception e){

			if(checking ==1)
				e.printStackTrace();
		}
	}
		*/
	public int returnBookingId() {
		int bookingId=0;
		try{
			
			List bookingObj=session.createCriteria(Booking.class).add(Restrictions.eq("empId", m.empId)).add(Restrictions.isNull("actualReturnDate")).list();
			Iterator i=bookingObj.iterator();
			if(!(bookingObj.isEmpty()))
			{
					Booking bookedCar=(Booking)i.next();
					//System.out.println("Your booking id is :"+bookedCar.getBookingId());
					bookingId= bookedCar.getBookingId();
			}
		}catch(Exception e){

			if(checking ==1)
				e.printStackTrace();
		}
		return bookingId;
	}
}
