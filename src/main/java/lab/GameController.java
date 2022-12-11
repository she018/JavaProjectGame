package lab;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GameController {

	private World world;
	
	@FXML
	private Canvas canvas;
	
	@FXML
	private Label score;
	
	@FXML 
	private TableView<Score> scoreList;
	
	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextField playerName;
	
	private List<Score> highScores = new LinkedList<>();
	
	private AnimationTimer animationTimer;
	
	private ScoreDAO scoreDAO = new ScoreDAO();
	
	public GameController() {
	}
	
	public void startGame() {
		
		TableColumn<Score, String> nameColumn = new TableColumn<>("Jméno");
		nameColumn.setPrefWidth(75);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		
		TableColumn<Score, Integer> scoreColumn = new TableColumn<>("Skóre");
		scoreColumn.setPrefWidth(75);
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		scoreList.getColumns().add(nameColumn);
		scoreList.getColumns().add(scoreColumn);
		initScoreList();
		
		this.world = new World(canvas.getWidth(), canvas.getHeight());
		this.world.setGameListener(new GameListenerImpl());
		animationTimer = new AnimationTimerImpl();
		
		
		
		animationTimer.start();
	}
	
	
	public void setPositionUP(double actionx, double actiony) {
		world.setPositionUP(actionx, actiony);
	}
	
	public void setPositionDOWN(double actionx, double actiony) {
		world.setPositionDOWN(actionx, actiony);
	}
	
	public void setPositionLEFT(double actionx, double actiony) {
		world.setPositionLEFT(actionx, actiony);
	}

	public void setPositionRIGHT(double actionx, double actiony) {
		world.setPositionRIGHT(actionx, actiony);
	}
	
	
	@FXML
	private void gameOverPressed() {
		//Score score = new Score(new Random().nextInt(11), nameTextField.getText());
		Score score = new Score(world.getScore(), nameTextField.getText());
		scoreList.getItems().add(score);
		Collections.sort(scoreList.getItems(),  new ScoreComparator().reversed());
	}
	
	@FXML
	private void highScoresPressed() {
		Set<Score> tempScores = new HashSet<>();
		tempScores.addAll(highScores);
		highScores.clear();
		highScores.addAll(tempScores);
		Collections.sort(highScores,  new ScoreComparator().reversed());
		initScoreList();
	}

	@FXML
	private void savePressed() throws IOException {
		scoreDAO.save(highScores);
	}
	
	
	@FXML
	private void loadPressed() throws IOException{
		highScores.clear();
		highScores.addAll(scoreDAO.load());
		initScoreList();
	}

	private void initScoreList() {
		scoreList.setItems(FXCollections.observableList(highScores));
	}
	
	public void stopGame() {
		animationTimer.stop();
	}
	
	private void drawScene(double deltaT) {
		world.draw(canvas);
		world.simulate(deltaT);
	}
	
	private final class AnimationTimerImpl extends AnimationTimer {
		private Long previous;

		@Override
		public void handle(long now) {
			if (previous == null) {
				previous = now;
			} else {
				drawScene((now - previous)/1e9);
				previous = now;
			}
		}
	}
	
	private class GameListenerImpl implements GameListener {

		@Override
		public void stateChanged(int score) {
			GameController.this.score.setText("" + score);
		}

		@Override
		public void gameOver() {
			stopGame();
		}
		
	}
}
