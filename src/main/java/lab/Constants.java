package lab;

import javafx.scene.image.Image;

public final class Constants {
	private Constants() {}
	
	public static final Image ENEMY_IMAGE;
	public static final Image RUNNER_IMAGE;
	public static final Image COIN_IMAGE;
	public static final Image BLOCK_IMAGE;
	public static final Image LADDER_IMAGE;
	
	static{ 
		ENEMY_IMAGE = new Image(Constants.class.getResourceAsStream("enemy.gif"));
		
		RUNNER_IMAGE = new Image(Constants.class.getResourceAsStream("fireball-transparent.gif"));
		
		COIN_IMAGE = new Image(Constants.class.getResourceAsStream("coin.gif"));
		
		BLOCK_IMAGE = new Image(Constants.class.getResourceAsStream("images.gif"));
		
		LADDER_IMAGE = new Image(Constants.class.getResourceAsStream("lestnica.gif"));
	}
}
