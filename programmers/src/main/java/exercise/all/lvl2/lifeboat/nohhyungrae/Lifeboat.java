package exercise.all.lvl2.lifeboat.nohhyungrae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Lifeboat {

	public static void main(String[] args) {
		
		int[] people = {70,50,80,50};
		int limit = 100;
		
		int[] people2 = {40, 50, 60, 90, 10,70, 80,90};
		int limit2 = 100;
		
		int[] people3 = {70, 80, 50};
		int limit3 = 100;
		
		int[] people4 = {40, 40, 40, 40, 40, 40};
		int limit4 = 240;
		
		System.out.println("solution : "+solution(people2, limit2));
	}
	
	//정확성 100 효율성 10 
	public static int solution(int[] people, int limit) {
		int answer = 0, tot = 0, index =0;
        boolean sof = false;
        List<Integer> list = new ArrayList<Integer>();
        list = Arrays.stream(people).boxed().collect(Collectors.toList());
        Collections.sort(list);
        
		while(index < Math.ceil((double)list.size()/2)) {
			tot = limit - list.get(index);
			sof = false;
			for(int j=list.size()-1; j>index; j--) {
				int a = list.get(j);
                if(a <= tot) {
                    answer++;
                    list.remove(j);
                    list.remove(index);
                    sof = true;
                    break;
                }
			}
			
			if(!sof) {
    			answer = answer +  list.size();
    			break;
    		}
		}
		return answer;
	}
	
	//정확성 100 효율성 0 
	public static int solution6(int[] people, int limit) {
        int answer = 0;
        
        List<Integer> list = new ArrayList<Integer>();
        list = Arrays.stream(people).boxed().collect(Collectors.toList());
        Collections.sort(list);
        int tot = 0, index = 0;
        boolean sof = false;

        while(!list.isEmpty()) {
        	sof = false;
        	tot = limit - list.get(index);
        	/*
    		for(int j=list.size()-1 ; j>index; j--) {
    			if(tot != 0) {
    				if(list.get(j).equals(tot)) {
    					System.out.println("FRONT : "+list.get(index)+", BACK : "+list.get(j));
    					list.remove(j);
    					System.out.println("size : "+list.size());
    					list.remove(index);
    					System.out.println("size : "+list.size());
    					answer++;
    					sof = true;
    					break;
    				}else if(list.get(j) < tot) {
    					System.out.println("FRONT : "+list.get(index)+", BACK : "+list.get(j));
    					tot = tot - list.get(j);
    					list.remove(j);
    				}
    			}
        	}
    		*/
    		
            for(int j=list.size()-1 ; j>index; j--) {
    			int a = list.get(j);
                if(a <= tot) {
                	System.out.println("FRONT : "+list.get(index)+", BACK : "+list.get(j));
                    list.remove(j);
                    list.remove(index);
                    answer++;
                    sof = true;
                    break;
                }
        	}
        	
    		if(!sof) {
    			sof = true;
    			answer++;
    			list.remove(index);
    		}
    		
        }
       
		return answer;
    }
	public static int solution5(int[] people, int limit) {
		int answer = 0;
		
		List<Integer> list = new ArrayList<Integer>();
		list = Arrays.stream(people).boxed().collect(Collectors.toList());
		Collections.sort(list);
		int tot = 0, index = 0;
		boolean sof = false;
		
		while(!list.isEmpty()) {
			sof = false;
			tot = limit - list.get(index);
			
			for(int j= index+1; j<list.size(); j++) {
				if(list.get(j).equals(tot)) {
					list.remove(index);
					list.remove(j);
					answer++;
					sof = true;
					break;
				}
			}
			
			if(!sof) {
				sof = true;
				answer++;
				list.remove(index);
			}
			
		}
		
		return answer;
	}
	
	public static int solution4(int[] people, int limit) {
		int answer = 0;
		
		List<Integer> list = new ArrayList<Integer>();
		list = Arrays.stream(people).boxed().collect(Collectors.toList());
		Collections.sort(list);
		int tot = 0;
		boolean sof = false;
		
		for(int i=0;i <= list.size()/2; i++) {
			sof = false;
			tot = limit - list.get(i);
			for(int j= list.size()-1; j>i; j--) {
				if(list.get(j).equals(tot)) {
					answer++;
					sof = true;
					break;
				}
			}
			
			if(!sof) {
				sof = true;
				answer++;
			}
		}
		
		if(!sof) {
			sof = true;
			answer++;
		}
		
		return answer;
	}
	
	public static int solution3(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);
		Queue<Integer> boat = new LinkedList<>();
		int sum = 0;
		
		for(int a : people) {
			System.out.print(a+" ");
		}
		System.out.println();
		//======================================================================
		for(int i=0; i< people.length/2; i++) {
        	System.out.print(people[i]+" ");
        	System.out.print(people[people.length-(i+1)]+" ");
        	if(people.length%2 == 0) {
        		if(people[i]+people[people.length-(i+1)] == limit) {
        			
        		}else {
        			
        		}
        	}else {
        		
        	}
        }
        System.out.println();
        System.out.println("people.length/2 : "+people.length/2);
        for(int j=people.length-1; j>=people.length/2 ; j--) {
        	System.out.print(people[j]+" ");
        }
        System.out.println();
		//======================================================================
		for(int i=0; i<= people.length; i++) {
			System.out.println(boat);
			sum = boat.stream().mapToInt(e -> e).sum();
			System.out.println("sum : "+sum);
			System.out.println("answer : "+answer);
			if(i == people.length) {
				answer++;
				boat.removeAll(boat);
				sum = 0;
				break;
			}
			
			if(sum +people[i] > limit) { //내림
				sum = 0;
				answer++;
				boat.removeAll(boat);
				boat.offer(people[i]);
			}else{
				boat.offer(people[i]);
			}
		}
		
		System.out.println("==================");
		System.out.println("sum : "+sum);
		return answer;
	}
	
	public static int solution2(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);
		int sum = 0;
		
		for(int a : people) {
			System.out.print(a+" ");
		}
		System.out.println();
		for(int i=0; i<= people.length; i++) {
			if(i == people.length) {
				answer++;
				break;
			}
			System.out.println("sum : "+sum);
			System.out.println("answer : "+answer);
			if(sum == 0) {
				sum = people[i];
			}else {
				if(sum+people[i] <= limit) {
					sum = sum + people[i];
				}else {
					sum = 0;
					answer++;
					sum = people[i];
				}
				
			}
		}
		
		System.out.println("==================");
		System.out.println("sum : "+sum);
		return answer;
	}

}