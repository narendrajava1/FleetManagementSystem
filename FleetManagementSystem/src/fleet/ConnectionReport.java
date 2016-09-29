package fleet;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionReport {

	public Connection getJDBCConnection() {
		try

		{

		Class.forName("com.mysql.jdbc.Driver");

		    String url="jdbc:mysql://localhost:3306/fleetmanagementsystem";

		    String uname="root";

		    String pword="";

		    Connection conn= DriverManager.getConnection(url,uname, pword);

		    return conn;

		}

		catch(Exception ex)

		{

		System.out.println("Check Connection");

		return null;

		}
	}

}
