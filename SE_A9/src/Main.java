import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	 StudentManager manager = new StudentManager();
	 final StudentManagerGUI gui = new StudentManagerGUI(manager);
	
	public static void main(String[] args) throws Exception {
//		StudentManager manager = new StudentManager();
//		gui = new StudentManagerGUI(manager);
		
//		manager.addObserver(gui);
		
		launch(args);
	}
	
	@Override
	public void start(final Stage stage) throws Exception {
		manager.addObserver(gui);
		gui.start(stage);
	}

}
