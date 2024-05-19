package de.demoncore.game.animator;

import de.demoncore.utils.Vector3;

public class Vector3Animator {

	int fps = 30; // Frames per second
    int frameDuration = 1000 / fps; // Länge jedes frames
	
	public Vector3Animator(Vector3 fromValue, Vector3 toValue, float duration, boolean looping, AnimatorUpdateEvent updateEvent, AnimatorOnCompleteEvent onComplete) {		
		Thread thread = new Thread() {
			
			Vector3 value = fromValue;
            
			public void run() {
                try {
                	
                    int totalFrames = (int) (duration * fps); // total number of frames to run
                    for (int i = 0; i < totalFrames; i++) {
                        long startTime = System.currentTimeMillis();

                        value = Vector3.Lerp(fromValue, toValue, Easing.EaseInOutQuint((float)i / (float)totalFrames));
                        updateEvent.OnUpdate(value);
                        
                        long endTime = System.currentTimeMillis();
                        long sleepTime = frameDuration - (endTime - startTime);
                        if (sleepTime > 0) {
                            Thread.sleep(sleepTime);
                        }
                        
                    }
                    
                    onComplete.OnComplete();
                    
                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        };

        thread.start();
	}
}