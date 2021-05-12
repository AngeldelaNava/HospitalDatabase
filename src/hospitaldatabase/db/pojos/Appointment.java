package hospitaldatabase.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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
	private Integer duration;
	private Boolean success;
	public Appointment(Integer id, String type, String intervention, Date dateStart, Time timeStart, Integer duration, Boolean success) {
		super();
		this.id = id;
		this.type = type;
		this.intervention = intervention;
		this.dateStart = dateStart;
		this.timeStart = timeStart;
		this.duration = duration;
		this.success = success;
	}
	public Appointment(int id, String type, Date dateStart, Time timeStart) {
		super();
		this.id = id;
		this.type = type;
		this.dateStart = dateStart;
		this.timeStart = timeStart;
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
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Boolean isSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", type=" + type + ", intervention=" + intervention + ", dateStart="
				+ dateStart + ", timeStart="+ timeStart + ", duration=" + duration + ", success=" + success + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
