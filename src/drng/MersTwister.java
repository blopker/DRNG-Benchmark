package drng;

import harness.DRNG;

import org.apache.commons.math3.random.MersenneTwister;

public class MersTwister extends DRNG {
	MersenneTwister rnd;
	byte[] currentHash;

	public MersTwister() {
		rnd = new MersenneTwister();
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
