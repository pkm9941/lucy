package exercise.all.lvl2.targetNumber.nohhyungrae;

public class TargetNumber {
	
	public static void main(String[] args) {
		int[] numbers = {1,1,1,1,1};
		int target = 3;
		System.out.println("solution : "+solution(numbers, target));
	}
	
	private static int total = 0;
	
	public static int solution(int[] numbers, int target) {
		int answer = 0;
		boolean[] valueCheck = new boolean[numbers.length];
		
		findTarget(valueCheck, numbers,target,0);
		
		return total;
	}
	
	public static void findTarget(boolean[] valueCheck, int[] numbers, int target, int start) {
		
		for(int i=start; i<numbers.length; i++) {
			valueCheck[i] = true;
			findTarget(valueCheck, numbers, target ,i+1);
			valueCheck[i] = false;
		}
		
		int sum = 0;
		
		for(int i=0 ; i<valueCheck.length; i++) {
			if(valueCheck[i]) {
				sum += numbers[i];
			}else {
				sum -= numbers[i];
			}
		}
		
		if(sum == target) {
			total++;
		}
		return ;

		
	}
	
//	static void comb1(int[] arr, boolean[] visited, int start, int n, int r) {
//		if(r == 0) {
//			System.out.println("arr : "+arr+ ", n : "+ n);
//			return;
//		} else {
//			for(int i = start; i < n; i++) {
//				visited[i] = true;
//				comb1(arr, visited, i + 1, n, r - 1);
//				visited[i] = false;
//			}
//		}
//	}
//	
//	static void comb(int[] arr, boolean[] visited, int depth, int n, int r) {
//        if (r == 0) {
//            //print(arr, visited, n);
//            return;
//        }
//        
//        if (depth == n) {
//            return;
//        }
//
//        visited[depth] = true;
//        comb(arr, visited, depth + 1, n, r - 1);
//
//        visited[depth] = false;
//        comb(arr, visited, depth + 1, n, r);
//    }

}
