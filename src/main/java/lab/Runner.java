package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Affine;
import javafx.scene.image.Image;

public class Runner implements DrawableSimulable, Collisionable {

	private Point2D position;
	private Point2D size;
    private Point2D changePosition;
    private final World world;
    private Image image;
    
    public Runner(World world, Point2D position, Point2D size) {
    	super();
    	this.world = world;
		this.position = position;
		this.size = size;
		image = new Image(getClass().getResourceAsStream("fireball-transparent.gif"), size.getX(), size.getY(),
				true, true);
		this.changePosition = new Point2D(0,0);
    }
    
    @Override
	public void draw(GraphicsContext gc) {
		
    	gc.save();
		
    	if((position.getX() >= 120 && position.getX() <= 135 && (position.getY() <= 185 || position.getY() >= 40)) 
    			|| (position.getX() >= 480 && position.getX() <= 500 && (position.getY() <= 390 || position.getY() >= 170))
    			|| (position.getX() >= 400 && position.getX() <= 420 && (position.getY() <= 460 || position.getY() >= 360))
    			|| (position.getX() >= 680 && position.getX() <= 700 && (position.getY() <= 500 || position.getY() >= 360))
    			|| (position.getX() >= 80 && position.getX() <= 100 && (position.getY() <= 497 || position.getY() >= 280))
    			|| (position.getX() >= 240 && position.getX() <= 255 && (position.getY() <= 310 || position.getY() >= 160))) {
    		position = new Point2D(position.getX() - changePosition.getX(), position.getY() - changePosition.getY());
    	}
    	else {
    		position = new Point2D(position.getX() - changePosition.getX(), position.getY());
    	}
    	
		Point2D canvasPosition = world.getCanvasPoint(position);
		gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
		
		gc.restore();
	}
    
    @Override
	public void simulate(double timeDelta) {
        
	 }
    
    @Override
	public Rectangle2D getBoundingBox(){
		
    	return new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY());
	}
    
    @Override
	public void hitBy(Collisionable other) {
		if (other instanceof Enemy) {
			hit();
		}
		if (other instanceof Enemy) {
			//hit();
		}
	}
    
    public void hit() {
		
	}
    
    

	public void setControl(double valuex, double valuey) {
		changePosition = new Point2D(valuex, valuey);
	}
}
