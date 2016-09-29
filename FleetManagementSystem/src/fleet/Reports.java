package fleet;

import java.sql.Connection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Reports {

	ConnectionReport conObj=new ConnectionReport();

	Connection conn=conObj.getJDBCConnection();
	
	public void getInventoryReport(){
		try{     
			JasperReport jasperReport = JasperCompileManager.compileReport("InventoryDetails.jrxml");      

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),conn);      

			JasperExportManager.exportReportToPdfFile(jasperPrint, "Inventory.pdf");

			JasperViewer.viewReport(jasperPrint);
			System.out.println("PDF has been generated!");

		}

		catch (JRException e){

			e.printStackTrace();

		}
	}
	
	public void getEmployeeDetails(){
		try{     
			JasperReport jasperReport = JasperCompileManager.compileReport("EmployeeDetails.jrxml");      

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),conn);      

			JasperExportManager.exportReportToPdfFile(jasperPrint, "Employee.pdf");

			JasperViewer.viewReport(jasperPrint);
			System.out.println("PDF has been generated!");

		}

		catch (JRException e){

			e.printStackTrace();

		}
		
	}
	
	public void getStatusOfVehicles(){
		try{     
			JasperReport jasperReport = JasperCompileManager.compileReport("statusOfCars.jrxml");      

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),conn);      

			JasperExportManager.exportReportToPdfFile(jasperPrint, "Car_Status.pdf");

			JasperViewer.viewReport(jasperPrint);
			System.out.println("PDF has been generated!");

		}

		catch (JRException e){

			e.printStackTrace();

		}
		
	}
	
	
}
