package exercise.all.lvl2.scovilleScale.parkkwangmin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class ScovilleScale {

	private int k;
	Queue<Integer> sortedScovilles;
	private int mixedCnt = 0;
	//혼합하여 생성된 새로운 물질도 재료가 되므로 기존 재료 목록에 추가해야 하는데 정렬 상태를 유지하며 추가하기가 힘든 상황...(혼합할 때마다 정렬해야 하는 비용 큼)
	//혼합한 재료는 특성상 혼합한 시간과 스코빌지수가 상관관계가 있으므로(나중에 혼합된 재료의 스코빌지수가 먼저 혼합된 스코빌지수보다 작을 수 없음)
	//이들만 별도의 컬렉션으로 관리해서 정렬 상태를 유지하고자 함
	Queue<Integer> mixedScovilles = new LinkedList<>();
	boolean isAllFoodsMoreSpicyThanK = false;
	
	public ScovilleScale(int[] scovilleArray, int k) {
		this.k = k;
		Arrays.sort(scovilleArray);
		sortedScovilles = new LinkedList<>();
		for (int scoville : scovilleArray)
			sortedScovilles.add(scoville);
	}

	public static void main(String[] args) {
//		System.out.println(getCnt1(new int[]{1, 2, 3, 9, 10, 12}, 7));
//		System.out.println(getCnt2(new int[]{1, 2, 3, 9, 10, 12}, 7));
		ScovilleScale scovilleScale = new ScovilleScale(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 20);
		scovilleScale.mixUntilAllFoodMoreSpicyThanK();
		System.out.println(scovilleScale.getMixedCnt());
		System.out.println(getCntBySortEveryTimeMix(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 20));
		System.out.println(getCntByDivideMixedScovilles(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, 20));
	}

	private int getMixedCnt() {
		return mixedCnt;
	}

	private void mixUntilAllFoodMoreSpicyThanK() {
		while(true) {
			mixTwoLeastSpicyFoodsUntilCleanFoodsEmpty();
			if (isAllFoodsMoreSpicyThanK)
				break;
			
			if (noMoreFood()) {
				mixedCnt = -1;
				break;
			}
			
			moveMixedFoodToCleanFood();
		}
	}

	/**
	 * 섞은 음식끼리 다시 섞기 위해 섞은 음식들을 순수 음식용 컬렉션에 옮겨 담는다. 
	 * @author 박광민
	 * @since 2021. 11. 9.
	 */
	private void moveMixedFoodToCleanFood() {
		sortedScovilles = mixedScovilles;
		mixedScovilles = new LinkedList<>();
	}

	private boolean noMoreFood() {
		return mixedScovilles.isEmpty();
	}

	public void mixTwoLeastSpicyFoodsUntilCleanFoodsEmpty() {
		isAllFoodsMoreSpicyThanK = false;
		while(!sortedScovilles.isEmpty()) {
			Integer firstSmallScoville = pollFirstSmallScoville();
			if (firstSmallScoville >= k) {
				isAllFoodsMoreSpicyThanK = true;
				return;
			}
			
			Integer secondSmallScoville = pollFirstSmallScoville();
			if (secondSmallScoville == null)
				return;
			
			int mixedScoville = firstSmallScoville + secondSmallScoville * 2;
			mixedCnt++;
			
			mixedScovilles.add(mixedScoville);
		}
	}

	public Integer pollFirstSmallScoville() {
		Integer firstSmallScoville = 0;
		if (sortedScovilles.peek() == null) {
			firstSmallScoville = mixedScovilles.poll();
		} else if (mixedScovilles.peek() == null) {
			firstSmallScoville = sortedScovilles.poll();
		} else {
			firstSmallScoville = sortedScovilles.peek() <= mixedScovilles.peek() ? sortedScovilles.poll() : mixedScovilles.poll();
		}
		return firstSmallScoville;
	}

	private static int getCntByDivideMixedScovilles(int[] scovilleArray, int k) {
		Arrays.sort(scovilleArray);
		Queue<Integer> scovilles = new LinkedList<>();
		for (int scoville : scovilleArray)
			scovilles.add(scoville);
		
		int combineCnt = 0;
		//혼합하여 생성된 새로운 물질도 재료가 되므로 기존 재료 목록에 추가해야 하는데 정렬 상태를 유지하며 추가하기가 힘든 상황...(혼합할 때마다 정렬해야 하는 비용 큼)
		//혼합한 재료는 특성상 혼합한 시간과 스코빌지수가 상관관계가 있으므로(나중에 혼합된 재료의 스코빌지수가 먼저 혼합된 스코빌지수보다 작을 수 없음)
		//이들만 별도의 컬렉션으로 관리해서 정렬 상태를 유지하고자 함
		Queue<Integer> mixedScovilles = new LinkedList<>();
		while(true) {
			if (scovilles.isEmpty() && !mixedScovilles.isEmpty()) {
				scovilles = mixedScovilles;
				mixedScovilles = new LinkedList<>();
				continue;
			}
			
			Integer firstSmallScoville = 0;
			if (scovilles.peek() == null) {
				firstSmallScoville = mixedScovilles.poll();
			} else if (mixedScovilles.peek() == null) {
				firstSmallScoville = scovilles.poll();
			} else {
				firstSmallScoville = scovilles.peek() <= mixedScovilles.peek() ? scovilles.poll() : mixedScovilles.poll();
			}
			
			if (firstSmallScoville >= k) break;
			
			Integer secondSmallScoville = null;
			if (scovilles.peek() == null) {
				secondSmallScoville = mixedScovilles.poll();
			} else if (mixedScovilles.peek() == null) {
				secondSmallScoville = scovilles.poll();
			} else {
				secondSmallScoville = scovilles.peek() <= mixedScovilles.peek() ? scovilles.poll() : mixedScovilles.poll();
			}
			
			if (secondSmallScoville == null) return -1;

			int mixedScoville = firstSmallScoville + secondSmallScoville * 2;
			combineCnt++;
			
			mixedScovilles.add(mixedScoville);
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