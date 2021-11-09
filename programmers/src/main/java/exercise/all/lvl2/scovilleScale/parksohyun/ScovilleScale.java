package exercise.all.lvl2.scovilleScale.parksohyun;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ScovilleScale {
	
	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K=7;
		
		solution(scoville,K);
		//solution2(scoville,K);
	}

	private static int solution(int[] scoville, int K) {		
		int answer =0;
        LinkedList<Integer> list = new LinkedList<Integer>();
        
        for(int num : scoville) {
        	list.add(num);
        }
        
        while(list.size()>1) {
        	Collections.sort(list);        	
        	if(list.peek()>=K) {
        		return answer;
        	}
        	
        	int first = list.poll();
        	int second = list.poll(); 
        	int sum= first + (second * 2);
        	
        	list.add(sum);
        	answer++;
        }
        
        if(list.get(0)<K) return -1;        
      	
		return answer;

	}
	private static int solution2(int[] scoville, int K) {
		
		int answer=0;
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

		for(int num : scoville) {
        	queue.add(num);
        }
        
		while(queue.size()>1) {
			if(queue.peek()>=K) {
        		return answer;
        	}
			int queFirst =queue.poll();
			int queSecond =queue.poll();
			int queSum = queFirst+(queSecond*2);
			
			queue.add(queSum);
			answer++;
		}
		
     if(queue.peek()<K) return -1;        

		return answer;
		
	}
}