package objects;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.spi.AbstractResourceBundleProvider;

import utils.LoadSave;
import utils.Constants.PanelConstants;

import static utils.Constants.ObjectsConstants.*;
import static utils.Constants.PanelConstants.*;


/**
 * This represents the generic game object
 *
 */
public class GameObject {

	protected float initXPosition;
	protected float initYPosition;
	protected float currentXPosition;
	protected int width;
	protected int height;
	
	protected boolean isActive;
	
	protected Rectangle2D.Float hitBox;
	protected BufferedImage objectImage;
	
	/**
	 * Builds a new game objects defining his initial position
	 * 
	 * @param x
	 * 		initial x position
	 * @param y
	 * 		initial y position
	 */
	public GameObject(float x, float y, int width, int height, String imgPath) {
		this.initXPosition = x;
		this.currentXPosition = x;
		this.initYPosition = y;
		this.width = width;
		this.height = height;
		this.isActive = false;
		this.objectImage = LoadSave.getImage(imgPath);
		this.initHitBox();
	}
	
	/**
	 * sets the hitbox of the object
	 * 
	 */
	protected void initHitBox() {
		this.hitBox = new Rectangle2D.Float((int)initXPosition, 
											(int)initYPosition, 
											BULLET_W_HB, 
											BULLET_H_HB);
	}
	
	/**
	 * updates the position of the hitbox
	 * 
	 */
	public void updateHitBox() {
		this.hitBox.x = (currentXPosition);
		this.hitBox.y = (initYPosition);
	}
	
    /**
     * Returns the entity's hitbox
     * 
     * @return the hitbox of this entity
     */
	public Rectangle2D.Float getHitBox() {	
		return this.hitBox;
	}
	
	//test
	public void drawHitBox(Graphics2D g) {
		g.draw(this.hitBox);
	}
	
	/**
	 * Activates the object which is drawn and updated
	 * 
	 */
	public void activate() {
		this.isActive = true;
	}

	/**
	 * Destroys the object, it is no longer drawn
	 * and updated
	 * 
	 */
	public void destroy() {
		this.isActive = false;
	}
	
	/**
	 * Returns the status of the object
	 * 
	 * @return if is currently active
	 * 
	 */
	public boolean getStatus() {
		return this.isActive;
	}
	
	/**
	 * Draws the game object
	 * 
	 * @param g
	 * 		java drawing object
	 */
	public void draw(Graphics2D g, int offset){
		g.drawImage(objectImage, (int)this.currentXPosition - offset, (int)this.initYPosition, this.width, this.height, null);
	}
}
