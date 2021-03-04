package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.util.List;

public class Disease implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private int id;
	private String diseaseName;
	private String prescripition;
	private List<Worker> workers;
	private List<Patient> patients;
	public Disease(int id, String diseaseName, String prescripition, List<Worker> workers, List<Patient> patients) {
		super();
		this.id = id;
		this.diseaseName = diseaseName;
		this.prescripition = prescripition;
		this.workers = workers;
		this.patients = patients;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getPrescripition() {
		return prescripition;
	}
	public void setPrescripition(String prescripition) {
		this.prescripition = prescripition;
	}
	public List<Worker> getWorkers() {
		return workers;
	}
	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	@Override
	public String toString() {
		return "Disease [id=" + id + ", diseaseName=" + diseaseName + ", prescripition=" + prescripition + ", workers="
				+ workers + ", patients=" + patients + "]";
	}
	
	
}
