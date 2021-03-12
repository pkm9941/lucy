package exercise.all.lvl1.newIdRecommend.parkkwangmin;

public class NewIdRecommend {
	public static void main(String[] args) {
		String new_id = "...!@BaT#*..y.abcde_fghijk-lm";
		//String new_id = "b";
		String applyedId = applyIdRule(new_id);
		System.out.println("applyedId : " + applyedId);
	}

	private static String applyIdRule(String inputedId) {
		//1단계 모든 대문자를 대응되는 소문자로 치환
		String applyedId = inputedId.toLowerCase();
		//2단계 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		applyedId = applyedId.replaceAll("[^a-z0-9-_.]", "");
		//3단계 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		applyedId = applyedId.replaceAll("(\\.)+", ".");
		//4단계 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		applyedId = applyedId.replaceAll("^\\.", "");
		applyedId = applyedId.replaceAll("\\.$", "");
		//5단계 빈 문자열이라면, new_id에 "a"를 대입합니다.
		if (applyedId.isEmpty()) applyedId = "a";
		//6단계 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
		//만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		if (applyedId.length() > 15) applyedId = applyedId.substring(0, 15);
		applyedId = applyedId.replaceAll("\\.$", "");
		//7단계 길이가 2자 이하라면, 마지막 문자를 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		if (applyedId.length() < 3) {
			String lastChar = applyedId.substring(applyedId.length() - 1);
			applyedId = rpad(applyedId, lastChar, 3);
		}
		
		return applyedId;
	}

	private static String rpad(String str, String charToAdd, int finalLength) {
		String inputedStr = str;
		for (int i = str.length(); i < finalLength; i++) {
			inputedStr += charToAdd;
		}
		return inputedStr;
	}
}
