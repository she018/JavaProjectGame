package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Coin implements DrawableSimulable, Collisionable {

	private Point2D position;
	private Point2D size;
    private final World world;
    private Image image;
    
    public Coin (World world, Point2D position, Point2D size) {
    	super();
    	this.world = world;
    	this.position = position;
    	this.size = size;
    	image = new Image(getClass().getResourceAsStream("coin.gif"), size.getX(), size.getY(),
				true, true);
    }
    
    
    public void draw(GraphicsContext gc) {
    	
    	gc.save();
		Point2D canvasPosition = world.getCanvasPoint(position);
		gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
		gc.restore();
	}

	public void simulate(double deltaT) {
		
	}
    
    
    public Rectangle2D getBoundingBox() {
		return new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY() * 2);
	}
	
	public boolean overlaps(Runner runner) {
		return getBoundingBox().intersects(runner.getBoundingBox());
	}
    
    
    public void hitBy(Collisionable other) {
		
		if (other instanceof Runner) {
			reload();
		}
		
	}
	
	public void reload() {
		
		size = new Point2D(0,0);
		position = new Point2D(-1,-1);
		world.action(3);
	}

	public void fire() {
		
	}
    
	
}
