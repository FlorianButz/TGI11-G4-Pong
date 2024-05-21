package de.demoncore.game.animator;

import java.awt.Color;

import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Vector3;

public class ColorAnimator {

	int fps = 60; // Frames per second
    int frameDuration = 1000 / fps; // Länge jedes frames
	
    Color fromValue;	// Animiere von diesem wert
    Color toValue;	// Zu diesem wert
    float duration;		// Länge der animation

    EasingType easeType;	// Easing type / wie sich die animation verhält
    AnimatorUpdateEvent updateEvent;	// Jeden frame der animation wird das ausgeführt
    AnimatorOnCompleteEvent onCompleteEvent;	// Wenn die animation fertig ist wird das ausgeführt
    
	public ColorAnimator(Color fromValue, Color toValue, float duration, EasingType easeType) {		
		this.fromValue = fromValue;
		this.toValue = toValue;
		this.duration = duration;
		this.easeType = easeType;
	}

	public void SetOnComplete(AnimatorOnCompleteEvent event) {
		this.onCompleteEvent = event;
	}
	
	public void SetOnUpdate(AnimatorUpdateEvent event) {
		this.updateEvent = event;
	}
	
	boolean isAnimationCancled = false;
	
	public void Stop() {
		isAnimationCancled = true;
	}
	
	
	public Thread animationThread;
	
	public ColorAnimator Play() {
		
		animationThread = new Thread("coloranimator.animation") {
			
			Color value = fromValue;
            
			public void run() {
                try {
                	
                    int totalFrames = (int) (duration * fps);
                    for (int i = 0; i < totalFrames; i++) {
                    	
                    	if(isAnimationCancled) {
                    		isAnimationCancled = false;
                    		return;
                    	}
                    	
                        long startTime = System.currentTimeMillis();

                        float time = (float)i / (float)totalFrames;
                        
                        switch(easeType) {
                        case OutExponential:
                        	time = Easing.EaseOutExpo(time);
                        	break;
                        case InOutQuint:
                        	time = Easing.EaseInOutQuint(time);
                        	break;
                        case Linear:
                        	break;
                        }
                        
                        value = GameMath.LerpColor(fromValue, toValue, time);
                        if(updateEvent != null && !isAnimationCancled)
                        	updateEvent.OnUpdate(value);
                        
                        long endTime = System.currentTimeMillis();
                        long sleepTime = frameDuration - (endTime - startTime);
                        if (sleepTime > 0) {
                            Thread.sleep(sleepTime);
                        }
                        
                    }
                    
                    if(onCompleteEvent != null && !isAnimationCancled)
                    	onCompleteEvent.OnComplete();
                    
                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        };

        animationThread.start();
		
		return this;
	}
}