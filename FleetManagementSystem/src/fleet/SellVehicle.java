package fleet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SellVehicle {
	
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	
	public void checkVehicle()
	{
		DisplayingVehicles displayVehicleObj=new DisplayingVehicles();
		MaintenanceDetails selectedVehicle=displayVehicleObj.getListOfVehicles(4);
		if(selectedVehicle!=null)
		{
			viewCarToBeSold(selectedVehicle);
		}
		else
		{
			System.out.println("No vehicles to be sold.");
		}
	}

	private void viewCarToBeSold(MaintenanceDetails selectedVehicle) {
		try
		{
			System.out.println("The details of the vehicle you selected :");
			Vehicle vehicleDetails=getCarDetails(selectedVehicle.getCarNumber());
			System.out.println("Car Number : "+ vehicleDetails.getCarNumber());
			System.out.println("Car mileage : "+vehicleDetails.getCarMilage());
			System.out.println("The car was rated : "+vehicleDetails.getCarRating());
			System.out.println("The car was initially bought at "+vehicleDetails.getCarCostPrice()+ " Euros. ");
			RequestUserValues requestingUserInfo=new RequestUserValues();
			int sellingPrice = requestingUserInfo.getSellingPrice();
			vehicleDetails.setIsOccupied(0);
			saveSellingPrice(vehicleDetails,sellingPrice);
			selectedVehicle.setIsCompleted(2);
			if(session.isOpen())
			{
				session.beginTransaction();
				session.update(selectedVehicle);
				session.getTransaction().commit();
				System.out.println("Succesfully sold the vehicle");
			}
			else
			{
				System.out.println("problem");
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	private void saveSellingPrice(Vehicle vehicleDetails, int sellingPrice) {
		
		try
		{
			//session.beginTransaction();
			vehicleDetails.setCarSellingPrice(sellingPrice);
			vehicleDetails.setIsOccupied(2);
			session.update(vehicleDetails);
			session.getTransaction().commit();
			//session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private Vehicle getCarDetails(String carNumber) {
		
		session.beginTransaction();
		Vehicle vehicleDetails=(Vehicle)session.get(Vehicle.class, carNumber);
		return vehicleDetails;

	}
}
