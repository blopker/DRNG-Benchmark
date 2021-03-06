package drng;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import harness.DRNG;

public class SHA1 extends DRNG {
	MessageDigest rnd;
	byte[] currentHash;

	public SHA1() {
		try {
			rnd = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setSeed(int seed) {
		ByteBuffer dbuf = ByteBuffer.allocate(4);
		dbuf.putInt(seed);
		currentHash = dbuf.array();
	}

	@Override
	public int run() {
		currentHash = rnd.digest(currentHash);
		return 0;
	}

}
