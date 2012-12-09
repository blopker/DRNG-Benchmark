package harness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import drng.DRNG;

public class Benchmark {
	private static Map<DRNG, Long> results = new HashMap<DRNG, Long>();
	
	public static void main(String[] args) {
		Reflections ref = new Reflections("drng");
		Set<Class<? extends DRNG>> drngClasses = ref.getSubTypesOf(DRNG.class);
		
		List<DRNG> drngs = createDRNGs(drngClasses);
		
		runTests(drngs);
		
		System.out.println("Tests done!");
	}

	private static void runTests(List<DRNG> drngs) {
		for (DRNG drng : drngs) {
			System.out.println("Running test: " + drng.getClass().getName());
			
			long count = 0;
			int runTime = 3000;
			long start = System.currentTimeMillis();
			long end = start + runTime;
			
			while (System.currentTimeMillis() < end){
				drng.run();
				count++;
			}
			long duration = System.currentTimeMillis() - start;
			
			long speed = count/(duration/1000);
			results.put(drng, speed);
			System.out.println(drng.getClass().getName() + " done. Speed: " + speed + " num/sec");
		}
	}

	private static List<DRNG> createDRNGs(Set<Class<? extends DRNG>> drngClasses) {
		List<DRNG> drngs = new ArrayList<DRNG>();
		for (Class<? extends DRNG> drngClass : drngClasses) {
			try {
				drngs.add(drngClass.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return drngs;
	}
	
	
	
}
