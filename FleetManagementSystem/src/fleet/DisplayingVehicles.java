package fleet;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class DisplayingVehicles {
	int checking=10;
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	
	public MaintenanceDetails getListOfVehicles(int stateOfVehicle)
	{
		try{
			List vehiclesThatNeedToBeListed=session.createCriteria(MaintenanceDetails.class).add(Restrictions.eq("sateOfCar", stateOfVehicle)).add(Restrictions.eqOrIsNull("isCompleted", 0)).list();
			Iterator i=vehiclesThatNeedToBeListed.iterator();
			int  index=1;
			int selectedCar;
			if(!(vehiclesThatNeedToBeListed.isEmpty()))
			{
				if(stateOfVehicle<=3)
				{
					System.out.println("The vehicles that have to be Checked : ");
				}
				else
				{
					System.out.println("The vehicles that have to be sold : ");
				}
				while(i.hasNext())
				{
					MaintenanceDetails records=(MaintenanceDetails)i.next();
					System.out.println(index+". "+records.getBookingId()+" - "+records.getCarNumber());
					index++;
				}
				if(stateOfVehicle<=3)
				{
					System.out.println("Which vehicle will you like to check first? ");
				}
				else
				{
					System.out.println("Which vehicle will you like to sell first? ");
				}
				selectedCar=userInput.nextInt();
				return (MaintenanceDetails)vehiclesThatNeedToBeListed.get(selectedCar-1);
			}
		}catch(Exception e){
			if(checking == 1)
				e.printStackTrace();
			else
				System.out.println("Some error at the system");
		}
		
		
		return (MaintenanceDetails)null;
		
		
	}
}
