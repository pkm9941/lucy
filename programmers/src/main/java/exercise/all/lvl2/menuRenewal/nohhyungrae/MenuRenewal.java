package exercise.all.lvl2.menuRenewal.nohhyungrae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MenuRenewal {
	
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		int[] course2 = {2,3,5};
		String[] orders3 = {"XYZ", "XWY", "WXA"};
		int[] course3 = {2,3,4};

		
		System.out.println("solution : "+solution(orders3,course3));
		
		
	}
	
	public static String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		List<String> menu = new ArrayList<String>();
		Arrays.sort(course);
		boolean[] eaten = new boolean[orders.length];
		for(int i=0;i<course.length; i++) {
			menuList = new HashMap<>();
			for(int j=0; j<orders.length; j++) {
				combination(orders[j], eaten, 0, orders[j].length(), course[i]);
			}
			
			if(!menuList.isEmpty()) {
				int maxV = Collections.max(menuList.values());
	         	for (Entry<String, Integer> entry : menuList.entrySet()) { 
	         		if (entry.getValue()==maxV && entry.getValue() > 1) { 
	         			menu.add(entry.getKey());
	         		}
	         	}
			}
		}
		System.out.println( "menuList : "+menuList);
		
		menu.sort(null);
		System.out.println(menu);
		
		return menu.toArray(new String[menu.size()]);
	}
	
	private static Map<String ,Integer> menuList;
	
	public static void combination(String orders, boolean[] eaten,int start, int n, int r) {
		
		if(r == 0) {
			String m = "";
			for(int i=0;i<n; i++) {
				if(eaten[i]) {
					System.out.println(orders.charAt(i));
					m += orders.charAt(i);
				}
			}
			
			System.out.println( "menuList : "+menuList);
			char[] charArr = m.toCharArray(); // String to Char Array
			String result = "";
			Arrays.sort(charArr);
			for(char c : charArr ) {
				System.out.println("c : "+c);
				result += c;
			}
			System.out.println("result : "+result+", charArr : "+charArr);
			if(menuList.containsKey(result)) menuList.put(result, menuList.get(result)+1);
			else menuList.put(result, 1);
			
			return;
		}
		
		for(int i=start; i<n; i++) {
			eaten[i] = true;
			combination(orders, eaten ,i+1 ,n ,r-1);
			eaten[i] = false;
		}
	}
	
	
	 //중복제거
//	 public static Map<String, Integer> setMenuall( Map<String, Integer> menu){
//		 List<String> list = new ArrayList<String>();
//		 for (Entry<String, Integer> entry : menu.entrySet()) { 
//			 list.add(entry.getKey());
//		 }
//	
//		 for(int i = 0; i<list.size(); i++) {
//			 Map<Character, Integer> af = new HashMap<>();
//			 for(int k=0; k<list.get(i).length(); k++) {
//				 af.put(list.get(i).charAt(k), 1);
//			 }
//			 
//			 for(int j =i+1; j<list.size(); j++) {
//				 Map<Character, Integer> bf = new HashMap<>();
//				 for(int k=0; k<list.get(j).length(); k++) {
//					 bf.put(list.get(j).charAt(k), 1);
//				 }
//				 if(bf.equals(af)) {
//					 menu.put(list.get(i),menu.get(list.get(i)) +1);
//				 }
//			 }
//		 }
//		 return menu;
//	 }
	
	/*********************************************************************************/
	
//	static List<Map<String, Integer>> menuList = new ArrayList<Map<String, Integer>>();
//	static Map<String, Integer> menuMap = new HashMap<String, Integer>();
//	
//	public static String getMenu(String order, int course) {
//		
//		for(int i=0; i<order.length(); i++) {
//			menuMap.put(makeMenu(order.charAt(i), i, course, order),1);
//			menuList.add(menuMap);
//		}
//		
//		return menuList.toString();
//	}
//	
//	public static String makeMenu(char m, int start, int len, String menu) {
//		Map<String, Integer> mn = new HashMap<>();
//		String ms = ""+m;
//		if(start == len) {
//			//mn.put(ms, 1);
//			return ms;
//		}else {
//			
//			ms += makeMenu(menu.charAt(start), start+1,len, menu);
//		}
//		
//		return ms;
//	}
	
	/*********************************************************************************/
//	static String[] a = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//    static boolean[] a_b = {false, false, false, false, false};
//
//    public static void print_arr() {
//        for(int i =0; i< a_b.length; i++) {
//            if(a_b[i])
//                System.out.print(""+a[i]+" ");
//        }
//        System.out.println();
//    }
//
//    //count(= 몇 개를 뽑을지). 그리고 index(= 숫자 뽑는데 쓰이는 Iterator 역할).
//    public static  void combination(int index, int count)
//    {
//    	//4번째 재귀에 이 구문이 실행된다. 출력 후 재귀탈출. (뽑는건 세번째에서 끝)
//        if(count==a.length-1) {
//            print_arr();
//            return ;
//        }
//        else {
//            for(int z = index; z < a.length; z++) {
//                if(a_b[z]) // 선택된 숫자는 무시
//                    continue;
//                else { //false일 경우
//                    a_b[z] = true; // 선택되지 않은 숫자일 경우 선택하는 것.
//                    combination(z+1, count+1); // 마지막으로 선택한 숫자 이후부터 다시 선택검사 시키도록.(뒤는 돌아보지 않음)
//                    a_b[z] = false; // 해당 숫자에 대한 모든 조합이 끝나면 false, 그리고 다음 숫자로 이동시키는 것.
//                }
//            }
//        }
//    }
	
