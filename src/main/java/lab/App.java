package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 *  Class <b>App</b> - extends class Application and it is an entry point of the program
 * @author     Java I
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	private GameController controller;
	@Override
	public void start(Stage primaryStage) {
		try {
			//Construct a main window with a canvas.  
			
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("GameView.fxml"));
			
			BorderPane root = loader.load();
			
			Scene scene = new Scene(root);
			
			
			scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent event) {
					switch(event.getCode()) {
					case UP: //goNorth = false;
						controller.setPositionUP(0, -1);
						break;
					case DOWN: //goSouth = false;
						controller.setPositionDOWN(0, 1);
						break;
					case LEFT: //goWest = false; 
						controller.setPositionLEFT(1, 0);
						break;
					case RIGHT: //goEast = false; 
						controller.setPositionLEFT(-1, 0);
						break;
					}
				}
			});
			
			
			
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("Java 1 - 6th laboratory");
			primaryStage.show();
			
			controller = loader.getController();
			controller.startGame();
			//Exit program when main window is closed
			primaryStage.setOnCloseRequest(this::exitProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	private void exitProgram(WindowEvent evt) {
		controller.stopGame();
		System.exit(0);
	}
}