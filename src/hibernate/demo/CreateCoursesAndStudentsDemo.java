package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import hibernate.demo.entity.Review;
import hibernate.demo.entity.Student;

public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Course math = new Course("math");
			Course java = new Course("java");
			Course html = new Course("html");
			
			Student ivan = new Student("Ivan", "Bozhkov", "ok@abv.bg");
			Student gosho = new Student("Gosho", "Mikov", "no@abv.bg");
			
			math.addStudent(ivan);
			java.addStudent(gosho);
			java.addStudent(ivan);
			html.addStudent(new Student("Chicho", "Pencho", "mail@mail.mail"));
			
			session.persist(math);
			session.persist(java);
			session.persist(html);
			
			session.getTransaction().commit();
			
			System.out.println("done");
		} finally {
			session.close();
			
			factory.close();
		}

	}

}
