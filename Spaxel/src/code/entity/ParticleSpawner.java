package code.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import code.graphics.Sprite;

public class ParticleSpawner extends Entity {
	private int rate;
	private int duration;
	private double maxDeltaRot;
	private double maxSpeed;
	private int maxLife;
	private Sprite sprite;
	private Random rand;
	private boolean alive;

	public ParticleSpawner(double x, double y, int rate, int duration, double maxDeltaRot, double maxSpeed, int maxLife, Sprite sprite) {
		super(x, y, 0);
		this.rate = rate;
		this.duration = duration;
		this.maxDeltaRot = maxDeltaRot;
		this.maxSpeed = maxSpeed;
		this.maxLife = maxLife;
		this.sprite = sprite;
		rand = new Random();
		alive = true;
	}

	public void update() {

	}

	public List<Particle> spawn() {
		duration--;
		List<Particle> temp = new ArrayList<>();
		if (duration == 0) {
			alive = false;
		} else {
			for (int i = 0; i < rate; i++) {
				temp.add(new Particle(x, y, rot, rand.nextDouble() * maxDeltaRot, rand.nextDouble() * 2 * Math.PI, rand.nextDouble() * maxSpeed, rand
						.nextInt(maxLife), sprite));
			}
		}
		return temp;
	}

	public boolean isAlive() {
		return alive;
	}
}
