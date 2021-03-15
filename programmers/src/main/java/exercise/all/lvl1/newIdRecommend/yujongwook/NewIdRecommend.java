package exercise.all.lvl1.newIdRecommend.yujongwook;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * <pre>
 *
 * [개요]
 * 카카오에 입사한 신입 개발자 네오는 "카카오계정개발팀"에 배치되어, 카카오 서비스에 가입하는 유저들의 아이디를 생성하는 업무를 담당하게 되었습니다. "네오"에게 주어진 첫 업무는 새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을 때, 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램을 개발하는 것입니다.
 * 다음은 카카오 아이디의 규칙입니다.
 * 아이디의 길이는 3자 이상 15자 이하여야 합니다.
 * 아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
 * 단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.
 * "네오"는 다음과 같이 7단계의 순차적인 처리 과정을 통해 신규 유저가 입력한 아이디가 카카오 아이디 규칙에 맞는 지 검사하고 규칙에 맞지 않은 경우 규칙에 맞는 새로운 아이디를 추천해 주려고 합니다.
 * 신규 유저가 입력한 아이디가 new_id 라고 한다면,
 *
 *
 * [문제]
 * 신규 유저가 입력한 아이디를 나타내는 new_id가 매개변수로 주어질 때, "네오"가 설계한 7단계의 처리 과정을 거친 후의 추천 아이디를 return 하도록 solution 함수를 완성해 주세요.
 *
 *
 * [제한사항]
 * new_id는 길이 1 이상 1,000 이하인 문자열입니다.
 * new_id는 알파벳 대문자, 알파벳 소문자, 숫자, 특수문자로 구성되어 있습니다.
 * new_id에 나타날 수 있는 특수문자는 -_.~!@#$%^&*()=+[{]}:?,<>/ 로 한정됩니다.
 *
 *
 * [Step]
 * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
 * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
 * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
 * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
 * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
 * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
 * 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
 * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 *
 *
 * [예시]
 * no | new_id 							| result
 * 예1 | "...!@BaT#*..y.abcdefghijklm" 	| "bat.y.abcdefghi"
 * 예2 | "z-+.^." 						| "z--"
 * 예3 |  "=.=" 						| "aaa"
 * 예4 |  "123_.def" 					| "123_.def"
 * 예5 |  "abcdefghijklmn.p" 			| "abcdefghijklmn"
 *
 * </pre>
 *
 * @package : exercise.all.lvl1.newIdRecommend.yujongwook
 * @name : NewIdRecommend.java
 * @date : 2021-03-15
 * @author : jw.yu
 * @description :
 */
public class NewIdRecommend {
	public static void main(String[] args) {

		/* 테스트할 문자열 (selectTestId 에서 선택) */
		String tId = "1";

		/* 실행 */
		executor1(tId);
		executor2(tId); //Optional 로 해봄
	}

	public static String selectTestId (String i){
		Map<String, String> condition = new HashMap<>();
		condition.put("1", "...!@BaT#*..y.abcdefghijklm."); //bat.y.abcdefghi
		condition.put("2", "z-+.^."); //z--
		condition.put("3", "=.="); //aaa
		condition.put("4", "123_.def"); //123_.def
		condition.put("5", "abcdefghijklmn.p"); //abcdefghijklmn
		condition.put("6", "asdf;qwer"); //특수문자 테스트용
		condition.put("7", "asdf★qwer"); //특수문자 테스트용
		condition.put("8", "asdf[qw[e]r"); //특수문자 테스트용
		return condition.get(i);
	}

