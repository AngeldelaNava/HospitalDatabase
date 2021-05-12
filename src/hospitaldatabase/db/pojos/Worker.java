package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.util.List;

public class Worker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private Integer id;
	private String name;
	private String type;
	private String job;
	private String disease;
	private String externCompany;
	private String project;
	private Contract contract;
	private List<Appointment> appointments;
	private List<Patient> patients;
	private List<Disease> diseases;
	public Worker(int id, String name, String type, String jobInTheHospital, String diseaseThatInvestigates,
			String externCompany, String project, Contract contract, List<Appointment> appointments,
			List<Patient> patients, List<Disease> diseases) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.job = jobInTheHospital;
		this.disease = diseaseThatInvestigates;
		this.externCompany = externCompany;
		this.project = project;
		this.contract = contract;
		this.appointments = appointments;
		this.patients = patients;
		this.diseases = diseases;
	}
	public Worker(int id, String name, String type, String diseaseThatInvestigates, Contract contract,
			List<Appointment> appointments, List<Patient> patients, List<Disease> diseases) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.disease = diseaseThatInvestigates;
		this.contract = contract;
		this.appointments = appointments;
		this.patients = patients;
		this.diseases = diseases;
	}
	public Worker(int id, String name, String type, String jobInTheHospital, String externCompany, String project,
			Contract contract, List<Appointment> appointments, List<Patient> patients, List<Disease> diseases) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.job = jobInTheHospital;
		this.externCompany = externCompany;
		this.project = project;
		this.contract = contract;
		this.appointments = appointments;
		this.patients = patients;
		this.diseases = diseases;
	}
	public Worker(int id, String name, String type, Contract contract, List<Appointment> appointments,
			List<Patient> patients, List<Disease> diseases) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.contract = contract;
		this.appointments = appointments;
		this.patients = patients;
		this.diseases = diseases;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getExternCompany() {
		return externCompany;
	}
	public void setExternCompany(String externCompany) {
		this.externCompany = externCompany;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public List<Disease> getDiseases() {
		return diseases;
	}
	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}
	@Override
	public String toString() {
		String vuelta = "Worker [id=" + id + ", name=" + name + ", type=" + type + ", job=" + job
				+ ", disease=" + disease + ", externCompany=" + externCompany
				+ ", project=" + project + ", appointments=\n";
		for(int i = 0; i < appointments.size(); i++) {
			vuelta += appointments.get(i) + "\n";
		}
		vuelta +=  ", patients=\n";
		for(int i = 0; i < patients.size(); i++) {
			vuelta += patients.get(i)+ "\n";
		}
		vuelta += ", diseases=\n";
		for(int i = 0; i < diseases.size(); i++) {
			vuelta += diseases.get(i) + "\n";
		}
		vuelta += "]";
		return vuelta;
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
		Worker other = (Worker) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
