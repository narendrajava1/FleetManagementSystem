package fleet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	private int EmpID;
	private String EmpName;
	private int EmpRating;
	private int EmpTickets;
	
	@Id
	public int getEmpID() {
		return EmpID;
	}
	public void setEmpID(int empID) {
		EmpID = empID;
	}
	public String getEmpName() {
		return EmpName;
	}
	public void setEmpName(String empName) {
		EmpName = empName;
	}
	public int getEmpRating() {
		return EmpRating;
	}
	public void setEmpRating(int empRating) {
		EmpRating = empRating;
	}
	public int getEmpTickets() {
		return EmpTickets;
	}
	public void setEmpTickets(int empTickets) {
		EmpTickets = empTickets;
	}
	
	
}
