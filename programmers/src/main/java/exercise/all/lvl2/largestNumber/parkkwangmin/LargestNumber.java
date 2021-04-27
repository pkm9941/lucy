package exercise.all.lvl2.largestNumber.parkkwangmin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 각 정수를 이어 붙여 만들 수 있는 가장 큰 수 구하기
 * 예) {6, 10, 2} -> {6102, 6210, 1062, 1026, 2610, 2106} -> 6210
 * 
 * 구현해야 하는 중심 로직...
 * 1. 각 정수를 모두 조합할 수 있는 경우의 수를 구하는 방법
 * 2. 어떤 수가 더 큰지 비교하는 방법
 * 
 * @author 박광민
 * @since 2021. 4. 21.
 */
public class LargestNumber {
	
	public static void main(String[] args) {
		//System.out.println("abc".split("").length);
		//int[] numbers = {12, 1, 3, 98, 9, 120};
		int[] numbers = {0, 0, 0};
		//String result = solution1(numbers);
		String result2 = solution2(numbers);
		
		System.out.println(result2);
		//한자리수 최대, 두자리수 최대, 세자리수 최대
		//
	}

	public static String solution2(int[] numbers) {
		Map<Integer, List<String>> numbersByLength = convertToMayKeyIsLength(numbers);
		
		convertNumbersInReverseOrder(numbersByLength);
		
		List<String> finalNumbers = new ArrayList<>();
		while (true) {
			if (numbersByLength.isEmpty())
				break;
			
			List<String> maxNumbersPerLength = numbersByLength.values().stream()
															.map(v -> v.get(0)).collect(Collectors.toList());
			
			if (maxNumbersPerLength.size() == 1) {
				finalNumbers.add(maxNumbersPerLength.get(0));
				
				int numberLength = maxNumbersPerLength.get(0).length();
				numbersByLength.get(numberLength).remove(0);
				if (numbersByLength.get(numberLength).isEmpty())
					numbersByLength.remove(numberLength);
				
				continue;
			}
			
			List<List<String>> combinedNumbers = new ArrayList<>();
			combineNumber(new ArrayList<Integer>(), maxNumbersPerLength, combinedNumbers);
			
			List<String> maxNumbers = Collections.max(combinedNumbers, new Comparator<List<String>>() {
				@Override
				public int compare(List<String> curr, List<String> next) {
					String currStr = curr.stream().collect(Collectors.joining());
					String nextStr = next.stream().collect(Collectors.joining());
					if (currStr.compareTo(nextStr) >= 0) {
						return 1;
					} else {
						return -1;
					}
				}
			});
			
			String maxNumber = maxNumbers.get(0);
			finalNumbers.add(maxNumber);
			
			int maxNumberLength = maxNumber.length();
			numbersByLength.get(maxNumberLength).remove(0);
			if (numbersByLength.get(maxNumberLength).isEmpty())
				numbersByLength.remove(maxNumberLength);
		}
		
		if (finalNumbers.isEmpty() || Integer.valueOf(finalNumbers.get(0)) == 0) {
			finalNumbers.clear();
			finalNumbers.add("0");
		}
		
		return finalNumbers.stream().collect(Collectors.joining());
	}

	private static Map<Integer, List<String>> convertToMayKeyIsLength(int[] numbers) {
		List<String> stringNumbers = IntStream.of(numbers).boxed()
														.map(i -> String.valueOf(i))
														.collect(Collectors.toList());
		
		String maxLengthStr = Collections.max(stringNumbers, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() >= o2.length() ? 1 : -1;
			}
		});
		
		Map<Integer, List<String>> mayKeyIsLength = new HashMap<>();
		for (int i = 1; i <= maxLengthStr.length(); i++) {
			List<String> sameLengths = new ArrayList<>();
			for (String number : stringNumbers) {
				if (number.length() == i)
					sameLengths.add(number);
			}
			
			if (sameLengths.isEmpty()) continue;
			
			Collections.sort(sameLengths, Collections.reverseOrder());
			mayKeyIsLength.put(i, sameLengths);
		}
		return mayKeyIsLength;
	}

	private static void convertNumbersInReverseOrder(Map<Integer, List<String>> numbersByLength) {
		for (int key : numbersByLength.keySet())
			Collections.sort(numbersByLength.get(key), Collections.reverseOrder());
	}

	private static void combineNumber(List<Integer> currIndexs, List<String> eachMaxNumber, List<List<String>> combinedNumbers) {
		if (currIndexs.size() < eachMaxNumber.size()) {
			for (int i = 0; i < eachMaxNumber.size(); i++) {
				if (currIndexs.contains(i)) continue;
				
				List<Integer> nextCurrIndexs = new ArrayList<Integer>();
				for (Integer index : currIndexs) {
					nextCurrIndexs.add(index);
				}
				nextCurrIndexs.add(i);
				combineNumber(nextCurrIndexs, eachMaxNumber, combinedNumbers);
			}
		} else {
			List<String> combinedNumber = currIndexs.stream().map(i -> eachMaxNumber.get(i)).collect(Collectors.toList());
			combinedNumbers.add(combinedNumber);
		}
	}

//	private static String solution1(int[] numbersArray) {
//		//List<Integer> numbers = IntStream.of(numbersArray).boxed().collect(Collectors.toList());
//		List<String> numbers = IntStream.of(numbersArray).boxed().map(i -> String.valueOf(i)).collect(Collectors.toList());
//		
//		List<String> orderedNumbers = new ArrayList<>();
//		
//		while(true) {
//			if (numbers.isEmpty()) break;
//			
//			String maxNumber = Collections.max(numbers, new Comparator<String>() {
//															@Override
//															public int compare(String str1, String str2) {
//																int minLength = str1.length() <= str2.length() ? str1.length() : str2.length();
//																
//																int num1 = Integer.valueOf(str1.substring(0, minLength));
//																int num2 = Integer.valueOf(str2.substring(0, minLength));
//																
//																if (num1 > num2) return 1;
//																if (codePoint1 < codePoint2) return -1;
//																
//																
//																
//																
//																if (str1.length() == 1) return 1;
//																
//																if (str1.length() <= str2.length()) return 1;
//																return -1;
//															}
//			});
//			
//			orderedNumbers.add(maxNumber);
//			numbers.remove(maxNumber);
//		}
//		
//		return null;
//	}
	
}
