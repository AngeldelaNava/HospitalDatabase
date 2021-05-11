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
					+ " job TEXT, "
					+ " disease TEXT, "
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
					+ " bloodType TEXT NOT NULL, "
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
			sql = "CREATE TABLE DiseaseWorker( "
					+ " diseaseId REFERENCES Disease(id), "
					+ " workerId REFERENCES Worker(id), "
					+ " PRIMARY KEY(diseaseId, workerId))";
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
			String sql = "INSERT INTO Worker (name, type, job, disease, externCompany, project) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, w.getName());
			prep.setString(2, w.getType());
			prep.setString(3, w.getJob());
			prep.setString(4, w.getDisease());
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
				String job = rs.getString("job");
				String disease = rs.getString("disease");
				String externCompany = rs.getString("externCompany");
				String project = rs.getString("project");
				List<Appointment> appointments = new ArrayList<Appointment>();
				sql = "SELECT a.* FROM Appointment AS a JOIN AppointmentWorker AS aw ON a.id = aw.appointmentId WHERE aw.workerId = ?";
				prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				ResultSet rs2 = prep.executeQuery();
				while(rs2.next()) {
					appointments.add(new Appointment(rs2.getInt("id"), rs2.getString("type"), rs2.getString("intervention"), rs2.getDate("dateStart"), rs2.getTime("timeStart"), rs2.getInt("duration"), rs2.getBoolean("success")));
				}
				List<Patient> patients = new ArrayList<Patient>();
				sql = "SELECT p.* FROM Patient AS p JOIN PatientWorker AS pw ON p.id = pw.patientId WHERE pw.workerId = ?";
				prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				rs2 = prep.executeQuery();
				while(rs2.next()) {
					patients.add(new Patient(rs2.getInt("id"), rs2.getString("name"), rs2.getString("gender"), rs2.getString("bloodType"), rs2.getInt("roomNumber"), null, null, null));
				}
				List<Disease> diseases = new ArrayList<Disease>();
				sql = "SELECT d.* FROM Disease AS d JOIN DiseaseWorker AS dw ON d.id = dw.diseaseId WHERE dw.workerId = ?";
				prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				rs2 = prep.executeQuery();
				while(rs2.next()) {
					diseases.add(new Disease(rs2.getInt("id"), rs2.getString("diseasename"), rs2.getString("prescription")));
				}
				w = new Worker(id, name, type, job, disease, externCompany, project, null, appointments, patients, diseases);
			
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
				String job = rs.getString("job");
				String disease = rs.getString("disease");
				String externCompany = rs.getString("externCompany");
				String project = rs.getString("project");
				List<Appointment> appointments = new ArrayList<Appointment>();
				sql = "SELECT a.* FROM Appointment AS a JOIN AppointmentWorker AS aw ON a.id = aw.appointmentId WHERE aw.workerId = ?";
				prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				ResultSet rs2 = prep.executeQuery();
				while(rs2.next()) {
					appointments.add(new Appointment(rs2.getInt("id"), rs2.getString("type"), rs2.getString("intervention"), rs2.getDate("dateStart"), rs2.getTime("timeStart"), rs2.getInt("duration"), rs2.getBoolean("success")));
				}
				List<Patient> patients = new ArrayList<Patient>();
				sql = "SELECT p.* FROM Patient AS p JOIN PatientWorker AS pw ON p.id = pw.patientId WHERE pw.workerId = ?";
				prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				rs2 = prep.executeQuery();
				while(rs2.next()) {
					patients.add(new Patient(rs2.getInt("id"), rs2.getString("name"), rs2.getString("gender"), rs2.getString("bloodType"), rs2.getInt("roomNumber"), null, null, null));
				}
				List<Disease> diseases = new ArrayList<Disease>();
				sql = "SELECT d.* FROM Disease AS d JOIN DiseaseWorker AS dw ON d.id = dw.diseaseId WHERE dw.workerId = ?";
				prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				rs2 = prep.executeQuery();
				while(rs2.next()) {
					diseases.add(new Disease(rs2.getInt("id"), rs2.getString("diseasename"), rs2.getString("prescription")));
				}
				w.add(new Worker(id, rs.getString("name"), type, job, disease, externCompany, project, null, appointments, patients, diseases));
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
			String sql = "INSERT INTO Contract (salary, hireDate, dateOfEnd, staffId) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, ct.getSalary());
			prep.setDate(2, ct.getHireDate());
			prep.setDate(3, ct.getDateOfEnd());
			if (ct.getWorker() == null) {
				prep.setNull(4, java.sql.Types.INTEGER);
			} else {
				prep.setInt(4, ct.getWorker().getId());
			}
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
				Date endDate = rs.getDate("dateOfEnd");
				int workerId = rs.getInt("staffId");
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
			String sql = "INSERT INTO Patient (name, gender, bloodType, roomNumber) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getGender());
			prep.setString(3, p.getBloodType());
			if (p.getRoomNumber() == null) {
				prep.setNull(4, java.sql.Types.INTEGER);
			} else {
				prep.setInt(4, p.getRoomNumber());
			}
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
				String bloodType = rs.getString("bloodType");
				int roomNumber = rs.getInt("roomNumber");
				sql = "SELECT d.* FROM Disease AS d JOIN PatientDisease AS pd ON d.id = pd.diseaseId WHERE pd.patientId = ?";
				prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				ResultSet rs2 = prep.executeQuery();
				List<Disease> d = new ArrayList<Disease>();
				while(rs2.next()) {
					String diseaseName = rs.getString("diseaseName");
					String prescription = rs.getString("prescription");
					d.add(new Disease(id, diseaseName, prescription)) ;
				}
				p = new Patient(id, name, gender, bloodType, roomNumber, null, d, null);
				rs2.close();
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
				String bloodType = rs.getString("bloodType");
				int roomNumber = rs.getInt("roomNumber");
				sql = "SELECT d.* FROM Disease AS d JOIN PatientDisease AS pd ON d.id = pd.diseaseId WHERE pd.patientId = ?";
				prep = c.prepareStatement(sql);
				prep.setInt(1, id);
				ResultSet rs2 = prep.executeQuery();
				List<Disease> d = new ArrayList<Disease>();
				while(rs2.next()) {
					String diseaseName = rs.getString("diseaseName");
					String prescription = rs.getString("prescription");
					d.add(new Disease(id, diseaseName, prescription)) ;
				}
				p.add(new Patient(id, rs.getString("name"), gender, bloodType, roomNumber, null, d, null));
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
			String sql = "INSERT INTO Appointment (type, intervention, dateStart, timeStart, duration, success) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, a.getType());
			prep.setString(2, a.getIntervention());
			prep.setDate(3, a.getDateStart());
			prep.setTime(4, a.getTimeStart());
			if (a.getDuration() == null) {
				prep.setNull(5, java.sql.Types.INTEGER);
			} else {
				prep.setInt(5, a.getDuration());
			}
			if (a.isSuccess() == null) {
				prep.setNull(6, java.sql.Types.BOOLEAN);
			} else {
				prep.setBoolean(6, a.isSuccess());
			}
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
				a = new Appointment(id, type, intervention, dateStart, timeStart, duration, success);
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
			String sql = "UPDATE Worker SET name = ?, type = ?, job = ?, disease = ?, externCompany = ?, project = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, w.getName());
			prep.setString(2, w.getType());
			prep.setString(3, w.getJob());
			prep.setString(4, w.getDisease());
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
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM Patient";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Patient p = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), rs.getString("bloodType"), rs.getInt("roomNumber"), null, null, null);
				patients.add(p);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public void setPatient(Patient p, int id) {
		try {
			String sql = "UPDATE Patient SET name = ?, gender = ?, bloodType = ?, roomNumber = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getGender());
			prep.setString(3, p.getBloodType());
			if (p.getRoomNumber() == null) {
				prep.setNull(4, java.sql.Types.INTEGER);
			} else {
				prep.setInt(4, p.getRoomNumber());
			}
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
				a.add(new Appointment(id, type, intervention, date, time, duration, success));
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
				a.add(new Appointment(id, type, intervention, date, timeStart, duration, success));
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
				a.add(new Appointment(id, rs.getString("type"), intervention, dateStart, timeStart, duration, success));
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
			String sql = "UPDATE Appointment SET type = ?, intervention = ?, dateStart = ?, timeStart = ?, duration = ?, success = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, a.getType());
			prep.setString(2, a.getIntervention());
			prep.setDate(3, a.getDateStart());
			prep.setTime(4, a.getTimeStart());
			if (a.getDuration() == null) {
				prep.setNull(5, java.sql.Types.INTEGER);
			} else {
				prep.setInt(5, a.getDuration());
			}
			if (a.isSuccess() == null) {
				prep.setNull(6, java.sql.Types.BOOLEAN);
			} else {
				prep.setBoolean(6, a.isSuccess());
			}
			prep.setInt(7, id);
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
	public void setProject(String project, int id) {
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
			String sql = "INSERT INTO Disease (diseaseName, prescription) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, d.getDiseaseName());
			prep.setString(2, d.getPrescripition());
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
				d = new Disease(id, diseaseName, prescription) ;
			}
			rs.close();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	@Override
	public List<Disease> searchDiseaseByName(String name) {
		List<Disease> d = new ArrayList<Disease>();
		try {
			String sql = "SELECT * FROM Disease WHERE diseaseName LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String prescription = rs.getString("prescription");
				d.add(new Disease(id, rs.getString("diseaseName"), prescription));
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
		List<Disease> diseases = new ArrayList<Disease>();
		String sql= "SELECT d.* FROM Disease AS d JOIN PatientDisease AS pd ON d.id= pd.diseaseId WHERE pd.patientId= ?";
		try {
		PreparedStatement prep= c.prepareStatement(sql);
		prep.setInt(1, patientId);
		ResultSet rs = prep.executeQuery();
		while(rs.next()) {
			Disease d = new Disease(rs.getInt("id"), rs.getString("diseaseName"), rs.getString("prescription"));
			diseases.add(d);	
		}
		rs.close();
		prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return diseases;
	}
	
	
	@Override
	public void setDisease(Disease d, int id) {
		try {
			String sql = "UPDATE Disease SET diseaseName = ?, prescription = ? WHERE ID = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, d.getDiseaseName());
			prep.setString(2, d.getPrescripition());
			prep.setInt(3, id);
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDisease(int id) {
		try {
			String sql = "DELETE FROM Disease WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void relationPatientWorker(int patientId, int workerId) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO PatientWorker (patientId, workerId) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, patientId);
			prep.setInt(2, workerId);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void relationDiseaseWorker(int diseaseId, int workerId) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO DiseaseWorker (diseaseId, workerId) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, diseaseId);
			prep.setInt(2, workerId);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void relationAppointentWorker(int appointmentId, int workerId) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO AppointmentWorker (appointmentId, workerId) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, appointmentId);
			prep.setInt(2, workerId);
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void relationPatientDisease(int patientId, int diseaseId) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO PatientDisease (patientId, diseaseId) VALUES (?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, patientId);
			prep.setInt(2, diseaseId);
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void relationWorkerContract(int workerId, int contractId) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE Contract SET staffId = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, workerId);
			prep.setInt(2, contractId);
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void relationPatientAppointment(int patientId, int appointmentId) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE Appointment SET patientId = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, patientId);
			prep.setInt(2, appointmentId);
			prep.executeUpdate();
			prep.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Contract> listAllContracts() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM Contract";
			List<Contract> contracts = new ArrayList<Contract>();
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				contracts.add(new Contract(rs.getInt("id"), rs.getInt("salary"), rs.getDate("hireDate"), rs.getDate("dateOfEnd"), getWorker(rs.getInt("id"))));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
