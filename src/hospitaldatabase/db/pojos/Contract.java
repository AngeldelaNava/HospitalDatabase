package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.sql.Date;

public class Contract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private Integer id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
