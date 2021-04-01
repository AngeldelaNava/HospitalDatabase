package hospitaldatabase.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import hospitaldatabase.db.ifaces.HospitalDBManager;
import hospitaldatabase.db.pojos.Appointment;
import hospitaldatabase.db.pojos.Contract;
import hospitaldatabase.db.pojos.Disease;
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
					+ " dateStart DATE NOT NULL, "
					+ " timeStart TIME NOT NULL, "
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
		try {
			String sql = "INSERT INTO Appointment (patientId, type, intervention, dateStart, timeStart, duration, success) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, a.getPatient().getId());
			prep.setString(2, a.getType());
			prep.setString(3, a.getIntervention());
			prep.setDate(4, a.getDateStart());
			prep.setTime(5, a.getTimeStart());
			prep.setInt(6, a.getDuration());
			prep.setBoolean(7, a.isSuccess());
			prep.executeUpdate();
			prep.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Appointment getAppointment(int id) {
		Appointment a = null;
		try {
			String sql = "SELECT * FROM Appointment WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				String type = rs.getString("type");
				String intervention = rs.getString("intervention");
				Date dateStart = rs.getDate("dateStart");
				Time timeStart = rs.getTime("timeStart");
				int duration = rs.getInt("duration");
				boolean success = rs.getBoolean("success");
				int patientId = rs.getInt("patientId");
				Patient patient = getPatient(patientId);
				a = new Appointment(id, type, intervention, dateStart, timeStart, duration, success, patient, null);
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void setWorker(Worker w, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWorker(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> checkListOfPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPatient(Patient p, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePatient(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Appointment> searchAppointmentByDateAndTime(Date date, Time time) {
		List<Appointment> a = new ArrayList<Appointment>();
		try {
			String sql = "SELECT * FROM Appointment WHERE dateStart = ? AND timeStart = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDate(1, date);
			prep.setTime(2, time);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String type = rs.getString("type");
				String intervention = rs.getString("intervention");
				int duration = rs.getInt("duration");
				boolean success = rs.getBoolean("success");
				int patientId = rs.getInt("patientId");
				Patient patient = getPatient(patientId);
				a.add(new Appointment(id, type, intervention, date, time, duration, success, patient, null));
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Appointment> searchAppointmentByDate(Date date) {
		List<Appointment> a = new ArrayList<Appointment>();
		try {
			String sql = "SELECT * FROM Appointment WHERE dateStart = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDate(1, date);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String type = rs.getString("type");
				String intervention = rs.getString("intervention");
				Time timeStart = rs.getTime("timeStart");
				int duration = rs.getInt("duration");
				boolean success = rs.getBoolean("success");
				int patientId = rs.getInt("patientId");
				Patient patient = getPatient(patientId);
				a.add(new Appointment(id, type, intervention, date, timeStart, duration, success, patient, null));
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Appointment> searchAppointmentByType(String type) {
		List<Appointment> a = new ArrayList<Appointment>();
		try {
			String sql = "SELECT * FROM Appointment WHERE type LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + type + "%");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String intervention = rs.getString("intervention");
				Date dateStart = rs.getDate("dateStart");
				Time timeStart = rs.getTime("timeStart");
				int duration = rs.getInt("duration");
				boolean success = rs.getBoolean("success");
				int patientId = rs.getInt("patientId");
				Patient patient = getPatient(patientId);
				a.add(new Appointment(id, type, intervention, dateStart, timeStart, duration, success, patient, null));
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void setAppointment(Appointment a, int id) {
		try {
			String sql = "UPDATE Appointment SET patientId = ?, type = ?, intervention = ?, dateStart = ?, timeStart = ?, duration = ?, success = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, a.getId());
			prep.setString(2, a.getType());
			prep.setString(3, a.getIntervention());
			prep.setDate(4, a.getDateStart());
			prep.setTime(5, a.getTimeStart());
			prep.setInt(6, a.getDuration());
			prep.setBoolean(7, a.isSuccess());
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAppointment(int id) {
		try {
			String sql = "DELETE FROM Appointment WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addProject(String p, Worker w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProject(int workerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProject(int workerId, String project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProject(int workerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDisease(Disease d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Disease getDisease(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Disease> searchDiseaseByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Disease> searchDiseaseByPatient(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDisease(Disease d, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDisease(int id) {
		// TODO Auto-generated method stub
		
	}

}
