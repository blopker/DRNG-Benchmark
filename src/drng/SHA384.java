package drng;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import harness.DRNG;

public class SHA384 extends DRNG {
	MessageDigest rnd;
	byte[] currentHash;

	public SHA384() {
		try {
			rnd = MessageDigest.getInstance("SHA-384");
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
