package fleet;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Maintenance {

	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	
	public void displayVehiclesToBeRepaired()
	{
		DisplayingVehicles displayVehicleObj=new DisplayingVehicles();
		MaintenanceDetails selectedVehicle=displayVehicleObj.getListOfVehicles(2);
		if(selectedVehicle!=null)
		{
			viewCarForRepair(selectedVehicle);
			fixCar(selectedVehicle);
		}
		else
		{
			System.out.println("No vehicles to be repaired.");
		}
	}

	private void fixCar(MaintenanceDetails selectedVehicle) {
		try
		{

			Vehicle vehicleObj=session.get(Vehicle.class,selectedVehicle.getCarNumber());
			selectedVehicle.setComments("");
			selectedVehicle.setIsCompleted(1);
			vehicleObj.setIsOccupied(0);
			System.out.println("How much did fixing the vehicle cost?");
			int costsForRepair=selectedVehicle.getCostsForRepairOrServicing()+userInput.nextInt();
			selectedVehicle.setCostsForRepairOrServicing(costsForRepair);
			session.beginTransaction();
			session.update(selectedVehicle);
			session.update(vehicleObj);
			session.getTransaction().commit();
			System.out.println("Success");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	private void viewCarForRepair(MaintenanceDetails selectedVehicle) {
		
		System.out.println(selectedVehicle.getCarNumber()+" - "+selectedVehicle.getComments());
	}
	
	
}