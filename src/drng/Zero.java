package drng;

import harness.DRNG;

public class Zero extends DRNG {

	@Override
	public void setSeed(int seed) {
	}

	@Override
	public int run() {
		return 0;
	}

}
