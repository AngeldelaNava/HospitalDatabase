package hospitaldatabase.db.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import hospitaldatabase.db.ifaces.HospitalDBManager;
import hospitaldatabase.db.jdbc.HospitalJDBCManager;



public class Menu {

	public static HospitalDBManager dbman = new HospitalJDBCManager();
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		dbman.connect();
		System.out.println("Database connection opened.");
		dbman.disconnect();

	}

}
