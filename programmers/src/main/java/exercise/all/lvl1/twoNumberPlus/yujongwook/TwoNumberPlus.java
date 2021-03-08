package exercise.all.lvl1.twoNumberPlus.yujongwook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 * 두 개 뽑아서 더하기
 * 
 * 
 * 1. 문제 설명
 * 정수 배열 numbers가 주어집니다. numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.
 * 
 * 2. 제한사항
 * numbers의 길이는 2 이상 100 이하입니다. numbers의 모든 수는 0 이상 100 이하입니다.
 * 
 * 3. info
 * 1) Collections.sort 는 Arrays.sort 보다 느리다
 * 2) for 문 사용시, 조건절에 대상 변수의 사이즈나 길이를 넣는 형태는 일반적으로 사용하는 코딩형태이나, 반복적으로 크기를 체크하게 되므로 데이터의 양이 많을 경우. 먼저 선언하고 하는 것이 더 빠르다. 
 */

//@BenchmarkMode({Mode.AverageTime})
public class TwoNumberPlus {
	public static void main(String[] args) {

		//Test Variables
		int[] numbers = setVar(MODE.DEFAULT, 0);
		//int[] numbers = setVar(MODE.RANDOM, 100);
		System.out.println("numbers ::: " + Arrays.toString(numbers));
		

		//[1] MultiForTest1 
		System.out.println("=== [1] MultiForTest1 ===");
		print(MultiForTest1(numbers));
		
		//[2] MultiForTest2
		System.out.println("=== [2] MultiForTest2 ===");
		print(MultiForTest2(numbers));
		
		//[3] MultiForTest3
		System.out.println("=== [3] MultiForTest3 ===");
		print(MultiForTest3(numbers));
		
	}
	
	public enum MODE {
		DEFAULT,
		RANDOM
	}
	
	
	//setVriables
	public static int[] setVar (MODE mode, int cnt) {
		
		int[] result = null;
		
		switch(mode) {
		case DEFAULT :
			int[] v1 = {2,1,3,4,1};
			result = v1;
			break;
		case RANDOM :
			Random random = new Random();
			int v2[] = new int[cnt];
			List<Integer> arr = new ArrayList<>();
			for (int i=0; i<cnt; i++) {
				arr.add(random.nextInt(cnt));
			}
			result = arr.stream().mapToInt(Integer::intValue).toArray();
			break;	
		}
		
		return result;
	}
	
	
	//Console
	public static void print(List<Integer> result) {
		for (int cnt=0; cnt<result.size(); cnt++) {
			System.out.println(result.get(cnt));
		}
	}
	
	
	//MultiForTest1
	public static List<Integer> MultiForTest1(int[] numbers){
		List<Integer> arr = new ArrayList<>();
		
		int length = numbers.length;
		for (int i=0; i<length; i++) {
			for (int j=0; j<length; j++) {
				int sum = numbers[i] +numbers[j];
				if (!arr.contains(numbers[i] +numbers[j])) {
					arr.add(sum);
				}
			}
		}
		Collections.sort(arr);
		
		return arr;
	}
	
	//MultiForTest2
	public static List<Integer> MultiForTest2(int[] numbers){
		List<Integer> arr = new ArrayList<>();
		
		int length = numbers.length;
		for (int i=0; i<length; i++) {
			for (int j=i; j<length; j++) {
				int sum = numbers[i] +numbers[j];
				if (!arr.contains(sum)) arr.add(sum);
			}
		}
		
		Collections.sort(arr);
		
		return arr;
	}
	
	//MultiForTest3
	public static List<Integer> MultiForTest3(int[] numbers){
		List<Integer> arr = new ArrayList<>();
		
		int length = numbers.length;
		for (int i=0; i<length; i++) {
			for (int j=i; j<length; j++) {
				arr.add(numbers[i] +numbers[j]);				
			}
		}
		List<Integer> ans = arr.stream().sorted().distinct().collect(Collectors.toList());
		
		return ans;
	}
	
}
