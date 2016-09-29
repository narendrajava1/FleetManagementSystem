package fleet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;


public class TrackVehicle {
	int checking=10;
	String nameToSend="";
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();

	public void displayAllVehiclesCurrentlyInUse(){

		//===========================================DISPLAYING ALL VEHICLES WHICH ARE BOOK AND OUT
		try{
			List vehiclesThatNeedTobeTracked=session.createCriteria(TrackingDetails.class).list();
			Iterator i=vehiclesThatNeedTobeTracked.iterator();
			int  index=1;
			if(!(vehiclesThatNeedTobeTracked.isEmpty()))
			{
				System.out.println("Vehicles Currently booked");
				while(i.hasNext())
				{
					TrackingDetails records=(TrackingDetails)i.next();
					getLocationName (records.getCurrentLocation());
					System.out.println(index+". "+nameToSend+" - "+records.getCarNumber());				
					getLocationName(records.getCurrentLocation());
					index++;
				}
			}else{
				System.out.println("No cars booked at the moment");
			}
			//===========================================DISPLAYING ALL VEHICLES WHICH ARE BOOK AND OUT
		}catch(Exception e){
			if(checking == 1){
				e.printStackTrace();
			}else{
				System.out.println("Error while Registering ! please contact IT-department");
			}
		}
	}

	private void getLocationName(String currentLocation) {

		//===========================================GET LOCATION NAME FROM LAT LONG
		
		nameToSend="";
		//String [] latLongToSplit = currentLocation.split(",");
		try{
			String customURL = "https://maps.googleapis.com/maps/api/geocode/json?address="+currentLocation.replaceAll(" ","" );
			URL oracle = new URL(customURL);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			String word="distance",finalstring="";

			boolean found=false;
			while ((inputLine = in.readLine()) != null) {
				finalstring=finalstring.concat(inputLine);//SAVING DISTANCE VALUE INTO A STRING 

			}
			in.close();
			dirtyMethod(finalstring);
			//===========================================GET LOCATION NAME FROM LAT LONG
		}catch(Exception e){
			//IF SYSTEM NOT CONNECTTED TO INTERNET
			//REACHED MAX FREE LIMIT OF CALLING API
			//e.printStackTrace();
			//System.out.println("Please check your internet connectivity and try again");
			//System.exit(0);
			nameToSend="Parking,Heidelberg";
		}
	}

	private void dirtyMethod(String finalstring) {
		//===========================================PLEASE IGNORE THIS PART
		finalstring = finalstring.replaceAll(" ", "");
		int startSkiping =0 ;
		String [] splitToGetName = finalstring.split("long_name");
		//System.out.println(Arrays.toString(splitToGetName));
		String checkNameForRecursion="";
		for (int i = 0; i < splitToGetName.length; i++) {
			//System.out.println(splitToGetName[i]);
			String [] splitThis = splitToGetName[i].split(",");
			if(i > 0 && i < 10){
				if(checkNameForRecursion.equals(splitThis[0].replaceAll("\"", "").replaceAll(":", "")))
				{
					//DO NOTHING 
				}else{
					if(startSkiping == 0){
						nameToSend= nameToSend.concat(splitThis[0].replaceAll("\"", "").replaceAll(":", ""));
						if(i!= 9){
							nameToSend=nameToSend.concat(",");
						}
						checkNameForRecursion=splitThis[0].replaceAll("\"", "").replaceAll(":", "");
					}	 
				}
				try{
					int checkForPincode =Integer.parseInt(splitThis[0].replaceAll("\"", "").replaceAll(":", "")); 
					if(checkForPincode / 1000 != 0)
						startSkiping = 1;
				}catch(Exception e){
					//CANNOT BE CONVERTED INTO STRING
					
				}
			}	        
		}
		//===========================================PLEASE IGNORE THIS PART
	}
}
