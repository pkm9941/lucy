package exercise.all.lvl2.hIndex.nohhyungrae;

import java.util.Arrays;
import java.util.Collections;

public class HIndex {
	
	public static void main(String[] args) {
		int [] citations = {3, 0, 6, 1, 5};
		int [] citations2 = {0,1,2,3,3,3,3,3,3,4,10,20,30,40};
		int [] citations3 = {22,42};
		
		System.out.println("solution : "+solution(citations));
	}
	
	public static int solution(int[] citations) {
        int answer = 0;
        Integer[] arr = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        
        for(int i=0; i< arr.length; i++) {
        	if(arr[i] <= (i+1))
        		return i;
        	else
                answer = i+1;
        }
        
        
        return answer;
    }

}