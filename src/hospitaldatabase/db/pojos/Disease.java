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
		Disease other = (Disease) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
