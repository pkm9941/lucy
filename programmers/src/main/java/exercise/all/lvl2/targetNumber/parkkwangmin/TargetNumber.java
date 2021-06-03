package exercise.all.lvl2.targetNumber.parkkwangmin;

import java.util.ArrayList;
import java.util.List;

public class TargetNumber {
	
	public static void main(String[] args) {
		//조합
		int[] numbers = {1, 1, 1, 1, 1};
		int target = 3;
		
		int result = answer(numbers, target);//재귀
		System.out.println(result);
		int result2 = answer2(numbers, target);//2중for문
		System.out.println(result2);
		int result3 = answer3(numbers, target);//2진법활용
		System.out.println(result3);
	}

	public static int answer3(int[] numbers, int target) {
		String twoStr = "";
		for (int i = 0; i < numbers.length; i++) {
			twoStr += "1";
		}
		//System.out.println(twoStr);
		int tenNumber = (int) Long.parseLong(twoStr, 2);
		//System.out.println(tenNumber);
		
		int count = 0;
		String format = "%0" + numbers.length + "d";
		for (int i = 0; i <= tenNumber; i++) {
			
			
			String binaryString = Long.toBinaryString(i);
			String formatString = String.format(format, Long.parseLong(binaryString));
			//System.out.println(formatString);
			
			int sum = 0;
			for (int j = 0; j < numbers.length; j++) {
				if (Integer.parseInt(formatString.substring(j, j+1)) == 0) sum += numbers[j];
				else sum -= numbers[j];
			}
			
			if (target == sum) count++;
		}
		
		return count;
	}

	public static int answer2(int[] numbers, int target) {
		List<Integer> sumedList = new ArrayList<>();
		for (int number: numbers) {
			if (sumedList.isEmpty()) {
				sumedList.add(number);
				sumedList.add(-number);
				continue;
			}
			List<Integer> addedSumedList = new ArrayList<>();
			for (Integer sumedNumber: sumedList) {
				addedSumedList.add(sumedNumber + number);
				addedSumedList.add(sumedNumber - number);
			}
			sumedList = addedSumedList;
		}
		int count = (int) sumedList.stream().filter(n -> n == target).count();
		return count;
	}

	public static int answer(int[] numbers, int target) {
		int count = reflect(numbers, target, 0, 0);
		return count;
	}

	private static int reflect(int[] numbers, int target, int depth, int sumedValue) {
		if (numbers.length == depth) {
			if (target == sumedValue) return 1;
			return 0;
		}
		
		int count1 = reflect(numbers, target, depth + 1, sumedValue + numbers[depth]);
		int count2 = reflect(numbers, target, depth + 1, sumedValue - numbers[depth]);
		return count1 + count2;
	}
}
