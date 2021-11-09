package exercise.all.lvl2.scovilleScale.parkkwangmin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class ScovilleScale {
	
	public static void main(String[] args) {
//		System.out.println(getCnt1(new int[]{1, 2, 3, 9, 10, 12}, 7));
//		System.out.println(getCnt2(new int[]{1, 2, 3, 9, 10, 12}, 7));
		System.out.println(getCntBySortEveryTimeMix(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 350));
		System.out.println(getCntByDivideMixedScovilles(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 350));
	}

	private static int getCntByDivideMixedScovilles(int[] scovilleArray, int base) {
		Arrays.sort(scovilleArray);
		Queue<Integer> scovilles = new LinkedList<>();
		for (int scoville : scovilleArray)
			scovilles.add(scoville);
		
		int combineCnt = 0;
		Queue<Integer> combinedScovilles = new LinkedList<>();
		while(true) {
			if (scovilles.isEmpty() && !combinedScovilles.isEmpty()) {
				scovilles = combinedScovilles;
				combinedScovilles = new LinkedList<>();
				continue;
			}
			
			Integer firstScoville = 0;
			if (scovilles.peek() == null) {
				firstScoville = combinedScovilles.poll();
			} else if (combinedScovilles.peek() == null) {
				firstScoville = scovilles.poll();
			} else {
				firstScoville = scovilles.peek() <= combinedScovilles.peek() ? scovilles.poll() : combinedScovilles.poll();
			}
			
			if (firstScoville >= base) break;
			
			Integer secondScoville = null;
			if (scovilles.peek() == null) {
				secondScoville = combinedScovilles.poll();
			} else if (combinedScovilles.peek() == null) {
				secondScoville = scovilles.poll();
			} else {
				secondScoville = scovilles.peek() <= combinedScovilles.peek() ? scovilles.poll() : combinedScovilles.poll();
			}
			
			if (secondScoville == null) return -1;

			int combinedScoville = firstScoville + secondScoville * 2;
			combineCnt++;
			
			combinedScovilles.add(combinedScoville);
		}
		
		return combineCnt;
	}
	
	private static int getCntBySortEveryTimeMix(int[] scoville, int base) {
		List<Integer> scovilleScales = Arrays.stream(scoville).boxed().sorted().collect(Collectors.toList());
		int combineCnt = 0;
		while(!scovilleScales.isEmpty()) {
			if (scovilleScales.size() == 1) {
				if (scovilleScales.get(0) < base)
					combineCnt = -1;
				
				break;
			}
			
			int firstScoville =  scovilleScales.get(0);
			if (firstScoville >= base)
				break;
			
			int secondScoville =  scovilleScales.get(1);
			
			int combinedScoville = firstScoville + secondScoville * 2;
			combineCnt++;
			
			scovilleScales.remove(1);
			scovilleScales.remove(0);
			scovilleScales.add(combinedScoville);
			
			scovilleScales.sort((a, b) -> a > b ? 1 : -1);
		}
		
		return combineCnt;
	}

}