	public static void executor1 (String tId){

		String orgId = selectTestId(tId);
		System.out.println("============================================ excutor1 실행");
		System.out.println("::::: 대상 문자열 = " + orgId);

		if (validator(orgId)){

			//1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
			String modId = orgId.toLowerCase();
			orgId = stepPrint("1", orgId, modId);

			//2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
			modId = orgId.replaceAll("[^a-z0-9-_.]", "");
			orgId = stepPrint("2", orgId, modId);

			//3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
			modId = orgId.replaceAll("\\.+", ".");
			orgId = stepPrint("3", orgId, modId);

			//4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
			modId = orgId.replaceAll("^(\\.)|(\\.)$", "");
			orgId = stepPrint("4", orgId, modId);

			//5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
			if ("".equals(modId)) modId = "a";
			orgId = stepPrint("5", orgId, modId);

			//6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
			// 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
			if (modId.length() >= 16) modId = modId.substring(0, 15).replaceAll("(\\.)$", "");
			orgId = stepPrint("6", orgId, modId);

			//7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
			if (modId.length() <= 2) modId = String.format("%-3s", modId).replace(" ",modId.substring(modId.length()-1));
			orgId = stepPrint("7", orgId, modId);

			System.out.println("::::: 결과 문자열 = " + orgId);
		}
	}

	//Optional 로 해봄
	public static void executor2 (String tId){

		String orgId = selectTestId(tId);
		System.out.println("============================================ excutor2 실행");
		System.out.println("::::: 변환전 : " + orgId);

		if (validator(orgId)){
//			Optional<String> opt = Optional.ofNullable(orgId)
			Optional.ofNullable(orgId)
					.map(o -> stepPrint("1", o, o.toLowerCase()))
					.map(o -> stepPrint("2", o, o.replaceAll("[^a-z0-9-_.]", "")))
					.map(o -> stepPrint("3", o, o.replaceAll("\\.+", ".")))
					.map(o -> stepPrint("4", o, o.replaceAll("^(\\.)|(\\.)$", "")))
					.map(o -> {
						String a = o;
						if ("".equals(o)) a = "a";
						return stepPrint("5", o, a);
					})
					.map(o -> {
						String a = o;
						if (a.length() >= 16) a = a.substring(0, 15).replaceAll("(\\.)$", "");
						return stepPrint("6", o, a);
					})
					.map(o -> {
						String a = o;
						if (a.length() <= 2) a = String.format("%-3s", a).replace(" ",a.substring(a.length()-1));
						return stepPrint("7", o, a);
					});
		}
	}

	public static boolean validator (String str){
		boolean success = true;
		String msg = "Check Pass";

		if ("".equals(str) || str.length() > 1000) {
			success = false;
			msg = "new_id 는 길이 1 이상 1,000 이하인 문자열입니다.";
		}

		/*
		 * [info]
		 * Warning : Redundant character escape.
		 * 		     Reports character escapes that are replaceable with the unescaped character without a change in meaning. Note that inside the square brackets of a character class, many escapes are unnecessary that would be necessary outside of a character class. For example the regex [\.] is identical to [.]
		 * [comment]
		 * 대괄호 안에는 escapes character 안하고 사용 되는 것으로 보임. 단 대괄호안에 사용되는 대괄호는 escapes character 표기
		 */
		//String pattern = "[\\w\\-\\_\\.\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\=\\+\\[\\{\\]\\}\\:\\?\\,\\<\\>\\/]*$";
		String pattern = "[\\w-_.~!@#$%^&*()=+\\[{\\]}:?,<>/]*$";

		if (!Pattern.matches(pattern, str)){
			success = false;
			msg = "new_id 는 알파벳 대문자, 알파벳 소문자, 숫자, 특수문자(-_.~!@#$%^&*()=+[{]}:?,<>/) 만 허용합니다.";
		}

		System.out.println("::::: "+msg);
		return success;
	}

	public static String stepPrint(String step, String orgId, String modId){

		String printMsg;
		String rtnValue;

		if (orgId.equals(modId)){
			printMsg = step + "단계 변화 없습니다.";
			rtnValue = orgId;
		}else{
			printMsg = step + "단계 " + orgId + " -> " + modId;
			rtnValue = modId;
		}
		System.out.println(printMsg);

		return rtnValue;
	}
}