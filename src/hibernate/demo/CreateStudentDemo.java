package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			Student tempStudent = new Student("First", "StudentFirst", "good@man.com");
			
			session.beginTransaction();
			
			session.save(tempStudent);
			
			session.getTransaction().commit();
			
		} finally {
			// TODO: handle finally clause
		}

	}

}
