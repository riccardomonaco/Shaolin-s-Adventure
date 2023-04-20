package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import levels.Level;

import utils.LoadSave;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PanelConstants.*;

import static utils.HelpMethods.*;

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
	
	private int currentOffset;
	
	private HashMap<Integer, BufferedImage[]> playerAnimations = new HashMap<>();
	
	private Level currentLevel; 

	public Player() {
		
		super(INITPLAYERX, INITPLAYERY);
		
		this.setDefaults();
		this.initHitBox(HITBOX_W_OFFSET, HITBOX_H_OFFSET, HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
	}
	
	public void setCurrentLevel(Level level) {
		
		this.currentLevel = level;
	}
	
	
	/*
	 * setting the defaults initial values
	 */
	
	public void setDefaults() {
		
		//initializing various forces
		this.playerSpeed = 2f;
		this.airSpeed = 0f;
		this.jumpSpeed = -6f;
		this.gravitySpeed = 0.05f * SCALE;
		this.fallSpeed = 0.05f * SCALE;
		
		//initializing animations support counters
		this.indexAnimation = 0;
		this.tickAnimation = 0;
		this.speedAnimation = 35;
				
		//setting initial value for actions
		this.playerAction = IDLE;
		this.isMoving = false;
		this.isJumping = false;
		this.inAir = true;
		
		//initializing direction
		this.goingLeft = false;
		this.goingRight = false;
		
		//getting animations sprites
		this.playerAnimations = LoadSave.getPlayerSprites();
		
		this.currentOffset = 0;
	}
	
	
	public void setRight(boolean value) {
		
		this.goingRight = value;
	}
	
	public void setLeft(boolean value) {
		
		this.goingLeft = value;
	}
	
	public void setJump(boolean value) {
		
		this.isJumping = value;
	}
		
	
	/*
	 *  updating the player position checking if left or right key is pressed
	 */
	
	private void updatePosition(){
		
		isMoving = false;
		
		if(!goingLeft && !goingRight && !inAir && !isJumping) return; 	
		
		float xSpeed = 0;
		
		if(isJumping) jump();
		if(goingRight) xSpeed += playerSpeed;
		if(goingLeft) xSpeed -= playerSpeed;
		
		if(!inAir) {
			if(!isOnTheFloor(hitBox, currentLevel.getMap())) {
				inAir = true;
			}
		}
		
		if(inAir) {
			if(canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, currentLevel.getMap())) {
				yPosition += airSpeed;
				airSpeed += gravitySpeed;
				updateXPos(xSpeed);
				
		/*	}else if(!canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, currentLevel.getMap())) {
				if(isJumping) jump();	*/
				
			}else {
				if(airSpeed > 0) {
					inAir = false;
					airSpeed = 0;
				}else {
					airSpeed = fallSpeed;
				}
			}
		}else updateXPos(xSpeed);
		
		isMoving = true;
	}
	
	
	private void jump() {
		
		if(inAir) return;
		else {
			inAir = true;
			airSpeed = jumpSpeed;
		}
	}


	private void updateXPos(float xSpeed) {
		
		if(canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, currentLevel.getMap())) {
			
			xPosition += xSpeed;
		}
		
	}
	
	
	/*
	 *  setting the player animation based on the current action he's doing
	 */

	private void setAnimation() {
		
		int aniCheck = playerAction;
		
		if (isJumping) playerAction = JUMPING;
		else if (isMoving) playerAction = RUNNING;
		else playerAction = IDLE;
		
		//if the action is changed, restart the animation from the first "frame"
		if(playerAction != aniCheck) {
			this.resetAnimation();
		}	
	}
	
	
	/*
	 * re-initializing animation related values
	 */
	
	private void resetAnimation() {
		
		this.indexAnimation = 0;
		this.tickAnimation = 0;
	}
	
	
	/*
	 * 	flowing through different sprites of an action animation every specific amount of time [speedAnimation]
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
	

	/*
	 * resetting direction values and stopping the sprite
	 */
	
	public void resetDirections() {
		
		this.goingLeft = this.goingRight = false;
	}
	
	
	/*
	 * updating the player values
	 */
	
	public void update() {
		
		//updating the player position and hitbox position
		this.updatePosition();
		this.updateHitBox(HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		
		//updating the sprite based on the animation
		this.setAnimation();
		this.updateAnimation();
	}
	
	
	/*
	 * drawing the player
	 */
	
	public void draw(Graphics2D g) {
		
		g.drawImage(playerAnimations.get(playerAction)[indexAnimation], (int)xPosition - currentOffset, (int)yPosition, TILE_SIZE, TILE_SIZE, null);
		//System.out.println(xPosition+" "+yPosition);
	}

	public void setOffset(int currentOffset) {
		
		this.currentOffset = currentOffset;
	}
}
