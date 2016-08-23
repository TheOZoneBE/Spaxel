package code.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import code.graphics.Sprite;

public class ParticleSpawner extends Entity {
	private int rate;
	private float maxDeltaRot;
	private float maxSpeed;
	private int maxLife;
	private Sprite sprite;
	private Random rand;

	public ParticleSpawner(float x, float y, int rate, int duration, float maxDeltaRot, float maxSpeed, int maxLife, Sprite sprite) {
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

	public List<Particle> spawn() {
		List<Particle> temp = new ArrayList<>();
		for (int i = 0; i < rate; i++) {
			temp.add(new Particle(x, y, rot, (rand.nextFloat() - .5f) * maxDeltaRot, rand.nextFloat() * 2 * (float)Math.PI, rand.nextFloat() * maxSpeed, rand
					.nextInt(maxLife), sprite));
		}
		return temp;
	}
}
