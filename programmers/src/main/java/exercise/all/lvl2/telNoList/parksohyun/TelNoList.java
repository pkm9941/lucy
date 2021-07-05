package exercise.all.lvl2.telNoList.parksohyun;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TelNoList {
	//전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
	//전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
	//구조대 : 119
	//박준영 : 97 674 223
	//지영석 : 11 9552 4421
	//전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
	//제한 사항
	//phone_book의 길이는 1 이상 1,000,000 이하입니다.
	//각 전화번호의 길이는 1 이상 20 이하입니다.
	//같은 전화번호가 중복해서 들어있지 않습니다.
	
	public static void main(String[] args) {
		String[] phone_book  = {"12","123","1235","567","88"};
		System.out.println(solution(phone_book));
	}

	private static boolean solution(String[] phone_book) {
		 boolean answer = true;
		 
         Arrays.sort(phone_book);         //정렬
      
        for(int i=1; i < phone_book.length; i++) {
    	   String preFix =phone_book[i-1];  //접두사
    	   for(int j=0; j<=phone_book[i].length();j++) {  		
    		   if(preFix.equals(phone_book[i].substring(0, j))) {
       				answer =false;
       				return answer;
       			}
    	   }
       }                 
      	return answer; 
	}
}