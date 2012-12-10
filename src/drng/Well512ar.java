package drng;

import harness.DRNG;

import org.apache.commons.math3.random.Well19937a;

public class Well512ar extends DRNG {
	Well19937a rnd;
	byte[] currentHash;

	public Well512ar() {
		rnd = new Well19937a();
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
