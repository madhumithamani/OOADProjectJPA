import javax.persistence.*;
import java.util.*;

public class CourseListService {
	 private EntityManager manager;
	 
	 public CourseListService(EntityManager manager) {
		 this.manager = manager;
	 }
	 
    // method to create a new record
     public CourseList createCourse(String courseName, String courseNumber, int enrollment, String startDate, String endDate) {
    	CourseList course = new CourseList();
 	    course.setCourseName(courseName);
 	    course.setCourseNumber(courseNumber);
 	    course.setEnrollment(enrollment);
 	    course.setStartDate(startDate);
 	    course.setEndDate(endDate);
 	    manager.persist(course);
 	    return course;
 	    
 	    
     }
    
    // method to read a record
     public CourseList readCourse(String courseNumber) {
    	 CourseList course = manager.find(CourseList.class, courseNumber);
    	 return course;   	 
     }

     // method to read all records
     public List<CourseList> readAll() {
    	 TypedQuery<CourseList> query = manager.createQuery("SELECT e FROM courselist e", CourseList.class);
    	 List<CourseList> result =  query.getResultList();

    	 return result;   	 
     }
     
    // method to update a record
     public CourseList updateCourse(String courseName, String courseNumber, int enrollment, String startDate, String endDate) {
    	 CourseList course = manager.find(CourseList.class, courseNumber);
    	 if (course != null) {
    		 course.setCourseName(courseName);
    		 course.setEnrollment(enrollment);
    		 course.setStartDate(startDate);
    		 course.setEndDate(endDate);
    	 }
    	 return course;
     }

    // method to delete a record
    public void deleteCourse(String courseNumber) {
    	 CourseList course = manager.find(CourseList.class, courseNumber);
    	 if (course != null) {
    		 manager.remove(course);
    	 }
    }
}
