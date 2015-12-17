import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * The Class StudentDialog.
 * Extends the JavaFX Dialog to create a custom dialog.
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class StudentDialog extends Dialog<Student>{

    private TextField matNrTF = new TextField();
    private TextField nameTF = new TextField();
    private TextField surnameTF = new TextField();

    
    /**
     * Instantiates a new student dialog.
     *
     * @param title the title
     * @param student the student
     */
    public StudentDialog(String title, Student student) {
    	
        setTitle(title);
        
        GridPane dPane = new GridPane();
        Label matNrLabel = new Label("Matricular Nr");
        Label nameLabel= new Label("Name: ");
        Label surnameLabel = new Label("Surname: ");       
        
        // a student given in the constructor means, we rename this student
        // the matriculation number must not be editable
        if (student != null) {
        	matNrTF.setText("" + student.getMatNr());
        	matNrTF.setEditable(false);
        	nameTF.setText(student.getName());
        	surnameTF.setText(student.getSurname()); 	
        }
        
        dPane.setHgap(7D);
        dPane.setVgap(8D);
        GridPane.setConstraints(matNrLabel, 0, 0);
        GridPane.setConstraints(matNrTF, 1, 0);
        GridPane.setConstraints(nameLabel, 0, 1);
        GridPane.setConstraints(nameTF, 1, 1);
        GridPane.setConstraints(surnameLabel, 0, 2);
        GridPane.setConstraints(surnameTF, 1, 2);

        ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        dPane.getChildren().addAll(matNrLabel, nameLabel, surnameLabel, matNrTF, nameTF, surnameTF);
        getDialogPane().getButtonTypes().addAll(ok, cancel);
        getDialogPane().setContent(dPane);        
        
        
        // if the input is not valid, consume the event
        final Button OKButton = (Button) getDialogPane().lookupButton(ok);
        OKButton.addEventFilter(ActionEvent.ACTION, event -> {
        	if (!validateInput()) {
        		event.consume();
        	}
	   	    	 
        });
        
        // since we have a custom dialog, the resultconverter needs to be set
        // it returns a new student as a result
        setResultConverter(button -> {
        	if (button == ok) 	{
        		return new Student(Integer.parseInt(matNrTF.getText()), nameTF.getText(), surnameTF.getText());	
        	}
  
        	return null;
        });
	}
    
    
    /**
     * Validates input and colors wrong/right input fields
     * Rules:
     * 		- Each Textfield must not be empty
     * 		- The matriculation number must be an integer
     *
     * @return true, if successful
     */
    private boolean validateInput() {
    	String matNr = "^\\d+$";
    	String name = "^.+$";
    	String RedBorder = "-fx-text-box-border: red;";
    	String greenBorder = "-fx-text-box-border: green;";
    	
    	boolean valid = true;
    	
    	if (!matNrTF.getText().matches(matNr)) {
    		matNrTF.setStyle(RedBorder);
    		valid = false;
    	}
    	else 
    		matNrTF.setStyle(greenBorder);
    	
    	if (!nameTF.getText().matches(name)) {
    		nameTF.setStyle(RedBorder);
    		valid = false;
    	}
    	else
    		nameTF.setStyle(greenBorder);
    	
    	if (!surnameTF.getText().matches(name)) {
    		surnameTF.setStyle(RedBorder);
    		valid = false;
    	}
    	else
    		surnameTF.setStyle(greenBorder);
    		
    	return valid;
    }
}
