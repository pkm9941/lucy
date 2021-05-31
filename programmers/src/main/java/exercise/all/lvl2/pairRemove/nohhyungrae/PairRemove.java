package exercise.all.lvl2.pairRemove.nohhyungrae;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PairRemove {
	
	public static void main(String[] args) {
		String s = "baaasewrdbsadfbwerrbaaasd";
		System.out.println("solution : "+solution(s));
    }

	public static int solution(String s){
        Queue<Character> c = new LinkedList<>();
        Stack<Character> que = new Stack<>();
        s.chars().forEach(a->c.add((char) a));

        while(!c.isEmpty()) {

            for(int i=0; i<c.size(); i++) {

                if(que.isEmpty()) {
                    que.add(c.poll());
                }else if(que.peek().equals(c.peek())){
                    que.pop();
                    c.poll();
                }else if(!que.peek().equals(c.peek())) {
                    que.add(c.poll());
                }   

            }

            if(s.length() < 2 || que.size() > (s.length()/2)) break;

        }

        return que.size() == 0 ? 1 : 0;
    }

//---------------------------------------------------------------------------
	
//  Queue<Character> c = new LinkedList<>();
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
////  		if(s.charAt(i) == s.charAt(i+1)) {
////				s = s.replace(s.substring(i, i+2), "");
////			}else if(i == s.length()-2) {
////				answer = s.length();
////				break;
////			}
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
