package code.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import code.components.*;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.factories.entities.HitParticleIndustry;
import code.graphics.SpriteData;
import code.math.VectorF;

public class ParticleSpawner extends Entity {
	private int rate;
	private float maxDeltaRot;
	private float maxSpeed;
	private int maxLife;
	private SpriteData sprite;
	private Random rand;

	public ParticleSpawner(float x, float y, int rate, int duration, float maxDeltaRot, float maxSpeed, int maxLife, SpriteData sprite) {
		super(x, y, 0);
		this.rate = rate;
		this.life = duration;
		this.maxDeltaRot = maxDeltaRot;
		this.maxSpeed = maxSpeed;
		this.maxLife = maxLife;
		this.sprite = sprite;
		rand = new Random();
	}

	public void update() {

	}

	/*
	public List<Particle> spawn() {
		List<Particle> temp = new ArrayList<>();
		for (int i = 0; i < rate; i++) {
			temp.add(new Particle(x, y, rot, (rand.nextFloat() - .5f) * maxDeltaRot, rand.nextFloat() * 2 * (float)Math.PI, rand.nextFloat() * maxSpeed, rand
					.nextInt(maxLife), sprite));
		}
		return temp;
	}*/

	/*
	public List<NEntity> spawn(){
		List<NEntity> temp = new ArrayList<>();
		HitParticleIndustry hpi = (HitParticleIndustry)Engine.getEngine().getIndustryMap().get("hit_particle_industry");
		for (int i = 0; i < rate; i++) {
			int life = rand.nextInt(maxLife);
			float dir = rand.nextFloat() * 2 * (float)Math.PI;
			float speed = rand.nextFloat() * maxSpeed;
			float deltaRot = (rand.nextFloat() - .5f) * maxDeltaRot;
			float dx = (float)Math.sin(dir) * speed;
			float dy = (float)Math.cos(dir) * speed;
			temp.add(hpi.produce(new VectorF(x, y), rot, life, life, new VectorF(dx, dy), deltaRot, sprite, 4));
		}
		return temp;
	}*/
}
