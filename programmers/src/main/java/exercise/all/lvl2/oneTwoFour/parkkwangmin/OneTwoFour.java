package exercise.all.lvl2.oneTwoFour.parkkwangmin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class OneTwoFour {
	
	public static void main(String[] args) {
		//10 =       101; = 1 * 3*3 + 1*1 = 10; 
		//1 = 1
		//2 = 2
		//3 = 4
		Long n = 40L;
		System.out.println("10진번 : " + n);
		BigInteger v = BigInteger.valueOf(n);
		BigInteger divisor = BigInteger.valueOf(3);
		List<Integer> base3List = new ArrayList<>();
		
		while(true) {
			BigInteger[] resDivide = v.divideAndRemainder(divisor);
			
			base3List.add(resDivide[1].intValue());
			if (resDivide[0] == BigInteger.ZERO)
				break;
			
			v = resDivide[0];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = base3List.size() - 1; i >= 0; i--) {
			int intValue = base3List.get(i);
			sb.append(base3List.get(i));
		}
		
		String base3 = sb.toString();
		System.out.println("3진법 : " + base3);
		
		Stack<String> base124List = new Stack<>();
		int jari = base3.length() + 1;
		for (int i = 0; i < base3.length(); i++) {
			jari--;
			if (jari == 0) break;
			
			Integer number = Integer.valueOf(base3.substring(i, i + 1));
			
			if (jari == 1) {
				if (number == 1) {
					base124List.add("1");
				} else if (number == 2) {
					base124List.add("2");
				}
				break;
			}
			
			if (number == 0) continue;
			
			if (jari == 2) {
				if (number == 1) {
					base124List.add("4");
				} else if (number == 2) {
					base124List.add("14");
				}
				continue;
			}
			
			String format = "%" + (jari - 1) + "s";
			String numberStr = String.format(format, 4).replace(" ", "2");
			
			if (number == 2) numberStr = "1" + numberStr;
			base124List.add(numberStr);
		}
		
		base124List.forEach(System.out::println);
		
		String base124 = null;
		while(true) {
			if (base124List.isEmpty()) break;
			
			if (base124List.size() == 1) {
				base124 = base124List.pop();
				break;
			}
			
			String pop1 = base124List.pop();
			String pop2 = base124List.pop();
			
			int longerLength = pop1.length() >= pop2.length() ? pop1.length() : pop2.length();
			if (pop1.length() != longerLength) {
				pop1 = String.format("%" + longerLength + "s", pop1).replace(" ", "0");
			}
			if (pop2.length() != longerLength) {
				pop2 = String.format("%" + longerLength + "s", pop1).replace(" ", "0");
			}
			
			List<String> sumList = new ArrayList<>();
			int passedNum = 0;
			for (int i = longerLength - 1; i >= -1; i--) {
				if (i == -1) {
					if (passedNum == 0) break;
					
					sumList.add(String.valueOf(passedNum));
					break;
				}
				
				int aPop1Num = Integer.parseInt(pop1.substring(i, i + 1));
				int aPop2Num = Integer.parseInt(pop2.substring(i, i + 1));
				
				int thisPasswdNum = 0;
				int thisNum = 0;
				if (aPop1Num == 0 || aPop2Num == 0) {
					thisNum = aPop1Num + aPop2Num;
				} else {
					int popSumed = aPop1Num + aPop2Num;
					
					if (popSumed == 2) {
						thisNum = 2;
					} else if (popSumed == 3) {
						thisNum = 4;
					} else if (popSumed == 4) {
						thisNum = 1;
						thisPasswdNum = 1;
					} else if (popSumed == 5) {
						thisNum = 1;
						thisPasswdNum = 1;
					} else if (popSumed == 6) {
						thisNum = 2;
						thisPasswdNum = 1;
					} else if (popSumed == 8) {
						thisNum = 4;
						thisPasswdNum = 1;
					}
				}
				
				if (passedNum == 0) {
					passedNum = thisPasswdNum;
					sumList.add(String.valueOf(thisNum));
					continue;
				}
				
				int passedSumd = thisNum + passedNum;
				
				if (passedSumd == 2) {
					thisNum = 2;
				} else if (passedSumd == 3) {
					thisNum = 4;
				} else if (passedSumd == 4) {
					thisNum = 1;
					thisPasswdNum += 1;
				} else if (passedSumd == 5) {
					thisNum = 1;
					thisPasswdNum += 1;
				} else if (passedSumd == 6) {
					thisNum = 2;
					thisPasswdNum += 1;
				} else if (passedSumd == 8) {
					thisNum = 4;
					thisPasswdNum += 1;
				}
				
				passedNum = thisPasswdNum;
				sumList.add(String.valueOf(thisNum));
			}
			
			Collections.reverse(sumList);
			
			String sumedStr = sumList.stream().collect(Collectors.joining(""));
			System.out.println("sum : " + pop1 + " + " + pop2);
			System.out.println("sumedStr : " + sumedStr);
			
			base124List.push(sumedStr);
		}
		
		System.out.println("base124 : " + base124);
    }

}
