package fleet;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class sampleReport {
	
	static Logger logger =Logger.getLogger("sampleReport.java");
	public static void main(String[] args)
	{
		PropertyConfigurator.configure("log4j.properties");
		String src="sampl4.jrxml";
		System.out.println("Compiling Report Design ...");
	      try {
	          /**
	          * Compile the report to a file name same as
	          * the JRXML file name
	          */
	         JasperCompileManager.compileReportToFile(src);
	      }
	      catch(JRException ex)
	      {
	    	  ex.printStackTrace();
	      }
	      System.out.println("Done compiling!!! ...");
	}

}
