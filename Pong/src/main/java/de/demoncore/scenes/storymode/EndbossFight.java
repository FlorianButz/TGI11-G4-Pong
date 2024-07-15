package de.demoncore.scenes.storymode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import de.demoncore.audio.MusicManager;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackCross;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackHorizontalParallel;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackPlus;
import de.demoncore.gui.GUIValueBar;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class EndbossFight extends BaseScene {
	
	private GUIValueBar bossBar;

	@Override
	public void initializeScene() {
		super.initializeScene();
		
		addObject(new GameObject(0, 0, 1500, 1500) {
			@Override
			public void update() {
				super.update();
				collisionEnabled = false;
			}
			
			@Override
			public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
				Vector3 worldPos = getPosition();
				g2d.setColor(color);
				g2d.setStroke(new BasicStroke(25));
				g2d.drawRect(worldPos.getX(), worldPos.getY(), size.getX(), size.getY());
			}
		});

		GameObject w1 = new GameObject(1500 / 2, 0, 25, 1500);
		GameObject w2 = new GameObject(0, 1500 / 2, 1500, 25);
		GameObject w3 = new GameObject(-1500 / 2, 0, 25, 1500);
		GameObject w4 = new GameObject(0, -1500 / 2, 1500, 25);

		w1.enableRendering = false;
		w2.enableRendering = false;
		w3.enableRendering = false;
		w4.enableRendering = false;
		
		addObject(w1);
		addObject(w2);
		addObject(w3);
		addObject(w4);
		
		addObject(new StorymodePlayer(0, 150));
		addObject(new PauseMenu());
		
		bossBar = new GUIValueBar(0, 90, 850, 25, 0, 100) {
			@Override
			public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
				super.draw(g2d, screenWidth, screenHeight);
				String text = "Big fat gyat destroyer";

				Graphics2D g = (Graphics2D) g2d.create();

				g.setFont(Resources.uiFont.deriveFont(35f));
				Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);

				// Composite comp = g2d.getComposite();
				// g2d.setComposite(new InvertedComposite());

				g.setColor(Color.white);
				
				g.drawString(text,
						getUIPosition(screenWidth, screenHeight).x + (int) (size.x / 2) - (int) (bounds.getMaxX() / 2),
						(float) (getUIPosition(screenWidth, screenHeight).y - bounds.getMaxY() / 2 - 25));

				// g2d.setComposite(comp);
			}
		};
		addObject(bossBar);
		
		cameraZoom = 0.525f;
		cameraPosition = new Vector3(0, -75);
		
		MusicManager.ForcePlayMusic(MusicManager.endboss, true);
	}
	
	int timeCounter = 0;
	
	@Override
	public void updateScene() {
		super.updateScene();
		
		timeCounter++;
		
		if(timeCounter >= 400) {
			timeCounter = 0;
			attack();
		}
	}
	
	int attackCount = 0;
	
	void attack(){
		Logger.logInfo("Attacked");
		
		if(attackCount == 0) {
			addObject(new BossAttackPlus());		
		}else if (attackCount == 1){
			addObject(new BossAttackCross());
		}else if (attackCount == 2){
			addObject(new BossAttackHorizontalParallel());
			attackCount = 0;
			System.out.println("dwaowado");
		}
		
		attackCount++;
	}
	
}
