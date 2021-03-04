package exercise.all.lvl1.twoNumberPlus.nohhyungrae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwoNumberPlus {
	public static void main(String[] args) {
		int[] numbers = {2,1,3,4,1};
		System.out.println("result : "+solution(numbers));
	}
	
	public static int[] solution(int[] numbers) {
		int[] answer = {};
		Arrays.sort(numbers);
		
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();

		for(int num : numbers) {
			list.add(num);
		}
		
		for(int i= 0 ; i <list.size()-1; i++) {
            for(int j=i+1; j<list.size(); j++){
            	if(!list2.contains(list.get(i) + list.get(j)))
            		list2.add(list.get(i) + list.get(j)); 
            }
		}
		Collections.sort(list2);
		
		//리스트 -> 배열로 변환 (Integer형)
		answer = list2.stream().mapToInt(i->i).toArray();
        return answer;
	}
}
