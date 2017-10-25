package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import hibernate.demo.entity.Review;
import hibernate.demo.entity.Student;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			Course math = new Course("math");
//			Course java = new Course("java");
//			Course html = new Course("html");
//			
			math.addReview(new Review("5 stars"));
			math.addReview(new Review("3 stars"));
			math.addReview(new Review("2 stars"));
			System.out.println(math.getReviews());
			session.save(math);
			
			session.getTransaction().commit();
			
		} finally {
			session.close();
			
			factory.close();
		}

	}

}
