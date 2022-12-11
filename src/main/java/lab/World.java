package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Random;

public class World {

	private final static int NUMBER_OF_ENEMY = 3;
	
	private double width;
	private double height;
	
	private GameListener gameListener = new EmptyGameListener();
	
	private int score = 0;
	
	private DrawableSimulable []entities;
	
	private Runner runner;
	
	//private Level level;
	
	public World(double width, double height) {
		super();
		this.width = width;
		this.height = height;
		
		runner = new Runner(this, new Point2D(50,100), new Point2D(50,50)); //dopisat
		Level level = new Level(this, new Point2D(40,40), new Point2D(50,50));
		Gate gate = new Gate(this, new Point2D(760,515), new Point2D(50,50));
		//Coin coin = new Coin(this); //dopisat
		entities = new DrawableSimulable[] {
				new Enemy(this, new Point2D(430,400), new Point2D(20, 0), new Point2D(750,430)),
				new Enemy(this, new Point2D(150,320), new Point2D(20, 0), new Point2D(470,30)),
				new Enemy(this, new Point2D(120,80), new Point2D(20, 0), new Point2D(750,50)),
				new Coin(this, new Point2D(350,456), new Point2D(15,15)),
				new Coin(this, new Point2D(200,500), new Point2D(15,15)),
				new Coin(this, new Point2D(550,180), new Point2D(15,15)),
				new Coin(this, new Point2D(200,180), new Point2D(15,15)),
				new Coin(this, new Point2D(400,65), new Point2D(15,15)),
				
				new Coin(this, new Point2D(255,300), new Point2D(15,15)),
				
				runner,
				level, 
				gate
		};
	}
	
	public Point2D getCanvasPoint(Point2D worldPoint){
        return new Point2D(worldPoint.getX(),height - worldPoint.getY());
    }
	
	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		gc.fillRect(0, 0, 2000, 2000);
		
		//level.draw(gc);
		
		for(DrawableSimulable entity: entities) {
			entity.draw(gc);
		}
	}
	
	public void simulate(double timeDelta) {
		for(DrawableSimulable entity: entities) {
			entity.simulate(timeDelta);
			
		}
		
		for (DrawableSimulable i: entities) {
			if (i instanceof Collisionable obj1) {
				for (DrawableSimulable ii: entities) {
					if (i != ii && ii instanceof Collisionable obj2) {
						if (obj2.intersects(obj1)) {
							//obj2.hitBy(obj1);
							obj1.hitBy(obj2);
						}
					}
				}
			}
		}
	}
	
	public void action(int act) {
		if(act == 2) {
			gameListener.stateChanged(0); 
			gameListener.gameOver();
		}
		else if(act == 1) {
			gameListener.gameOver();
		}
		else if(act == 3) {
			hitOfBullet();
		}
	}
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	//???
	
	public void setGameListener(GameListener gameListenerImpl) {
		this.gameListener = gameListenerImpl;
		
	}
	
	private void hitOfBullet() {
		score += 100;
		gameListener.stateChanged(score); 
	}
	
	public int getScore() {
		return score;
	}
	
	public void setPositionUP(double actionx, double actiony) {
		runner.setControl(actionx, actiony);
	}
	
	public void setPositionDOWN(double actionx, double actiony) {
		runner.setControl(actionx, actiony);
	}

	public void setPositionLEFT(double actionx, double actiony) {
		runner.setControl(actionx, actiony);
	}

	public void setPositionRIGHT(double actionx, double actiony) {
		runner.setControl(actionx, actiony);
	}
}
