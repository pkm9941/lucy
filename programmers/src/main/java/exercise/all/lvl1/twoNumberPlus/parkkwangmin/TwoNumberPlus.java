package exercise.all.lvl1.twoNumberPlus.parkkwangmin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TwoNumberPlus {
	public static void main(String[] args) {
		
		int[] numbers = {2,1,3,4,1};
		int[] sortedPlusNumbers = getSortedPlusNumbers(numbers);

		String strForPrint = Arrays.stream(sortedPlusNumbers).boxed().map(t -> String.valueOf(t))
				.collect(Collectors.joining(","));
		System.out.println("sortedPlusNumbers : " + strForPrint);
	}

	private static int[] getSortedPlusNumbers(int[] numbers) {
		Set<Integer> plusNumbers = new HashSet<>();//중복 제거를 위해 Set 선택
		for (int index = 0; index < numbers.length - 1; index++) {
			for (int otherIndex = index + 1; otherIndex < numbers.length; otherIndex++) {
				plusNumbers.add(numbers[index] + numbers[otherIndex]);
			}
		}
		
		int[] sortedPlusNumbers = plusNumbers.stream().sorted()
										.mapToInt(Integer::intValue)//Integer를 int로 형변환
										.toArray();
		return sortedPlusNumbers;
	}
}
