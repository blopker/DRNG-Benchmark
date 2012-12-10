package drng;

import harness.DRNG;

import org.apache.commons.math3.random.Well1024a;

public class Well1024ar extends DRNG {
	Well1024a rnd;
	byte[] currentHash;

	public Well1024ar() {
		rnd = new Well1024a();
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
