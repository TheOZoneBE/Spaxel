package code.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import code.graphics.Sprite;

public class ParticleSpawner extends Entity {
	private int rate;
	private double maxDeltaRot;
	private double maxSpeed;
	private int maxLife;
	private Sprite sprite;
	private Random rand;

	public ParticleSpawner(double x, double y, int rate, int duration, double maxDeltaRot, double maxSpeed, int maxLife, Sprite sprite) {
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
			temp.add(new Particle(x, y, rot, (rand.nextDouble() - .5) * maxDeltaRot, rand.nextDouble() * 2 * Math.PI, rand.nextDouble() * maxSpeed, rand
					.nextInt(maxLife), sprite));
		}
		return temp;
	}
}
