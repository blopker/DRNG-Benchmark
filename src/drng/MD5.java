package drng;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import harness.DRNG;

public class MD5 extends DRNG {
	MessageDigest rnd;
	byte[] currentHash;

	public MD5() {
		try {
			rnd = MessageDigest.getInstance("MD5");
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

		ByteBuffer wrapped = ByteBuffer.wrap(currentHash);
		return wrapped.getInt();
	}

}
