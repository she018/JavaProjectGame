package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Level implements DrawableSimulable {

	private Point2D size;
	private final World world;
	private Image image;
	private Image ladder;
	
	private Point2D position;
	
	public Level(World world, Point2D size, Point2D position) {
		super();
		this.position = position;
		this.world = world;
		this.size = size;
		image = new Image(getClass().getResourceAsStream("images.jpg"), size.getX(), size.getY(),
				true, true);
		ladder = new Image(getClass().getResourceAsStream("lestnica.png"), size.getX(), size.getY()* 2,
				true, true);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		
		char [][]leveles = {
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{'A','A','L','A','A','A',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','L','A','A'},
				{' ',' ','L',' ',' ',' ',' ',' ','A','A','L',' ',' ',' ',' ',' ',' ','L',' ',' '},
				{' ',' ','L',' ',' ',' ',' ',' ','A','A','L',' ',' ',' ',' ',' ',' ','L',' ',' '},
				{' ',' ','L',' ',' ',' ',' ',' ','A','A','A','A','L','A','A','A','A','L','A','A'},
				{' ',' ','L',' ',' ',' ',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ','L',' ',' '},
				{'A','A','A','A','A','A','L','A','A','A','A','A','L',' ',' ',' ',' ','L',' ',' '},
				{' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ','L',' ',' ',' ',' ','L',' ',' '},
				{' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ',' ','L',' ',' ',' ',' ','L',' ',' '},
				{' ',' ',' ','L','A','A','A','A',' ',' ',' ',' ','A','A','A','A',' ','L',' ',' '},
				{' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ','L',' ',' '},
				{' ',' ',' ','L',' ',' ',' ',' ',' ',' ',' ',' ','L',' ',' ',' ',' ','L',' ',' '},
				{'A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A'}
				
		};
		
		
		int tmpx = 0;
		int tmpy = 30;
		for(int i = 12; i >= 0; i--) {
			for(int j = 0; j < 20; j++) {
				if(leveles[i][j] == 'A') {
					position = new Point2D(tmpx, tmpy);
					gc.save();
					Point2D canvasPosition = world.getCanvasPoint(position);
					gc.drawImage(image, canvasPosition.getX(), canvasPosition.getY());
					gc.restore();
					
				}
				else if(leveles[i][j] == 'L') {
					position = new Point2D(tmpx, tmpy);
					gc.save();
					Point2D canvasPosition = world.getCanvasPoint(position);
					gc.drawImage(ladder, canvasPosition.getX(), canvasPosition.getY());
					gc.restore();
					
				}
				tmpx += size.getX();
			}
			tmpy += size.getY();
			tmpx = 0;
		}
	}
	
	 @Override
	public void simulate(double timeDelta) {
	        
	}
	 
	 
	 
}
