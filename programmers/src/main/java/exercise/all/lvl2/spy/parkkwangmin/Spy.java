package exercise.all.lvl2.spy.parkkwangmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 종류의 수(n)를 구하고, 하나를 선택할 때의 경우의 수를 구한다. 모두 더함
 * 두 개 이상 선택할 때의 경우의 수
 * 	1. 조합을 구한다.
 * 	2. 한 종류에 옷이 여러개(m)인 경우, (m-1) * ((n -1)의 경우의 수)를 더한다.
 * 
 * @author 박광민
 * @since 2021. 4. 28.
 */
public class Spy {
	
	public static void main(String[] args) {
		//array를 list로... 
		List<String> a = Arrays.asList(new String[]{"a", "b", "c"});
		//a.stream().forEach(System.out::println);
		List<Integer> b = Arrays.asList(new Integer[]{1, 2, 3});
		//b.stream().forEach(System.out::println);
		List<Integer> c = Arrays.asList(Arrays.stream(new int[]{1, 2, 3}).boxed().toArray(Integer[]::new));
		//c.stream().forEach(System.out::println);
		
		//String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}, {"1", "1"}};
		//String[][] clothes = {{"0", "0"}, {"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}, {"1", "1"}};
		//List<Map<String, List<String>>> clothesByCategory = new ArrayList<>();
		Map<String, List<String>> clothesByCategory = new LinkedHashMap<>();
		for (String[] cloth : clothes) {
			String category = cloth[1];
			
			if (clothesByCategory.containsKey(category)) {
				clothesByCategory.get(category).add(cloth[0]);
			} else {
				List<String> cloths = new ArrayList<>();
				cloths.add(cloth[0]);
				clothesByCategory.put(category, cloths);
			}
		}
		
		int totalCount = 0;
		for (int i = 1; i <= clothesByCategory.size(); i++) {
			//n개 중에 r개를 뽑아야 함
			List<String> result = new ArrayList<>();
			boolean[] used = new boolean[clothesByCategory.size()];
			
			int count = getCombination(clothesByCategory.size(), i, 1, used, result, clothesByCategory);
			System.out.println(i + "개 뽑는 경우의 수 : " + count);
			totalCount += count;
		}
		
		System.out.println("총 조합 수 : " + totalCount);
		
		
		
    }
	//처음, 중간, 끝 경우를 모두 커버해야 한다.
	//처음 : 시작
	//끝 : r개가 이미 뽑혔거나 depth가 이미 마지막에 이른 경우
	private static int getCombination(int n, int r, int depth, boolean[] used, List<String> result, Map<String, List<String>> clothesByCategory) {
		//r개가 이미 뽑혔니?
		if (result.size() == r) {
			System.out.println(result.stream().collect(Collectors.joining(",")));
			return 1;
		}
		//끝에 다다랐는데 r개를 못채웠니?
		if (depth > n && result.size() < r) {
			return 0;
		}
		
		//나머지 : r개를 못채웠고 끝에 다다르지 않았음 -> 그다음 depth 진행
		//depth에 해당하는 수 뽑은경우
		List<String> clothes = new ArrayList<List<String>>(clothesByCategory.values()).get(depth - 1);
		used[depth - 1] = true;
		int count = 0;
		for (int i = 0; i < clothes.size(); i++) {
			String colth = clothes.get(i);
			result.add(colth);
			count += getCombination(n, r, depth + 1, used, result, clothesByCategory);
			result.remove(result.size() - 1);
		}
		used[depth - 1] = false;//원복
		
		//depth에 해당하는 수 안뽑은경우
		used[depth - 1] = false;
		count += getCombination(n, r, depth + 1, used, result, clothesByCategory);
		
		return count;
	}

}
