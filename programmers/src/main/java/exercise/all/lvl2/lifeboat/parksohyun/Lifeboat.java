package exercise.all.lvl2.lifeboat.parksohyun;

import java.util.Arrays;

public class Lifeboat {

	public static void main(String[] args) {
		int[] people = {100,100};
		int limit =100;
		System.out.println(solution(people,limit));
	}
 
	public static int solution2(int[] people, int limit) {
		int answer = 0;		
		Arrays.sort(people); //오름차순
		
		//20,30,50,80,90
		int i=0;
		for(int j=people.length-1;j>=i;j--) {
			if(people[j]+people[i]>limit) {
				answer++;
			}else {
				answer++;
				i++;
			}
		}

		return answer;
	}

	public static int solution(int[] people, int limit) {
		int answer = 0;
		
		Arrays.sort(people); //오름차순
		//20,30,50,80,90
		//10,20,30,40,50,60
		//29,49,11,23,55
		int jLen=people.length;
		for(int i=0;i<people.length;i++){
			for(int j=jLen-1;j>=i;j--) {
				if(people[j]+people[i]>limit) {
					jLen=j;
					answer++;
				}else {
					jLen=j;
					answer++;
					break;
				}
			}
		}
		return answer;
	}

}