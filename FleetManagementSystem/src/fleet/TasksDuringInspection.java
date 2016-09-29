package fleet;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;




public class TasksDuringInspection {

	int checking =11;
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	static int totalDistanceTravelled;
	public void addDataAfterInspection(MaintenanceDetails selectedCarInfo)
	{
		try{
			MaintenanceDetails setDetails=(MaintenanceDetails)session.get(MaintenanceDetails.class, selectedCarInfo.getBookingId());
			System.out.print("Enter the amount of fuel present in the vehicle :");
			int fuelPresent=userInput.nextInt();
			setDetails.setFulePresent(fuelPresent);
			setDetails.setFuleConsumed((selectedCarInfo.getFuleConsumed()-fuelPresent));
			System.out.print("Enter the current Km reading of the vehicle :");
			int kmReading=userInput.nextInt();
			totalDistanceTravelled=kmReading - selectedCarInfo.getCurrentKmReading();
			int mileage=totalDistanceTravelled/setDetails.getFuleConsumed();
			setDetails.setMilage(mileage);
			setDetails.setCurrentKmReading(kmReading);
			checkIfAnyChangesToBeMade(setDetails);
			session.beginTransaction();
			session.update(setDetails);
			session.getTransaction().commit();
			//session.close();
		}catch(Exception e){
			if(checking ==1)
				e.printStackTrace();
			else
				System.out.println("System error ! Please contact IT-department");		}
		
	}

	private void checkIfAnyChangesToBeMade(MaintenanceDetails setDetails) {
		//checking for servicing
		try
		{
			Vehicle vehicleObj=session.get(Vehicle.class, setDetails.getCarNumber());
			System.out.print("Any thing to be noted? (y/n) :");
			char response=userInput.next().charAt(0);
			if(setDetails.getCurrentKmReading()>50000)
			{
				String comments=" ";
				setDetails.setComments(comments);
				setDetails.setSateOfCar(4);
				setDetails.setIsCompleted(0);
				//vehicleObj.setIsOccupied(0);
				}
			else if(setDetails.getCurrentKmReading()>vehicleObj.getServicingFlag()*5000)
			{
				String comments=" ";
				setDetails.setComments(comments);
				setDetails.setSateOfCar(3);
				setDetails.setIsCompleted(0);
				}
			else
			{
				//checking if any repair or damage
				if(response=='y' || response=='Y')
				{
					String comments= getCommentsOnChanging();
					setDetails.setComments(comments);
					setDetails.setSateOfCar(2);
					setDetails.setIsCompleted(0);
					}
				else
				{
					String comments=" ";
					setDetails.setComments(comments);
					setDetails.setSateOfCar(1);
					setDetails.setIsCompleted(1);
					vehicleObj.setIsOccupied(0);
				}
			}
			session.beginTransaction();
			session.update(vehicleObj);
			session.getTransaction().commit();
			//session.close();
		}
		catch(Exception ex)
		{
			if(checking ==1)
				ex.printStackTrace();
			else
				System.out.println("System error ! Please contact IT-department");
		}
	}
	
	private String getCommentsOnChanging() {
		
		System.out.println("What are the changes to be made? ");
		userInput=new Scanner(System.in);
		String comments=userInput.nextLine();
		return comments;
	}
	
	public void gradeEmployee(Booking bookingDetails)
	{
		try
		{
			System.out.println("Answer the following questions so the employee is graded :");
			int counter=0;
			List loginObj= session.createCriteria(LoginDetails.class).add(Restrictions.eq("id",bookingDetails.getEmpId())).list();
			Iterator i=loginObj.iterator();
			LoginDetails loggedUser=(LoginDetails)i.next();
			Employee empDetail=(Employee)session.get(Employee.class,bookingDetails.getEmpId());
			int chkDates=bookingDetails.getExpectedReturnDate().compareTo(bookingDetails.getActualReturnDate());
			if(chkDates<0)
			{
				counter+=1;
			}
			System.out.println("Any violations for "+empDetail.getEmpName()+" with Employee id : "+empDetail.getEmpID()+"?");
			char response=userInput.next().charAt(0);
			int newTicketCount;
			if(response=='y' || response=='Y')
			{
				counter+=1;
				newTicketCount=empDetail.getEmpTickets()+1;
				empDetail.setEmpTickets(newTicketCount);
				}
			double roundTrip=bookingDetails.getDistance()*2;
			double distanceAllowed=(roundTrip)+(0.1*roundTrip);
			if(totalDistanceTravelled > distanceAllowed)
			{
				counter+=1;
			}
			int oldRating=empDetail.getEmpRating();;
			int newRating;
			if(counter==0)
			{
				newRating=(1+oldRating)/2;
				empDetail.setEmpRating(newRating);
				loggedUser.setUserRating(newRating);
			}
			else if(counter==1)
			{
				newRating=(2+oldRating)/2;
				empDetail.setEmpRating(newRating);
				loggedUser.setUserRating(newRating);
			}
			else if(counter==2)
			{
				newRating=(3+oldRating)/2;
				empDetail.setEmpRating(newRating);
				loggedUser.setUserRating(newRating);
			}
			else
			{
				newRating=(4+oldRating)/2;
				empDetail.setEmpRating(newRating);
				loggedUser.setUserRating(newRating);
			}
			
			session.beginTransaction();
			session.update(empDetail);
			session.update(loggedUser);
			session.getTransaction().commit();
			
			
			System.out.println("Employee has been graded");
		}
		catch(Exception ex)
		{
			if(checking == 1)
				ex.printStackTrace();
			else
				System.out.println("Some error in the system");
		}
		
	}

	public void updateTheVehicleTable(String carNumber){
		try{
			Vehicle vehicleToUpdate = new Vehicle();
			//vehicleToUpdate.setIsOccupied(0);
			vehicleToUpdate=(Vehicle)session.get(Vehicle.class,carNumber);
			
			session.beginTransaction();
			session.update(vehicleToUpdate);
			session.getTransaction().commit();
		}catch (Exception e){
			if(checking ==1 )
				e.printStackTrace();
		}
			
	}
}