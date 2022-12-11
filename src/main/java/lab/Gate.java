package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gate implements DrawableSimulable, Collisionable {

	private World world;
	private Point2D position;
	private Point2D size;
	private Image image;
	
	public Gate(World world, Point2D position, Point2D size) {
		super();
		this.world = world;
		this.position = position;
		this.size = size;
		image = new Image(getClass().getResourceAsStream("gate.gif"), size.getX(), size.getY(),
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
		
	}
	
	public Rectangle2D getBoundingBox() {
		return new Rectangle2D(position.getX(), position.getY(), size.getX(), size.getY());
	}
	
	@Override
	public void hitBy(Collisionable other) {
		if (other instanceof Runner) {
			end();
		}
	}
	
	public void end() {
		world.action(1);
	}
	
}
