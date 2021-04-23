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
			case 0:
				dbman.disconnect();
				System.exit(0);
				break;
			default:
				break;
			}
		} while(true);

	}

	private static void deleteWorker() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println(dbman.searchWorkerByName(""));
		System.out.print("Introduce worker's id: ");
		int id = Integer.parseInt(reader.readLine());
		dbman.deleteWorker(id);
	}

	private static void setWorker() {
		// TODO Auto-generated method stub
		
	}

	private static void searchWorkerByName() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Introduce worker's name: ");
		String name = reader.readLine();
		System.out.println(dbman.searchWorkerByName(name));
		
	}

	private static void searchWorkerByID() {
		// TODO Auto-generated method stub
		
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
