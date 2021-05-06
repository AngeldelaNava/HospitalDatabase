package hospitaldatabase.db.ifaces;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import hospitaldatabase.db.pojos.*;

public interface HospitalDBManager {

	public void connect();
	public void disconnect();
	
	public void addWorker(Worker w);
	public Worker getWorker(int id);
	public List<Worker> searchWorkerByName(String name);
	public void setWorker(Worker w, int id);
	public void deleteWorker(int id);
	
	public void addContract(Contract c);
	public Contract getContract(int id);
	
	public void addPatient(Patient p);
	public Patient getPatient(int id);
	public List<Patient> searchPatientByName(String name);
	public List<Patient> checkListOfPatients();
	public void setPatient(Patient p, int id);
	public void deletePatient(int id);
	
	public void addAppointment(Appointment a);
	public Appointment getAppointment(int id);
	public List<Appointment> searchAppointmentByDateAndTime(Date date, Time time);
	public List<Appointment> searchAppointmentByDate(Date date);
	public List<Appointment> searchAppointmentByType(String type);
	public void setAppointment(Appointment a, int id);
	public void deleteAppointment(int id);
	
	public String getProject(int workerId);
	public void setProject(String project, int id);
	public void deleteProject(int workerId);
	
	public void addDisease(Disease d);
	public Disease getDisease(int id);
	public List<Disease> searchDiseaseByName(String name);
	public List<Disease> searchDiseaseByPatient(int patientId);
	public void setDisease(Disease d, int id);
	public void deleteDisease(int id);
	
	public void relationPatientWorker(int patientId, int workerId);
	public void relationDiseaseWorker(int diseaseId, int workerId);
	public void relationAppointentWorker(int appointmentId, int workerId);
	public void relationPatientDisease(int patientId, int diseaseId);
	public void relationWorkerContract(int workerId, int contractId);
	public void relationPatientAppointment(int patientId, int appointmentId);
}
