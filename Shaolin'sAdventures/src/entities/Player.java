package entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import levels.Level;

import utils.LoadSave;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PanelConstants.*;

import static utils.HelpMethods.*;

/**
 * This represent an extension of {@link entities.Entity}
 * 
 * This represent the Player implementation
 *
 */
public class Player extends Entity{
	
	private float playerSpeed;
	private float airSpeed;
	private float jumpSpeed;
	private float gravitySpeed;
	private float fallSpeed;
	
	private boolean inAir;
	private boolean isMoving;
	private boolean isJumping;
	
	private boolean goingRight;
	private boolean goingLeft;
	
	private int playerAction;
	private int tickAnimation;
	private int indexAnimation;
	private int speedAnimation;
		
	private HashMap<Integer, BufferedImage[]> playerAnimations = new HashMap<>();
	
	private Level currentLevel; 

	/**
	 * Builds a Player setting his initial position,
	 * attributes and hitbox
	 */
	public Player() {
		super(INITPLAYERX, INITPLAYERY);
		this.setDefaults();
		this.initHitBox(HITBOX_W_OFFSET, HITBOX_H_OFFSET, HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaults() {
		/*
		 * initializing player forces
		 */
		this.playerSpeed = 2f;
		this.airSpeed = 0f;
		this.jumpSpeed = -6f;
		this.gravitySpeed = 0.05f * SCALE;
		this.fallSpeed = 0.05f * SCALE;
		
		/*
		 * initializing animations support counters
		 */
		this.indexAnimation = 0;
		this.tickAnimation = 0;
		this.speedAnimation = 35;
				
		/*
		 * setting initial value for actions
		 */
		this.playerAction = IDLE;
		this.isMoving = false;
		this.isJumping = false;
		this.inAir = true;
		
		/*
		 * initializing direction
		 */
		this.goingLeft = false;
		this.goingRight = false;
		
		/*
		 * initializing player sprites
		 */
		this.playerAnimations = LoadSave.getPlayerSprites();
	}
	
	/**
	 * Sets the going right reference
	 * 
	 * @param value
	 * 		direction related boolean
	 */
	public void setRight(boolean value) {
		this.goingRight = value;
	}
	
	/**
	 * Sets the going left reference
	 * 
	 * @param value
	 * 		direction related boolean
	 */
	public void setLeft(boolean value) {
		this.goingLeft = value;
	}
	
	/**
	 * Sets the jump reference
	 * 
	 * @param value
	 * 		jump related boolean
	 */
	public void setJump(boolean value) {
		this.isJumping = value;
	}
	
	/**
	 * Updates the player horizontal
	 * and vertical position
	 */
	private void updatePosition(){
		isMoving = false;
		if(!goingLeft && !goingRight && !inAir && !isJumping) return; 	
		if(isJumping) jump();
		
		float xSpeed = 0;
		if(goingRight) xSpeed += playerSpeed;
		if(goingLeft) xSpeed -= playerSpeed;
		/*
		 * Sets inAir if the player is not in air
		 * and neither on the floor
		 */
		if(!inAir) {
			if(!isOnTheFloor(hitBox, currentLevel.getMap())) {
				inAir = true;
			}
		}
		/*
		 * Updates "in air" Player position
		 * while updating the horizonal position
		 */
		if(inAir) {
			if(canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, currentLevel.getMap())) {
				yPosition += airSpeed;
				airSpeed += gravitySpeed;
				updateXPos(xSpeed);				
			}else {
				if(airSpeed > 0) {
					inAir = false;
					airSpeed = 0;
				}else airSpeed = fallSpeed;
			}
		}else updateXPos(xSpeed);
		isMoving = true;
	}
	
	/**
	 * Sets the jump relative airSpeed
	 */
	private void jump() {
		if(inAir) return;
		else {
			inAir = true;
			airSpeed = jumpSpeed;
		}
	}

	/**
	 * Checks if the player can move in a place
	 * and updates his position
	 * 
	 * @param xSpeed
	 * 		player x shift
	 */
	private void updateXPos(float xSpeed) {
		if(canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, currentLevel.getMap())) xPosition += xSpeed;
	}
	
	/**
	 * Sets the Player animation based on 
	 * the current action
	 */
	private void setAnimation() {
		int aniCheck = playerAction;
		if (isJumping) playerAction = JUMPING;
		else if (isMoving) playerAction = RUNNING;
		else playerAction = IDLE;
		/*
		 * if the action is changed, restarts the animation from the first "frame"
		 */
		if(playerAction != aniCheck) {
			this.resetAnimation();
		}	
	}
	
	/**
	 * Resets animation related values
	 */
	private void resetAnimation() {
		this.indexAnimation = 0;
		this.tickAnimation = 0;
	}
	
	/**
	 * Switches between Player sprites to create the animation
	 * based on the current action
	 */
	private void updateAnimation() {
			this.tickAnimation++;
			if(this.tickAnimation >= this.speedAnimation) {
				this.indexAnimation++;
				if(this.indexAnimation >= this.playerAnimations.get(playerAction).length) {
					this.indexAnimation = 0;
					this.isJumping = false;
				}
				this.tickAnimation = 0;
			}
	}
	
	/**
	 * Resets direction values and stops the Player
	 */
	public void resetDirections() {
		this.goingLeft = this.goingRight = false;
	}
	
	/**
	 * Set the current level 
	 * 
	 * @param level
	 * 		level to be set
	 */
	public void setCurrentLevel(Level level) {
		this.currentLevel = level;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(int offset) {
		this.updatePosition();
		this.updateHitBox(HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		this.setAnimation();
		this.updateAnimation();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics2D g, int currentOffset) {
		g.drawImage(playerAnimations.get(playerAction)[indexAnimation], (int)xPosition - currentOffset, (int)yPosition, TILE_SIZE, TILE_SIZE, null);
	}

	/**
	 * Test purposes method which
	 * returns the player speed
	 *
	 * @return player speed
	 */
	public float getPlayerSpeed(){ return this.playerSpeed; }

	/**
	 * Test purposes method which
	 * returns the player x coordinate
	 *
	 * @return player x coordinate
	 */
	public float getPlayerX(){ return this.xPosition; }
}
