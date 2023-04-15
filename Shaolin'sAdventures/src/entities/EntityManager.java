package entities;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import levels.LevelManager;
import main.Game;

public class EntityManager {
	
	private Game game;
	private LevelManager levelManager;
	public List<Entity> currentEntities;

	public EntityManager(Game game, LevelManager levelManager) {
		
		this.game = game;
		this.levelManager = levelManager;
		this.currentEntities = new ArrayList<>();
		this.setCurrentEntities();
	}

	public void setCurrentEntities() {
		
		this.currentEntities = this.levelManager.getCurrentLevel().getEnemies();
	}
	
	public void update() {
		
		for(Entity e: currentEntities) {
			e.update();
		}
	}
	
	public void draw (Graphics2D g, int offset) {
		
		for(Entity e: currentEntities) {
			e.draw(g, offset);
		}
	}
}
