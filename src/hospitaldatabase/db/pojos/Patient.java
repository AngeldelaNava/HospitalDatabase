package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.util.List;

public class Patient implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private Integer id;
	private String name;
	private String gender;
	private String bloodType;
	private Integer roomNumber;
	private List<Worker> workers;
	private List<Disease> diseases;
	private Appointment appointment;
	public Patient(int id, String name, String gender, String bloodGroup, Integer roomNumber, List<Worker> workers,
			List<Disease> diseases, Appointment appointment) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.bloodType = bloodGroup;
		this.roomNumber = roomNumber;
		this.workers = workers;
		this.diseases = diseases;
		this.appointment = appointment;
	}
	public Patient(int id, String name, String gender, String bloodGroup, List<Worker> workers, List<Disease> diseases,
			Appointment appointment) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.bloodType = bloodGroup;
		this.workers = workers;
		this.diseases = diseases;
		this.appointment = appointment;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public List<Worker> getWorkers() {
		return workers;
	}
	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
	public List<Disease> getDiseases() {
		return diseases;
	}
	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	@Override
	public String toString() {
		String vuelta = "Patient [id=" + id + ", name=" + name + ", gender=" + gender + ", bloodType=" + bloodType
				+ ", roomNumber=" + roomNumber + ", diseases=\n";
		for(int i = 0; i < diseases.size(); i++) {
			vuelta+= diseases.get(i) + "\n";
		}
		vuelta += "]";
		return vuelta;
	}
	
	
}
