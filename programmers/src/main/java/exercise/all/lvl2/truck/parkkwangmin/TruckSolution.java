package exercise.all.lvl2.truck.parkkwangmin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class TruckSolution {

	public static class Bridge {

		private int width;
		private int maximumLoad;
		private Queue<Integer> trucksOnBridge;
		
		public Bridge(int width, int maximumLoad) {
			this.width = width;
			this.maximumLoad = maximumLoad;
			
			resetTrucksOnBridge();
		}
		
		private void resetTrucksOnBridge() {
			trucksOnBridge = new LinkedList<Integer>();
			for (int i = 0; i < width; i++) {
				trucksOnBridge.add(0);
			}
		}

		public int elapsedTime(int[] waitingTrucksArray) {
			Queue<Integer> waitingTrucks = new LinkedList<>(Arrays.stream(waitingTrucksArray).boxed().collect(Collectors.toList()));
			int elapsedTime = 0;
			int sumOfLoadedWeight = 0;
			
			//트럭 진입
			while (true) {
				//대기하는 트럭이 더이상 없다면 마무리
				if (waitingTrucks.isEmpty()) {
					elapsedTime += width;
					resetTrucksOnBridge();
					break;
				}
				
				//다리 맨 끝 트럭 제외(빈 공간(값=0)일 수도 있음)
				sumOfLoadedWeight -= trucksOnBridge.poll();
				
				
				if (maximumLoad < waitingTrucks.peek()) //무한루프 제어용도 
					throw new RuntimeException("다리가 지탱할 수 무게보다 더 무거운 트럭이 있습니다. 트럭 무게 : " + waitingTrucks.peek());
				
				//트럭이 다리로 진입할 수 있는지 판단하여 처리
				if (maximumLoad >= sumOfLoadedWeight + waitingTrucks.peek()) {
					int loadingTruck = waitingTrucks.poll();
					sumOfLoadedWeight += loadingTruck;
					trucksOnBridge.add(loadingTruck);
				} else {
					trucksOnBridge.add(0);
				}
				
				elapsedTime++;
			}
			return elapsedTime;
		}
		
	}

	public static void main(String[] args) {
		Bridge bridge = new Bridge(10, 30);
	}
	
	public int solution(int bridgeLength, int weight, int[] truckWeights) {
		//초기 상황 세팅
		//다리의 상태를 큐로 구현. 트럭이 없는 빈 공간은 값 0으로 채운다.
		Queue<Integer> bridge = new LinkedList<Integer>();
		
		for (int i = 0; i < bridgeLength; i++) {
			bridge.add(0);
		}
		//트럭의 대기열을 큐로 구현
		Queue<Integer> truckWeightsQueue = new LinkedList<>(Arrays.stream(truckWeights).boxed().collect(Collectors.toList()));
		
		int elapsedTime = 0;
		
		//트럭 진입
		while (true) {
			//대기하는 트럭이 더이상 없다면 마무리
			if (truckWeightsQueue.isEmpty()) {
				elapsedTime += bridge.size();
				bridge = new LinkedList<Integer>();
				break;
			}
			
			//다리 맨 끝 트럭 제외(빈 공간(값=0)일 수도 있음)
			bridge.poll();
			
			if (weight < truckWeightsQueue.peek()) //무한루프 제어용도 
				throw new RuntimeException("다리가 지탱할 수 무게보다 더 무거운 트럭이 있습니다. 트럭 무게 : " + truckWeightsQueue.peek());
			
			//트럭이 다리로 진입할 수 있는지 판단하여 처리
			int sumOfWeightOnBridge = bridge.stream().mapToInt(Integer::intValue).sum();
			if (weight >= sumOfWeightOnBridge + truckWeightsQueue.peek()) {
				bridge.add(truckWeightsQueue.poll());
			} else {
				bridge.add(0);
			}
			
			elapsedTime++;
		}
		return elapsedTime;
	}
	
}
