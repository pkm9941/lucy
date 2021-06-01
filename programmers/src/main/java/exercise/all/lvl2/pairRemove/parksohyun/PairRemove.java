package exercise.all.lvl2.pairRemove.parksohyun;

import java.util.ArrayList;

public class PairRemove {
	/*짝지어 제거하기*/
	public static void main(String[] args) {
		String S = "baabaa";
		int result = solution(S);
		System.out.println(result);
    }

	private static int solution(String s) {
	   ArrayList<Character> aList = new ArrayList<Character>();
 	   
	   for(int i = 0; i < s.length(); i++){
		   aList.add(s.charAt(i));  
		   if(aList.size()>1 && aList.get(aList.size()-2)==aList.get(aList.size()-1)) {
			   aList.remove(aList.size()-1);
			   aList.remove(aList.size()-1);
		   }
	   }
	   //baab ( X )
	  // int aListSize = aList.size();
	  // for(int i=0;i<aListSize;i++) {		   
		 //  if(aList.get(aList.size()-2)==aList.get(aList.size()-1)) {
			//   aList.remove(aList.size()-1);
			//   aList.remove(aList.size()-1);  
			 //  aListSize= aList.size();
		  // }
	   //}
	   
	   //baabaa ( X )
	  // int i =0;
	  // while(i<aList.size()-1) {
		 // if(aList.get(i)==aList.get(i+1)) {
			//  aList.remove(i);
			 // aList.remove(i);
			 // i=0; //제거 하고 다시 첨부터
		  //}else {
			 // i++;
	      //}
	   //}
	      
	   if(aList.isEmpty()) {
		   return 1;
	   }else {
		   return 0;
	   }
	}
}
