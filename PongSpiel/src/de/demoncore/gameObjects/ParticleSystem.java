package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicPasswordFieldUI;

import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.Settings;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Vector3;

public class ParticleSystem extends GameObject {

	public List<Particle> particles;

	public int initialParticleEmitCount = 25;
	public int initialParticleEmitCountRandom = 2;

	public Vector3 particleSpawnArea = new Vector3(0, 0, 0);

	public Vector3 initialParticleSpeedMax = new Vector3(0, 0, 0);
	public Vector3 initialParticleSpeedMin = new Vector3(0, 0, 0);

	public float particleSpeedMultiplier = 0.5f;

	public float initialParticleSize = 5;
	public float initialParticleSizeRandom = 2;
	public float endParticleSize = 0;

	public float particleGravity = 0.01f ;

	public float particleDamping = 0.99f; // Nur werte zwischen 0 und 1!

	public int particleLifetime = 500;
	public int particleLifetimeRandom = 250;

	public Color particleColorFirst = new Color(1, 1, 1, 0.2f);
	public Color particleColorSecond = particleColorFirst;
	public Color particleColorEnd = new Color(1, 1, 1, 0);

	public boolean emitLoop = false;
	public float emitPause = 5;	// Pause zwischen den spawn
	public float emitChunk = 5; // Wie viele Partikel auf einem Schlag gespawnt werden
	public float emitTime = 0;

	public float particleSystemTime = 0;
	float particleSystemTimeStart = 0;

	boolean hasInitialized = false;
	
	public ParticleSystem(int x, int y) {
		super(x, y, 5, 5);

		this.anchorPoint = Vector3.one().multiply(0.5f);
		collisionEnabled = false;
	}

	public void Init() {
		int pCount = initialParticleEmitCount + GameMath.RandomRange(0, initialParticleEmitCountRandom);
		particles = new ArrayList<Particle>(pCount);
		
		particleSystemTimeStart = GameLogic.GetInstance().gameTime;
		
		for(int i = 0; i < pCount; i++) {
			AddParticle();
		}
		
		hasInitialized = true;
	}
	
	void AddParticle() {
		Particle p = new Particle();

		p.size = new Vector3(
				initialParticleSize + GameMath.RandomRange(-initialParticleSizeRandom, initialParticleSizeRandom),
				initialParticleSize + GameMath.RandomRange(-initialParticleSizeRandom, initialParticleSizeRandom)
				);
		p.velocity = 
				new Vector3(
						GameMath.RandomRange(initialParticleSpeedMin.x, initialParticleSpeedMax.x),
						GameMath.RandomRange(initialParticleSpeedMin.y, initialParticleSpeedMax.y)
						).multiply(particleSpeedMultiplier);

		p.maxLifetime = particleLifetime + GameMath.RandomRange(-particleLifetimeRandom, particleLifetimeRandom);

		Vector3 randomSpawnPosition = new Vector3(
				GameMath.RandomRange(-particleSpawnArea.x, particleSpawnArea.x),
				GameMath.RandomRange(-particleSpawnArea.y, particleSpawnArea.y)
				);
		p.position = p.position.add(randomSpawnPosition).add(this.GetPosition());

		float randomColorValue = GameMath.RandomRange(0f, 1f);
		p.startColor = GameMath.LerpColor(particleColorFirst, particleColorSecond, randomColorValue);

		p.rotation = GameMath.RandomRange(0, 90);
		
		particles.add(p);
	}

	@Override
	public void Update() {
		super.Update();
		
		if(GameLogic.isGamePaused) return;
		
		if(hasInitialized == false) return;
		
		particleSystemTime = GameLogic.GetInstance().gameTime - particleSystemTimeStart;

		if(emitLoop) {
			if(emitTime >= emitPause) {
				for(int s = 0; s < emitChunk; s++) {
					AddParticle();
				}
				emitTime = 0;
			}
			else
				emitTime++;
		}

		List<Particle> removeParticles = new ArrayList<Particle>();
		for(Particle p : particles) {
			p.position.x += p.velocity.x;
			p.position.y += p.velocity.y;

			p.velocity = p.velocity.multiply(particleDamping);
			p.velocity.y += particleGravity;

			p.color = GameMath.LerpColor(p.startColor, particleColorEnd, (float)p.currentLifetime / (float)p.maxLifetime);
			p.size = Vector3.one().multiply(GameMath.Lerp(initialParticleSize, endParticleSize, (float)p.currentLifetime / (float)p.maxLifetime));
			
			if(p.currentLifetime >= p.maxLifetime) {
				removeParticles.add(p);
			}
			p.currentLifetime++;
		}

		particles.removeAll(removeParticles);
	}
	
	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		for (Particle p : new ArrayList<Particle>(particles)){
			if(p == null) continue;
			
			Vector3 worldPos = p.position;
			
			if(Settings.GetDebugMode()) {
				g2d.setColor(Color.white);
				g2d.drawString("P" + particles.indexOf(p), worldPos.x + 2, worldPos.y - 5);
			}
			
			g2d.setColor(p.color);
		    g2d.rotate(Math.toRadians(p.rotation), worldPos.x, worldPos.y);
			g2d.fillRect((int)worldPos.x + (int)(p.size.x / 2), (int)worldPos.y + (int)(p.size.y / 2), (int)p.size.x, (int)p.size.y);
			g2d.rotate(Math.toRadians(-p.rotation), worldPos.x, worldPos.y);
		}
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		
		particles = null;
	}
}