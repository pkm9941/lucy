package exercise.all.lvl2.newsClustering.parkkwangmin;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NewsClustering {
	
	public static void main(String[] args) {
	}

	public int calculateJaccardIndex(String str1, String str2) {
		Map<String, Integer> str1Map = generateJaccardMap(str1);
		Map<String, Integer> str2Map = generateJaccardMap(str2);
		
		if (str1Map.isEmpty() && str2Map.isEmpty()) return getJaccardIndex(str1Map, str2Map);
		
		Map<String, Integer> intersectionMap = getIntersection(str1Map, str2Map);
		Map<String, Integer> unionMap = getUnion(str1Map, str2Map);
		return getJaccardIndex(intersectionMap, unionMap);
	}

	protected Map<String, Integer> generateJaccardMap(String str) {
		int length = str.length();
		Map<String, Integer> strMap = new HashMap<>();
		for (int i = 1; i < length; i++) {
			String twoChar = str.substring(i - 1, i + 1).toLowerCase();
			if (twoChar.matches("^[a-z]*$")) {
				if (!strMap.containsKey(twoChar)) {
					strMap.put(twoChar, 1);
				} else {
					strMap.put(twoChar, strMap.get(twoChar) + 1);
				}
			}
		}
		return strMap;
	}

	protected Map<String, Integer> getIntersection(Map<String, Integer> strMap1, Map<String, Integer> strMap2) {
		Map<String, Integer> intersectionMap = new HashMap<String, Integer>();
		for (Entry<String, Integer> entry : strMap1.entrySet()) {
			String key = entry.getKey();
			if (strMap2.containsKey(key)) {
				intersectionMap.put(key, Math.min(entry.getValue(), strMap2.get(key)));
			}
		}
		return intersectionMap;
	}

	protected Map<String, Integer> getUnion(Map<String, Integer> strMap1, Map<String, Integer> strMap2) {
		Map<String, Integer> unionMap = new HashMap<>();
		unionMap.putAll(strMap2);
		for (Entry<String, Integer> entry : strMap1.entrySet()) {
			String key = entry.getKey();
			if (unionMap.containsKey(key)) {
				unionMap.put(key, Math.max(entry.getValue(), strMap2.get(key)));
			} else {
				unionMap.put(key, entry.getValue());
			}
		}
		return unionMap;
	}

	protected int getJaccardIndex(Map<String, Integer> intersectionMap, Map<String, Integer> unionMap) {
		if (unionMap.isEmpty()) return 1 * 65536;
		
		int intersectionCnt = intersectionMap.values().stream().reduce(0, (a, b) -> (a + b)).intValue();
		int unionCnt = unionMap.values().stream().reduce(0, (a, b) -> (a + b)).intValue();
		
		return (int) Math.floor( (intersectionCnt / (double)unionCnt) * 65536 );
	}

}
