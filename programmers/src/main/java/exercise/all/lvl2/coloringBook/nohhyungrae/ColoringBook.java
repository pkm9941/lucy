package exercise.all.lvl2.coloringBook.nohhyungrae;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ColoringBook {

	public static void main(String[] args) {
		int m = 6;
		int n = 4;
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		
		System.out.println("solution : "+solution(m, n, picture));
		
	}
	
	static Map<Integer,Integer> numMap = new LinkedHashMap<>();
	static List<Integer> numList = new ArrayList<Integer>();
	
	public static int[] solution(int m, int n, int[][] picture) {
        //그림에 몇 개의 영역
		int numberOfArea = 0;
		//가장 큰 영역은 몇 칸
        int maxSizeOfOneArea = 0;
        int top = -1;
        
        for(int i=0; i<m; i++) {
        	recursion(i, 0, picture, n, 0, top ,0);
        }
        
		for(int key : numMap.keySet()) {
			numList.add(numMap.get(key));
		}

        int[] answer = new int[2];
        answer[0] = numberOfArea = numList.size();
        answer[1] = maxSizeOfOneArea = Collections.max(numList);
        System.out.println("answer : [ "+numberOfArea+" , "+maxSizeOfOneArea+" ]");
        return answer;
    }
	
	public static int recursion(int i, int j, int[][] picture,int len, int before,int top, int result) {

		if(len == j)
			return 0;
		
		if(i - 1 >= 0) 
			top = picture[i-1][j];
		
		
		if(picture[i][j] != 0) {
			if(top != 0) {
				if(picture[i][j] == before) {
					numMap.put(picture[i][j], numMap.get(picture[i][j])+1);	
				}else {
					if(top == picture[i][j]) 
						numMap.put(picture[i][j], numMap.get(picture[i][j])+1);	
					else 
						numMap.put(picture[i][j], 1);
				}
			}else{
				if(numMap.containsKey(picture[i][j])) {
					numList.add(numMap.get(picture[i][j]));
					numMap.remove(picture[i][j]);
					numMap.put(picture[i][j], 1);
				}
			}
		}
		
		return recursion(i,j+1, picture,len , picture[i][j], top, result);
	}

}