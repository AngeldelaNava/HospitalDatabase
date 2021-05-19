package hospitaldatabase.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import hospitaldatabase.db.pojos.Patient;

public class JavaToXML {

	// Put entity manager and buffered reader here so it can be used
	// in several methods
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
	private static void printPatients() {
		Query q1 = em.createNativeQuery("SELECT * FROM patients", Patient.class);
		List<Patient> reps = (List<Patient>) q1.getResultList();
		// Print the departments
		for (Patient rep : reps) {
			System.out.println(rep);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// Get the entity manager
		// Note that we are using the class' entity manager
		em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		// Choose a patient to see his medical history in XML
		printPatients();
		System.out.print("Choose a patient to see his medical history in a XML file:");
		int rep_id = Integer.parseInt(reader.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM patients WHERE id = ?", Patient.class);
		q2.setParameter(1, rep_id);
		Patient patient = (Patient) q2.getSingleResult();
		
		// Use the Marshaller to marshal the Java object to a file
		File file = new File("./XML/MedicalHistory.xml");
		marshaller.marshal(patient, file);
		// Printout
		marshaller.marshal(patient, System.out);

	}
}
