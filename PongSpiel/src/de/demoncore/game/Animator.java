package de.demoncore.game;

import de.demoncore.utils.Vector3;

public class Animator {

	SimpleEvent onComplete;
	
	int fps = 30; // Frames per second
    int frameDuration = 1000 / fps; // Länge jedes frames
	
	public void SetOnComplete(SimpleEvent event) {
		onComplete = event;
	}
	
	public Animator(Vector3 valueToAnimate, Vector3 fromValue, Vector3 toValue, float duration, boolean looping) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    int totalFrames = (int) (duration * fps); // total number of frames to run
                    for (int i = 0; i < totalFrames; i++) {
                        long startTime = System.currentTimeMillis();

                        valueToAnimate = Vector3.Lerp(fromValue, toValue, (float)totalFrames / (float)i);
                        
                        long endTime = System.currentTimeMillis();
                        long sleepTime = frameDuration - (endTime - startTime);
                        if (sleepTime > 0) {
                            Thread.sleep(sleepTime);
                        }
                    }
                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        };

        thread.start();
	}
	
}
