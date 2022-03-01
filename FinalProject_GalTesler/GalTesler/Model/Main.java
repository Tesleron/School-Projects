package Model;
	
import Controller.Controller;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Model theModel = new Model();
		View theView = new View(primaryStage);
		Controller theController = new Controller(theModel,theView);		
	}	
	public static void main(String[] args) {
		launch(args);
	}
}