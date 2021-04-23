package exercise.all.lvl2.largestNumber.parkkwangmin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
		int[] numbers = {12, 1, 3, 98, 9, 120};
		//String result = solution1(numbers);
		String result2 = solution2(numbers);
		
		System.out.println(result2);
		//한자리수 최대, 두자리수 최대, 세자리수 최대
		//
	}

	private static String solution2(int[] numbersArray) {
		List<String> numbers = IntStream.of(numbersArray).boxed().map(i -> String.valueOf(i)).collect(Collectors.toList());
		String maxLengthStr = Collections.max(numbers, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() >= o2.length() ? 1 : -1;
			}
		});
		
		List<List<String>> categorizedNumbers = new ArrayList<>();
		int maxLength = maxLengthStr.length();
		for (int i = 1; i <= maxLength; i++) {
			List<String> sameLengths = new ArrayList<>();
			for (String number : numbers) {
				if (number.length() == i)
					sameLengths.add(number);
			}
			Collections.sort(sameLengths, Collections.reverseOrder());
			categorizedNumbers.add(sameLengths);
		}
		
		List<String> result = new ArrayList<>();
		
		while (true) {
			if (!categorizedNumbers.stream().anyMatch(t -> !t.isEmpty()))
				break;
			
			List<String> eachMaxNumber = categorizedNumbers.stream().filter(t -> !t.isEmpty()).map(t -> t.get(0)).collect(Collectors.toList());
			if (eachMaxNumber.size() == 1) {
				result.add(eachMaxNumber.get(0));
				categorizedNumbers.forEach(t -> {
					if (!t.isEmpty()) {
						t.remove(0);
					}
				});
				
				continue;
			}
			
			//순열조합
			List<List<String>> combinedNumbers = new ArrayList<>();
			combineNumber(new ArrayList<Integer>(), eachMaxNumber, combinedNumbers);
			
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
			result.add(maxNumber);
			for (List<String> aCategorizedNumbers : categorizedNumbers) {
				if (aCategorizedNumbers.contains(maxNumber)) {
					aCategorizedNumbers.remove(0);
					break;
				}
			}
		}
		
		return result.stream().collect(Collectors.joining());
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
