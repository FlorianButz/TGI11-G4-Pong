package de.demoncore.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.ColorAnimator;
import de.demoncore.game.animator.Vector3Animator;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class GUIMenu extends GameObject {

	protected List<GUIObject> menuContent = new ArrayList<GUIObject>();
	
	GUIObject background;
	GUIButton back;

	int menuYPos = 0;
	Vector3Animator inAnim;
	Vector3Animator outAnim;
	ColorAnimator bgInAnim;
	ColorAnimator bgOutAnim;

	protected Vector3 inAnimationFromPosition = new Vector3(0, -3000, 0);
	protected Vector3 outAnimationToPosition = new Vector3(0, -3000, 0);

	protected EasingType inAnimationEasingType = EasingType.OutExponential;
	protected EasingType outAnimationEasingType = EasingType.InOutQuint;
	protected EasingType inAnimationEasingTypeBackground = EasingType.OutExponential;
	protected EasingType outAnimationEasingTypeBackground = EasingType.InOutQuint;

	protected float inAnimationTimePosition = 0.75f;
	protected float inAnimationTimeBackground = 0.5f;
	protected float outAnimationTimePosition = 0.75f;
	protected float outAnimationTimeBackground = 1f;
	
	public Color backgroundColor = new Color(0f, 0f, 0f, 0.95f);
	
	protected boolean isMenuVisible = false;
	private boolean hasMenuBeenCreated = false;
	protected boolean isMenuFocused = true;
	
	protected boolean canCloseWithEscape = true;
	
	GameActionListener l;
	
	public GUIMenu() {
		super(0, 0, 0, 0);

		this.enableRendering = false;
		this.collisionEnabled = false;
		this.color = new Color(0, 0, 0, 0);
		
		l = new GameActionListener() {
			@Override
			public void onEscapePressed() {
				super.onEscapePressed();
				OnEscape();
			}
		};
		
		KeyHandler.listeners.add(l);
	}

	protected GUIButton createBackButton() {
		GUIButton back = new GUIButton(-65, 65, 50, 50, Translation.literal("X"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
		@Override
		public void ButtonClick() {
			HideMenu();
			super.ButtonClick();
		}
		});
		back.alignment = GUIAlignment.TopRight;
		
		return back;
	}
	
	protected List<GUIObject> addMenuContent() {
		return new ArrayList<GUIObject>();
	}
	
	protected GUIObject createBackground() {
		GUIRectangle bg = new GUIRectangle(0, 0, (int)Gui.GetScreenDimensions().x + 1500, (int)Gui.GetScreenDimensions().y + 1500, new Color(0, 0, 0, 0f));
		bg.alignment = GUIAlignment.Center;
		bg.doUIScale = false;
		return bg;
	}
	
	private void createMenu() {
		hasMenuBeenCreated = true;
		
		background = createBackground();
		
		back = createBackButton();
		
		menuContent.add(background);
		menuContent.addAll(addMenuContent());
		menuContent.add(back);
		
		for(GUIObject o : menuContent) {
			SceneManager.getActiveScene().addObject(o);
		}
		
		boolean isInScene = false;
		for(GameObject g : SceneManager.getActiveScene().getSceneObjects()) {
			if(g == this) isInScene = true;
		}
		
		if(isInScene == false) SceneManager.getActiveScene().addObject(this);
	}
	
	private void changePos() {
		for(GameObject g : menuContent) {
			if(g == background) continue;
			g.setLocalPosition(new Vector3(0, menuYPos, 0));
		}
	}
	
	public void ShowMenu() {
		if(!hasMenuBeenCreated) 
			createMenu();
		
		inAnimationFromPosition = new Vector3(0, Settings.isSmallGui() ? -4500 : -2500, 0);
		outAnimationToPosition = new Vector3(0, Settings.isSmallGui() ? -4500 : -2500, 0);
		
		if(outAnim != null) outAnim.Stop();
		if(bgOutAnim != null) bgOutAnim.Stop();
		
		isMenuVisible = true;
		
		bgInAnim = new ColorAnimator(background.color, backgroundColor, inAnimationTimeBackground, inAnimationEasingTypeBackground);
		bgInAnim.setOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void onUpdate(Color value) {
			super.onUpdate(value);
			background.color = value;
		}
		});
		
		inAnim = new Vector3Animator(inAnimationFromPosition, new Vector3(0, 0, 0), inAnimationTimePosition, inAnimationEasingType);
		inAnim.setOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void onUpdate(Vector3 value) {
				super.onUpdate(value);
				
				if(!background.enableRendering) {
					for(GUIObject o : menuContent) {
						o.enableRendering = true;
					}
				}
				
				menuYPos = (int) value.y;
				changePos();
			}
		});
		
		bgInAnim.Play();
		inAnim.play();
	}

	public void HideMenu() {
		
		inAnimationFromPosition = new Vector3(0, Settings.isSmallGui() ? -4500 : -2500, 0);
		outAnimationToPosition = new Vector3(0, Settings.isSmallGui() ? -4500 : -2500, 0);
		
		inAnim.Stop();
		bgInAnim.Stop();

		bgOutAnim = new ColorAnimator(background.color, new Color(0f, 0f, 0f, 0f), outAnimationTimeBackground, outAnimationEasingTypeBackground);
		bgOutAnim.setOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void onUpdate(Color value) {
			super.onUpdate(value);
			background.color = value;
		}
		});
		bgOutAnim.SetOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void onComplete() {
				super.onComplete();			
				background.enableRendering = false;
			}
		});
		
		outAnim = new Vector3Animator(new Vector3(0, menuYPos, 0), outAnimationToPosition, outAnimationTimePosition, outAnimationEasingType);
		outAnim.setOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void onUpdate(Vector3 value) {
				super.onUpdate(value);
				menuYPos = (int) value.y;
				changePos();
			}
		});
		outAnim.setOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void onComplete() {
				super.onComplete();
				
				for(GUIObject o : menuContent) {
					if(o != background)
						o.enableRendering = false;
				}
				
				isMenuVisible = false;
			}
		});
		
		bgOutAnim.Play();
		outAnim.play();
	}
	
	@Override
	public void update() {
		super.update();
		
		if(isMenuVisible) {			
			this.isMenuFocused = CheckIfFocused();
		}
	}
	
	private boolean CheckIfFocused() {
		for(GameObject g : SceneManager.getActiveScene().getSceneObjects()) {
			if(g != this) {
				if(g instanceof GUIMenu) {
					if(SceneManager.getActiveScene().getSceneObjects().indexOf(g) > SceneManager.getActiveScene().getSceneObjects().indexOf(this))
						if(((GUIMenu)g).isMenuVisible)
							return false;
				}
			}
		}
		return true;
	}

	public void OnEscape() {
		if(isMenuVisible && isMenuFocused) HideMenu();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		KeyHandler.listeners.remove(l);
		
		for(GUIObject o : menuContent) {
			SceneManager.getActiveScene().destroyObject(o);
		}
	}
}
