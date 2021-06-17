package exercise.all.lvl2.hIndex.parkkwangmin;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HIndexNormal {
	
	public static void main(String[] args) {
		int[] citations = {3, 0, 6, 1, 5};
		int hIndex = getHIndex(citations);
		System.out.println(hIndex);
		int hIndex2 = getHIndexList(citations);
		System.out.println(hIndex2);
	}

	public static int getHIndex2(int[] citations) {
		Map<Integer, Long> citationsMap = Arrays.stream(citations)
			.boxed()
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		int maxCitation = Arrays.stream(citations).max().orElse(0);
		
		int paperCntOfTheCitation = 0;
		for (int citation = maxCitation; citation > 0; citation--) {
			paperCntOfTheCitation += citationsMap.getOrDefault(citation, (long)0);
			if (paperCntOfTheCitation >= citation)
				return citation;
		}
		
		return 0;
	}
	
	public static int getHIndex(int[] citations) {
		Map<Integer, Integer> citationsMap = new HashMap<>();
		Arrays.stream(citations)
				.forEach(citation -> {
					if (citationsMap.containsKey(citation)) {
						citationsMap.put(citation, citationsMap.get(citation) + 1);
					} else {
						citationsMap.put(citation, 1);
					}
			});
		
		int maxCitation = Arrays.stream(citations).max().orElse(0);
		
		int paperCntOfTheCitation = 0;
		for (int citation = maxCitation; citation > 0; citation--) {
			paperCntOfTheCitation += citationsMap.getOrDefault(citation, 0);
			if (paperCntOfTheCitation >= citation)
				return citation;
		}
		
		return 0;
	}
	
	public static int getHIndexList(int[] citations) {
		List<Integer> reversedCitations = Arrays.stream(citations).boxed()
				.sorted(Collections.reverseOrder()).collect(Collectors.toList());
		int maxCitation = reversedCitations.get(0);
		
		int reversedCitationsSize = reversedCitations.size();
		int currIndex = 0;
		int paperCntOfTheCitation = 0;
		for (int citation = maxCitation; citation > 0; citation--) {
			for (int i = currIndex; i < reversedCitationsSize; i++) {
				if (reversedCitations.get(i) < citation) {
					currIndex = i;
					break;
				}
				paperCntOfTheCitation += 1;
			}
			
			if (paperCntOfTheCitation >= citation)
				return citation;
		}
		
		return 0;
	}
	
	public static int getHIndexArray(int[] citations) {
		Integer[] reversedCitations = Arrays.stream(citations).boxed()
				.sorted(Collections.reverseOrder()).toArray(Integer[]::new);
		int maxCitation = reversedCitations[0];
		
		int reversedCitationsSize = reversedCitations.length;
		int currIndex = 0;
		int paperCntOfTheCitation = 0;
		for (int citation = maxCitation; citation > 0; citation--) {
			for (int i = currIndex; i < reversedCitationsSize; i++) {
				if (reversedCitations[i] < citation) {
					currIndex = i;
					break;
				}
				paperCntOfTheCitation += 1;
			}
			
			if (paperCntOfTheCitation >= citation)
				return citation;
		}
		
		return 0;
	}
	
	public static int getHIndexIntArray(int[] citations) {
		int[] reversedCitations = Arrays.stream(citations).boxed()
				.sorted(Collections.reverseOrder())
				.mapToInt(x -> x).toArray();
		int maxCitation = reversedCitations[0];
		
		int reversedCitationsSize = reversedCitations.length;
		int currIndex = 0;
		int paperCntOfTheCitation = 0;
		for (int citation = maxCitation; citation > 0; citation--) {
			for (int i = currIndex; i < reversedCitationsSize; i++) {
				if (reversedCitations[i] < citation) {
					currIndex = i;
					break;
				}
				paperCntOfTheCitation += 1;
			}
			
			if (paperCntOfTheCitation >= citation)
				return citation;
		}
		
		return 0;
	}

}