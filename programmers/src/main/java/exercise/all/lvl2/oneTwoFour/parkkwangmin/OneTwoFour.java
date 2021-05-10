package exercise.all.lvl2.oneTwoFour.parkkwangmin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class OneTwoFour {
	
	public static void main(String[] args) {
		int n = 500000000;
		String base3 = convertToBase3(n);
		
		List<String> base124List = changeToBase124ByJari(base3);
		
		System.out.println("reduce");
		String result = base124List.stream().reduce((el1, el2) -> sum(el1, el2)).get();
		
//		Stack<String> stack = new Stack<>();
//		
//		for (int i = base124List.size() - 1; i >= 0; i--) {
//			stack.push(base124List.get(i));
//		}
//		
//		String base124 = sumValueOfJari(stack);
		
		System.out.println("3진법   : " + base3);
		System.out.println("base124 : " + result);
    }

	private static String convertToBase3(int numberOfBase10) {
		System.out.println("10진법 : " + numberOfBase10);
		BigInteger v = BigInteger.valueOf(numberOfBase10);
		BigInteger divisor = BigInteger.valueOf(3);
		List<String> base3List = new ArrayList<>();
		
		while(true) {
			BigInteger[] resDivide = v.divideAndRemainder(divisor);
			
			base3List.add(resDivide[1].toString());
			if (resDivide[0] == BigInteger.ZERO)
				break;
			
			v = resDivide[0];
		}
		
		Collections.reverse(base3List);
		
		String base3 = base3List.stream().collect(Collectors.joining());
		System.out.println("3진법 : " + base3);
		return base3;
	}

	/**
	 * 3진법을 124진법으로 바꾼다.
	 * 3진법의 각 자리수마다 124진법으로 바꿨을 때 규칙성이 있으므로, 3진법의 각 자리수 단위로 124진법으로 치환하는 작업을 한다. 
	 * 12(3) -> 4(124) + 2(124)
	 * 100022(3) -> 22224(124) + 14(124) + 2(124)
	 * @param base3
	 * @return
	 * @author 박광민
	 * @since 2021. 5. 10.
	 */
	private static List<String> changeToBase124ByJari(String base3) {
		List<String> base124List = new ArrayList<>();
		int base3Length = base3.length();
		for (int jari = 1; jari <= base3.length(); jari++) {
			Integer number = Integer.valueOf(base3.substring(base3Length - jari, base3Length - jari + 1));
			
			if (number == 0) continue;
			
			if (jari == 1) {
				if (number == 1) {
					base124List.add("1");
				} else if (number == 2) {
					base124List.add("2");
				}
				continue;
			}
			
			String format = "%" + (jari - 1) + "s";
			String numberStr = String.format(format, 4).replace(" ", "2");
			
			if (number == 2) numberStr = "1" + numberStr;
			base124List.add(numberStr);
		}
		
		base124List.forEach(System.out::println);
		return base124List;
	}

	private static String sum(String el1, String el2) {
		int passOveredNumToNextJari = 0;
		List<String> SumedUnits = new ArrayList<>();
		int jari = 0;
		while (true) {
			jari++;
			int num1 = el1.length() >= jari ? Integer.parseInt(el1.substring(el1.length() - jari, el1.length() - jari + 1)) : 0;
			int num2 = el2.length() >= jari ? Integer.parseInt(el2.substring(el2.length() - jari, el2.length() - jari + 1)) : 0;
			
			if (num1 == 0 && num2 == 0) {//자릿수 모두 더한 경우 
				if (passOveredNumToNextJari > 0) SumedUnits.add(String.valueOf(passOveredNumToNextJari));
				break;
			}
			
			String resultSumUnit = sumUnit(num1, num2);
			String resultSumToPassOveredNum = sumUnit(Integer.valueOf(resultSumUnit.substring(1)), passOveredNumToNextJari);
			
			passOveredNumToNextJari = Integer.valueOf(resultSumUnit.substring(0, 1)) + Integer.valueOf(resultSumToPassOveredNum.substring(0, 1));
			SumedUnits.add(String.valueOf(resultSumToPassOveredNum.substring(1)));
		}
		
		Collections.reverse(SumedUnits);
		
		String sumedNum = SumedUnits.stream().collect(Collectors.joining(""));
		
		System.out.println("sum : " + el1 + " + " + el2);
		System.out.println("sumedStr : " + sumedNum);
		
		return sumedNum;
	}

	/**
	 * 두 숫자(124진법 수)를 더한다.
	 * @param num1
	 * @param num2
	 * @return sumedValue 덧셈 결과값 ex) 1 + 1 => 인 02, 2 + 4 => 12
	 * @author 박광민
	 * @since 2021. 5. 10.
	 */
	private static String sumUnit(int num1, int num2) {
		if (num1 == 0 || num2 == 0) {
			return "0" + (num1 + num2);
		}
		
		int sumedNum = num1 + num2;
		if (sumedNum == 2) {//1 + 1
			return "02";
		} else if (sumedNum == 3) {//1 + 2
			return "04";
		} else if (sumedNum == 4) {//2 + 2
			return "11";
		} else if (sumedNum == 5) {//1 + 4
			return "11";
		} else if (sumedNum == 6) {//2 + 4
			return "12";
		} else if (sumedNum == 8) {//4 + 4
			return "14";
		} else {
			throw new IllegalArgumentException();
		}
	}

	private static String sumValueOfJari(Stack<String> base124List) {
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
		return base124;
	}

}
