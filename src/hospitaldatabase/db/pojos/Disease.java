package hospitaldatabase.db.pojos;

import java.io.Serializable;

public class Disease implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1077803972062837560L;
	private Integer id;
	private String diseaseName;
	private String prescripition;
	public Disease(int id, String diseaseName, String prescripition) {
		super();
		this.id = id;
		this.diseaseName = diseaseName;
		this.prescripition = prescripition;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getPrescripition() {
		return prescripition;
	}
	public void setPrescripition(String prescripition) {
		this.prescripition = prescripition;
	}


	@Override
	public String toString() {
		return "Disease [id=" + id + ", diseaseName=" + diseaseName + ", prescripition=" + prescripition + "]";
	}
	
	
}
