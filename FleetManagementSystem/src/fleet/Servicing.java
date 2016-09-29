package fleet;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Servicing {
	
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	
	public void displayVehiclesToBeServiced()
	{
		DisplayingVehicles displayVehicleObj=new DisplayingVehicles();
		MaintenanceDetails selectedVehicle=displayVehicleObj.getListOfVehicles(3);
		if(selectedVehicle!=null)
		{
			viewCarForServicing(selectedVehicle);
			serviceCar(selectedVehicle);
		}
		else
		{
			System.out.println("No cars to be serviced.");
		}			
	}

	private void serviceCar(MaintenanceDetails selectedVehicle) {
		try
		{
			Vehicle vehicleObj=session.get(Vehicle.class,selectedVehicle.getCarNumber());
			selectedVehicle.setComments("");
			int newServicingFlag=vehicleObj.getServicingFlag()+1;
			vehicleObj.setServicingFlag(newServicingFlag);
			selectedVehicle.setIsCompleted(1);
			vehicleObj.setIsOccupied(0);
			System.out.println("How much did servicing cost?");
			int costsForServicing=selectedVehicle.getCostsForRepairOrServicing()+userInput.nextInt();
			selectedVehicle.setCostsForRepairOrServicing(costsForServicing);
			session.beginTransaction();
			session.update(selectedVehicle);
			session.update(vehicleObj);
			session.getTransaction().commit();
			System.out.println("Success");
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
		}
	}

	private void viewCarForServicing(MaintenanceDetails selectedVehicle) {
		
		System.out.println("The details of the vehicle to be serviced :");
		System.out.println("Booking ID :" + selectedVehicle.getBookingId());
		System.out.println("Car Number :" + selectedVehicle.getCarNumber());
	}
}