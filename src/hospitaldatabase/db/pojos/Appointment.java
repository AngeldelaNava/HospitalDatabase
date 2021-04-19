package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Appointment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private Integer id;
	private String type;
	private String intervention;
	private Date dateStart;
	private Time timeStart;
	private int duration;
	private boolean success;
	public Appointment(int id, String type, String intervention, Date dateStart, Time timeStart, int duration, boolean success) {
		super();
		this.id = id;
		this.type = type;
		this.intervention = intervention;
		this.dateStart = dateStart;
		this.timeStart = timeStart;
		this.duration = duration;
		this.success = success;
	}
	public Appointment(int id, String type, Date dateStart) {
		super();
		this.id = id;
		this.type = type;
		this.dateStart = dateStart;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIntervention() {
		return intervention;
	}
	public void setIntervention(String intervention) {
		this.intervention = intervention;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Time getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", type=" + type + ", intervention=" + intervention + ", dateStart="
				+ dateStart + ", timeStart="+ timeStart + ", duration=" + duration + ", success=" + success + "]";
	}
	
	
}
