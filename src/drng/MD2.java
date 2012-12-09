package drng;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import harness.DRNG;

public class MD2 extends DRNG {
	MessageDigest rnd;
	byte[] currentHash;

	public MD2() {
		try {
			rnd = MessageDigest.getInstance("MD2");
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
