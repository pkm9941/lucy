package exercise.all.lvl2.tuple.parksohyun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tuple {
	
	public static void main(String[] args) {
		String str="{{20,111},{111}}";
		int[] answer = {};
		System.out.println("답:"+Solution(str));
	}
	public static int[] Solution(String str) {
		int[] answer = {};
		String str2=str.substring(1, str.length()-2);
		String str3=	str2.replace("{", "").replace("}", "");
		System.out.println(str3);
		//int[] arr;	
		String[] arr = str3.split("},");				
		String[] arr2=null;
	
		for(String a : arr) {
			arr2 = a.split(",");
		}
		
		  Map<String,Integer> map = new HashMap<String,Integer>();
		  
		  for(int i=0; i<arr2.length; i++) {
			  if(map.containsKey(arr2[i])) {
				  int count = (int)map.get(arr2[i]);
				  map.put(arr2[i], count+1);
			  }else {
				  map.put(arr2[i],1);
			  }			  
        }

		  List<String> arrList = new ArrayList<>();
		  arrList = sortByValue(map); //내림차순 
		  System.out.println(arrList);
		 
        	answer = new int[arrList.size()];

		  for(int i=0;i<arrList.size();i++) {
			  answer[i]=Integer.parseInt(arrList.get(i));
		  }
		  
		  return answer;
	 }
	
	public static List sortByValue(final Map map) {
		List<String> list = new ArrayList<>();
		list.addAll(map.keySet());
		
		Collections.sort(list,new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
					Object v1 = map.get(o1);
					Object v2 = map.get(o2);
					
				return ((Comparable) v2).compareTo(v1);
			}
		});
			
		//Collections.reverse(list);
		return list;
	}
}