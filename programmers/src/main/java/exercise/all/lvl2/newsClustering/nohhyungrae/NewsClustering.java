package exercise.all.lvl2.newsClustering.nohhyungrae;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class NewsClustering {
	
	public static void main(String[] args) {
		
//		String str1 = "FRANCE";
//		String str2 = "french";
		
//		String str1 = "handshake";
//		String str2 = "shake hands";
		
//		String str1 = "aa1+aa2";
//		String str2 = "AAAA12";
		
		String str1 = "a abcccc";
		String str2 = "cccdefff";
//		
//		String str1 = "E=M*C^2";
//		String str2 = "e=m*c^2";
		
		System.out.println("solution : "+solution(str1, str2));
	}
	
	public static int solution(String str1, String str2) {
        int answer = 65536;
        double tot = 0;
        List<String> same = new ArrayList<String>();
        List<String> dif = new ArrayList<String>();
        
        List<String> str1List = new ArrayList<String>();
        List<String> str2List = new ArrayList<String>();
        List<String> str2List2 = new ArrayList<String>();

    	str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        System.out.println(str1);
        System.out.println(str2);
        
        //리스트에 정리
        if(str1.length() == str2.length()) {
			for(int i=0; i<str1.length()-1; i++) {
				if(Pattern.matches("^[a-zA-Z]*$", str1) && Pattern.matches("^[a-zA-Z]*$", str2) ) {
					str1List.add(str1.charAt(i)+""+str1.charAt(i+1));
					str2List.add(str2.charAt(i)+""+str2.charAt(i+1));
					str2List2.add(str2.charAt(i)+""+str2.charAt(i+1));
				}else {
					if(Pattern.matches("^[a-zA-Z]*$", str1.charAt(i) +""+ str1.charAt(i+1))) {
						str1List.add(str1.charAt(i)+""+str1.charAt(i+1));
					}
					
					if(Pattern.matches("^[a-zA-Z]*$", str2.charAt(i) +""+ str2.charAt(i+1))) {
						str2List.add(str2.charAt(i)+""+str2.charAt(i+1));
						str1List.add(str2.charAt(i)+""+str2.charAt(i+1));
					}
				}
			}
		}else {
			for(int i=0; i<str1.length()-1; i++) {
				if(Pattern.matches("^[a-zA-Z]*$", str1.charAt(i) +""+ str1.charAt(i+1))){
					str1List.add(str1.charAt(i)+""+str1.charAt(i+1));
				}
			}
			
			for(int i=0; i<str2.length()-1; i++) {
				if(Pattern.matches("^[a-zA-Z]*$", str2.charAt(i) +""+ str2.charAt(i+1))){
					str2List.add(str2.charAt(i)+""+str2.charAt(i+1));
					str2List2.add(str2.charAt(i)+""+str2.charAt(i+1));
				}
			}
		}
        
        Collections.sort(str1List);
        Collections.sort(str2List);
        Collections.sort(str2List2);

        //예외처리
        if(str1List.equals(str2List)) return 1*answer;


        //합집합 구하기
        for(String hap : str1List) {
            if(str2List.contains(hap)) {
            	str2List.remove(hap);
            }
            same.add(hap);
        }
        same.addAll(str2List);
        
        //교집합 구하기
        for(String cha : str1List) {
            if(str2List2.contains(cha)) {
                dif.add(cha);
                str2List2.remove(cha);
            }
        }
        
        System.out.println("str1List : "+str1List);
        System.out.println("str2List : "+str2List);
    	System.out.println("same : "+same);
    	System.out.println("dif : "+dif);
    	
    	
        if(same.size() == 1) {
        	tot = (double) str1List.size()/ (double)str2List.size();
        }else {
        	tot = (double) dif.size()/ (double)same.size();
        }
    	System.out.println("same : "+same.size()+" , dif : "+dif.size()+" , tot : "+tot);
    	return (int)(answer * tot);
        
    }
	
	public static int solution3(String str1, String str2) {
		int answer = 65536;
		List<String> same = new ArrayList<String>();
		List<String> dif = new ArrayList<String>();
		
		List<String> string1 = new ArrayList<String>();
		List<String> string2 = new ArrayList<String>();
		
		List<String> string11 = new ArrayList<String>();
		List<String> string22 = new ArrayList<String>();
		
		String str11 = "";
		String str22 = "";
		
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		
		
		if(str1.length() == str2.length()) {
			for(int i=0; i<str1.length()-1; i++) {
				if(Pattern.matches("^[a-zA-Z]*$", str1) && Pattern.matches("^[a-zA-Z]*$", str2) ) {
					str11 = str1.charAt(i) +""+ str1.charAt(i+1);
					str22 = str2.charAt(i) +""+ str2.charAt(i+1);
					string1.add(str11);
					string2.add(str22);
					string11.add(str11);
					string22.add(str22);
				}else {
					if(Pattern.matches("^[a-zA-Z]*$", str1.charAt(i) +""+ str1.charAt(i+1))) {
						str11 = str1.charAt(i) +""+ str1.charAt(i+1);
						string1.add(str11);
						string11.add(str11);
					}
					
					if(Pattern.matches("^[a-zA-Z]*$", str2.charAt(i) +""+ str2.charAt(i+1))) {
						str22 = str2.charAt(i) +""+ str2.charAt(i+1);
						string2.add(str22);
						string22.add(str22);
					}
				}
			}
		}else {
			for(int i=0; i<str1.length()-1; i++) {
				if(Pattern.matches("^[a-zA-Z]*$", str1.charAt(i) +""+ str1.charAt(i+1))){
					str11 = str1.charAt(i) +""+ str1.charAt(i+1);
					string1.add(str11);
					string11.add(str11);
				}
			}
			
			for(int i=0; i<str2.length()-1; i++) {
				if(Pattern.matches("^[a-zA-Z]*$", str2.charAt(i) +""+ str2.charAt(i+1))){
					str22 = str2.charAt(i) +""+ str2.charAt(i+1);
					string2.add(str22);
					string22.add(str22);
				}
			}
		}
		
		
		if(str1.equals(str2)) return 1*answer;
		
		
		if(string1.size() > string2.size()) {
			for(String cha : string2) {
				if(string2.contains(cha)) {
					same.add(cha);
					string2.remove(cha);
				}
				dif.add(cha);
				string22.remove(cha);
			}
			dif.addAll(string1);
			same.retainAll(string11);
			
		}else {
			for(String cha : string1) {
				if(string2.contains(cha)) {
					string2.remove(cha);
					same.add(cha);
				}
				dif.add(cha);
				string11.remove(cha);
			}
			dif.addAll(string2);
			same.retainAll(string22);
			
		}
		
		
		
		
		
		
		
//        else {
//        	if(string1.size() >= string2.size()) {
//        		for(int i=0; i<string1.size()-1; i++) {
//        			
//        			//if(string1.matches("[A-Z][A-Z]") && string2.matches("[A-Z][A-Z]")) {
//        				System.out.println("string1 : "+string1.get(i)+" , string2 : "+string2.get(i) );
//        				if(string1.get(i).equals(string2.get(i))) {
//        					same.add(string1.get(i));
//        					dif.add(string1.get(i));
//        				}else {
//        					dif.add(string1.get(i));
//        					dif.add(string2.get(i));
//        				}
//        			//}
//        		}
//        	}else {
//        		for(int i=0; i<string2.size()-1; i++) {
//        			
//        			//if(string1.matches("[A-Z][A-Z]") && string2.matches("[A-Z][A-Z]")) {
//        				System.out.println("string1 : "+string1.get(i)+" , string2 : "+string2.get(i) );
//        				if(string1.get(i).equals(string2.get(i))) {
//        					same.add(string1.get(i));
//        					dif.add(string1.get(i));
//        				}else {
//        					dif.add(string1.get(i));
//        					dif.add(string2.get(i));
//        				}
//        			//}
//        		}
//        	}
//        	
//        	
//        	System.out.println(same);
//        	System.out.println(dif);
//             
//        	double tot = (double) same.size()/ (double)dif.size();
//        	System.out.println("same : "+same.size()+" , dif : "+dif.size()+" , tot : "+tot);
//        	return (int)(answer * tot);
//        }
		
		System.out.println("string1 : "+string1);
		System.out.println("string11 : "+string11);
		System.out.println("string2 : "+string2);
		System.out.println("string22 : "+string22);
		System.out.println("same : "+same);
		System.out.println("dif : "+dif);
		double tot;
		if(same.size() == 1) {
			tot = (double) string1.size()/ (double)string2.size();
		}else {
			tot = (double) same.size()/ (double)dif.size();
		}
//    	double tot = (double) same.size()/ (double)dif.size();
		System.out.println("same : "+same.size()+" , dif : "+dif.size()+" , tot : "+tot);
		System.out.println(3/2*tot);
		return (int)(answer * tot);
		
	}
	
	public static int solution2(String str1, String str2) {
		int answer = 65536;
		List<String> same = new ArrayList<String>();
		List<String> dif = new ArrayList<String>();
		
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		
		if(str1.equals(str2)) return 1*answer;
		
		for(int i=0; i<str1.length()-1; i++) {
			String string1 = str1.charAt(i) +""+ str1.charAt(i+1);
			String string2 = str2.charAt(i) +""+ str2.charAt(i+1);
			
			if(string1.matches("[A-Z][A-Z]") && string2.matches("[A-Z][A-Z]")) {
				System.out.println("string1 : "+string1+" , string2 : "+string2 );
				if(string1.equals(string2)) {
					same.add(string1);
					dif.add(string1);
				}else {
					dif.add(string1);
					dif.add(string2);
				}
			}
		}
		Collections.sort(same);
		Collections.sort(dif);
		
		if(same.equals(dif)) {
			return 1*answer;
		}else {
			System.out.println(dif);
			
			double tot = (double) same.size()/ (double)dif.size();
			System.out.println("same : "+same.size()+" , dif : "+dif.size()+" , tot : "+tot);
			return (int)(answer * tot);
		}
		
	}

}
