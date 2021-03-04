package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.util.List;

public class Worker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private int id;
	private String name;
	private String type;
	private String jobInTheHospital;
	private String diseaseThatInvestigates;
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
		this.jobInTheHospital = jobInTheHospital;
		this.diseaseThatInvestigates = diseaseThatInvestigates;
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
		this.diseaseThatInvestigates = diseaseThatInvestigates;
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
		this.jobInTheHospital = jobInTheHospital;
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
	public String getJobInTheHospital() {
		return jobInTheHospital;
	}
	public void setJobInTheHospital(String jobInTheHospital) {
		this.jobInTheHospital = jobInTheHospital;
	}
	public String getDiseaseThatInvestigates() {
		return diseaseThatInvestigates;
	}
	public void setDiseaseThatInvestigates(String diseaseThatInvestigates) {
		this.diseaseThatInvestigates = diseaseThatInvestigates;
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
		return "Worker [id=" + id + ", name=" + name + ", type=" + type + ", jobInTheHospital=" + jobInTheHospital
				+ ", diseaseThatInvestigates=" + diseaseThatInvestigates + ", externCompany=" + externCompany
				+ ", project=" + project + ", contract=" + contract + ", appointments=" + appointments + ", patients="
				+ patients + ", diseases=" + diseases + "]";
	}
	
	
	
}
