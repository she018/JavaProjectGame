package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy implements DrawableSimulable, Collisionable {

	private Point2D position;
	
	private Point2D speed;
	
	private final World world;
		
	private final double size = 50;
	
	private Image image;
	
	private Point2D bound;
	
	//private Image image;
	
	public Enemy(World world, Point2D position, Point2D speed, Point2D bound) {
		super();
		this.world = world;
		this.position = position;
		this.speed = speed;
		this.bound = bound;
		image = new Image(getClass().getResourceAsStream("enemyRevers.gif"), size, size,
				true, true);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		gc.save();
		Point2D canvasPosition = world.getCanvasPoint(position);
		gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
		gc.restore();
	}

	@Override
	public void simulate(double timeDelta) {
		int tmp = 1;
		double timeDeltaS = timeDelta;
		double newX = (position.getX() + speed.getX() * timeDeltaS + world.getWidth()) % world.getWidth(); 
		double newY = (position.getY() + speed.getY() * timeDeltaS + world.getHeight()) % world.getHeight();
		position = new Point2D(newX, newY);
		if(newX >= bound.getX()) {
			speed = new Point2D(-speed.getX(), speed.getY());
			if(tmp % 2 == 0) {
				image = new Image(getClass().getResourceAsStream("enemyRevers.gif"), size, size,
						true, true);
			}
			else {
				image = new Image(getClass().getResourceAsStream("enemy.gif"), size, size,
						true, true);
			}
			tmp++;
		}
		else if (newX <= bound.getY()) {
			speed = new Point2D(-speed.getX(), speed.getY());
			image = new Image(getClass().getResourceAsStream("enemy.gif"), size, size,
					true, true);
		}
		
	}


	public Rectangle2D getBoundingBox() {
		return new Rectangle2D(position.getX(), position.getY(), size, size);
	}
	
	@Override
	public void hitBy(Collisionable other) {
		if (other instanceof Runner) {
			hit();
		}
	}
	
	public void hit() {
		world.action(2);
	}
}
