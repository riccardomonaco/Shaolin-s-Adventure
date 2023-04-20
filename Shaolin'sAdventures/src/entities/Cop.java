package entities;
import static utils.Constants.PanelConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.HITBOX_H_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_W_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_X_OFFSET;
import static utils.Constants.PlayerConstants.HITBOX_Y_OFFSET;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class Cop extends Entity{
	
	private float copSpeed;
	private boolean direction;
	private int tickDirection;
	private BufferedImage[] copAnimations;

	public Cop(int initXPosition, int initYPosition) {
		
		super(initXPosition, initYPosition);
		
		this.initHitBox(HITBOX_W_OFFSET, HITBOX_H_OFFSET, HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		this.copAnimations = LoadSave.getEnemySprites().get(1);
		
		this.copSpeed = 1f;
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
			if(this.indexAnimation >= this.copAnimations.length) {
				this.indexAnimation = 0;
			}
			this.tickAnimation = 0;
		}
	}
	
	private void updatePosition() {
		
		if(this.direction) this.xPosition += this.copSpeed;
		else this.xPosition -= this.copSpeed;
		
		this.tickDirection++;
		
		if(this.tickDirection == 50) {
			this.tickDirection = 0;
			this.direction = !this.direction;
		}
	}
	
	@Override
	public void update() {
		this.updatePosition();
		this.updateHitBox(HITBOX_X_OFFSET, HITBOX_Y_OFFSET);
		this.updateAnimation();
	}
	
	@Override
	public void draw(Graphics2D g, int offset) {
		
		g.drawImage(copAnimations[this.indexAnimation], (int)xPosition - offset, (int)yPosition, TILE_SIZE, TILE_SIZE, null);

		//TEST DRAW HITBOX
		g.setColor(new Color(255, 255, 255));
		g.drawRect((int)this.getHitBox().x - offset, (int)this.getHitBox().y, TILE_SIZE - HITBOX_W_OFFSET, TILE_SIZE - HITBOX_W_OFFSET);
	}
}