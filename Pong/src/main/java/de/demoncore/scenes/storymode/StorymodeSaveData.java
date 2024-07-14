package de.demoncore.scenes.storymode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StorymodeSaveData implements Serializable {

	private static final long serialVersionUID = 5565031600558695508L;

	public int playerHealth = 6;
	public float playerXP = 0;
	
	public float playerX = 0, playerY = 0;
	
	public List<Integer> completedDungeons = new ArrayList<Integer>();
	public boolean compassUnlocked = false;
}
