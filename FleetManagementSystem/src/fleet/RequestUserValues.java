package fleet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class RequestUserValues {
	
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Scanner userInput=new Scanner(System.in);
	Main m = new Main();
	public Vehicle getNewValuesOfVehicle(Vehicle vehicleObj) {
		try{
			System.out.println("Enter the following data to register the vehicle : ");
			//System.out.print("1. Car ID : ");
			//vehicleObj.setCarId(userInput.nextInt());
			System.out.print("Car Number : ");
			userInput=new Scanner(System.in);
			String car;
			if(checkIfCarAlreadyExists(car=userInput.nextLine().toUpperCase().trim().replaceAll(" ", ""))){
				vehicleObj.setCarNumber(car);
			}
			else{
				System.out.println("Car already present in the database");
				m.viewMenu();
			}
			System.out.print("Milage of Car :");
			vehicleObj.setCarMilage(userInput.nextDouble());
			vehicleObj.setCarRating(3);
			vehicleObj.setServicingFlag(1);
			System.out.print("CostPrice of Car :");
			vehicleObj.setCarCostPrice(userInput.nextInt());
			
			
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("Error while inputing the values please try again");
			m.viewMenu();
		}
		return vehicleObj;
	}
	
	public int getSellingPrice()
	{
		System.out.println("Enter the selling price of the vehicle : ");
		int vehicleSellingPrice=userInput.nextInt();
		return vehicleSellingPrice;
	}

	public Booking getBookingValues()
	{
		String destination,dateOfTravel,intededReturn;
		String confirmName;
		int count=0;
		Booking bookingDetails=new Booking();
		CalculateDistance cal=new CalculateDistance();
		//===========================================DEMAND FOR VALUE
		ChecksForBooking checkingObj=new ChecksForBooking();
		//destination
		do{
			do{
				if(count >0)
					System.out.println("TRY AGAIN!! Please enter Destination. Alternatly you can also add pincode along with country  eg: 69123,germany");				 
				else
					System.out.println("Please enter Destination");
				destination=userInput.nextLine();
				destination=destination.replaceAll(" ", "");
				
				//System.out.println(destination);
				try {
					cal.getDistance(destination);
				} catch (Exception e) {
					//e.printStackTrace();
				}
				count++;
				if(count == 5){
					System.out.println("You have succesfully logout");
					System.exit(0);
				}
				if(cal.finalDistanceInMiles != 0)
					break;
			}while(true);

			System.out.println("Do you mean "+cal.nameToVerify+" ?");
			System.out.println("Press yes to continue or no to enter destination again.");
			confirmName=userInput.nextLine();
			
			bookingDetails.setDestiantion(cal.nameToVerify.replaceAll("\"", "").toUpperCase());
			confirmName=confirmName.toLowerCase();
			if(confirmName.contentEquals("yes"))
				break;
		}while(true);


		//date of travel
		count=0;
		do{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d=new Date();
			if(count > 0){
				System.out.println("TRY AGAIN!!Date should not a past date");
			}else{
				System.out.println("Please enter Intended date of travel" );
			}
			if(count == 5){
				System.out.println("You have succesfully logout");
				System.exit(0);
			}
			dateOfTravel=userInput.nextLine();
			dateOfTravel=dateOfTravel.toLowerCase();		
			
			Date intendedDateOfTravel=new Date();
			Calendar calDate=Calendar.getInstance();
			calDate.setTime(intendedDateOfTravel);
			bookingDetails.setBookingDate(calDate);
			if(dateOfTravel.contentEquals("today"))
				dateOfTravel=df.format(d);
			else if (dateOfTravel.contentEquals("tomorrow")){
				Calendar calendar = Calendar.getInstance();
				Date today = calendar.getTime();
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				//Date tomorrow =calendar.getTime();
				dateOfTravel=df.format(today);
			}else {
				try
				{
				intendedDateOfTravel=df.parse(dateOfTravel);
				}
				catch(ParseException ex)
				{
					//ex.printStackTrace();
					System.out.println("Please enter a proper date");
				}
			}
			count++;
		}while(checkingObj.checkIfLaterThanToday(dateOfTravel));


		//date of return
		count=0;
		do{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d=new Date();
			if(count > 0){
				System.out.println("TRY AGAIN!!Add a date which is later than the Date of Booking");
			}else{
				System.out.println("Please enter Intended date of return" );
			}
			if(count == 5){
				System.out.println("You have succesfully logout");
				System.exit(0);
			}
			intededReturn=userInput.nextLine();
			intededReturn=intededReturn.toLowerCase();
			Date intendedDateOfReturn=new Date();
			Calendar calDate=Calendar.getInstance();
			calDate.setTime(intendedDateOfReturn);
			bookingDetails.setExpectedReturnDate(calDate);
			if(intededReturn.contentEquals("today"))
				intededReturn=df.format(d);
			else if (intededReturn.contentEquals("tomorrow")){
				Calendar calendar = Calendar.getInstance();
				Date today = calendar.getTime();
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				//Date tomorrow =calendar.getTime();
				intededReturn=df.format(today);
			}else{
				try
				{
				intendedDateOfReturn=df.parse(intededReturn);
				}
				catch(ParseException ex)
				{
					//ex.printStackTrace();
					System.out.println("Please enter a proper date");
				}
			}
			count++;
		}while(checkingObj.compareDate(dateOfTravel,intededReturn));

		return bookingDetails;
		//===========================================DEMAND FOR VALUE
	}

	public LoginDetails getLoginInputDetails(){
		LoginDetails loginDetails = new LoginDetails();
		CheckLoginInformation check =new CheckLoginInformation();
		String username="",password="";
		int count= 1;
		//===========================================LOGIN IN 
		do{
			if(count< 5){				
				if(count != 1)
					System.out.println("===========LOGIN "+ (5-count) +" ATTEMPTS LEFT===========");
				else
					System.out.println("==================LOGIN====================");
				System.out.println();
				System.out.println("Please enter Username");
				username = userInput.nextLine();
				System.out.println("Please enter Password");
				password = userInput.nextLine();
				System.out.println();
				System.out.println("===========================================");
			}
			else{
				System.out.println("Please Contact customer service!!");
				System.exit(0);
			}	
			count++;
		}while(check.checkAuthentication(username, password));
		//===========================================LOGIN IN 
		return loginDetails;
	}

	private boolean checkIfCarAlreadyExists(String carNumber){
		
		Criteria cr = session.createCriteria(Vehicle.class);
		cr.add(Restrictions.eq("carNumber", carNumber));
		List results = cr.list();
		
		if(results.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
}
