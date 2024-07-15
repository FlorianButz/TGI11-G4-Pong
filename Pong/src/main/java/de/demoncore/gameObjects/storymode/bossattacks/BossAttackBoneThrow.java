package de.demoncore.gameObjects.storymode.bossattacks;

import java.awt.Color;
import java.util.Vector;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.scenes.storymode.EndbossFight;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class BossAttackBoneThrow extends GameObject {

	private AudioSource source;

	public BossAttackBoneThrow() {
		super(0, 0, 1, 1);

		source = new AudioSource(this);
		source.setSpacial(false);
		SceneManager.getActiveScene().addObject(source);

		source.SetVolume(0.35f);

		color = new Color(0, 0, 0, 0);
		collisionEnabled = false;

		// Update

		Logger.logInfo("Add attack " + getClass().getName());

		Thread thread = new Thread() {
			public void run() {

				for(int i = 0; i < 16; i++) {
					try {
						sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if(!(SceneManager.getActiveScene() instanceof EndbossFight)) return;
					
					source.Play(Resources.playerAttackNormal);

					Vector3 speedVec =
							new Vector3(0, 4).rotated(45 * i);
					
					Vector3 rotVec =
							new Vector3(0, 325).rotated(45 * i);
					Bone bone = new Bone(rotVec.getX(), rotVec.getY(), speedVec.multiply(4.5f));
					bone.setRotationZ(i * 45);
					SceneManager.getActiveScene().addObject(bone);

					Vector3 speedVec2 =
							new Vector3(0, -4).rotated(45 * i);
					
					Vector3 rotVec2 =
							new Vector3(0, -325).rotated(45 * i);
					Bone bone2 = new Bone(rotVec2.getX(), rotVec2.getY(), speedVec2.multiply(4.5f));
					bone.setRotationZ(i * 45);
					SceneManager.getActiveScene().addObject(bone2);
				}

				SceneManager.getActiveScene().destroyObject(getObj());
			};
		};
		thread.start();
	}

	BossAttackBoneThrow getObj() {
		return this;
	}

}
