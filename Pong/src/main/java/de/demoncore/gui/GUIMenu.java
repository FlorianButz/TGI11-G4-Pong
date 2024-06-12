package de.demoncore.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
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

	protected Vector3 inAnimationFromPosition = new Vector3(0, -2500, 0);
	protected Vector3 outAnimationToPosition = new Vector3(0, -2500, 0);

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

	protected GUIButton CreateBackButton() {
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
	
	protected List<GUIObject> AddMenuContent() {
		return new ArrayList<GUIObject>();
	}
	
	protected GUIObject CreateBackground() {
		GUIRectangle bg = new GUIRectangle(0, 0, (int)Gui.GetScreenDimensions().x + 1500, (int)Gui.GetScreenDimensions().y + 1500, new Color(0, 0, 0, 0f));
		bg.alignment = GUIAlignment.Center;
		bg.doUIScale = false;
		return bg;
	}
	
	private void CreateMenu() {
		hasMenuBeenCreated = true;
		
		background = CreateBackground();
		
		back = CreateBackButton();
		
		menuContent.add(background);
		menuContent.addAll(AddMenuContent());
		menuContent.add(back);
		
		for(GUIObject o : menuContent) {
			SceneManager.GetActiveScene().addObject(o);
		}
		
		boolean isInScene = false;
		for(GameObject g : SceneManager.GetActiveScene().getSceneObjects()) {
			if(g == this) isInScene = true;
		}
		
		if(isInScene == false) SceneManager.GetActiveScene().addObject(this);
	}
	
	private void ChangePos() {
		for(GameObject g : menuContent) {
			if(g == background) continue;
			g.SetLocalPosition(new Vector3(0, menuYPos, 0));
		}
	}
	
	public void ShowMenu() {
		if(!hasMenuBeenCreated) 
			CreateMenu();
		
		if(outAnim != null) outAnim.Stop();
		if(bgOutAnim != null) bgOutAnim.Stop();
		
		isMenuVisible = true;
		
		bgInAnim = new ColorAnimator(background.color, backgroundColor, inAnimationTimeBackground, inAnimationEasingTypeBackground);
		bgInAnim.SetOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void OnUpdate(Color value) {
			super.OnUpdate(value);
			background.color = value;
		}
		});
		
		inAnim = new Vector3Animator(inAnimationFromPosition, new Vector3(0, 0, 0), inAnimationTimePosition, inAnimationEasingType);
		inAnim.SetOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void OnUpdate(Vector3 value) {
				super.OnUpdate(value);
				
				if(!background.enableRendering) {
					for(GUIObject o : menuContent) {
						o.enableRendering = true;
					}
				}
				
				menuYPos = (int) value.y;
				ChangePos();
			}
		});
		
		bgInAnim.Play();
		inAnim.Play();
	}

	public void HideMenu() {
		
		inAnim.Stop();
		bgInAnim.Stop();

		bgOutAnim = new ColorAnimator(background.color, new Color(0f, 0f, 0f, 0f), outAnimationTimeBackground, outAnimationEasingTypeBackground);
		bgOutAnim.SetOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void OnUpdate(Color value) {
			super.OnUpdate(value);
			background.color = value;
		}
		});
		bgOutAnim.SetOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void OnComplete() {
				super.OnComplete();			
				background.enableRendering = false;
			}
		});
		
		outAnim = new Vector3Animator(new Vector3(0, menuYPos, 0), outAnimationToPosition, outAnimationTimePosition, outAnimationEasingType);
		outAnim.SetOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void OnUpdate(Vector3 value) {
				super.OnUpdate(value);
				menuYPos = (int) value.y;
				ChangePos();
			}
		});
		outAnim.SetOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void OnComplete() {
				super.OnComplete();
				
				for(GUIObject o : menuContent) {
					if(o != background)
						o.enableRendering = false;
				}
				
				isMenuVisible = false;
			}
		});
		
		bgOutAnim.Play();
		outAnim.Play();
	}
	
	@Override
	public void update() {
		super.update();
		
		if(isMenuVisible) {			
			this.isMenuFocused = CheckIfFocused();
		}
	}
	
	private boolean CheckIfFocused() {
		for(GameObject g : SceneManager.GetActiveScene().getSceneObjects()) {
			if(g != this) {
				if(g instanceof GUIMenu) {
					if(SceneManager.GetActiveScene().getSceneObjects().indexOf(g) > SceneManager.GetActiveScene().getSceneObjects().indexOf(this))
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
			SceneManager.GetActiveScene().destroyObject(o);
		}
	}
}
