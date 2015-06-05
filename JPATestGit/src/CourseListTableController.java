import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


/**
* Glue between the view (CourseListGUI) and the model (CourseListTableModel). 
* No database specific code here. Contains a reference to both the TableModel and the graphical user interface.
* @author rgrover
*/
public class CourseListTableController implements ListSelectionListener, TableModelListener {
	private CourseListTableModel tableModel;
	private CourseListGUI gui;
	
	public CourseListTableController(CourseListGUI gui) {
		this.gui = gui;   
         // create the tableModel using the data in the cachedRowSet
    		tableModel = new CourseListTableModel(); 
    		tableModel.addTableModelListener(this);
	}
	
	
	// new code
	public TableModel getTableModel() {
		return tableModel;
	}
	
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectModel = (ListSelectionModel) e.getSource();
		int firstIndex = selectModel.getMinSelectionIndex();
		
		// read the data in each column using getValueAt and display it on corresponding textfield
		gui.setCourseNameTextField( (String) tableModel.getValueAt(firstIndex, 0));
		gui.setCourseNumberTextField( (String) tableModel.getValueAt(firstIndex, 1));
		gui.setEnrollmentTextField( (String) tableModel.getValueAt(firstIndex, 2));
		gui.setStartDateTextField( (String) tableModel.getValueAt(firstIndex, 3));
		gui.setEndDateTextField( (String) tableModel.getValueAt(firstIndex, 4));
	}
	
	public void addRow(String[] array) {
		tableModel.addRow(array);			
	}


	@Override
	public void tableChanged(TableModelEvent e) {
		try {
	    	// get the index of the inserted row
	        //tableModel.getRowSet().moveToCurrentRow();
	    	int firstIndex =  e.getFirstRow();
	    	
	    	// create a new table model with the new data
	        tableModel = new CourseListTableModel(tableModel.getList(), tableModel.getEntityManager());
	        tableModel.addTableModelListener(this);
	        // update the JTable with the data
	    	gui.updateTable();
	    
	        // read the data in each column using getValueAt and display it on corresponding textfield
			gui.setCourseNameTextField( (String) tableModel.getValueAt(firstIndex, 0));
			gui.setCourseNumberTextField( (String) tableModel.getValueAt(firstIndex, 1));
			gui.setEnrollmentTextField( (String) tableModel.getValueAt(firstIndex, 2));
			gui.setStartDateTextField( (String) tableModel.getValueAt(firstIndex, 3));
			gui.setEndDateTextField( (String) tableModel.getValueAt(firstIndex, 4));
	} catch(Exception exp) {
		exp.getMessage();
		exp.printStackTrace();
	}

		
	}
	
	
}
