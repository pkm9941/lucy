package exercise.all.lvl2.telNoList.nohhyungrae;

import java.util.Arrays;
import java.util.Collections;

public class TelNoList {

	public static void main(String[] args) {
		String[] phone_book1 = {"119", "97674223", "195524421","123","456","789","12","123","1235","567","88"};
		String[] phone_book2 = {"123","456","789"};
		String[] phone_book3 = {"12","123","1235","567","88"};
		
		System.out.println("solution : "+solution(phone_book1));
	}
	
	public static boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for(String a : phone_book) {
        	System.out.println(a);
        }
        System.out.println("==============================================================");
        String first = phone_book[0];
        String subStr = "";
        
    	System.out.println("first : "+first);
        for(int i=1; i< phone_book.length; i++) {
        	if(first.length() <= phone_book[i].length()) {
        		subStr = phone_book[i].substring(0, first.length());
        	}else {
        		subStr = first.substring(0, phone_book[i].length());
        	}
        	
        	if(first.equals(subStr)) {
    			return false;
    		}else {
    			first = phone_book[i];
    		}

        }
        
        return answer;
    }
	
	public static String smallOne(String[] phone_book) {
		String first = phone_book[0];
		for(int i=1; i<phone_book.length; i++) {
			if ((phone_book[i].compareTo(first)) < 0) {
                first = phone_book[i];
            }
		}
		return first;
	}
	
	public boolean solution2(String[] phone_book) {
        boolean answer = true;
        String longer = "";
        for(int i=0; i< phone_book.length; i++) {
        	String strI = phone_book[i];
        	for(int j=i+1; j<phone_book.length; j++) {
        		String strJ = phone_book[j];
                if(strJ.equals(strI)) {
    				return false;
    			}
                
        		if(strI.length() < strJ.length()) {
        			longer = strJ.substring(0, strI.length());
        			if(longer.equals(strI)) {
        				return false;
        			}
        		}else if(strI.length() > strJ.length()){
        			longer = strI.substring(0, strJ.length());
    				if(longer.equals(strJ)) {
    					return false;
    				}
        		}
        	}
        }
        return answer;
    }
	
	public static boolean solution3(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        String first = phone_book[0];
        String next = "";
        String subStr = "";
        for(int i=1; i< phone_book.length; i++) {
        	next = phone_book[i];
        	if(first.length() < next.length()) {
        		subStr = next.substring(0, first.length());
    			if(first.equals(subStr)) 
    				return false;
    		}else if(first.length() > next.length()){
    			subStr = first.substring(0, next.length());
				if(next.equals(first)) 
					return false;
				first = phone_book[i];
    		}else {
    			if(first.equals(next)) {
					return false;
				}
    		}
        }
        
        return answer;
    }
	
	public static boolean solution4(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for(String a : phone_book) {
        	System.out.println(a);
        }
        String first = phone_book[0];
        String next = "";
        String subStr = "";
        int last = 0;
        
        while(last != phone_book.length) {
        	System.out.println("first : "+first);
	        for(int i=1; i< phone_book.length; i++) {
	        	next = phone_book[i];
	        	last = i+1;
	        	if(first.length() <= next.length()) {
	        		subStr = next.substring(0, first.length());
	    			System.out.println("subStr : "+subStr);
	    		}else {
	    			subStr = first.substring(0, next.length());
	    			System.out.println("subStr : "+subStr);
	    			if(next.equals(subStr)) {
						return false;
					}
					first = next;
					break;
	    		}
	        	
	        	if(first.equals(subStr)) {
					return false;
				}
	
	        }
        }
        
        return answer;
    }

}