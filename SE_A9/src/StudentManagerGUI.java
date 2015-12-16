import javafx.application.Application;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class StudentManagerGUI extends Application implements Observer{

	private ObservableList<Student> students;
	private ListView<Student> listView; 
	private StudentManager manager;
	
	public StudentManagerGUI(StudentManager manager) {
		this.manager = manager;
		students = FXCollections.observableArrayList();
		listView = new ListView<Student>(students);
	}
    
	public void start(final Stage stage) throws Exception {
		
		BorderPane root = new BorderPane();		
		
		Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { 
				
				StudentDialog addDialog = new StudentDialog("Add new student", null);
			    addDialog.showAndWait().ifPresent(result -> manager.addStudent(result));
			}
		});
		
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
	
	private Student findStudent(int matNr) {
		
		for (Student s : students)
			if (s.getMatNr() == matNr)
				return s;
		
		return null;
	}
	
	@Override
	public void update(Student s, StudentManager.Event e) {
		
		switch(e) {
			case ADD:		// manager.addStudent(s);
							students.add(s);
							break;
							
			case RENAME:	// manager.renameStudent(s, s.getName(), s.getSurname());
							students.set(students.indexOf(s), s);
							break;
							
			case REMOVE:	// manager.removeStudent(s);
							students.remove(s);
							break;
							
			default:		throw new RuntimeException("StudentManager.Event." + e + " is not a valid event");
		}
	}
}
