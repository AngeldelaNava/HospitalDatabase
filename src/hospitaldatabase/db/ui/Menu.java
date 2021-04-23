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

	private static void deleteWorker() {
		// TODO Auto-generated method stub
		
	}

	private static void setWorker() {
		// TODO Auto-generated method stub
		
	}

	private static void searchWorkerByName() {
		// TODO Auto-generated method stub
		
	}

	private static void searchWorkerByID() {
		// TODO Auto-generated method stub
		
	}

	private static void addWorker() throws IOException {
		// TODO Auto-generated method stub
		System.out.print("Please, input the worker info\n"
				+ "Insert name: ");
		String name = reader.readLine();
	}

}
