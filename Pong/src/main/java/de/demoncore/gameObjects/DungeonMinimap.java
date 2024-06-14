package de.demoncore.gameObjects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import de.demoncore.game.GameObject;
import de.demoncore.gui.GUIObject;
import de.demoncore.scenes.storymode.Dungeon;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Vector3;

public class DungeonMinimap extends GUIObject {

	public GameObject[][] dungeon;
	public StorymodePlayer player;
	public Dungeon dungeonSettings;

	public DungeonMinimap(int posX, int posY, GameObject[][] dungeon, StorymodePlayer player, Dungeon dungeonSettings) {
		super(posX, posY, 0, 0);
		this.dungeon = dungeon;
		this.player = player;
		this.dungeonSettings = dungeonSettings;
	}

	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {

		Shape s = g2d.getClip();
		
		g2d.setColor(new Color(0.25f, 0.25f, 0.25f, 0.25f));
		
		Rectangle viewRect = new Rectangle(
				(int)getUIPosition(screenWidth, screenHeight).x - (250) / 2,
				(int)getUIPosition(screenWidth, screenHeight).y - (250) / 2,
				250,
				250);
		
		g2d.fill(viewRect);
		
		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(2));
		
		g2d.clip(viewRect);

		if(dungeon == null) return;
		for(GameObject[] gArr : dungeon) {
			if(gArr == null) continue;
			for(GameObject gObj : gArr) {
				if(gObj == null) continue;

				float viewOffX = player.getPosition().x;
				float viewOffY = player.getPosition().y;
				
				g2d.setColor(Color.white);
				
				g2d.drawRect(
						(int)getUIPosition(screenWidth, screenHeight).x + (int)(((gObj.getPosition().x - viewOffX) / (float)dungeonSettings.dungeonSize) * 45),
						(int)getUIPosition(screenWidth, screenHeight).y + (int)(((gObj.getPosition().y - viewOffY) / (float)dungeonSettings.dungeonSize) * 45),
						40,
						40);

				g2d.setColor(new Color(1, 1, 1,
					GameMath.clamp(GameMath.RemapValue(Vector3.Distance(gObj.getPosition().add(gObj.size.multiply(0.5f)), player.getPosition()), 0, 1500, 1, 0), 0, 1)));
				
				g2d.fillRect(
						(int)getUIPosition(screenWidth, screenHeight).x + (int)(((gObj.getPosition().x - viewOffX) / (float)dungeonSettings.dungeonSize) * 45),
						(int)getUIPosition(screenWidth, screenHeight).y + (int)(((gObj.getPosition().y - viewOffY) / (float)dungeonSettings.dungeonSize) * 45),
						40,
						40);
			}
		}
		
		g2d.setColor(Color.yellow);
		
		g2d.fillOval(
				(int)getUIPosition(screenWidth, screenHeight).x - (5) / 2,
				(int)getUIPosition(screenWidth, screenHeight).y - (5) / 2,
				5,
				5);
		
		g2d.setClip(s);
	}
}
