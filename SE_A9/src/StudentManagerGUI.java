import javafx.collections.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * The Class StudentManagerGUI.
 * Actually for javafx, this class should extend Application and contain a main function with launch(args).
 * But since we want to have the possibility of multiple observers on one manager, 
 * we encapsulated this class and run it's start method from an external main class.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class StudentManagerGUI implements Observer{

	private ObservableList<Student> students;
	private ListView<Student> listView; 
	private StudentManager manager;
	
	/**
	 * Instantiates a new student manager gui.
	 *
	 * @param manager the manager
	 */
	public StudentManagerGUI(StudentManager manager) {
		this.manager = manager;
		students = FXCollections.observableArrayList();
		listView = new ListView<Student>(students);
	}
    
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(final Stage stage) throws Exception {
		
		BorderPane root = new BorderPane();		
		
		// the add-button and its eventhandler
		Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				
				StudentDialog addDialog = new StudentDialog("Add new student", null);
			    addDialog.showAndWait().ifPresent(result -> manager.addStudent(result));
			}
		});
		
		// the rename-button and its eventhandler
		Button editButton = new Button("Rename");
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				StudentDialog editDialog = new StudentDialog("Rename student", listView.getSelectionModel().getSelectedItem());
			    editDialog.showAndWait().ifPresent(result -> {

			    	String newName = result.getName();
			    	String newSurname = result.getSurname();
			    	
			    	Student s = findStudent(result.getMatNr());
			    	manager.renameStudent(s, newName, newSurname);
			    });
			}
		});
		
		// the delete-button and its eventhandler
		Button delButton = new Button("Delete");
		delButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				
				Student selected = listView.getSelectionModel().getSelectedItem();
				manager.removeStudent(selected);
			}
		});
		
		
		ToolBar toolBar = new ToolBar(addButton, editButton, delButton);
		root.setTop(toolBar);
		root.setCenter(listView);
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Find s a student inside our ObservableList of students by matriculation number
	 *
	 * @param matNr the matriculation number
	 * @return the student
	 */
	private Student findStudent(int matNr) {
		
		for (Student s : students)
			if (s.getMatNr() == matNr)
				return s;
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see Observer#update(Student, StudentManager.Event)
	 */
	@Override
	public void update(Student s, StudentManager.Event e) {
		
		switch(e) {
			case ADD:		students.add(s);
							break;
							
			case RENAME:	students.set(students.indexOf(s), s);
							break;
							
			case REMOVE:	students.remove(s);
							break;
							
			default:		throw new IllegalArgumentException("StudentManager.Event." + e + " is not a valid event");
		}
	}
}
