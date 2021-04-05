package exercise.all.lvl2.menuRenewal.nohhyungrae;

import java.util.ArrayList;
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
		
		System.out.println("solution : "+solution(orders2,course2));
	}
	
	 public static String[] solution(String[] orders, int[] course) {
       
        List<String> rtnValue = new ArrayList<>();
        Map<String, Integer> menu2 = new HashMap<>();
        for(int cs : course) {
        	menu2 = getMenu(orders, cs);
        	//System.out.println("menu2 :"+menu2.toString());
        	if(!menu2.isEmpty()) {
            	int maxV = Collections.max(menu2.values());
            	for (Entry<String, Integer> entry : menu2.entrySet()) { 
            		//System.out.println("entry : "+entry);
            		if (entry.getValue()==maxV && entry.getValue() > 1) { 
            			rtnValue.add(entry.getKey());
            		}
            	}
        	}

        }

        rtnValue.sort(null);
        System.out.println(rtnValue);
       
        String[] answer = rtnValue.toArray(new String[rtnValue.size()]);
        return answer;
    }
	 
	 public static Map<String, Integer> getMenu(String[] orders, int course) {
		 Map<String, Integer> menu = new HashMap<>();
		 for(String order : orders) {
			 System.out.println("order : "+order);
			 System.out.println("course : "+course);
        	for(int i=0;i<order.length()-1; i++) {
        		String cs = "";
        		cs += order.charAt(i);
        		
        		if(course < 3) {
        			for(int j=i+1; j<order.length(); j++) {
            			cs += order.charAt(j);
    	        		if(cs.length() == course) {
    		        		if(menu.get(cs) == null) {
    		        			menu.put(cs, 1);
    		        		}else {
    		        			menu.put(cs, menu.get(cs) + 1);
    		        		}
    		        		cs = "";
    		        		cs += order.charAt(i);
    	        		}
            		}
        		}else {
        			for(int j=i+1; j<order.length(); j++) {
        				cs += order.charAt(j);
        				for(int k=j+1; k<order.length(); k++) {
        					cs += order.charAt(k);
        					if(cs.length() == course) {
        		        		if(menu.get(cs) == null) {
        		        			menu.put(cs, 1);
        		        		}else {
        		        			menu.put(cs, menu.get(cs) + 1);
        		        		}
        		        		cs = "";
        		        		cs += order.charAt(i);
        		        		cs += order.charAt(j);
        	        		}
        				}
        				cs = "";
        				cs += order.charAt(i);
        			}
        		}
        	}
	     }
		 System.out.println("menu : "+menu);
		 
		 return setMenuall(menu);
	 }
	 
	 //중복제거
	 public static Map<String, Integer> setMenuall( Map<String, Integer> menu){
		 List<String> list = new ArrayList<String>();
		 for (Entry<String, Integer> entry : menu.entrySet()) { 
			 list.add(entry.getKey());
		 }
	
		 for(int i = 0; i<list.size(); i++) {
			 Map<Character, Integer> af = new HashMap<>();
			 for(int k=0; k<list.get(i).length(); k++) {
				 af.put(list.get(i).charAt(k), 1);
			 }
			 
			 for(int j =i+1; j<list.size(); j++) {
				 Map<Character, Integer> bf = new HashMap<>();
				 for(int k=0; k<list.get(j).length(); k++) {
					 bf.put(list.get(j).charAt(k), 1);
				 }
				 if(bf.equals(af)) {
					 menu.put(list.get(i),menu.get(list.get(i)) +1);
				 }
			 }
		 }
		 return menu;
	 }
	
}
