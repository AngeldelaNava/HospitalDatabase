package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Appointment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private Integer id;
	private String type;
	private String intervention;
	private Date dateAndTimeStart;
	private int duration;
	private boolean success;
	private Patient patient;
	private List<Worker> workers;
	public Appointment(int id, String type, String intervention, Date dateAndTimeStart, int duration, boolean success,
			Patient patient, List<Worker> workers) {
		super();
		this.id = id;
		this.type = type;
		this.intervention = intervention;
		this.dateAndTimeStart = dateAndTimeStart;
		this.duration = duration;
		this.success = success;
		this.patient = patient;
		this.workers = workers;
	}
	public Appointment(int id, String type, Date dateAndTimeStart, Patient patient, List<Worker> workers) {
		super();
		this.id = id;
		this.type = type;
		this.dateAndTimeStart = dateAndTimeStart;
		this.patient = patient;
		this.workers = workers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIntervention() {
		return intervention;
	}
	public void setIntervention(String intervention) {
		this.intervention = intervention;
	}
	public Date getDateAndTimeStart() {
		return dateAndTimeStart;
	}
	public void setDateAndTimeStart(Date dateAndTimeStart) {
		this.dateAndTimeStart = dateAndTimeStart;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<Worker> getWorkers() {
		return workers;
	}
	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", type=" + type + ", intervention=" + intervention + ", dateAndTimeStart="
				+ dateAndTimeStart + ", duration=" + duration + ", success=" + success + ", patient=" + patient
				+ ", workers=" + workers + "]";
	}
	
	
}
