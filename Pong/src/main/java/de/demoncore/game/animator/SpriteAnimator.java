package de.demoncore.game.animator;

import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.sprites.Sprite;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Vector3;

public class SpriteAnimator {

	int fps = 60; // Frames per second
    int frameDuration = 1000 / fps; // Länge jedes frames

    Sprite[] values;
    float duration;		// Länge der animation

    EasingType easeType;	// Easing type / wie sich die animation verhält
    AnimatorUpdateEvent updateEvent;	// Jeden frame der animation wird das ausgeführt
    AnimatorOnCompleteEvent onCompleteEvent;	// Wenn die animation fertig ist wird das ausgeführt
    
	boolean isPlaying = false;
    
    public SpriteAnimator(Sprite[] values, float duration, EasingType easeType) {		
		this.values = values;
		this.duration = duration;
		this.easeType = easeType;
	}

	public void setOnComplete(AnimatorOnCompleteEvent event) {
		this.onCompleteEvent = event;
	}
	
	public void setOnUpdate(AnimatorUpdateEvent event) {
		this.updateEvent = event;
	}
	
	boolean isAnimationCancled = false;
	
	public void stop() {
		isAnimationCancled = true;
		isPlaying = false;
	}
	
	public Thread animationThread;
	
	public SpriteAnimator play() {
		
		if(isPlaying) return this;
		else isPlaying = true;
		isAnimationCancled = false;
		
		animationThread = new Thread("spriteanimator.animation") {
			
			Sprite value = values[0];
            
			public void run() {
                try {
                	
                    while(!isAnimationCancled) {
                    	int totalFrames = (int) (duration * fps); // Anzahl frames
                        for (int i = 0; i < totalFrames; i++) {
                        	
                        	if(isAnimationCancled) {
                                if(onCompleteEvent != null)
                                	onCompleteEvent.onComplete();
                        		isAnimationCancled = false;
                        		return;
                        	}
                        	
                            long startTime = System.currentTimeMillis(); // Start zeit

                            float time = (float)i / (float)totalFrames; // Animationszeit jetzt zwischen 0 und 1
                            
                            switch(easeType) {
                            case OutExponential:
                            	time = Easing.EaseOutExpo(time);
                            	break;
                            case InOutQuint:
                            	time = Easing.EaseInOutQuint(time);
                            	break;
                            case Linear:
                            	break;
                            case InOutSine:
                            	time = Easing.EaseInOutSine(time);
                            }
                            
                            int index =(int)GameMath.Lerp(0, values.length, time);
                            value = values[index];
                            if(updateEvent != null && !isAnimationCancled)
                            	updateEvent.onUpdate(value);
                            
                            long endTime = System.currentTimeMillis();
                            long sleepTime = frameDuration - (endTime - startTime);
                            if (sleepTime > 0) {
                                Thread.sleep(sleepTime);
                            }
                            
                        }
                    }
                    
                    if(onCompleteEvent != null)
                    	onCompleteEvent.onComplete();
                    
                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        };

        animationThread.start();
		
		return this;
	}
}