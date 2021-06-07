package exercise.all.lvl2.targetNumber.parksohyun;

public class TargetNumber {
	static boolean[] visited;
	static int cnt=0;
	
	public static void main(String[] args) {
		int[] numbers = {1,1,1,1,1};
		int target =3;
		solution(numbers,target);
		
	}
	
	private static int solution(int[] numbers, int target) {
		int answer =0;
		int len = numbers.length;
	    visited = new boolean[len];
	    answer= answerComb(0,0,len,target,numbers);
	    System.out.println("답 "+answer);
		return answer;
	}

	private static int answerComb(int istart, int r, int len, int target, int[] numbers) {
		int sum =0;
		for(int i=istart;i<len;i++) {
			visited[i]=true;
			answerComb(i+1,r,len,target,numbers);
			visited[i]=false;
		}

		for(int i=0;i<len;i++) {
			if(visited[i]){
				sum+=numbers[i];
			}else {
				sum-=numbers[i];
			}
		}
			
		System.out.println(sum);
		
		if(sum==target) {
			cnt++;
		}
		return sum;
		}
}
