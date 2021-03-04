package exercise.all.lvl1.twoNumberPlus.nohhyungrae;

import java.util.ArrayList;
import java.util.List;

public class TwoNumberPlus {
	public static void main(String[] args) {
		int[] numbers = {2,1,3,4,1};
		System.out.println("result : "+solution(numbers));
	}
	
	public static int[] solution(int[] numbers) {
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i= 0 ; i <numbers.length-1; i++) {
            for(int j=i+1; j<numbers.length; j++){
            	if(!list.contains(numbers[i] + numbers[j]))
            		list.add(numbers[i] + numbers[j]); 
            }
		}
		
		//Collections.sort(list2);
		//리스트 -> 배열로 변환 (Integer형)
		//answer = list2.stream().sorted().mapToInt(i->i).toArray();
        return list.stream().sorted().mapToInt(i->i).toArray();
	}
}
