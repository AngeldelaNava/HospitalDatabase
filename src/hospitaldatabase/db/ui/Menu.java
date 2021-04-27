package hospitaldatabase.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

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
			case 0:
				dbman.disconnect();
				System.exit(0);
				break;
			default:
				break;
			}
		} while(true);

	}

	private static void deleteDisease() {
		// TODO Auto-generated method stub
		
	}

	private static void setDisease() {
		// TODO Auto-generated method stub
		
	}

	private static void searchDiseaseByPatient() {
		// TODO Auto-generated method stub
		
	}

	private static void searchDiseaseByName() {
		// TODO Auto-generated method stub
		
	}

	private static void searchDiseaseByID() {
		// TODO Auto-generated method stub
		
	}

	private static void addDisease() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteProject() {
		// TODO Auto-generated method stub
		
	}

	private static void setProject() {
		// TODO Auto-generated method stub
		
	}

	private static void searchProject() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteAppointment() {
		// TODO Auto-generated method stub
		
	}

	private static void setAppointment() {
		// TODO Auto-generated method stub
		
	}

	private static void searchAppointmentByType() {
		// TODO Auto-generated method stub
		
	}

	private static void searchAppointmentByDate() {
		// TODO Auto-generated method stub
		
	}

	private static void searchAppointmentByDateAndTime() {
		// TODO Auto-generated method stub
		
	}

	private static void searchAppointmentByID() {
		// TODO Auto-generated method stub
		
	}

	private static void addAppointment() {
		// TODO Auto-generated method stub
		
	}

	private static void deletePatient() {
		// TODO Auto-generated method stub
		
	}

	private static void setPatient() {
		// TODO Auto-generated method stub
		
	}

	private static void readAllPatientsData() {
		// TODO Auto-generated method stub
		
	}

	private static void searchPatientByName() {
		// TODO Auto-generated method stub
		
	}

	private static void searchPatientByID() {
		// TODO Auto-generated method stub
		
	}

	private static void addPatient() {
		// TODO Auto-generated method stub
		
	}

	private static void searchContractByID() {
		// TODO Auto-generated method stub
		
	}

	private static void addContract() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteWorker() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println(dbman.searchWorkerByName(""));
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		dbman.deleteWorker(id);
	}

	private static void setWorker() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		System.out.println("Introduce worker's name: ");
		String name = reader.readLine();
		System.out.println(dbman.searchWorkerByName(name));
		
	}

	private static void searchWorkerByID() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		System.out.println(dbman.getWorker(id));
	}

	private static void addWorker() throws IOException {
		// TODO Auto-generated method stub
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
