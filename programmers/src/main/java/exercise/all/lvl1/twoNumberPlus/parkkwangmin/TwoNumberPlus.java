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
		Set<Integer> plusNumbers = new HashSet<>();
		
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				plusNumbers.add(numbers[i] + numbers[j]);
			}
		}
		
		int[] sortedPlusNumbers = plusNumbers.stream().sorted().mapToInt(Integer::intValue).toArray();
		return sortedPlusNumbers;
	}
}
