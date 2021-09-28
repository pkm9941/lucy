package exercise.all.lvl2.oneTwoFour.nohhyungrae;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OneTwoFour {
	
	//private static StopWatch stopWatch = new StopWatch;
	
	public static void main(String[] args) {
		
		int n = 1000000; 
		System.out.println("solution : "+solution(n));
    }
	
	static Queue<Integer> list = new LinkedList<>();
	static int index = 0;
	public static String solution(int n) {
		String answer = "";
		int[] oneTwoFour = {4,1,2};
		int[] oneTwoFour2 = {1,2,4};
		System.out.println("10진수 : "+n);
		List<Integer> num = new LinkedList<>();
		
		for(int i=0; i<=n; i*=3) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
			System.out.println("i : "+i+" , i%3 : "+i%3);
			answer += oneTwoFour2[i%3];
		}

		
		
		//increaseNum 방법 - 효율성이 안됨
//		while(n > 0) {
//			if(num.isEmpty()) {
//				num.add(oneTwoFour2[index]);
//			}else {
//				num = increaseNum(num,oneTwoFour2, index, 1);
//				
//			}
//			System.out.println("num : "+num+" , num.size() : "+num.size());
//			n--;
//		}
//		
//		for(int a : num) {
//			System.out.println("num : "+a);
//			answer += a;
//		}
		
		//condination 방법
//		condination(oneTwoFour,n,0);
//		for(int a : list) {
//			System.out.println("list : "+a);
//			answer += a;
//		}
		
		return answer;
	}
	
	public static List<Integer> increaseNum(List<Integer> num, int[] oneTwoFour, int i, int last){
		if(num.get(i) == 1) {
			num.set(i, oneTwoFour[1]);
		}else if(num.get(i) == 2)  {
			num.set(i, oneTwoFour[2]);
		}else {
			boolean tf = true;

			for(int a : num) { if(a != 4) {tf = false; break;} }
			
			if(tf) {
				index++;
				num.add(0, oneTwoFour[0]);
				System.out.println("index : "+index+", i : "+i+" , last : "+last);
				for(int x= last ; x < num.size(); x++) {
					num.set(x, oneTwoFour[0]);
				}
				num.set(i, oneTwoFour[0]);
			}else {
				num.set(i, oneTwoFour[0]);
			}
			
			if(i > 0) {
				increaseNum(num, oneTwoFour, index-last, last+1);
			}
			
			for(int x= i ; x < num.size(); x++) {
				num.set(x, oneTwoFour[0]);
			}
			
		}
		return num;
	}
	
	public static void condination(int[] oneTwoFour, int n, int start) {
		
		if(n <= 3) {
			System.out.println(oneTwoFour[n%3]);
			list.add(oneTwoFour[n%3]);
			return ;
		}else {
			if(n%3 == 0) {
				
			}
			condination(oneTwoFour, n/3,start++);
			list.add(oneTwoFour[n%3]);
		}
	}
}
