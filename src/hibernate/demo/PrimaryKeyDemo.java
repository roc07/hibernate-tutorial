package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			Student tempStudent1 = new Student("Sec", "StudentSec", "good@man1.com");
			Student tempStudent2 = new Student("Thre", "StudentThre", "good@man2.com");
			Student tempStudent3 = new Student("Four", "StudentFour", "good@man3.com");
			
			session.beginTransaction();
			
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}

	}

}
