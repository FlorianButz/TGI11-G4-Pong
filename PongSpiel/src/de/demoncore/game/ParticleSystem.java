package de.demoncore.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
		super(x, y, 25, 25);

		renderSpecial = true;
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
		p.position = p.position.add(randomSpawnPosition).add(this.position);

		float randomColorValue = GameMath.RandomRange(0f, 1f);
		p.startColor = GameMath.LerpColor(particleColorFirst, particleColorSecond, randomColorValue);

		particles.add(p);
	}

	@Override
	public void Update() {
		super.Update();
		
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

			if(p.currentLifetime >= p.maxLifetime) {
				removeParticles.add(p);
			}
			p.currentLifetime++;
		}

		particles.removeAll(removeParticles);
	}
}