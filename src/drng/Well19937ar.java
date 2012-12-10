package drng;

import harness.DRNG;

import org.apache.commons.math3.random.Well512a;

public class Well19937ar extends DRNG {
	Well512a rnd;
	byte[] currentHash;

	public Well19937ar() {
		rnd = new Well512a();
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
