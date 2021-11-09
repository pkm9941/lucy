package exercise.all.lvl2.coloringBook.parksohyun;

public class ColoringBook {

	public static void main(String[] args) {
		int m = 6;
		int n = 4;
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		solution(m,n,picture);
	}
	
	public static int[]  (int m, int n, int[][] picture) {
	    {
	        int numberOfArea = 0;
	        int maxSizeOfOneArea = 0;
     	    int[] answer = new int[2];  
     	    int[][] pictureCopyArr = new int[a.length][a[0].length]; 
     	    
     	    for(int i=0; i<picture.length; i++) {
                for(int j=0; j<picture[i].length; j++) {
                	 pictureCopyArr[i][j] = picture[i][j];  
                }
            }     	    
//     	    
//     	    if(picture[i][j]==picture[i][j+1] && picture[i][j]==picture[i+1][j]) {
//     	    	
//     	    }
     	 
     	    answer[0] = numberOfArea;
	        answer[1] = maxSizeOfOneArea;
	      	                
	        
	        return answer;
	    }
	}
}