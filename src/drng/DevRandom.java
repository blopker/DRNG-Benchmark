package drng;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import harness.DRNG;

public class DevRandom extends DRNG {
	SecureRandom rnd;
	
	public DevRandom() {
		try {
			rnd = SecureRandom.getInstance("NativePRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
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
