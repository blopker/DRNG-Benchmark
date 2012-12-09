package drng;

public abstract class DRNG {
	public DRNG() {
		
	}	
	
	public abstract void setSeed(int seed);
	
	public abstract int run();
}
