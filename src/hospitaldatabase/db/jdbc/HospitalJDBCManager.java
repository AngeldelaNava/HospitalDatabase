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
		try {
			String sql = "INSERT INTO Worker (name, type, jobInTheHospital, diseaseThatInvestigates, externCompany, project) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, w.getName());
			prep.setString(2, w.getType());
			prep.setString(3, w.getJobInTheHospital());
			prep.setString(4, w.getDiseaseThatInvestigates());
			prep.setString(5, w.getExternCompany());
			prep.setString(6, w.getProject());
			prep.executeUpdate();
			prep.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Worker getWorker(int id) {
		Worker w = null; 
		try {
			String sql = "SELECT * FROM Worker WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String type = rs.getString("type");
				String jobInTheHospital = rs.getString("jobInTheHospital");
				String diseaseThatInvestigates = rs.getString("diseaseThatInvestigates");
				String externCompany = rs.getString("externCompany");
				String project = rs.getString("project");
				w = new Worker(id, name, type, jobInTheHospital, diseaseThatInvestigates, externCompany, project, null, null, null, null);
			
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return w;
		
	}

	@Override
	public List<Worker> searchWorkerByName(String name) {
		List<Worker> w = new ArrayList<Worker>();
		try {
			String sql = "SELECT * FROM Worker WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String type = rs.getString("type");
				String jobInTheHospital = rs.getString("jobInTheHospital");
				String diseaseThatInvestigates = rs.getString("diseaseThatInvestigates");
				String externCompany = rs.getString("externCompany");
				String project = rs.getString("project");
				w.add(new Worker(id, name, type, jobInTheHospital, diseaseThatInvestigates, externCompany, project, null, null, null, null));
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return w;
	}

	@Override
	public void addContract(Contract ct) {
		try {
			String sql = "INSERT INTO Contract (salary, hireDate, endDate, staffId) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, ct.getSalary());
			prep.setDate(2, ct.getHireDate());
			prep.setDate(3, ct.getDateOfEnd());
			prep.setInt(4, ct.getWorker().getId());
			prep.executeUpdate();
			prep.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Contract getContract(int id) {
		Contract ct = null;
		try {
			String sql = "SELECT * FROM Contract WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				int salary = rs.getInt("salary");
				Date hireDate = rs.getDate("hireDate");
				Date endDate = rs.getDate("endDate");
				int workerId = rs.getInt("workerId");
				Worker worker = getWorker(workerId);
				ct = new Contract(id, salary, hireDate, endDate, worker);
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ct;
	}

	@Override
	public void addPatient(Patient p) {
		try {
			String sql = "INSERT INTO Patient (name, gender, bloodGroup, roomNumber) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getGender());
			prep.setString(3, p.getBloodGroup());
			prep.setInt(4, p.getRoomNumber());
			prep.executeUpdate();
			prep.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Patient getPatient(int id) {
		Patient p = null; 
		try {
			String sql = "SELECT * FROM Patient WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String bloodGroup = rs.getString("bloodGroup");
				int roomNumber = rs.getInt("roomNumber");
				
				p = new Patient(id, name, gender, bloodGroup, roomNumber,null, null, null);
			
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Patient> searchPatientByName(String name) {
		List<Patient> p = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM Patient WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String gender = rs.getString("gender");
				String bloodGroup = rs.getString("bloodGroup");
				int roomNumber = rs.getInt("roomNumber");
				String externCompany = rs.getString("externCompany");
				String project = rs.getString("project");
				p.add(new Patient(id, name, gender, bloodGroup, roomNumber, null, null, null));
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
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
		try {
			String sql = "UPDATE Worker SET name = ?, type = ?, jobInTheHospital = ?, diseaseThatInvestigates = ?, externCompany = ?, project = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, w.getName());
			prep.setString(2, w.getType());
			prep.setString(3, w.getJobInTheHospital());
			prep.setString(4, w.getDiseaseThatInvestigates());
			prep.setString(5, w.getExternCompany());
			prep.setString(6, w.getProject());
			prep.setInt(7, id);
			prep.executeUpdate();
			prep.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
		
	}

	@Override
	public void deleteWorker(int id) {
		try {
			String sql = "DELETE FROM Worker WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
			prep.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public List<Patient> checkListOfPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPatient(Patient p, int id) {
		try {
			String sql = "UPDATE Patient SET name = ?, gender = ?, bloodGroup = ?, roomNumber = ?, WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getGender());
			prep.setString(3, p.getBloodGroup());
			prep.setInt(4, p.getRoomNumber());
			prep.setInt(5, id);
			prep.executeUpdate();
			prep.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	}

	@Override
	public void deletePatient(int id) {
		try {
			String sql = "DELETE FROM Patient WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
			prep.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
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
			String sql = "UPDATE Appointment SET patientId = ?, type = ?, intervention = ?, dateStart = ?, timeStart = ?, duration = ?, success = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, a.getPatient().getId());
			prep.setString(2, a.getType());
			prep.setString(3, a.getIntervention());
			prep.setDate(4, a.getDateStart());
			prep.setTime(5, a.getTimeStart());
			prep.setInt(6, a.getDuration());
			prep.setBoolean(7, a.isSuccess());
			prep.setInt(8, id);
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
	public String getProject(int workerId) {
		String p = null;
		try {
			String sql = "SELECT project FROM Worker WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, workerId);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				p = rs.getString("project");			
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public void setProject(String project, int id) {//He cambiado el parametro de worker por su id, en el setString he metido el String que entra como parámetro y he puesto un wher para ponerlo en la persona que querem0s según su id
		try {
			String sql = "UPDATE Worker SET project = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, project);
			prep.setInt(2, id);
			prep.executeUpdate();
			prep.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}	
	}

	@Override
	public void deleteProject(int workerId) {
		try {
			String sql = "UPDATE Worker SET project = NULL WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, workerId);
			prep.executeUpdate();
			prep.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}	
	}

	@Override
	public void addDisease(Disease d) {
		try {
			String sql = "INSERT INTO Disease (id, diseaseName, prescription) VALUES (?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, d.getId());
			prep.setString(2, d.getDiseaseName());
			prep.setString(3, d.getPrescripition());
			prep.executeUpdate();
			prep.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Disease getDisease(int id) {
		Disease d = null;
		try {
			String sql = "SELECT * FROM Disease WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				String diseaseName = rs.getString("diseaseName");
				String prescription = rs.getString("prescription");
				d = new Disease(id, diseaseName, prescription, null, null) ;
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	@Override
	public Disease searchDiseaseByName(String name) {
		Disease d = null;
		try {
			String sql = "SELECT * FROM Disease WHERE diseaseName LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			if(rs.next()) {
				Integer id = rs.getInt("id");
				String prescription = rs.getString("prescription");
				d= new Disease(id, name, prescription, null, null);
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return d ;
	}

	@Override
	public List<Disease> searchDiseaseByPatient(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void setDisease(Disease d, int id) {
		try {
			String sql = "UPDATE Disease SET id = ?, diseaseName = ?, prescription = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, d.getId());
			prep.setString(2, d.getDiseaseName());
			prep.setString(3, d.getPrescripition());
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDisease(int id) {
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

	

}
