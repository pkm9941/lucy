package exercise.all.lvl2.stockPrice.parkkwangmin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StockPrice {
	
	public static void main(String[] args) {
		StockPrice stockPrice = new StockPrice();
		int[] prices = {1, 2, 3, 2, 3};

		int[] result = stockPrice.solution(prices);
		System.out.println(Arrays.stream(result)
								.boxed()
								.map(t -> String.valueOf(t))
								.collect(Collectors.joining(",")));
	}

	private int[] solution(int[] prices) {
		int[] result  = new int[prices.length];
		for (int i = 0; i < prices.length; i++) {
			int maintainedTime = 0;
			for (int j = i + 1; j < prices.length; j++) {
				maintainedTime++;
				
				if (prices[i] > prices[j])
					break;
			}
			result[i] = maintainedTime;
		}
		return result;
	}
	
}
