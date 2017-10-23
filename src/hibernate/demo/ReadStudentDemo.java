package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			//SAVING Student
			Student tempStudent = new Student("Daffy", "Duck", "good@duck.com");		
			session.beginTransaction();		
			session.save(tempStudent);
			session.getTransaction().commit();
			
			//READING Student
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student resultStudent = session.get(Student.class, tempStudent.getId());
			session.getTransaction().commit();
			System.out.println(resultStudent);
		} finally {
			factory.close();
		}

	}

}
