package harness;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
		printResults(results);
	}

	/**
	 * Runs each DRNG for runTime milliseconds. Then stores results in result
	 * map.
	 * 
	 * @param drngs
	 */
	private static void runTests(List<DRNG> drngs) {
		for (DRNG drng : drngs) {
			System.out.println("Running test: " + drng.getClass().getName());

			// Set the seed as a control.
			drng.setSeed(0);

			long count = 0;
			int runTime = 3000;
			long start = System.currentTimeMillis();
			long end = start + runTime;

			while (System.currentTimeMillis() < end) {
				drng.run();
				count++;
			}
			// Get the actual duration in case the while loop ran over.
			long duration = System.currentTimeMillis() - start;

			long speed = count / (duration / 1000);
			results.put(drng, speed);

			System.out.println(drng.getClass().getSimpleName()
					+ " done. Speed: " + speed + " num/sec");
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

	private static void printResults(Map<DRNG, Long> res) {
		SortedSet<Map.Entry<DRNG, Long>> entries = entriesSortedByValues(res);
		
		System.out.println();
		System.out.println("Results (higher is better):");
		for (Entry<DRNG, Long> entry : entries) {
			String drngName = entry.getKey().getClass().getSimpleName();
			System.out.println(entry.getValue() + " num/sec -> " + drngName);
		}
	}

	private static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
				new Comparator<Map.Entry<K, V>>() {
					@Override
					public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
						return e1.getValue().compareTo(e2.getValue());
					}
				});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

}
