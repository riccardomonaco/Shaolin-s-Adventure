package entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import static utils.Constants.PanelConstants.*;
import static utils.Constants.PlayerConstants.HITBOX_H_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_W_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_X_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_Y_OFFSET;

public class Entity {

	protected float xPosition;
	protected float yPosition;
	protected Rectangle2D.Float hitBox;
	
	protected int tickAnimation;
	protected int indexAnimation;
	protected int speedAnimation;
	
	public Entity(float x, float y) {
		
		this.xPosition = x;
		this.yPosition = y;
		this.setDefaults();
	}
	
	public void setDefaults() {
		
		//initializing animations support counters
		this.indexAnimation = 0;
		this.tickAnimation = 0;
		this.speedAnimation = 35;
	}
	
	
	/*
	 * setting the hitbox relative to the offset of the entity
	 */
	
	protected void initHitBox(int HITBOX_W_OFFSET, int HITBOX_H_OFFSET, int HITBOX_X_OFFSET, int HITBOX_Y_OFFSET) {
		
		this.hitBox = new Rectangle2D.Float(xPosition + HITBOX_X_OFFSET, 
											yPosition + HITBOX_Y_OFFSET, 
											TILE_SIZE - HITBOX_W_OFFSET, 
											TILE_SIZE - HITBOX_H_OFFSET);
	}
	
	
	/*
	 * updating the position of the hitbox with the player movement, applying offsets
	 */
	
	protected void updateHitBox(int HITBOX_X_OFFSET, int HITBOX_Y_OFFSET) {
		
		this.hitBox.x = (xPosition + HITBOX_X_OFFSET);
		this.hitBox.y = (yPosition + HITBOX_Y_OFFSET);
	}
	
	
	/*
	 * getting the hitbox object
	 */
	
	public Rectangle2D.Float getHitBox() {
		
		return this.hitBox;
	}
	
	public void draw(Graphics2D g, int offset) {}
	
	public void update() {}
}

