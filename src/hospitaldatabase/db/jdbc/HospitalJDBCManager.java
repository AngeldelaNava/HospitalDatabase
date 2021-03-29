package hospitaldatabase.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import hospitaldatabase.db.ifaces.HospitalDBManager;
import hospitaldatabase.db.pojos.Appointment;
import hospitaldatabase.db.pojos.Contract;
import hospitaldatabase.db.pojos.Patient;
import hospitaldatabase.db.pojos.Worker;

public class HospitalJDBCManager implements HospitalDBManager {

	private Connection c;
	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/HospitalDatabase.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			this.createTables();
		}
		catch (SQLException e) {
			System.out.println("There was a database exception.");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("There was a general exception");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		try {
			
			Statement stmnt = c.createStatement();
			String sql = "CREATE TABLE Worker( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " name TEXT NOT NULL, "
					+ " type TEXT NOT NULL, "
					+ " jobInTheHospital TEXT, "
					+ " diseaseThatInvestigates TEXT, "
					+ " externCompany TEXT, "
					+ " project TEXT) ";
			stmnt.executeUpdate(sql);
			sql = "CREATE TABLE Contract( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " salary INTEGER NOT NULL, "
					+ " hireDate DATE NOT NULL, "
					+ " dateOfEnd DATE NOT NULL, "
					+ " staffId INTEGER REFERENCES Worker(id) ON UPDATE CASCADE ON DELETE CASCADE)";
			stmnt.executeUpdate(sql);
			sql = "CREATE TABLE Patient( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " name TEXT NOT NULL, "
					+ " gender TEXT NOT NULL, "
					+ " bloodGroup TEXT NOT NULL, "
					+ " roomNumber INTEGER)";
			stmnt.executeUpdate(sql);
			sql = "CREATE TABLE Disease( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " diseaseName TEXT NOT NULL, "
					+ " prescription TEXT NOT NULL)";
			stmnt.executeUpdate(sql);
			sql = "CREATE TABLE Appointment( "
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
					+ " patientId INTEGER REFERENCES Patient(id) ON UPDATE CASCADE ON DELETE CASCADE, "
					+ " type TEXT NOT NULL, "
					+ " intervention TEXT, "
					+ " dateAndTimeStart DATE NOT NULL, "
					+ " duration INTEGER, "
					+ " success BOOLEAN)";
			stmnt.executeUpdate(sql);
			sql = "CREATE TABLE PatientDisease( "
					+ " patientId REFERENCES Patient(id), "
					+ " diseaseId REFERENCES Disease(id), "
					+ " PRIMARY KEY(patientId, diseaseId))";
			stmnt.executeUpdate(sql);
			sql = "CREATE TABLE PatientWorker( "
					+ " patientId REFERENCES Patient(id), "
					+ " workerId REFERENCES Worker(id), "
					+ " PRIMARY KEY(patientId, workerId))";
			stmnt.executeUpdate(sql);
			sql = "CREATE TABLE AppointmentWorker( "
					+ " appointmentId REFERENCES Appointment(id), "
					+ " workerId REFERENCES Worker(id), "
					+ " PRIMARY KEY(appointmentId, workerId))";
			stmnt.executeUpdate(sql);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("There was an exception creating the tables");
		}
	}

	@Override
	public void disconnect() {
		try {
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addWorker(Worker w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Worker getWorker(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Worker> searchWorkerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addContract(Contract c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contract getContract(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPatient(Patient p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Patient getPatient(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> searchPatientByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAppointment(Appointment a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Appointment getAppointment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
