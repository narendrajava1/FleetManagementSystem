package fleet;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class Main {

	public static int empId=0,userType=0,empRating=0;
	static Logger logger =Logger.getLogger("Main.java");
	public static void main(String[] args) {	
		
		PropertyConfigurator.configure("log4j.properties");
		login();
		viewMenu();
	}

	public static void viewMenu() {
		Scanner userInput=new Scanner(System.in);
		int option;
		do{
			int count=1;
			System.out.println();
			System.out.println("-------------------------------------------");
			System.out.println("What do you want to do?");
			if(userType == 1){
				System.out.println(count+". Register Vehicle");
				count++;
				System.out.println(count+". Sell a Vehicle");
				count++;
				System.out.println(count+". Track Vehicles on road");
				count++;
				System.out.println(count+". View Inventory Details");
				count++;
				System.out.println(count+". View Employee Details");
				count++;
				System.out.println(count+". View Status of Vehicles");
				count++;
				System.out.println(count+". Log-out");
			}else if(userType == 2){
				System.out.println(count+". Inspect a vehicle");
				count++;
				System.out.println(count+". Maintenance");
				count++;
				System.out.println(count+". Servicing");
				count++;
				System.out.println(count+". Log-out");
			}else if(userType == 3){
				System.out.println(count+". Book a Vehicle");
				count++;
				System.out.println(count+". Take assigned Car");
				count++;
				System.out.println(count+". Return a Vehicle");
				count++;
				System.out.println(count+". Log-out");
			}
			
			
			try{
				option=userInput.nextInt();
				redirect(option);
				
				System.out.println("-------------------------------------------");

			}catch (Exception e){
				System.out.println("Plz try again");
				viewMenu();
			}
			
			
		}while(true);
	
	}

	private static void redirect(int option) {
		switch(option)
		{
		case 1: if(userType == 1){
					RegisterVehicle();
				}else if(userType == 2) {
					InspectVehicle();
				}else if(userType == 3) {
					BookVehicle();
				}
				break;
		case 2: if(userType == 1){
					SellAVehicle();
				}else if(userType == 2) {
					MaintainingVehicle();
				}else if(userType == 3) {
					getAssignedCar();
				}
				break;
		case 3: if(userType == 1){
					trackVehicle();
				}else if(userType == 2) {
					ServicingVehicle();
				}else if(userType == 3) {
					ReturnVehicle();
				}
				break;
		case 4:if(userType == 1){
					getInventoryDetails();
				}else{
					System.out.println("Logged out succesfully"); 
					System.out.println("-------------------------------------------");
					System.out.println("");
					login();
				}
		break;
			
		case 5:	if(userType == 1){
					getEmployeeDetails();
					
				}else{
			System.out.println("please try again"); 
			viewMenu(); 
				}
		break;
		case 6:if(userType == 1){
			getStatusOfVehicle();
				}else{
					System.out.println("please try again"); 
					viewMenu(); 

				}
		break;

		case 7:if(userType == 1){
				System.out.println("Logged out succesfully"); 
				System.out.println("-------------------------------------------");
				System.out.println("");
				login();
				}else{
					System.out.println("please try again"); 
					viewMenu(); 

				}
				//System.exit(0); 
				break;
		default:System.out.println("Please try again");
			viewMenu(); 
				break;
		}
		
	}

	private static void trackVehicle() {
		TrackVehicle trackingVehicle=new TrackVehicle();
		trackingVehicle.displayAllVehiclesCurrentlyInUse();
		
	}

	private static void getAssignedCar() {
		TakeCar takingCarObj=new TakeCar();
		takingCarObj.getAllocatedVehicle();
		
	}

	private static void BookVehicle() {
		BookAVehicle vehicleToBeBooked=new BookAVehicle();
		vehicleToBeBooked.allocateCar(empId);
	}

	private static void SellAVehicle() {
		
		SellVehicle vehicleToBeSold=new SellVehicle();
		vehicleToBeSold.checkVehicle();
		
	}

	private static void RegisterVehicle() {
		VehicleRegistration newVehicle=new VehicleRegistration();
		newVehicle.setVehicleParameters();
		
	}

	private static void ServicingVehicle() {
		Servicing vehicleToBeServiced=new Servicing();
		vehicleToBeServiced.displayVehiclesToBeServiced();
	}

	private static void MaintainingVehicle() {
		Maintenance vehicleToBeMaintained=new Maintenance();
		vehicleToBeMaintained.displayVehiclesToBeRepaired();
		
	}

	private static void InspectVehicle() {
		
		Inspection vehicleToBeInspected=new Inspection();
		vehicleToBeInspected.displayVehiclesToBeInspected();
	}

	private static void ReturnVehicle() {
		
		ReturnVehicle vehicleToBeReturned=new ReturnVehicle();
		vehicleToBeReturned.acceptInfo();
	}
	
	public static void login(){
		
		RequestUserValues requestUserValue = new RequestUserValues ();
		requestUserValue.getLoginInputDetails();
	}
	
	private static void getInventoryDetails() {
		
		Reports rep=new Reports();
		rep.getInventoryReport();
	}
	
	private static void getEmployeeDetails() {
		
		Reports rep=new Reports();
		rep.getEmployeeDetails();
	}
	
	private static void getStatusOfVehicle() {
		
		Reports rep=new Reports();
		rep.getStatusOfVehicles();
	}
}
