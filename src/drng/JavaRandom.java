package drng;

import harness.DRNG;

import java.util.Random;

public class JavaRandom extends DRNG {
	Random rnd = new Random();

	@Override
	public int run() {
		return rnd.nextInt();
	}

	@Override
	public void setSeed(int seed) {
		rnd.setSeed(seed);
	}
	
}
