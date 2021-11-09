package exercise.all.lvl2.scovilleScale.nohhyungrae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
<문제 설명>
* 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
* Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
* Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 
* 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.

<제한 사항>
* scoville의 길이는 2 이상 1,000,000 이하입니다.	
* K는 0 이상 1,000,000,000 이하입니다.
* scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
* 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
*/
public class ScovilleScale {

	private int[] scoville;
	private int K = 0;
	private int scovilleLate = 0;
	private PriorityQueue<Integer> que = new PriorityQueue<Integer>();

	public ScovilleScale(int[] scoville, int K) {
		this.K = K;
		this.scoville = scoville;
	}

	public int solution() {
		int answer = 0;

		for (int s : scoville) {
			que.add(s);
		}

		while (true) {

			if (que.peek() >= K) {
				return answer;
			} else if (que.size() <= 1) {
				return -1;
			} else {
				scovilleLate = que.poll() + (que.poll() * 2);
				que.add(scovilleLate);
				answer++;
			}

		}

	}

	/******************************** MAIN ****************************************/

	public static void main(String[] args) {

		int[] scovilleArr = { 0, 1, 2, 3 };
		int[] scovilleArr2 = { 1, 2, 3, 9, 10, 12, 13, 2, 100000, 3, 9, 10, 12, 100, 300, 400 };
		int[] scovilleArr3 = { 1, 2, 3 };
		int[] scovilleArr4 = { 0, 0, 5, 5, 5 };
		int[] scovilleArr5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] scovilleArr6 = { 1, 2, 3, 9, 10, 12 };

		int K = 11;
		int K4 = 2;
		int K5 = 7;
		int K6 = 7;

		// Object
		ScovilleScale scoville = new ScovilleScale(scovilleArr6, K6);
		System.out.println("Object solution : " + scoville.solution());
		// ARRAY
		System.out.println("Array  solution : " + solution2(scovilleArr6, K6));
		// PriorityQueue
		System.out.println("Queue  solution : " + solution3(scovilleArr6, K6));
		// LIST
		System.out.println("List   solution : " + solution4(scovilleArr6, K6));

	}

	/******************************* LIST **************************************/

	public static int solution4(int[] scoville, int K) {
		int answer = 0;
		int scovilleLate = 0;
		int first = 0, second = 0;
		List<Integer> list = new ArrayList<Integer>();

		for (int a : scoville) {
			list.add(a);
		}
		sortValue(list);

		while (true) {

			if (list.get(0) >= K) {
				return answer;
			} else if (list.size() <= 1) {
				return -1;
			} else {
				first = list.get(0);
				second = list.get(1) * 2;
				scovilleLate = first + second;
				removeList(list);
				list.add(scovilleLate);
				sortValue(list);
				answer++;
			}
		}

	}

	private static List<Integer> sortValue(List<Integer> list) {
		Collections.sort(list);
		return list;
	}

	private static List<Integer> removeList(List<Integer> list) {
		for (int i = 0; i < 2; i++) {
			list.remove(0);
		}
		return list;
	}

	/******************************* PriorityQueue ********************************/

	public static int solution3(int[] scoville, int K) {
		int answer = 0;
		int scovilleLate = 0;
		int first = 0, second = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

		for (int a : scoville) {
			queue.add(a);
		}

		while (true) {

			if (queue.peek() >= K) {
				return answer;
			} else if (queue.size() <= 1) {
				return -1;
			} else {
				first = queue.poll();
				second = queue.poll() * 2;
				scovilleLate = first + second;
				queue.add(scovilleLate);
				answer++;
			}
		}
	}

	/****************************** ARRAY *************************************/
	public static int solution2(int[] scoville, int K) {
		int answer = 0;
		int scovilleLate = 0;

		Arrays.sort(scoville);

		while (true) {
			if (scoville[0] >= K) {
				break;
			} else if (scoville.length <= 1) {
				return -1;
			} else {
				scovilleLate = scoville[0] + (scoville[1] * 2);
				scoville = minusArr(scoville);
				scoville[0] = scovilleLate;
				Arrays.sort(scoville);
				answer++;
			}
		}

		return answer;
	}

	private static int[] minusArr(int[] arr) {
		int[] arr2 = new int[arr.length - 1];
		for (int j = 0; j < arr.length - 1; j++) {
			arr2[j] = arr[j + 1];
		}
		return arr2;
	}

}