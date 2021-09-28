package exercise.all.lvl2.stockPrice.nohhyungrae;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StockPrice {
	
	public static void main(String[] args) {
		int[] prices = {1,2,3,2,3};
		int[] prices2 = {1, 2, 3, 8, 6, 54, 3, 2, 99, 1};
		int n = 4;
		int w = 8, h = 12;
		System.out.println("result : "+solution4(2,h));
//		System.out.println("result : "+solution3(n));
	}
	
	public static long solution4(int w, int h) {
        long answer = 1;
        
        return w * h - (w *2);
    }
	
	public static int[] solution3(int n) {
        int[] answer = {};
        List<Integer> numbers = new LinkedList<>();
        int num = 1;
        while(n != 0) {
            for(int i=0; i<n; i++) {
            	numbers.add(num);
            	num++;
            }
            n--;
        }
        System.out.println(numbers.toString());

        return answer;
    }
	
	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];
		Queue<Integer> p = new LinkedList<Integer>();
		for(int a : prices) { p.add(a); }
		p.remove();

		for(int i=0; i<prices.length-1; i++) {
			System.out.println(p.toString());
			int a = prices[i];
			answer[i] = (int) p.stream().filter(e -> e >= a).count();
			p.remove();
		}
		answer[prices.length-1] = 0;
		
		for(int a : answer) {
			System.out.println(a);
		}
		
        return answer;
	}
	
	public int[] solution2(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i=0; i<prices.length-1; i++) {
		    int count = 0;
		    for(int j=i+1; j<prices.length; j++) {
				count++;
				if(prices[i] > prices[j]) {
					break;
				}
			}
			answer[i] = count;
		}
		answer[prices.length-1] = 0;
        return answer;
    }
	
}
