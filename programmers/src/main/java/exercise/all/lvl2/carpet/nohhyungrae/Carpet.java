package exercise.all.lvl2.carpet.nohhyungrae;

public class Carpet {
	
	public static void main(String[] args) {
		int brown = 10;
		int yellow = 2;
		
		int brown2 = 8;
		int yellow2 = 1;
		
		int brown3 = 24;
		int yellow3 = 24;
		
		System.out.println("solution : "+solution(brown3, yellow3));
	}
	
	public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int tot = brown + yellow;
        
        for(int i=1;i<=tot/2; i++) {
        	int f = tot/i;
        	if(i >=3 && f >= i && f*i == tot) {
        		System.out.println((f*2)+((i-2)*2));
        		if((f*2)+((i-2)*2)== brown) {
        			answer[0] = f;
        			answer[1] = i;
        		}
        	}
        }
        
        
        return answer;
    }

}