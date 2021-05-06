package hospitaldatabase.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import hospitaldatabase.db.ifaces.HospitalDBManager;
import hospitaldatabase.db.jdbc.HospitalJDBCManager;
import hospitaldatabase.db.pojos.*;



public class Menu {

	public static HospitalDBManager dbman = new HospitalJDBCManager();
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		dbman.connect();
		System.out.println("Database connection opened.");
		do {
			System.out.println("Choose an option:");
			System.out.println("1. Add a worker");
			System.out.println("2. Search worker by id");
			System.out.println("3. Search worker by name");
			System.out.println("4. Edit worker information and data");
			System.out.println("5. Delete worker");
			System.out.println("6. Add a contract");
			System.out.println("7. Search contract by id");
			System.out.println("8. Add a patient");
			System.out.println("9. Search patient by id");
			System.out.println("10. Search patient by name");
			System.out.println("11. Read all patients data");
			System.out.println("12. Edit patient data");
			System.out.println("13. Delete patient");
			System.out.println("14. Add an appointment");
			System.out.println("15. Search appointment by id");
			System.out.println("16. Search appointment by date and time");
			System.out.println("17. Search appointment by date");
			System.out.println("18. Search appointment by type");
			System.out.println("19. Edit appointment data");
			System.out.println("20. Delete appointment");
			System.out.println("21. Search the project from a worker");
			System.out.println("22. Add/edit a worker's project");
			System.out.println("23. Delete a worker's project");
			System.out.println("24. Add a disease");
			System.out.println("25. Search disease by id");
			System.out.println("26. Search disease by name");
			System.out.println("27. Search disease by patient");
			System.out.println("28. Edit disease data");
			System.out.println("29. Delete disease");
			System.out.println("30. Relate a patient with a worker");
			System.out.println("31. Relate a disease with a worker");
			System.out.println("32. Relate an appointment with a worker");
			System.out.println("33. Relate a patient with a disease");
			System.out.println("34. Relate a worker with a contract");
			System.out.println("35. Relate a patient with an appointment");
			System.out.println("0. Exit");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				addWorker();
				break;
			case 2:
				searchWorkerByID();
				break;
			case 3:
				searchWorkerByName();
				break;
			case 4:
				setWorker();
				break;
			case 5:
				deleteWorker();
				break;
			case 6:
				addContract();
				break;
			case 7:
				searchContractByID();
				break;
			case 8:
				addPatient();
				break;
			case 9:
				searchPatientByID();
				break;
			case 10:
				searchPatientByName();
				break;
			case 11:
				readAllPatientsData();
				break;
			case 12:
				setPatient();
				break;
			case 13:
				deletePatient();
				break;
			case 14:
				addAppointment();
				break;
			case 15:
				searchAppointmentByID();
				break;
			case 16:
				searchAppointmentByDateAndTime();
				break;
			case 17:
				searchAppointmentByDate();
				break;
			case 18:
				searchAppointmentByType();
				break;
			case 19:
				setAppointment();
				break;
			case 20:
				deleteAppointment();
				break;
			case 21:
				searchProject();
				break;
			case 22:
				setProject();
				break;
			case 23:
				deleteProject();
				break;
			case 24:
				addDisease();
				break;
			case 25:
				searchDiseaseByID();
				break;
			case 26:
				searchDiseaseByName();
				break;
			case 27:
				searchDiseaseByPatient();
				break;
			case 28:
				setDisease();
				break;
			case 29:
				deleteDisease();
				break;
			case 30:
				relationPatientWorker();
				break;
			case 31:
				relationDiseaseWorker();
				break;
			case 32:
				relationAppointmentWorker();
				break;
			case 33:
				relationPatientDisease();
				break;
			case 34:
				relationWorkerContract();
				break;
			case 35:
				relationPatientAppointment();
				break;
			case 0:
				dbman.disconnect();
				System.exit(0);
				break;
			default:
				break;
			}
		} while(true);

	}

	private static void relationPatientAppointment() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<Patient> patients = dbman.checkListOfPatients();
		for(int i = 0; i < patients.size(); i++) {
			System.out.println(patients.get(i));
		}
		System.out.print("Introduce the patient id: ");
		int patientId = Integer.parseInt(reader.readLine());
		List<Appointment> appointments = dbman.searchAppointmentByType("");
		for(int i = 0; i < appointments.size(); i++) {
			System.out.println(appointments.get(i));
		}
		System.out.print("Introduce the appointment id: ");
		int appointmentId = Integer.parseInt(reader.readLine());
		dbman.relationPatientAppointment(patientId, appointmentId);
	}

	private static void relationWorkerContract() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<Worker> workers = dbman.searchWorkerByName("");
		for(int i = 0; i < workers.size(); i++) {
			System.out.println(workers.get(i));
		}
		System.out.print("Introduce the worker id: ");
		int workerId = Integer.parseInt(reader.readLine());
		System.out.print("Introduce the contract id: ");
		int contractId = Integer.parseInt(reader.readLine());
		dbman.relationWorkerContract(workerId, contractId);
	}

	private static void relationPatientDisease() {
		// TODO Auto-generated method stub
		
	}

	private static void relationAppointmentWorker() {
		// TODO Auto-generated method stub
		
	}

	private static void relationDiseaseWorker() {
		// TODO Auto-generated method stub
		
	}

	private static void relationPatientWorker() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteDisease() throws NumberFormatException, IOException {
		System.out.println(dbman.searchDiseaseByName(""));
		System.out.print("Introduce disease's id: ");
		int id = Integer.parseInt(reader.readLine());
		dbman.deleteDisease(id);
	}

	private static void setDisease() throws IOException {
		System.out.println(dbman.searchDiseaseByName(""));
		System.out.print("Introduce disease id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println(dbman.getDisease(id));
		System.out.print("Please, input the new information. (If the info is unchanged enter the same information) \n"
				+ "Insert disease name: ");
		String diseaseName = reader.readLine();
		System.out.print("Insert the prescription: ");
		String prescription = reader.readLine();
		Disease d = new Disease(1, diseaseName, prescription);
		dbman.setDisease(d, id);
	}

	private static void searchDiseaseByPatient() {
		// TODO Auto-generated method stub
		
	}

	private static void searchDiseaseByName() throws IOException {
		System.out.println("Introduce disease's name: ");
		String diseaseName = reader.readLine();
		System.out.println(dbman.searchDiseaseByName(diseaseName));
	}

	private static void searchDiseaseByID() throws NumberFormatException, IOException {
		System.out.println("Introduce disease's name: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println(dbman.getDisease(id));
	}

	private static void addDisease() throws IOException {
		System.out.print("Please, input the disease info\n"
				+ "Insert disease name: ");
		String diseaseName = reader.readLine();
		System.out.print("Insert the prescription: ");
		String prescription = reader.readLine();
		Disease d = new Disease(1, diseaseName, prescription);
		dbman.addDisease(d);
	}

	private static void deleteProject() throws NumberFormatException, IOException {
		System.out.println(dbman.searchWorkerByName(""));
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		dbman.deleteProject(id);
	}

	private static void setProject() throws NumberFormatException, IOException {
		System.out.println(dbman.searchWorkerByName(""));
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println("Introduce the project: ");
		String project = reader.readLine();
		dbman.setProject(project, id);
	}

	private static void searchProject() throws NumberFormatException, IOException {
		System.out.println(dbman.searchWorkerByName(""));
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println(dbman.getProject(id));
		
	}

	private static void deleteAppointment() throws NumberFormatException, IOException {
		dbman.searchAppointmentByType("");
		System.out.print("Introduce the appointment id: ");
		int id = Integer.parseInt(reader.readLine());
		dbman.deleteAppointment(id);
	}

	private static void setAppointment() throws NumberFormatException, IOException {
		System.out.print("Introduce the appointment id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println("Please input the appointment info. If it is null, press enter");
		System.out.print("Introduce the type: ");
		String type = reader.readLine();
		System.out.print("Introduce the intervention: ");
		String intervention = reader.readLine();
		if (intervention.equals("")) {
			intervention = null;
		}
		System.out.print("Introduce the start date (yy-mm-dd): ");
		LocalDate startDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.print("Introduce the start time (hh:mm:ss): ");
		LocalTime startTime = LocalTime.parse(reader.readLine());
		System.out.print("Introduce the duration: ");
		String reading = reader.readLine();
		Integer duration;
		if (reading.equals("")) {
			duration = null;
		} else {
			duration = Integer.parseInt(reading);
		}
		System.out.print("Introduce the succes (T for true, other thing except entering for false): ");
		reading = reader.readLine();
		Boolean success;
		if (reading.equals("")) {
			success = null;
		} else {
			if (reading.equals("T") || reading.equals("t")) {
				success = true;
			} else {
				success = false;
			}
		}
		Appointment a = new Appointment(id, type, intervention, Date.valueOf(startDate), Time.valueOf(startTime), duration, success);
		dbman.setAppointment(a, id);
	}

	private static void searchAppointmentByType() throws IOException {
		System.out.print("Introduce the type: ");
		String type = reader.readLine();
		System.out.println(dbman.searchAppointmentByType(type));
	}

	private static void searchAppointmentByDate() throws IOException {
		System.out.print("Introduce the start date (yy-mm-dd): ");
		LocalDate startDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.println(dbman.searchAppointmentByDate(Date.valueOf(startDate)));
	}

	private static void searchAppointmentByDateAndTime() throws IOException {
		System.out.print("Introduce the start date (yy-mm-dd): ");
		LocalDate startDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.print("Introduce the start time (hh:mm:ss): ");
		LocalTime startTime = LocalTime.parse(reader.readLine());
		System.out.println(dbman.searchAppointmentByDateAndTime(Date.valueOf(startDate), Time.valueOf(startTime)));
	}

	private static void searchAppointmentByID() throws NumberFormatException, IOException {
		System.out.print("Introduce the appointment id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println(dbman.getAppointment(id));
	}

	private static void addAppointment() throws NumberFormatException, IOException {
		System.out.println("Please input the appointment info. If it is null, press enter");
		System.out.print("Introduce the type: ");
		String type = reader.readLine();
		System.out.print("Introduce the intervention: ");
		String intervention = reader.readLine();
		if (intervention.equals("")) {
			intervention = null;
		}
		System.out.print("Introduce the start date (yy-mm-dd): ");
		LocalDate startDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.print("Introduce the start time (hh:mm:ss): ");
		LocalTime startTime = LocalTime.parse(reader.readLine());
		System.out.print("Introduce the duration: ");
		String reading = reader.readLine();
		Integer duration;
		if (reading.equals("")) {
			duration = null;
		} else {
			duration = Integer.parseInt(reading);
		}
		System.out.print("Introduce the succes (T for true, other thing except entering for false): ");
		reading = reader.readLine();
		Boolean success;
		if (reading.equals("")) {
			success = null;
		} else {
			if (reading.equals("T") || reading.equals("t")) {
				success = true;
			} else {
				success = false;
			}
		}
		Appointment a = new Appointment(1, type, intervention, Date.valueOf(startDate), Time.valueOf(startTime), duration, success);
		dbman.addAppointment(a);
	}

	private static void deletePatient() throws NumberFormatException, IOException {
		System.out.println(dbman.searchPatientByName(""));
		System.out.print("Introduce patient's id: ");
		int id = Integer.parseInt(reader.readLine());
		dbman.deletePatient(id);
		
	}

	private static void setPatient() throws NumberFormatException, IOException {
		System.out.print("Introduce patient's id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println("Please, input the new information. (If the info is unchanged enter the same information)");
		System.out.print("Insert name: ");
		String name = reader.readLine();
		System.out.print("Insert the patient's gender: ");
		String gender = reader.readLine();
		System.out.print("Insert the patients blood type: ");
		String bloodType = reader.readLine();
		System.out.print("Insert room number (if it is null, press enter): ");
		String reading = reader.readLine();
		Integer roomNumber;
		if (reading.equals("")) {
			roomNumber = null;
		} else {
			roomNumber = Integer.parseInt(reading);
		}

		Patient p = new Patient(1, name, gender, bloodType, roomNumber, null, null, null);
		dbman.setPatient(p, id);
	}
		


	private static void readAllPatientsData() {
		System.out.println(dbman.checkListOfPatients());
	}

	private static void searchPatientByName() throws IOException {
		System.out.println("Introduce patient's name: ");
		String patientName = reader.readLine();
		System.out.println(dbman.searchPatientByName(patientName));		
	}

	private static void searchPatientByID() throws NumberFormatException, IOException {
		System.out.print("Introduce patient's id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println(dbman.getPatient(id));
			
	}

	private static void addPatient() throws NumberFormatException, IOException {
		System.out.print("Please, input the patient's info\n"
				+ "Insert patients name: ");
		String patientName = reader.readLine();
		System.out.print("Insert the blood type: ");
		String bloodType = reader.readLine();
		System.out.print("Insert the patient's gender: ");
		String gender = reader.readLine();
		System.out.print("Insert the patient's room number (if it is null, press enter): ");
		String reading = reader.readLine();
		Integer roomNumber;
		if (reading.equals("")) {
			roomNumber = null;
		} else {
			roomNumber = Integer.parseInt(reading);
		}
		Patient p = new Patient(1, patientName, gender, bloodType, roomNumber, null, null, null);
		dbman.addPatient(p);
		
	}

	private static void searchContractByID() throws NumberFormatException, IOException {
		System.out.print("Introduce contract's id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println(dbman.getContract(id));
		
	}

	private static void addContract() throws NumberFormatException, IOException {
		System.out.print("Please, input the contract's info\n"
				+ "Insert salary: ");
		int salary = Integer.parseInt(reader.readLine());
		System.out.print("Insert hire date: ");
		LocalDate hireDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.print("Insert contract's date of end: ");
		LocalDate dateOfEnd = LocalDate.parse(reader.readLine(), formatter);
		System.out.println(dbman.searchWorkerByName(""));
		System.out.print("Insert worker's id of this contract: ");
		String reading = reader.readLine();
		Worker w;
		if (reading.equals("")) {
			w = null;
		} else {
			int id = Integer.parseInt(reading);
			w = dbman.getWorker(id);
		}
		Contract ct = new Contract(1, salary, Date.valueOf(hireDate), Date.valueOf(dateOfEnd), w);
		dbman.addContract(ct);
		
		
	}

	private static void deleteWorker() throws NumberFormatException, IOException {
		System.out.println(dbman.searchWorkerByName(""));
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		dbman.deleteWorker(id);
	}

	private static void setWorker() throws NumberFormatException, IOException {
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println("Please, input the new information. For not updating, input the same info. For setting null, press enter");
		System.out.print("Insert name: ");
		String name = reader.readLine();
		System.out.print("Insert type of worker (Surgeon, Doctor, Nurse...): ");
		String type = reader.readLine();
		System.out.print("Insert job: ");
		String job = reader.readLine();
		if (job.equals("")) {
			job = null;
		}
		System.out.print("Insert disease: ");
		String disease = reader.readLine();
		if (disease.equals("")) {
			disease = null;
		}
		System.out.print("Insert extern company: ");
		String externCompany = reader.readLine();
		System.out.print("Insert project: ");
		if (externCompany.equals("")) {
			externCompany = null;
		}
		String project = reader.readLine();
		if (project.equals("")) {
			project = null;
		}
		Worker w = new Worker(1, name, type, job, disease, externCompany, project, null, null, null, null);
		dbman.setWorker(w, id);
	}

	private static void searchWorkerByName() throws IOException {
		System.out.println("Introduce worker's name: ");
		String name = reader.readLine();
		System.out.println(dbman.searchWorkerByName(name));
		
	}

	private static void searchWorkerByID() throws NumberFormatException, IOException {
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println(dbman.getWorker(id));
	}

	private static void addWorker() throws IOException {
		System.out.print("Please, input the worker info\n"
				+ "Insert name: ");
		String name = reader.readLine();
		System.out.print("Insert type of worker (Surgeon, Doctor, Nurse...): ");
		String type = reader.readLine();
		System.out.print("Insert job: ");
		String job = reader.readLine();
		if (job.equals("")) {
			job = null;
		}
		System.out.print("Insert disease: ");
		String disease = reader.readLine();
		if (disease.equals("")) {
			disease = null;
		}
		System.out.print("Insert extern company: ");
		String externCompany = reader.readLine();
		if (externCompany.equals("")) {
			externCompany = null;
		}
		System.out.print("Insert project: ");
		String project = reader.readLine();
		if (project.equals("")) {
			project = null;
		}
		Worker w = new Worker(1, name, type, job, disease, externCompany, project, null, null, null, null);
		dbman.addWorker(w);
	}

}
