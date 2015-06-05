import javax.persistence.*;
import java.io.*;

@Entity(name = "courselist")
public class CourseList implements Serializable {

  @Column(name = "course_name")
  private String course_name;
  
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column
  private String course_number;
  
  @Column
  private int enrollment;
  
  @Column
  private String start_date;
  
 @Column
  private String end_date;
  
  public String getCourseName() {
    return course_name;
  }

  public void setCourseName(String name) {
    this.course_name = name;
  }

  public String getCourseNumber() {
    return course_number;
  }

  public void setCourseNumber(String num) {
    this.course_number = num;
  }
  
  public int getEnrollment() {
	    return enrollment;
   }

   public void setEnrollment(int num) {
	    this.enrollment = num;
	}
   
   public String getStartDate() {
	    return start_date;
	}
   
   public void setStartDate(String date) {
	    this.start_date = date;
	}

   public String getEndDate() {
	    return end_date;
	}
   
   public void setEndDate(String date) {
	    this.end_date = date;
	}
   
   // return number of columns in the table
   public int getNumberOfColumns() {
	   return 5;
   }
   
   // return the data in column i
   public String getColumnData(int i) throws Exception {
	   if (i == 0)
		   return getCourseName();
	   else if (i == 1)
		   return getCourseNumber();
	   else if (i == 2) 
		   return Integer.toString(getEnrollment());
	   else if (i == 3)
		   return getStartDate();
	   else if (i == 4)
		   return getEndDate();
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
   // return the name of column i
   public String getColumnName(int i) throws Exception {
	   String colName = null;
	   if (i == 0) 
		   colName = "course_name";
	   else if (i == 1)
		   colName = "course_number";
	   else if (i == 2)
		   colName = "enrollment";
	   else if (i == 3)
		   colName = "start_date";
	   else if (i == 4)
		   colName = "end_date";
	   else 
		   throw new Exception("Access to invalid column number in courselist table");
	   
	   return colName;
   }
   
   // set data column i to value
   public void setColumnData(int i, Object value) throws Exception {
	   if (i == 0) 
		   course_name = (String) value;
	   else if (i == 1) 
		   course_number = (String) value;
	   else if (i == 2) 
		   enrollment =  Integer.parseInt((String) value);
	   else if (i == 3)
		   start_date = (String) value;
	   else if (i == 4)
		  end_date = (String) value;
	   else
		   throw new Exception("Error: invalid column index in courselist table");    
   }
   
  @Override
  public String toString() {
    return "CourseList [Course Name =" + course_name + ", "
    	    + " Course Number =" + course_number + ","
    	    + " Enrollment =" + enrollment + ","
    	    + " Start Date =" + start_date + ","
    	    + " End Date =" + end_date + ","
        + "]";
  }

}
