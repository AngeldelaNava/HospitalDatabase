package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Contract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private int id;
	private int salary;
	private Date hireDate;
	private Date dateOfEnd;
	private Worker worker;
	public Contract(int id, int salary, Date hireDate, Date dateOfEnd, Worker worker) {
		super();
		this.id = id;
		this.salary = salary;
		this.hireDate = hireDate;
		this.dateOfEnd = dateOfEnd;
		this.worker = worker;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getDateOfEnd() {
		return dateOfEnd;
	}
	public void setDateOfEnd(Date dateOfEnd) {
		this.dateOfEnd = dateOfEnd;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	@Override
	public String toString() {
		return "Contract [id=" + id + ", salary=" + salary + ", hireDate=" + hireDate + ", dateOfEnd=" + dateOfEnd
				+ ", worker=" + worker + "]";
	}
	
	
}
