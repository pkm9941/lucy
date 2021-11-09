package exercise.all.lvl2.carpet.nohhyungrae;

public class Carpet {
	
	public static void main(String[] args) {
		int brown = 10;
		int yellow = 2;
		
		int brown2 = 8;
		int yellow2 = 1;
		
		int brown3 = 24;
		int yellow3 = 24;
		
		System.out.println("solution : "+solution2(brown, yellow));
	}
	
	public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        
        //세로
        for(int width=3;width<=sum/2; width++) {
        	int len = sum/width; //가로
        	if(len >= width && len*width == sum) {
        		//System.out.println((len*2)+((width-2)*2));
        		if((len*2)+((width-2)*2)== brown) {
        			answer[0] = len;
        			answer[1] = width;
        			break;
        		}
        	}
        }
        System.out.println("==============");
        for(int a : answer) {
        	System.out.println(a);
        }
        return answer;
        
    }
	
	public static int solution2(int brown, int yellow) {
		for(int i=1; i<=200; i++) {
			System.out.println("value : "+(i % 100));
		}
		
		return 1;
	}

}