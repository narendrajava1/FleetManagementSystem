package fleet;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class VehicleRegistration {
	int checking=10;
	SessionFactory input=new Configuration().configure().buildSessionFactory();	
	Session session=input.openSession();
	Scanner userInput=new Scanner(System.in);
	
	public void setVehicleParameters()
	{
		try{
			Vehicle vehicleObj=new Vehicle();
			RequestUserValues request=new RequestUserValues();
			Vehicle registerVehicle=request.getNewValuesOfVehicle(vehicleObj);
			session.beginTransaction();
			session.save(registerVehicle);
			session.getTransaction().commit();
			System.out.println("Successfully Registered the vehicle");
		}catch(Exception e){
			if(checking == 1){
				e.printStackTrace();
			}else{
				System.out.println("Error while Registrating ! please contact IT-department");
			}
		}
		
	}

}
