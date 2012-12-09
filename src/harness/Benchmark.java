package harness;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.reflections.Reflections;

import drng.DRNG;

public class Benchmark {
	public static void main(String[] args) {
		Reflections ref = new Reflections("drng");
		Set<Class<? extends DRNG>> drngClasses = ref.getSubTypesOf(DRNG.class);
		
		List<DRNG> drngs = createDRNGs(drngClasses);
		
		runTests(drngs);
		
		System.out.println("Tests done!");
	}

	private static void runTests(List<DRNG> drngs) {
		for (DRNG drng : drngs) {
			for (int i = 0; i < 20; i++) {
				System.out.println("RANDOM: " + drng.run());
			}
		}
	}

	private static List<DRNG> createDRNGs(Set<Class<? extends DRNG>> drngClasses) {
		List<DRNG> drngs = new ArrayList<DRNG>();
		for (Class<? extends DRNG> drngClass : drngClasses) {
			try {
				drngs.add(drngClass.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return drngs;
	}
	
	
	
}
