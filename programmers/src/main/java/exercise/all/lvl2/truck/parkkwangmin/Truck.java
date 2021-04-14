package exercise.all.lvl2.truck.parkkwangmin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Truck {
	
	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7,4,5,6};
		int elapsedTime = solution(bridge_length, weight, truck_weights);
		System.out.println("elapsedTime : " + elapsedTime);
	}

	private static int solution(int bridgeLength, int weight, int[] truckWeights) {
		Queue<Integer> bridge = new LinkedList<Integer>();
		Queue<Integer> truckWeightsQueue = new LinkedList<>(Arrays.stream(truckWeights).boxed().collect(Collectors.toList()));
		
		bridge.add(truckWeightsQueue.poll());
		int elapsedTime = 1;
		
		while (!bridge.isEmpty()) {
			elapsedTime++;
			
			//대기하는 트럭이 있는지 체크
			if (truckWeightsQueue.isEmpty()) {
				bridge.poll();
				continue;
			}
			
			//이번에 다리를 빠져나가는 트럭이 있다면 빠져나가게 하기
			if (bridge.size() == bridgeLength)
				bridge.poll();
			
			//트럭이 다리로 진입할 수 있는지 무게 계산
			int weightOfTrucksOnBridge = bridge.stream().mapToInt(i -> i).sum();
			if (weight >= weightOfTrucksOnBridge + truckWeightsQueue.peek()) {
				bridge.add(truckWeightsQueue.poll());
			} else {
				bridge.add(0);
			}
		}
		return elapsedTime;
	}
	
}
