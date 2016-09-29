package fleet;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class CheckLoginInformation {
	
	SessionFactory sessionObj=new Configuration().configure().buildSessionFactory();
	Session session=sessionObj.openSession();
	Main m =new Main();
	Employee empObj= new Employee ();
	public boolean checkAuthentication(String username , String password){
		
		try{
			List authentication=session.createCriteria(LoginDetails.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("password",password)).list();
			Iterator i=authentication.iterator();
			LoginDetails records=(LoginDetails)i.next();
			int usertype=records.getUsertype();
			double userRating = records.getUserRating();
			int id=records.getId();
			//System.out.println(usertype);
			//empObj.setEmpID(id);
			m.empId=id;
			m.userType=usertype;
			m.empRating=(int)userRating;
			System.out.println("Successfully logged into the system");	
			System.out.println("===========================================");
			

			return false;
		}catch(NoSuchElementException e){
			System.out.println("Invalid username or password");
			return true;
		}catch(Exception e){
			System.out.println("Something is wrong with the system");
			return true;
		}		
	}
}
