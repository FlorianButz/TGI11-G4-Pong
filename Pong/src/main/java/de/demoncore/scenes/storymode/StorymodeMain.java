package de.demoncore.scenes.storymode;

import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.main.Main;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.LevelLoader;
import de.demoncore.utils.Vector3;

public class StorymodeMain extends BaseScene {

	@Override
	public void initializeScene() {
		super.initializeScene();
		
		addObject(new PauseMenu());
		
		LevelLoader.LoadLevel(Main.class.getResource("/levels/storymode_main.plv"));
	}
	
	@Override
	public void updateScene() {
		super.updateScene();
	
		if(StorymodePlayer.getPlayerInstance() != null)
			cameraPosition = Vector3.Lerp(cameraPosition, StorymodePlayer.getPlayerInstance().getPosition(), 0.065f);
	}
	
}
