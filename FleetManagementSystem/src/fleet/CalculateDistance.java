package fleet;
import java.io.*;
import java.net.*;

public class CalculateDistance {

	//===========================================CLASS VARIABLES - ASSUMING WE HAVE THIS VALUES
	int checking=0;
	double finalDistanceInMiles;
	String nameToVerify;
	//===========================================CLASS VARIABLES - ASSUMING WE HAVE THIS VALUES

	public double getDistance(String city){
		try{
			finalDistanceInMiles=0;
			//===========================================GOOGLE API CALLING 
			String customURL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=HEIDLEBERG&destinations="+city;
			URL oracle = new URL(customURL);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			String word="distance",finalstring="";

			boolean found=false;
			while ((inputLine = in.readLine()) != null) {
				finalstring=finalstring.concat(inputLine);//SAVING DISTANCE VALUE INTO A STRING 
				if(found == false)
					found=inputLine.contains(word);
			}
			in.close();
			//===========================================GOOGLE API CALLING 
			finalDistanceInMiles=Double.parseDouble(dirtyMethod(finalstring));
			getLocationNameToVerify(finalstring);
			
			
		
		}catch(Exception e){
			if(checking == 1)
				e.printStackTrace();
		}
		return finalDistanceInMiles;
	}   

	public String dirtyMethod( String dirtyString){

		//===========================================PLEASE IGNORE THIS PART
		dirtyString=dirtyString.replaceAll("\\s+", "");
		String[] parts =dirtyString.split("distance");
		String impval=parts[1];
		impval=impval.replaceAll("\\\"", "");
		impval=impval.replaceAll(":\\{", "");
		parts =impval.split("mi,");
		impval=parts[0];
		//System.out.println(impval);
		impval=impval.replaceAll("text:", "");
		
		impval=impval.replaceAll("mi", "");
		impval=impval.replace(",", "");
		
		
		//===========================================PLEASE IGNORE THIS PART

		return impval;
		
	}

	public void getLocationNameToVerify(String dirtyString){
		
		dirtyString=dirtyString.replaceAll("\\s+", "");
		String[] parts=dirtyString.split("\\],");
		String impValue=parts[0];
		parts = impValue.split(":\\[");
		impValue=parts[1];
		nameToVerify=impValue;
		//System.out.println("val="+impValue);
	}

}
