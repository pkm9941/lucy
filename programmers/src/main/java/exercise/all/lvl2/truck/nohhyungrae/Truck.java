package exercise.all.lvl2.truck.nohhyungrae;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Truck {
	
	public static void main(String[] args) {
		int bridge_length = 2; 
		int weight = 10; 
		int[] truck_weights = {7,4,5,6};
		
		int bridge_length2 = 100; 
		int weight2 = 100; 
		int[] truck_weights2 = {10};
		
		int bridge_length3 = 100; 
		int weight3 = 100; 
		int[] truck_weights3 = {10,10,10,10,10,10,10,10,10,10};
		
		int bridge_length4 = 5; 
		int weight4 = 5; 
		int[] truck_weights4 = {2, 2, 2, 2, 1, 1, 1, 1, 1};
		
		System.out.println("solution : "+solution(bridge_length, weight, truck_weights));
	}
	
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0, totalweight = 0, index=0;
		Queue<Integer> que =  new ArrayDeque<Integer>();
		
        for(int x=0; x<bridge_length; x++) { que.add(0); }
        System.out.println("==========다리길이 : "+bridge_length+", ===========무게 : "+weight);
        totalweight += truck_weights[index];
        que.add(truck_weights[index++]);
        que.poll();
        time++;

        while(!que.isEmpty()) {
        	System.out.println("지난 시간 : "+time+"초 경과 "+" , 다리 : "+que.toString()+" totalweight : "+totalweight);
        	totalweight -= que.peek();
            que.poll();
            time++;
            System.out.println("totalweight : "+totalweight);
            if(joinBridgeCheck(totalweight,truck_weights ,index ,weight)) {
                if(index < truck_weights.length) {
                    totalweight += truck_weights[index];
                    que.add(truck_weights[index++]);
                }
            }else {
                que.add(0);
            }

        }
        
		System.out.println("지난 시간 : "+time+"초 경과 "+" , 다리 : "+que.toString());
        return time;
	
	}
	
	public static int getTotalWeight(List<Integer> brdg) {
		 int tot = 0;
		 for(int i=0; i<brdg.size(); i++) {
			 tot += brdg.get(i);
		 }
		 return tot;
	}
	 
	public static int getTotalWeight2(Queue<Integer> brdg) {
		 int tot = 0;
		 List<Integer> num = new LinkedList<Integer>();
		 brdg.forEach(e->{ num.add(e); });
		 for(int i=0; i<num.size(); i++) {
			 tot += num.get(i);
		 }
		 return tot;
	}
	 
	public static boolean joinBridgeCheck(int tot, int[] truck_weights, int i , int brdgWeight) {
		 boolean ch = true;
		 int a = 0;
		 if(i < truck_weights.length) a = truck_weights[i];
		 
		 if(tot+a <= brdgWeight) {
			 ch = true;
		 }else {
			 ch = false;
		 }
		 
		 return ch;
	}
	
	public static boolean joinBridgeCheck1(int tot, int nextTruck, int brdgWeight) {
		 boolean ch = true;
		 
		 if(tot+nextTruck <= brdgWeight) {
			 ch = true;
		 }else {
			 ch = false;
		 }
		 
		 return ch;
	}
	 
	 
	public static int solution2(int bridge_length, int weight, int[] truck_weights) {
		 int time = 0, totalweight = 0, afTruck =0, i=1;
		 List<Integer> waitTruck = new LinkedList<Integer>();
		 Queue<Integer> que =  new ArrayDeque<Integer>();
		 
		 for(int x=0; x<truck_weights.length; x++) {
			 waitTruck.add(truck_weights[x]);
		 }
		 
		 for(int x=0; x<bridge_length-1; x++) { 
			 que.add(0); 
		 }
		 
		 System.out.println("==========다리길이 : "+bridge_length+", ===========무게 : "+weight);
	 
		que.add(waitTruck.get(0));
		 //que.poll();
		waitTruck.remove(0);
		 //ime++;
	 
		while(!que.isEmpty()) {
			 time++;
			 System.out.println("지난 시간 : "+time+"초 경과 "+" , 다리 : "+que.toString());
		 
		totalweight = getTotalWeight2(que);
		afTruck = 0;
		if(!waitTruck.isEmpty()) afTruck = waitTruck.get(0);
		 
		System.out.println("totalweight : "+totalweight);
			if(joinBridgeCheck1(totalweight,afTruck,weight)) {
				 if(!waitTruck.isEmpty()) {
					 que.add(waitTruck.get(0));
					 waitTruck.remove(0);
				 }
				 que.poll();
			 }else {
				 que.add(0);
				 que.poll();
			 }
			 
			 
		}
		//time++;
		System.out.println("지난 시간 : "+time+"초 경과 "+" , 다리 : "+que.toString());
		 return time;
		 
	}
	
//	public static int truckPassingTheBridge(int bridge_length, int weight, int[] truck_weights) {
//		
//		int time = 0, totalweight = 0, afTruck =0;
//		List<Integer> waitTruck = new LinkedList<Integer>();
//		for(int i=0; i<truck_weights.length; i++) {
//			waitTruck.add(truck_weights[i]);
//		}
//		List<Integer> bridge = new LinkedList<Integer>();
//		List<Integer> finish = new LinkedList<Integer>();
//		System.out.println("==========다리길이 : "+bridge_length+", ===========무게 : "+weight);
//		while(finish.size() < truck_weights.length) {
//			System.out.println("지난 시간 : "+time+"초 경과 "+" 대기 : "+waitTruck.toString()+" , 다리 : "+bridge.toString()+", 완료 : "+finish.toString());
//			if(bridge.isEmpty() && !waitTruck.isEmpty()) {
//				bridge.add(waitTruck.get(0));
//				waitTruck.remove(0);
//				time++;
//				
//				for(int i=0;i<waitTruck.size(); i++) {
//					totalweight = 0;
//					afTruck =0;
//					for(int x=0;x<bridge.size(); x++) { totalweight+=bridge.get(x);}
//					if(!waitTruck.isEmpty()) afTruck = waitTruck.get(i);
//					
//					if(totalweight+afTruck > weight) {
//						time+=bridge_length;
//						for(int j=0;j<bridge.size();j++) { finish.add(bridge.get(j)); }
//						bridge.removeAll(bridge);
//						break;
//					}else {
//						if(waitTruck.isEmpty()) {
//							time+=bridge_length;
//							for(int j=0;j<bridge.size();j++) { finish.add(bridge.get(j)); }
//							bridge.removeAll(bridge);
//							break;
//						}else {
//							time++;
//							bridge.add(waitTruck.get(0));
//							waitTruck.remove(0);
//						}
//					}
//				}
//				
//				if(waitTruck.isEmpty()) {
//					if(time < bridge_length) time+=bridge_length;
//					finish.add(bridge.get(0));
//					bridge.remove(0);
//				}
//			}
//		}
//		System.out.println("지난 시간 : "+time+"초 경과 "+" 대기 : "+waitTruck.toString()+" , 다리 : "+bridge.toString()+", 완료 : "+finish.toString());
//		
//		return time;
//		
//	}
	
}
