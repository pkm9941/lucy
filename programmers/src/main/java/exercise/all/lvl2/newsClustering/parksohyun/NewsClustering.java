package exercise.all.lvl2.newsClustering.parksohyun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NewsClustering {
	/*
	 * 입력으로는 str1과 str2의 두 문자열이 들어온다. 각 문자열의 길이는 2 이상, 1,000 이하이다.
		입력으로 들어온 문자열은 두 글자씩 끊어서 다중집합의 원소로 만든다. 이때 영문자로 된 글자 쌍만 유효하고
		기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자 쌍을 버린다. 
		예를 들어 "ab+"가 입력으로 들어오면, "ab"만 다중집합의 원소로 삼고, "b+"는 버린다.
		다중집합 원소 사이를 비교할 때, 대문자와 소문자의 차이는 무시한다. "AB"와 "Ab", "ab"는 같은 원소로 취급한다.
	 */
	public static void main(String[] args) {
		String str1 = "handshake";
		String str2 = "shake hands";		
		solution(str1,str2);		
	}

	private static int solution(String str1, String str2) {
		int answer = 0;
		ArrayList<String> aList = new ArrayList<String>();
		ArrayList<String> bList = new ArrayList<String>();
     	ArrayList<String> abIntersectList = new ArrayList<String>(); //교집합
		ArrayList<String> abList = new ArrayList<String>(); 				//합집합

		for (int i = 0; i < str1.length() - 1; i++) {  	
			//알파벳인지 체크
			if(str1.substring(i, i + 1).matches(".*[a-z|A-Z]+.*") && str1.substring(i+1, i + 2).matches(".*[a-z|A-Z]+.*")) {
				aList.add(str1.substring(i, i + 2).toLowerCase());
			}
		}
		
		for(int i=0; i < str2.length()-1; i++) {
			//알파벳인지 체크
			if(str2.substring(i, i + 1).matches(".*[a-z|A-Z]+.*") && str2.substring(i+1, i + 2).matches(".*[a-z|A-Z]+.*")) {
				bList.add(str2.substring(i, i + 2).toLowerCase());
			}
		}

		//교집합 만들기
		for(int i=0;i<bList.size();i++) {
			if(aList.contains(bList.get(i)) && !(abIntersectList.contains(bList.get(i)))) {
				//중복안되게 넣기
				abIntersectList.add(bList.get(i));
			}
		}
		
	
		
		//합집합 만들기
		abList.addAll(aList);
		abList.addAll(bList);	
		abList.removeAll(abIntersectList);
		//abList.addAll(abIntersectList);
		
		Map<String, Integer> map = new HashMap<>();
		Map<String, Integer> map2 = new HashMap<>();
		Map<String, Integer> map3 = new HashMap<>();
		
		for(int i=0;i<aList.size();i++) {
			if(abIntersectList.contains(aList.get(i))) {
				map.put(aList.get(i),Collections.frequency(aList, aList.get(i)));
			}
		}
		
		for(int i=0;i<bList.size();i++) {
			if(abIntersectList.contains(bList.get(i))) {
				map2.put(bList.get(i),Collections.frequency(bList, bList.get(i)));
			}
		}
		
		Set<String> aKeys = map.keySet();
		Set<String> bKeys = map2.keySet();
		Set<String> cKeys = map3.keySet();
		
		if(bKeys.equals(aKeys)) {
			for(String key: bKeys) {
			   if(map.get(key)>map2.get(key)) {		   
				   map3.put(key, map.get(key));
				}else {
					 map3.put(key, map2.get(key));
				}
			}			
		}
		
	double unionNum = abList.size();
		
 
    for(String key: cKeys) {
    	  unionNum += map3.get(key);
      }
    
		double intesectNum= abIntersectList.size();

		double value = intesectNum / unionNum;
		
		if(bList.size()==0 && aList.size()==0) {
			value=1;
		}	

		answer =(int)(value * 65536);
		System.out.println(answer);
		return answer;	
		
	}

	/*
	 * private static boolean valiChk(String str){ System.out.println(str); return
	 * false; }
	 */
}
