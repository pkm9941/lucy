package exercise.all.lvl2.carpet.parksohyun;


public class Carpet {
	
	public static void main(String[] args) {
		int brown =8;
		int yellow =1;
		System.out.println(solution(brown, yellow));
	}

	private static int[] solution(int brown, int yellow) {
		int width = brown+yellow; 
	    int Y = 1;
	    int X = (brown + yellow) / Y;
	    int[] answer = new int[2];
	  
	    for(int i=1;i<brown;i++) {
	      Y=i;
	      X = (brown + yellow) / Y;
	      if((X-2) * (Y-2) == yellow) {
	    	  System.out.println(X);
	    	  answer[0]=(X<= Y)? Y:X;
	    	  answer[1]=(X<= Y)? X:Y;
	    	  return answer;
	      }
	    }
         
	      
//			int X=0; // 전체 가로
//			int Y=1;  // 전체 세로
//			int XY = width;
			
			//int X = (brown+yellow)/Y;
//			int XplusY =0;
//	      yellow = (x-2)(y-2); 
//	    	XplusY=(width/2)+ 2 -(yellow/2);

//	    	for(int i=1;i<brown;i++) {
//	    		for(int j=1;j<brown;j++) {
//	     		if(i+j==XplusY && i*j==width) {
//	     			Y=j;
//	     			X=i;
//	     		}
//	    	 }
//	     	} 
    	return answer;
		
	}

}