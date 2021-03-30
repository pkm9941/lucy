package exercise.all.lvl2.stockPrice.parkkwangmin;

import java.util.Date;

public class StockPrice {
	
	private int[] pricesPerSecond;
	private int[] maintainedTimesForEach;

	public StockPrice(int[] pricesPerSecond) {
		this.pricesPerSecond = pricesPerSecond;
		this.maintainedTimesForEach = new int[pricesPerSecond.length];
	}
	
	public int[] getPricesPerSecond() {
		return pricesPerSecond;
	}

	public static void main(String[] args) {
		int[] pricesPerSecond = generatePicesPerSecond(13000);
		StockPrice stockPrice = new StockPrice(pricesPerSecond);
		
		System.out.println("재귀");
		Date startTime2 = new Date();
		System.out.println(startTime2.toLocaleString());
		stockPrice.calculateMaintainedTimeForEach();
		int[] result2 = stockPrice.getMaintainedTimes();
		Date endTime2 = new Date();
		System.out.println(endTime2.toLocaleString());
		System.out.println(
				(endTime2.getTime() - startTime2.getTime()) / (double)1000
				);
		
		System.out.println("중복for문");
		Date startTime = new Date();
		System.out.println(startTime.toLocaleString());
		
		int[] result1 = stockPrice.solution(pricesPerSecond);
		Date endTime = new Date();
		System.out.println(endTime.toLocaleString());
		System.out.println(
				(endTime.getTime() - startTime.getTime()) / (double)1000
				);
		
		System.out.println(result1[0] == result2[0]);
		System.out.println(result1[10] == result2[10]);
		System.out.println(result1[100] == result2[100]);
		
		
//		System.out.println(Arrays.stream(result)
//								.boxed()
//								.map(t -> String.valueOf(t))
//								.collect(Collectors.joining(",")));
	}

	public static int[] generatePicesPerSecond(int size) {
		int[] pricesPerSecond = new int[size];
		for (int i = 0; i < size; i++) {
			pricesPerSecond[i] = (int) (Math.random() * size);
		}
		 
		return pricesPerSecond;
	}

	public int[] solution(int[] pricesPerSecond) {
		int[] result  = new int[pricesPerSecond.length];
		for (int i = 0; i < pricesPerSecond.length; i++) {
			int maintainedTime = 0;
			for (int j = i + 1; j < pricesPerSecond.length; j++) {
				maintainedTime++;
				
				if (pricesPerSecond[i] > pricesPerSecond[j])
					break;
			}
			result[i] = maintainedTime;
		}
		return result;
	}

	public void calculateMaintainedTimeForEach() {
		for (int index = 0; index < pricesPerSecond.length; index++) {
			plusSecond(index, index + 1);
		}
	}

	private void plusSecond(int baseIndex, int targetIndex) {
		if (targetIndex >= pricesPerSecond.length) return;
		
		maintainedTimesForEach[baseIndex] = maintainedTimesForEach[baseIndex] + 1;
		
		if (pricesPerSecond[baseIndex] <= pricesPerSecond[targetIndex])
			plusSecond(baseIndex, targetIndex + 1);
	}

	public int[] getMaintainedTimes() {
		return this.maintainedTimesForEach;
	}
}
