package exercise.all.lvl2.pairRemove.nohhyungrae;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PairRemove {
	
	
	
	public static void main(String[] args) {
		
		StringBuffer a1 = new StringBuffer();
		
		String s = "baaasewrdbsadfbwerrbaaasasdfasdcvdbnfghkmkjlrtysertadsefasdsabncvbnrnwsaertawewzsdfqewrwafxzcvddqwer";
		
		for(int i=0;i<500000;i++) {
			a1.append(s);
		}
		
		String str = a1.toString();
		System.out.println(str.length());
		
		long a = System.currentTimeMillis();
		System.out.println("solution : "+solution(str));
		long e = System.currentTimeMillis();
		System.out.println("e-a "+(e-a)/1000);
    }

	public static int solution(String s){
        Queue<Character> que = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        long b = System.currentTimeMillis();
        s.chars().forEach(a->que.add((char) a));
        long c = System.currentTimeMillis();
        System.out.println("c-b "+(c-b)/1000);
        while(!que.isEmpty()) {

            if(stack.isEmpty()) {
            	stack.add(que.poll());
            }else if(stack.peek().equals(que.peek())){
            	stack.pop();
            	que.poll();
            }else if(!stack.peek().equals(que.peek())) {
            	stack.add(que.poll());
            }   

            if(s.length() < 2 || stack.size() > (s.length()/2)) break;

        }
        long d = System.currentTimeMillis();
        System.out.println("d-c "+(d-c)/1000);
        return stack.size() == 0 ? 1 : 0;
    }
	


//---------------------------------------------------------------------------
	
//  LIST<Character> c = new LinkedList<>();
//  s.chars().forEach(a->c.add((char) a));
//
//  System.out.println("c : "+c);
//  
//  while(s.length() != answer) {
//  	
//  	if(s.length()%2 != 0) break;
//  	
//  	for(int i=0; i<s.length()-1; i++) {
//			
////			if(i > 4 && s.length()/2 >=4) {
////				answer = s.length();
////				break;
////			}
//			
//  		if(s.charAt(i) == s.charAt(i+1)) {
//				s = s.replace(s.substring(i, i+2), "");
//			}else if(i == s.length()-2) {
//				answer = s.length();
//				break;
//			}
//			
//			
//		}
	
	
//---------------------------------------------------------------------------
	
//  public static String str = "";
//    
//	public static int solution(String s){
//		int answer = -1;
//		str = s;
//		count(str);
//        
//		return str.length() == 0 ? 1 : 0;
//	}
//	
//	public static void count(String s) {
//		
//		
//		for(int i=0;i<str.length()-1; i++) {
//			
//			if(str.length() == 0){
//                return ;
//            }else if(str.charAt(i) == str.charAt(i+1)) {
//				str = str.replace(str.substring(i, i+2), "");
//				count(str);
//			}
//			
//		}
//		
//	}
}
