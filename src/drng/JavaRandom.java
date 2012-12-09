package drng;

import java.util.Random;

public class JavaRandom extends DRNG {
	Random rnd = new Random();

	@Override
	public int run() {
		return rnd.nextInt();
	}

	@Override
	public void setSeed(int seed) {
		rnd = new Random(seed);
	}
	
}
