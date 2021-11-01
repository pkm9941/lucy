package exercise.all.lvl2.tuple.nohhyungrae;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Tuple {
	
	public static void main(String[] args) {
		String s = "{{2,1},{2},{2,1,3},{2,1,3,4}}";
		String s2 = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
		String s3 = "{{2,1},{2},{2,1,3},{2,1,3,4}}";
		
		System.out.println("Solution : "+solution(s2));
		
	}
	
	public static int[] solution(String s) {
		int[] answer = {};
		int index = 0;
		List<String> minusList = new LinkedList<String>();
		String[] arr =  s.substring(2, s.length()-2).split("\\},\\{");
		
		//익명클래스 일반식
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		
		//익명클래스 람다식
		Arrays.sort(arr, (s1,s2) -> {return s1.length() - s2.length();});
		
		answer = new int[arr.length];
		for(String a : arr) {
			String[] intArr = a.split(",");
			for(String b : intArr) {
				if(minusList.contains(b)){
					continue;
				}else {
					answer[index++] = Integer.parseInt(b);
					minusList.add(b);
				}
			}
		}
		
		for(int a : answer) {
			System.out.println(a);
		}
		
		return answer;
	}
	
	public static int[] solution2(String s) {
		int[] answer = {};
		String[] arr = s.substring(2, s.length()-2).replaceAll(",", "").split("\\}"+"\\{");
		
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		
		int index = 0;
		answer = new int[arr.length];
		List<String> minusList = new LinkedList<String>();
		for(String a : arr) {
			if(!minusList.isEmpty()) {
				for(String minus : minusList) {
					a = a.replace(minus, "");
				}
			}
			answer[index] = Integer.parseInt(a);
			minusList.add(a);
			index++;
		}
		
		return answer;
	}


}