//	 public static String[] solution(String[] orders, int[] course) {
//       
//        List<String> rtnValue = new ArrayList<>();
//        Map<String, Integer> menu2 = new HashMap<>();
//    	for(int cs : course) {
//    		menu2 = getMenu(orders, cs);
//        	//System.out.println("menu2 :"+menu2.toString());
//        	if(!menu2.isEmpty()) {
//            	int maxV = Collections.max(menu2.values());
//            	for (Entry<String, Integer> entry : menu2.entrySet()) { 
//            		//System.out.println("entry : "+entry);
//            		if (entry.getValue()==maxV && entry.getValue() > 1) { 
//            			rtnValue.add(entry.getKey());
//            		}
//            	}
//        	}
//
//        }
//
//        rtnValue.sort(null);
//        System.out.println(rtnValue);
//       
//        String[] answer = rtnValue.toArray(new String[rtnValue.size()]);
//        return answer;
//    }
//	 
//	 public static Map<String, Integer> getMenu(String[] orders, int course) {
//		 Map<String, Integer> menu = new HashMap<>();
//		 for(int i =0; i<orders.length; i++) {
//			 String cs = back(orders[i], course);
//			 if(menu.get(cs) == null) {
//     			menu.put(cs, 1);
//     		}else {
//     			menu.put(cs, menu.get(cs) + 1);
//     		}
//			 
//		 }
//		 return null;
//	 }
//	 
//	 public static String back(String o, int count) {
//		 String rtnValue = "";
//		 if(count==rtnValue.length()) {
//			 return rtnValue;
//		 }else {
//	           for(int z = 0; z < o.length(); z++) {
//	        	   CharSequence a = o.charAt(z);
//	               if(rtnValue.contains(a)) // 선택된 숫자는 무시
//	                   continue;
//	               else { //false일 경우
//	                   rtnValue += back(o, z); // 마지막으로 선택한 숫자 이후부터 다시 선택검사 시키도록.(뒤는 돌아보지 않음)
//	               }
//	           }
//		 }
//		 return rtnValue;
//	 }
//	 
//	 public static Map<String, Integer> getMenu(String[] orders, int course) {
//	 Map<String, Integer> menu = new HashMap<>();
//	 for(String order : orders) {
//		 System.out.println("order : "+order);
//		 System.out.println("course : "+course);
//    	for(int i=0;i<order.length()-1; i++) {
//    		String cs = "";
//    		cs += order.charAt(i);
//    		
//    		if(course < 3) {
//    			for(int j=i+1; j<order.length(); j++) {
//        			cs += order.charAt(j);
//	        		if(cs.length() == course) {
//		        		if(menu.get(cs) == null) {
//		        			menu.put(cs, 1);
//		        		}else {
//		        			menu.put(cs, menu.get(cs) + 1);
//		        		}
//		        		cs = "";
//		        		cs += order.charAt(i);
//	        		}
//        		}
//    		}else {
//    			for(int j=i+1; j<order.length(); j++) {
//    				cs += order.charAt(j);
//    				for(int k=j+1; k<order.length(); k++) {
//    					cs += order.charAt(k);
//    					if(cs.length() == course) {
//    		        		if(menu.get(cs) == null) {
//    		        			menu.put(cs, 1);
//    		        		}else {
//    		        			menu.put(cs, menu.get(cs) + 1);
//    		        		}
//    		        		cs = "";
//    		        		cs += order.charAt(i);
//    		        		cs += order.charAt(j);
//    	        		}
//    				}
//    				cs = "";
//    				cs += order.charAt(i);
//    			}
//    		}
//    	}
//     }
//	 System.out.println("menu : "+menu);
//	 
//	 return setMenuall(menu);
// }
//	 
//	 //중복제거
//	 public static Map<String, Integer> setMenuall( Map<String, Integer> menu){
//		 List<String> list = new ArrayList<String>();
//		 for (Entry<String, Integer> entry : menu.entrySet()) { 
//			 list.add(entry.getKey());
//		 }
//	
//		 for(int i = 0; i<list.size(); i++) {
//			 Map<Character, Integer> af = new HashMap<>();
//			 for(int k=0; k<list.get(i).length(); k++) {
//				 af.put(list.get(i).charAt(k), 1);
//			 }
//			 
//			 for(int j =i+1; j<list.size(); j++) {
//				 Map<Character, Integer> bf = new HashMap<>();
//				 for(int k=0; k<list.get(j).length(); k++) {
//					 bf.put(list.get(j).charAt(k), 1);
//				 }
//				 if(bf.equals(af)) {
//					 menu.put(list.get(i),menu.get(list.get(i)) +1);
//				 }
//			 }
//		 }
//		 return menu;
//	 }
	 
	 

	
}
