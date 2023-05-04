package objects;

/**
 * This represents an extension of {@link objects.GameObject}
 * 
 */
public class Bullet extends GameObject{
	
	private final float bulletSpeed;
	
	/**
	 * Builds a bullet setting his values
	 * 
	 * @param x
	 * 		initial x position
	 * @param y
	 * 		initial y position
	 * @param width
	 * 		bullet width
	 * @param height
	 * 		bullet height
	 * @param imgPath
	 * 		bullet image file path
	 */
	public Bullet(float x, float y, int width, int height, String imgPath) {
		super(x, y, width, height, imgPath);
		this.bulletSpeed = 2f;
	}
	
	/**
	 * Updates the position of the Bullet 
	 * 
	 */
	private void updatePosition() {
		this.currentXPosition -= this.bulletSpeed;
		if(this.currentXPosition < 0) {
			this.destroy();
			this.currentXPosition = this.initXPosition;
		}
	}
	
	public void update() {
		this.updateHitBox();
		this.updatePosition();
	}
}
