package exercise.collection.kakao.bliend2020;

public class ParenthesisConvertTest {

	public static void main(String[] args) {
		String result = solution("()))((()");
		System.out.println(result);
	}

	private static String solution(String p) {
		if (p == null || p.isEmpty())
			return "";
		
		String leftBalancedStr = getLeftBalancedStr(p);
		String rightBalancedStr = p.substring(leftBalancedStr.length(), p.length());
		if (validStr(leftBalancedStr)) {
			return leftBalancedStr + solution(rightBalancedStr);
		}
		
		return String.format("(%s)%s", solution(rightBalancedStr), reverseStr(leftBalancedStr.substring(1, leftBalancedStr.length() - 1)));
	}

	private static String getLeftBalancedStr(String p) {
		String leftBalancedStr = "";
		int leftParenthesisCnt = 0;
		int rightParenthesisCnt = 0;
		for (int i = 0; i < p.length(); i++) {
			String oneChar = p.substring(i, i + 1);
			if ("(".equals(oneChar)) {
				leftParenthesisCnt++;
			} else {
				rightParenthesisCnt++;
			}
			
			if (leftParenthesisCnt == rightParenthesisCnt) {
				leftBalancedStr = p.substring(0, i + 1);
				break;
			}
		}
		return leftBalancedStr;
	}

	private static boolean validStr(String str) {
		if (!"(".equals(str.substring(0, 1)))
			return false;
		
		int balance = 0;
		for (int i = 0; i < str.length(); i++) {
			if ("(".equals(str.substring(i, i + 1))) {
				balance+= 1;
			} else {
				balance-= 1;
			}
			
			if (balance < 0)
				return false; 
		}
	
		return true;
	}

	private static String reverseStr(String str) {
		String reversedStr = "";
		for (int i = 0; i < str.length(); i++) {
			if ("(".equals(str.substring(i, i + 1))) {
				reversedStr+= ")";
			} else {
				reversedStr+= "(";
			}
		}
		return reversedStr;
	}

}
