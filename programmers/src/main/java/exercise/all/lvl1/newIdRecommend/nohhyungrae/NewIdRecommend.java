package exercise.all.lvl1.newIdRecommend.nohhyungrae;


public class NewIdRecommend {
	public static void main(String[] args) {
		String id1 = "...!@BaT#*..y.abcd-e__fghijklm.";
		String id2 = "z-+.^.";
		String id3 = "=.=";
		String id4 = "123_.def";
		String id5 = "abcdefghijklmn.p";
		
		System.out.println("result : "+solution(id3));

	}
	
	public static String solution(String new_id) {
		
		System.out.println("inputName : "+ new_id);
		//1단계 대소문자 -> 소문자 치환
		String new_id1 = new_id.toLowerCase();
		System.out.println("new_id1 : "+ new_id1);
		//2단계 특정 문자 빼고 제거
		String new_id2 = new_id1.replaceAll("[^a-z0-9-_.]", "");
		System.out.println("new_id2 : "+ new_id2);
		//3단계 (.) 여러개를 한개로 치환
		String new_id3 = new_id2.replaceAll("[.]++", ".");
		System.out.println("new_id3 : "+ new_id3);
		//4단계
		String new_id4 = new_id3.replaceAll("^[.]|[.]$", "");
		System.out.println("new_id4 : "+ new_id4);
		//5단계
		String new_id5 = new_id4;
		if(new_id5.isEmpty()) 
			new_id5 = "a";
		System.out.println("new_id5 : "+ new_id5);
		//6단계
		String new_id6 = new_id5;
		if(new_id6.length() > 15) {
			new_id6 = new_id6.substring(0, 15);
			new_id6 = new_id6.replaceAll("[.]$", "");
		}
		System.out.println("new_id6 : "+ new_id6);
		//7단계
		String new_id7 = new_id6;
		if(new_id7.length() < 3) {
			char a = new_id7.charAt(new_id7.length()-1);
			for(int i=new_id7.length(); i<3; i++) {
				new_id7 += a;
			}
		}
		
		return new_id7;
	}

}
