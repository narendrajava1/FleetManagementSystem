package fleet;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class Inspection {
	int checking = 0;
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	
	public void displayVehiclesToBeInspected()
	{
		DisplayingVehicles displayVehicleObj=new DisplayingVehicles();
		MaintenanceDetails selectedVehicle=displayVehicleObj.getListOfVehicles(1);
		if(selectedVehicle!=null)
		{
			viewCarForInspection(selectedVehicle);
		}
		else
		{
			System.out.println("No vehicles to be inspected.");
		}
	}

	private void viewCarForInspection(MaintenanceDetails selectedCarInfo) {
		
		try{
			System.out.println("The details of the vehicle you selected :");
			System.out.println("Booking ID :" + selectedCarInfo.getBookingId());
			System.out.println("Car Number :" + selectedCarInfo.getCarNumber());
			System.out.println("Total Fuel available :" + selectedCarInfo.getFuleConsumed());
			Booking moreDetails=getJourneyDetails(selectedCarInfo.getBookingId());
			System.out.println("Expected Date of Return :"+moreDetails.getExpectedReturnDate().get(Calendar.DATE)+"-"+moreDetails.getExpectedReturnDate().get(Calendar.MONTH)+"-"+moreDetails.getExpectedReturnDate().get(Calendar.YEAR));
			System.out.println("Actual Date of Return :"+moreDetails.getActualReturnDate().get(Calendar.DATE)+"-"+moreDetails.getActualReturnDate().get(Calendar.MONTH)+"-"+moreDetails.getActualReturnDate().get(Calendar.YEAR));
			System.out.println("Distance expected to be travelled :"+(moreDetails.getDistance()*2));
			String car=selectedCarInfo.getCarNumber();
			TasksDuringInspection taskObj=new TasksDuringInspection();
			taskObj.addDataAfterInspection(selectedCarInfo);
			//System.out.println("1");
			taskObj.gradeEmployee(moreDetails);
			taskObj.updateTheVehicleTable(car);
			
		}catch(Exception e){
			if(checking == 1){
				e.printStackTrace();
			}
		}
		
	}

	private Booking getJourneyDetails(int bookingId) {
		
		session.beginTransaction();
		Booking extraInfoOnJourney=(Booking)session.get(Booking.class,bookingId);
		session.getTransaction().commit();
		return extraInfoOnJourney;
	
	}

}
