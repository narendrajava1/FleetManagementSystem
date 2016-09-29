package fleet;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class TakeCar {


	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	Main m=new Main();
	public void getAllocatedVehicle()
	{
		try{
			TrackingDetails trackVehicle=new TrackingDetails();
			BookAVehicle getInfo =new BookAVehicle();
			int bookingIDOfemp= getInfo.returnBookingId();
			if(getCarNumber(bookingIDOfemp)!= null){
				System.out.println("Are you sure you want to take the vehicle("+getCarNumber(bookingIDOfemp) +")?");
				char response=userInput.next().charAt(0);
				if(response=='y' || response=='Y')
				{
					String carNumber= getCarNumber(bookingIDOfemp);
					trackVehicle.setBookingId(bookingIDOfemp);
					trackVehicle.setCarNumber(carNumber);
					trackVehicle.setCurrentLocation(" ");
					trackVehicle.setIsOccupied(1);
					session.beginTransaction();
					session.save(trackVehicle);
					session.getTransaction().commit();
					System.out.println("Have a safe journey!");
				}else{
					System.out.println("Try again please");
					m.viewMenu();
				}
			}else{
				System.out.println("No cars to take");
			}
			}catch(javax.persistence.PersistenceException exceptionIfNameAlreadyThere){
				System.out.println("The car has already been taken");
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("System is behaving abnormally! please try again later");
			}
	}

	public String getCarNumber(int bookingId) {
		
		Booking bookingData=(Booking)session.get(Booking.class, bookingId);
		if(bookingData!=null)
		{
			return bookingData.getCarNumber();
		}
		else
		{
			return null;
		}
		
	}
	private boolean checkIfThisIsTheCarByUser(int bookingId){
		
		Criteria cr = session.createCriteria(Booking.class);
		cr.add(Restrictions.eq("empId", m.empId));
		cr.add(Restrictions.eq("bookingId", bookingId));
		//cr.add(Restrictions.eq("actualReturnDate",null));
		cr.add(Restrictions.isNull("actualReturnDate"));
		List results = cr.list();
		
		if(results.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
}
