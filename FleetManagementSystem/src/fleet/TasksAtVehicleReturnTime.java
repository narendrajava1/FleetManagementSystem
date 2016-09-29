package fleet;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class TasksAtVehicleReturnTime {

	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	
	public void updateRecordsInDatabase(Booking bookingData,int fuelConsumed)
	{
		MaintenanceDetails maintenanceDataObj=new MaintenanceDetails();
		maintenanceDataObj.setBookingId(bookingData.getBookingId());
		maintenanceDataObj.setCarNumber(bookingData.getCarNumber());
		maintenanceDataObj.setFuleConsumed(fuelConsumed);
		maintenanceDataObj.setSateOfCar(1);
		maintenanceDataObj.setComments("");
		session.beginTransaction();	
		try
		{
			session.save(maintenanceDataObj);
			session.getTransaction().commit();
			deleteFromVehiclesBeingUsed(maintenanceDataObj.getCarNumber());
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
		}
	}
	
	private void deleteFromVehiclesBeingUsed(String carNumber) {
		
		TrackingDetails trackingObj=new TrackingDetails();
		session.beginTransaction();
		trackingObj.setCarNumber(carNumber);
		session.delete(trackingObj);
		session.getTransaction().commit();
		
	}

	public void setVehicleRatingUsingFeedback(String carNo,int vehicleRating){
		
		session.beginTransaction();
		Vehicle vehicleDataObj=(Vehicle)session.get(Vehicle.class, carNo);
		int finalRating=(vehicleRating+vehicleDataObj.getCarRating()/2);
		vehicleDataObj.setCarRating(finalRating);
		//vehicleDataObj.setIsOccupied(0);
		/*vehicleDataObj.setCarSellingPrice(88);*/
		session.update(vehicleDataObj);
		session.getTransaction().commit();
	}
	
}
