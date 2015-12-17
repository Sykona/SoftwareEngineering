import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main Class. 
 * 
 * @author Oliver Remy
 * @author Sebastian Strumegger
 */
public class Main extends Application{

 	StudentManager manager = new StudentManager();
 	final StudentManagerGUI gui = new StudentManagerGUI(manager);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {		
		launch(args);
	}
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage stage) throws Exception {
		manager.addObserver(gui);
		gui.start(stage);
	}

}
