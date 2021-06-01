package exercise.all.lvl2.pairRemove.parkkwangmin;

import java.util.Stack;

public class PairRemove {
	
	public static void main(String[] args) {
		String s = "baabaa";
		//String s = "cdcd";
		int answer = removePairs(s);
		
		System.out.println(answer);
    }

	public static int removePairs(String s) {
		if (s == null) {
			return 1;
		} else if (s.length() == 1) {
			return 0;
		}
		
		int answer = 0;
		Stack<Integer> remainedChars = new Stack<>();
		char[] charArray = s.toCharArray();
		int length = s.length();
		for (int i = length - 1; i >= 0; i--) {
			remainedChars.push(((int)charArray[i]));
		}
		
		Stack<Integer> unPairedChars = new Stack<>();
		int firstChar = remainedChars.pop();
		int secondChar = remainedChars.pop();
		while(true) {
			if (firstChar != secondChar) {
				unPairedChars.push(firstChar);
				firstChar = secondChar;
				
				if (remainedChars.isEmpty()) {
					answer = 0;
					break;
				}
				
				secondChar = remainedChars.pop();
				continue;
			}
			
			if (remainedChars.size() > 1) {
				firstChar = unPairedChars.isEmpty() ? remainedChars.pop() : unPairedChars.pop();
				secondChar = remainedChars.pop();
			} else if (remainedChars.size() == 1) {
				if (unPairedChars.isEmpty()) {
					answer = 0;
					break;
				}
				
				firstChar = unPairedChars.pop();
				secondChar = remainedChars.pop();
			} else {
				if (unPairedChars.isEmpty()) {
					answer = 1;
				} else {
					answer = 0;
				}
				break;
			}
		}
		
		return answer;
	}

}
