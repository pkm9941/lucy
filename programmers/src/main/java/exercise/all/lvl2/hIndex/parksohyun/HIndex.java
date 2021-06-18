package exercise.all.lvl2.hIndex.parksohyun;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HIndex {
	
	public static void main(String[] args) {
		int[] citations= {20,19,18,2,3};
		int result =solution(citations);
		System.out.println(result);
	}

	private static int solution(int[] citations) {
		
		int answer = 0;        
        
        Arrays.sort(citations);
        int max = citations[citations.length-1];
    	Map<Integer, Integer> map = new HashMap<>();
    	
       for(int i=0;i<=max;i++) {
       	int cnt =0;
        	for(int j=0;j<citations.length;j++) {
        		if(i<=citations[j]) {
        			cnt++;
        		}
        	}
        	map.put(i, cnt);      	
        }
      
       Set<Integer> aKeys = map.keySet();          
       for(Integer key: aKeys) {
    	   if(key<=map.get(key)) {
    		  answer =key; 
    	   }
       }       
        return answer;
    }

}