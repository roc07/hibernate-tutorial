package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			//UDATEING Student
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Student resultStudent = session.get(Student.class, studentId);
			resultStudent.setFirstName("UpdatedFirstStudent");
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("update Student set email='%!*(@.com' where lastName like 'S%'")
							.executeUpdate();
			session.getTransaction().commit();			
			
			System.out.println(resultStudent);
		} finally {
			factory.close();
		}

	}

}
