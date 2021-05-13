package exercise.all.lvl2.openChat.nohhyungrae;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChat {
	
	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] record2 = {"Enter uid0606 Gimoi", "Enter uid4567 Prodo", "Leave uid0606", "Enter uid1234 Prodo", "Change uid1234 OhYeah"};
		
		System.out.println("solution : "+solution(record2));
    }
	
	public static String[] solution(String[] record) {
        String[] answer = {};
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        Map<String,String> member = new HashMap<>();
        String[] row = new String[3];
        String total = "";
        List<String> res = new ArrayList<String>();
        for(String rows : record) {
        	Map<String,String> answers = new HashMap<String,String>();
        	row = rows.split(" ");
    		
    		switch (row[0]) {
			case "Enter":
				if(member.containsKey(row[1])) {
    				member.replace(row[1], row[2]);
    			}else {
    				member.put(row[1], row[2]);
    			}
    			answers.put(row[1], "님이 들어왔습니다.");
				result.add(answers);
				break;
			case "Leave":
				if(member.containsKey(row[1])) {
    				answers.put(row[1], "님이 나갔습니다.");
					//member.remove(row[1]);
                    result.add(answers);
				}
				break;
			case "Change":
				if(member.containsKey(row[1])) {
					member.replace(row[1], row[2]);
				}
				break;

			default:
				break;
			}
    		
    		
//    		if(row[0].equals("Enter")) {
//    			if(member.containsKey(row[1])) {
//    				member.replace(row[1], row[2]);
//    			}else {
//    				member.put(row[1], row[2]);
//    			}
//    			answers.put(row[1], "님이 들어왔습니다.");
//				total += row[1]+"님이 들어왔습니다./";
//				result.add(answers);
//    		}else if(row[0].equals("Leave")) {
//    			if(member.containsKey(row[1])) {
//    				answers.put(row[1], "님이 나갔습니다.");
//					member.remove(row[1]);
//                    result.add(answers);
//                    total += row[1]+"님이 나갔습니다./";
//				}
//    		}else if(row[0].equals("Change")) {
//    			if(member.containsKey(row[1])) {
//					member.replace(row[1], row[2]);
//				}
//    		}
    		
    		System.out.println("member : "+member);
        	System.out.println(result);
        	
        }
        
        int size = 0;
        
        //제일 느린 소스
//    	for(String value : res) {
//    		for(Map.Entry<String, String> entry : member.entrySet()){
//        		if(value.contains(entry.getKey())) {
//        			res.set(size++, value.replaceAll(entry.getKey(), entry.getValue()));
//        			break;
//        		}
//            }
//        }
//    	
        
        //Map 이용해서 문장씩 이름 치환하는 방법
        answer = new String[result.size()];
        System.out.println(result.size());
        for(int i=0;i<result.size(); i++) {
        	for(Map.Entry<String, String> entry : result.get(i).entrySet()) {
        		if(member.containsKey(entry.getKey())) {
        			answer[size++] = member.get(entry.getKey())+entry.getValue();
        			System.out.println(member.get(entry.getKey())+entry.getValue());
        		}
        	}
        }
        
        //한 문자열로 해서 치완하는 방법
//        for(Map.Entry<String, String> entry : member.entrySet()){
//        	total = total.replaceAll(entry.getKey(), entry.getValue()).trim();
//        }
//        
//        System.out.println(total);
        
//        for(Map.Entry<String, String> entry : answers.entrySet()){
//        	System.out.println(member.get(entry.getKey()));
//        	
//        	answer[size++] = member.get(entry.getKey())+entry.getValue();
//        	System.out.println(member.get(entry.getKey())+entry.getValue());
//        	System.out.println(answer);
//        }
        for(String a : answer) {
        	System.out.println("answer : "+a);
        }
        return answer;
//        return res.toArray(new String[res.size()]);
    }

}
