package drng;

import harness.DRNG;

import org.apache.commons.math3.random.Well44497a;

public class Well44497ar extends DRNG {
	Well44497a rnd;
	byte[] currentHash;

	public Well44497ar() {
		rnd = new Well44497a();
	}

	@Override
	public void setSeed(int seed) {
		rnd.setSeed(seed);
	}

	@Override
	public int run() {
		return rnd.nextInt();
	}
}
