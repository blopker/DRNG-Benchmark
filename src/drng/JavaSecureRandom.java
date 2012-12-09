package drng;

import java.security.SecureRandom;

public class JavaSecureRandom extends DRNG {
	SecureRandom rnd = new SecureRandom();

	@Override
	public void setSeed(int seed) {
		byte[] result = new byte[4];

		result[0] = (byte) (seed >> 24);
		result[1] = (byte) (seed >> 16);
		result[2] = (byte) (seed >> 8);
		result[3] = (byte) (seed /* >> 0 */);

		rnd = new SecureRandom(result);
	}

	@Override
	public int run() {
		return rnd.nextInt();
	}

}
