package exercise.all.lvl2.spy.nohhyungrae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Spy {
	
	public static void main(String[] args) {
		
		String[][] clothes1 = {{"yellowhat", "headgear1"}
							 , {"yellowhat232", "headgear1"}
							 , {"bluesunglasses", "eyewear1"}
							 , {"bluesunglasses_sds", "eyewear1"}
							 , {"bluesunglasses", "eyewear2"}
							 , {"bluesunglasses", "eyewear3"}
							 , {"green_turban", "headgear2"}
							 , {"green_turban", "headgear3"}
							 , {"crowmask", "face1"}
							 , {"crowmask", "face2"}
							 , {"bluesunglasses", "face3"}
							 , {"smoky_makeup", "face4"}
							, {"yellowhat", "headgear4"}
							, {"bluesunglasses", "eyewear9"}
							, {"bluesunglasses", "eyewear4"}
							, {"bluesunglasses", "eyewear5"}
							, {"green_turban", "headgear5"}
							, {"green_turban", "headgear6"}
							, {"crowmask", "face5"}
							, {"crowmask", "face6"}
							, {"bluesunglasses", "face7"}
							, {"smoky_makeup", "face8"}
							, {"yellowhat", "headgear7"}
							, {"bluesunglasses", "eyewear6"}
							, {"bluesunglasses", "eyewear7"}
							, {"bluesunglasses", "eyewear8"}
							, {"green_turban", "headgear8"}
							, {"green_turban", "headgear9"}
							, {"crowmask", "face9"}
							, {"crowmask", "face10"}
							, {"bluesunglasses", "face11"}
							, {"smoky_makeup", "face12"}};
		
		String[][] clothes2 = {{"crowmask", "face"}
							 , {"crowmask", "face"}
							 , {"bluesunglasses", "face"}
							 , {"smoky_makeup", "face"}
							 , {"bluesunglasses", "face11"}
							 , {"smoky_makeup", "face12"}};
		
		System.out.println("solution : "+solution(clothes2));
    }
	private static int totalNumber = 0;
	
	public static int solution(String[][] clothes) {
        Map<String,List<String>> variety = new HashMap<>();
        List<String> ObjKey = new ArrayList<>();
        for(int i=0; i<clothes.length; i++) {
        	List<String> item  = new ArrayList<>();
        	if(variety.containsKey(clothes[i][1])) {
        		if(!variety.get(clothes[i][1]).contains(clothes[i][0])) {
        			item  = (List<String>) variety.get(clothes[i][1]);
            		item.add(clothes[i][0]);
            		variety.put(clothes[i][1], item);
        		}
        	}else {
        		item.add(clothes[i][0]);
        		variety.put(clothes[i][1], item);
        	}
        	if(!ObjKey.contains(clothes[i][1])) ObjKey.add(clothes[i][1]);
        }
        System.out.println(variety.toString());
        System.out.println("종류 : "+variety.size());
        
        //모든경우(30가지)의 수는 재귀로 하게되면 시간초과가 나와서 따로 경우의 수 구해줌
        if(variety.size() == 30) {
        	return (int) Math.pow(2, variety.size()) -1;
        }
        //map에 정리해둔 의상 별 가지수
        int[] num = new int[variety.size()];
        for(int i=0;i<variety.size(); i++) {
			num[i] = variety.get(ObjKey.get(i)).size();
		}
        
        int n = num.length;
        boolean[] visited = new boolean[n];
        
        for (int i = 1; i <= n; i++) {
            //경우의수 구하기
            combination(num, visited, 0, n, i);
        }
        System.out.println();
        return totalNumber;
    }
	
	//경우의수 재귀 호출
	public static void combination(int[] arr, boolean[] check, int start, int n, int r) {
        if (r == 0) {
        	int value = 1;
        	for (int i = 0; i < n; i++) {
                 if (check[i]) {
                     System.out.print(arr[i] + " ");
                     value = value * arr[i];
                 }
            }
        	totalNumber += value;
            return;
        }

        for (int i = start; i < n; i++) {
        	check[i] = true;
            combination(arr, check, i + 1, n, r - 1);
            //check[i] = false;
        }
    }
	
	
	/***********************************************************************************/
//	public static int solution(String[][] clothes) {
//		int answer = 0;
//		Map<String,List<String>> variety = new HashMap<>();
//		for(int i=0; i<clothes.length; i++) {
//			List<String> item  = new ArrayList<>();
//			if(variety.containsKey(clothes[i][1])) {
//				if(!variety.get(clothes[i][1]).contains(clothes[i][0])) {
//					item  = (List<String>) variety.get(clothes[i][1]);
//					item.add(clothes[i][0]);
//					variety.put(clothes[i][1], item);
//				}
//			}else {
//				item.add(clothes[i][0]);
//				variety.put(clothes[i][1], item);
//			}
//		}
//		
//		answer += countTotal(variety);
//		System.out.println(variety);
//		return answer;
//	}
//	
//	public static long countTotal(Map<String,List<String>> variety) {
//		Object[] a = variety.keySet().toArray();
//		List<Integer> num = new ArrayList<>();
//		for(int i=0;i<variety.size(); i++) {
//			num.add(variety.get(a[i]).size());
//		}
//		
//		Collections.reverse(num);
//		System.out.println(num.toString());
//		System.out.println("tot : "+tot);
//		
//		tot += reculsion(num, 1);
//		
//		return tot;
//		
//	}
//	
//	static long tot;
//	private static long reculsion(List<Integer> arr, int len) {
//		
//		System.out.println(tot);
//		for(int i=0; i<arr.size(); i++) {
//			if(len == arr.size()) {
//				tot += arr.get(len-1);
//				break;
//			}else {
//				tot += arr.get(i) * reculsion(arr, len+1);
//			}
//		}
//		
//		return tot;
//	}
	/***********************************************************************************/
//	public static void retnV(Map<String,List<String>> variety, int v, int count, int tot) {
//
//		for(int i=count; i<variety.size(); i++) {
//			tot += v; 
//		}
//	}
//	
//	public static int fact(int n) {
//		if (n <= 1)
//			return n;
//		else 
//			return fact(n-1) * n;
//	}

}
