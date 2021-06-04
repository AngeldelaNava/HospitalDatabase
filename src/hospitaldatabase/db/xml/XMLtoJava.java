package hospitaldatabase.db.xml;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import hospitaldatabase.db.pojos.Patient;
import hospitaldatabase.db.pojos.Disease;

public class XMLtoJava {

	private static final String PERSISTENCE_PROVIDER = "company-provider";
	private static EntityManagerFactory factory;

	public void PatientXMLToJava() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			File file = new File("./XML/MedicalHistory.xml");
			Patient patient = (Patient) unmarshaller.unmarshal(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws JAXBException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("./XML/MedicalHistory.xml");
		Patient patient = (Patient) unmarshaller.unmarshal(file);

		// Print the medical history
		System.out.println("Patient:");
		System.out.println("Name: " + patient.getName());
		System.out.println("Gender: " + patient.getGender());
		System.out.println("BloodType: " + patient.getBloodType());
		List<Disease> listDis = patient.getDiseases();
		for (Disease dis : listDis) {
			System.out.println("Disease: " + dis.getDiseaseName());
			System.out.println("Prescription: " + dis.getPrescripition());
		}

		// Store the report in the database
		// Create entity manager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction tx1 = em.getTransaction();

		// Start transaction
		tx1.begin();

		// Persist
		// We assume the authors are not already in the database
		// In a real world, we should check if they already exist
		// and update them instead of inserting as new
		for (Disease disease : listDis) {
			em.persist(disease);
		}
		em.persist(patient);
		
		// End transaction
		tx1.commit();
	}
}
