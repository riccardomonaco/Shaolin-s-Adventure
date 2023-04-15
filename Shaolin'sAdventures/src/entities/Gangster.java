package entities;
import static utils.Constants.PanelConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.HITBOX_H_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_W_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_X_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_Y_OFFSET;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class Gangster extends Entity{
	
	private float gangsterSpeed;
	private boolean direction;
	private int tickDirection;
	private BufferedImage[] gangsterAnimations;
	
	

	public Gangster(int initXPosition, int initYPosition) {
		
		super(initXPosition, initYPosition);
		
		this.initHitBox(HITBOX_W_OFFSET, HITBOX_H_OFFSET, HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		this.gangsterAnimations = LoadSave.getEnemySprites().get(0);
		
		this.gangsterSpeed = 1f;
		this.direction = true;
		
		//initializing animations support counters
		this.indexAnimation = 0;
		this.tickAnimation = 0;
		this.speedAnimation = 35;
		this.tickDirection = 0;
	}
	
	private void updateAnimation() {

		this.tickAnimation++;
		if(this.tickAnimation >= this.speedAnimation) {
			this.indexAnimation++;
			if(this.indexAnimation >= this.gangsterAnimations.length) {
				this.indexAnimation = 0;
			}
			this.tickAnimation = 0;
		}
	}
	
	private void updatePosition() {
		
		if(this.direction) this.xPosition += this.gangsterSpeed;
		else this.xPosition -= this.gangsterSpeed;
		
		this.tickDirection++;
		
		if(this.tickDirection == 5) {
			this.tickDirection = 0;
			this.direction = !this.direction;
		}
	}
	
	@Override
	public void update() {
		this.updateAnimation();

	}
	
	@Override
	public void draw(Graphics2D g, int offset) {
		
		System.out.println((int)this.xPosition + (int)this.yPosition);
		g.drawImage(gangsterAnimations[this.indexAnimation], (int)this.xPosition - offset, (int)this.yPosition, TILE_SIZE, TILE_SIZE, null);
		//System.out.println(xPosition+" "+yPosition);
	}
}
