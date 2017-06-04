package code.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import code.components.*;
import code.engine.EntityType;
import code.engine.NEntity;
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

	public List<NEntity> spawn(){
		List<NEntity> temp = new ArrayList<>();
		for (int i = 0; i < rate; i++) {
			NEntity entity = new NEntity(EntityType.PARTICLE);
			PositionComponent pc = new PositionComponent(new VectorF(x, y), rot);
			int life = rand.nextInt(maxLife);
			AgeComponent ac = new AgeComponent(life, life);
			float dir = rand.nextFloat() * 2 * (float)Math.PI;
			float speed = rand.nextFloat() * maxSpeed;
			float deltaRot = (rand.nextFloat() - .5f) * maxDeltaRot;
			float dx = (float)Math.sin(dir) * speed;
			float dy = (float)Math.cos(dir) * speed;
			VelocityComponent vc = new VelocityComponent(new VectorF(dx, dy), deltaRot);
			SpriteComponent rc = new SpriteComponent(sprite,4);
			VelocityRendererComponent vrc = new VelocityRendererComponent();
			entity.addComponent(pc);
			entity.addComponent(ac);
			entity.addComponent(vc);
			entity.addComponent(rc);
			entity.addComponent(vrc);
			temp.add(entity);
		}
		return temp;
	}
}
