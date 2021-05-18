package exercise.all.lvl2.oneTwoFour.parkkwangmin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StopWatch;

public class OneTwoFour {
	
	private static StopWatch stopWatch = new StopWatch();
	
	public static void main(String[] args) {
		int n = 500000000;
		stopWatch.start("all");
		get124(n);
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
    }

	private static String get124(int n) {
		//stopWatch.start("convertToBase3");
		String base3 = convertToBase3(n);
		//stopWatch.stop();
		//stopWatch.start("changeToBase123ByJari");
		List<String> base123List = changeToBase123ByJari(base3);
		//stopWatch.stop();
		
		//stopWatch.start("sum");
		Map<Integer, Integer> base123SumByJariMap = new LinkedHashMap<>();
		for (String base123 : base123List) {
			String[] split = base123.split("");
			int length = base123.length();
			for (int jari = 1; jari <= length; jari++) {
				int value = Integer.parseInt(split[length - jari]);
				int existValue = base123SumByJariMap.getOrDefault(jari, 0);
				base123SumByJariMap.put(jari, value + existValue);
			}
		}
		//stopWatch.stop();
		
		//stopWatch.start("mapToList");
		
		//List<Integer> result123 = base123SumByJariMap.entrySet().stream().sorted((a, b) -> a.getKey() > b.getKey() ? 1 : -1).map(e -> e.getValue()).collect(Collectors.toList());
		List<Integer> result123 = new ArrayList<Integer>(base123SumByJariMap.values());
		//stopWatch.stop();
		
		//stopWatch.start("getResult");
		List<String> resultBase124 = new ArrayList<>();
		int passOveredNum = 0;
		for (int i = 0; i < result123.size(); i++) {
			int sum = passOveredNum + result123.get(i);
			
			int mok = sum / 3;
			int namuji = sum % 3;
			
			passOveredNum = namuji == 0 ? mok - 1 : mok;
			
			resultBase124.add(String.valueOf(namuji == 0 ? 4 : namuji));
		}
		
		if (passOveredNum > 0) {
			int mok = passOveredNum / 3;
			int namuji = passOveredNum % 3;
			
			resultBase124.add(String.valueOf(namuji == 0 ? 4 : namuji));
			
			passOveredNum = namuji == 0 ? mok - 1 : mok;
			if (passOveredNum > 0)
				resultBase124.add(String.valueOf(passOveredNum));
		}
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = resultBase124.size() - 1; i >= 0; i--) {
			builder.append(resultBase124.get(i));
		}
		//Collections.reverse(resultBase124);
		
		//String result = resultBase123.stream().map(s -> "3".equals(s) ? "4" : s).collect(Collectors.joining());
		String result = builder.toString();
		//stopWatch.stop();
			
		System.out.println("base124 : " + result);
		
		return result;
	}

	private static String convertToBase3(int numberOfBase10) {
		List<String> base3List = new ArrayList<>();
		
		int v = numberOfBase10;
		while(true) {
			int mok = v / 3;
			int namuji = v % 3;
			
			base3List.add(String.valueOf(namuji));
			if (mok == 0)
				break;
			
			v = mok;
		}
		
		StringBuilder builder = new StringBuilder();
		int length = base3List.size();
		for (int i = 1; i <= length; i++) {
			builder.append(base3List.get(length - i));
		}
		
//		Collections.reverse(base3List);
		
//		String base3 = base3List.stream().collect(Collectors.joining());
		String base3 = builder.toString();
		return base3;
	}

	private static List<String> changeToBase123ByJari(String base3) {
		List<String> base123List = new ArrayList<>();
		int base3Length = base3.length();
		for (int jari = 1; jari <= base3.length(); jari++) {
			String number = base3.substring(base3Length - jari, base3Length - jari + 1);
			if ("0".equals(number)) continue;
			
			if (jari == 1) {
				base123List.add(number);
				continue;
			}
			
//			String format = "%" + (jari - 1) + "s";
//			String str123 = String.format(format, "3").replace(" ", "2");
			StringBuilder builder = new StringBuilder();
			if ("2".equals(number)) builder.append("1");
			for (int i = 1; i < jari - 1; i++) {
				builder.append("2");
			}
			builder.append("3");
			String str123 = builder.toString();
			
			base123List.add(str123);
		}
		return base123List;
	}
}
