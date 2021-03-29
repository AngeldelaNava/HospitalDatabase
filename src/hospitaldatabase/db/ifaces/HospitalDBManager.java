package hospitaldatabase.db.ifaces;

import java.util.List;

import hospitaldatabase.db.pojos.*;

public interface HospitalDBManager {

	public void connect();
	public void disconnect();
	
	public void addWorker(Worker w);
	public Worker getWorker(int id);
	public List<Worker> searchWorkerByName(String name);
	
	public void addContract(Contract c);
	public Contract getContract(int id);
	
	public void addPatient(Patient p);
	public Patient getPatient(int id);
	public List<Patient> searchPatientByName(String name);
	
	public void addAppointment(Appointment a);
	public Appointment getAppointment(int id);
}
