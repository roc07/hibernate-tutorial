package hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			List<Student> allStudents = session.createQuery("from Student").getResultList();

			displayStudents(allStudents);
			
			System.out.println(" __ ");
			
			List<Student> someStudents = session.createQuery("from Student s where s.lastName='Duck'")
					.getResultList();
			
			displayStudents(someStudents);
			
			someStudents = session.createQuery("from Student s where s.lastName='Duck'"
					+ " OR s.firstName = 'First'").getResultList();
			
			System.out.println(" __ ");
			
			displayStudents(someStudents);			
			
			someStudents = session.createQuery("from Student s where s.lastName like 'D%'").getResultList();
			
			System.out.println(" __ ");
			
			displayStudents(someStudents);	
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> allStudents) {
		for (Student temp : allStudents) {
			System.out.println(temp);
		}
	}

}
