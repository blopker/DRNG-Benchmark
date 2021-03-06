package drng;

import harness.DRNG;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class JavaSecureRandom extends DRNG {
	SecureRandom rnd;

	public JavaSecureRandom() {
		try {
			rnd = SecureRandom.getInstance("SHA1PRNG");
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
