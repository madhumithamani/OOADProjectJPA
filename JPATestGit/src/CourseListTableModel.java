import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.table.*;
import javax.persistence.*;
import java.util.*;

/**
*
* @author rgrover
*/
public class CourseListTableModel extends AbstractTableModel {

	  List<CourseList> courseListResultList;   // stores the model data in a List collection of type CourseList
	  private static final String PERSISTENCE_UNIT_NAME = "PersistenceUnit";  // Used in persistence.xml
	  private static EntityManagerFactory factory;  // JPA  
	  private EntityManager manager;		      // JPA 
	  private CourseList courselist;// represents the entity courselist

      // This field contains additional information about the results   
	  private CourseListService courselistService;
	
	 
	  private int numcols, numrows; // number of rows and columns
	private EntityTransaction userTransaction;

	 CourseListTableModel() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    manager = factory.createEntityManager();
	    courselist = new CourseList();
	    courselistService = new CourseListService(manager);
	    
	    // read all th e records from courselist
	    courseListResultList = courselistService.readAll();
	    
	    // update the number of rows and columns in the model
	    numrows = courseListResultList.size();
	    numcols = courselist.getNumberOfColumns();
      }

	 // returns a count of the number of rows
	 public int getRowCount() {
		return numrows;
	 }
	
	 // returns a count of the number of columns
	 public int getColumnCount() {
		return numcols;
	 }
	
	 // returns the data at the given row and column number
	 public Object getValueAt(int row, int col) {
		try {
		  return courseListResultList.get(row).getColumnData(col);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	 }
	
	 // table cells are not editable
	 public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	 }
	
	 public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	 }
	
	 // returns the name of the column
	 public String getColumnName(int col) {
		   try {
				return courselist.getColumnName(col);
			} catch (Exception err) {
	             return err.toString();
	       }             
	 }
	
	 // update the data in the given row and column to aValue
	 public void setValueAt(Object aValue, int row, int col) {
		//data[rowIndex][columnIndex] = (String) aValue;
		try {
		   CourseList element = courseListResultList.get(row);
                   element.setColumnData(col, aValue); 
            fireTableCellUpdated(row, col);
		} catch(Exception err) {
			err.toString();
		}	
	 }
	
	 public List<CourseList> getList() {
		 return courseListResultList;
	 }

	 public EntityManager getEntityManager() {
	      return manager;
	 }
	 



	 // create a new table model using the existing data in list
	 public CourseListTableModel(List<CourseList> list, EntityManager em)  {
	    courseListResultList = list;
	    numrows = courseListResultList.size();
	    courselist = new CourseList();
	   	numcols = courselist.getNumberOfColumns();     
		manager = em;  
		courselistService = new CourseListService(manager);
	 }
	 
	 // In this method, a newly inserted row in the GUI is added to the table model as well as the database table
	 // The argument to this method is an array containing the data in the textfields of the new row.
	 public void addRow(Object[] array) {
		 // code in the method addRow in CourseListTableModel
		 EntityTransaction userTransaction = manager.getTransaction();  
		 userTransaction.begin();
		 CourseList newRecord = courselistService.createCourse((String) array[0], (String) array[1], 
				 												Integer.parseInt((String) array[2]), 
				 												(String) array[3], (String) array[4]);
		 userTransaction.commit();

		 // set the current row to rowIndex
		 courseListResultList.add(newRecord);
		 int row = courseListResultList.size();  
		 int col = 0;

		 // update the data in the model to the entries in array
		 for (Object data : array) {
			 setValueAt((String) data, row-1, col++);
		 }
		 numrows++;
	}	
}
