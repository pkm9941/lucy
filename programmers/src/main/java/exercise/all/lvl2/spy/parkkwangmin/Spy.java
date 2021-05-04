package exercise.all.lvl2.spy.parkkwangmin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 박광민
 * @since 2021. 4. 28.
 */
public class Spy {
	
	public static void main(String[] args) {
		
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
//		String[][] clothes = {
//				  {"01", "01"}
//				, {"02", "02"}
//				, {"03", "03"}
//				, {"04", "04"}
//				, {"05", "05"}
//				, {"06", "06"}
//				, {"07", "07"}
//				, {"08", "08"}
//				, {"09", "09"}
//				, {"10", "10"}
//				, {"11", "11"}
//				, {"12", "12"}
//				, {"13", "13"}
//				, {"14", "14"}
//				, {"15", "15"}
//				, {"16", "16"}
//				, {"17", "17"}
//				, {"18", "18"}
//				, {"19", "19"}
//				, {"20", "20"}
//				, {"21", "21"}
//				, {"22", "22"}
//				, {"23", "23"}
//				, {"24", "24"}
//				, {"25", "25"}
//				, {"26", "26"}
//				, {"27", "27"}
//				, {"28", "28"}
//				, {"29", "29"}
//				, {"30", "30"}
//				};
		//int totalCount = findCasesCanBeWorn2(clothes);
		int totalCount = findCasesCanBeWorn(clothes);
		System.out.println("총 조합 수 : " + totalCount);
    }
	
	public static int findCasesCanBeWorn(String[][] clothes) {
		List<Integer> clothesByCategory = classifyClothes(clothes);
		
		int totalCount = getCombination(clothesByCategory.size(), 1, clothesByCategory);
		totalCount -= 1; //하나도 선택하지 않은 경우 제외
		System.out.println("totalCount : " + totalCount);
		
		return totalCount;
	}
	
	private static List<Integer> classifyClothes(String[][] clothes) {
		Map<String, Integer> clothesByCategory = new LinkedHashMap<>();
		for (String[] cloth : clothes) {
			String category = cloth[1];
			
			if (clothesByCategory.containsKey(category)) {
				clothesByCategory.put(category, clothesByCategory.get(category) + 1);
			} else {
				clothesByCategory.put(category, 1);
			}
		}
		
		return new ArrayList<Integer>(clothesByCategory.values());
	}
	
	private static int getCombination(int n, int depth, List<Integer> clothesByCategory) {
		
		int clothesCntOfThisDepth = clothesByCategory.get(depth - 1);
		if (depth  == n) {//끝에 다다랐니?
			return 1 + clothesCntOfThisDepth;//안뽑은 경우 + 뽑은 경우
		}
		
		int count = 0;
		//depth에 해당하는 요소를 뽑은 경우
		int selectedCount = getCombination(n, depth + 1, clothesByCategory);
		count += selectedCount * clothesCntOfThisDepth;
		
		//depth에 해당하는 요소를 안뽑은 경우
		count += getCombination(n, depth + 1, clothesByCategory);
		return count;
	}
	
	public static int findCasesCanBeWorn3(String[][] clothes) {
		List<List<String>> clothesByCategory = classifyClothes3(clothes);
		
		int totalCount = 0;
		for (int i = 1; i <= clothesByCategory.size(); i++) {
			
			int count = getCombination3(clothesByCategory.size(), i, 1, new boolean[clothesByCategory.size()], clothesByCategory);
			System.out.println(i + "번째 경우의 수 : " + count);
			totalCount += count;
		}
		
		return totalCount;
	}

	private static List<List<String>> classifyClothes3(String[][] clothes) {
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
		
		return new ArrayList<List<String>>(clothesByCategory.values());
	}

	private static int getCombination3(int n, int r, int depth, boolean[] used, List<List<String>> clothesByCategory) {
		int usedCnt = 0;
		for (boolean isUsed : used) {
			if (isUsed) usedCnt++;
		}
		if (usedCnt == r) {//r개가 이미 뽑혔니?
			//System.out.println(result.stream().collect(Collectors.joining(",")));
			return 1;
		}
		
		if (depth > n && usedCnt < r) {//끝에 다다랐는데 r개를 못채웠니?
			return 0;
		}
		
		if ((r - usedCnt) - (n - (depth - 1)) > 0) {//앞으로 모두 선택해도 r개를 못채우는 경우
			return 0;
		}
		
		//depth에 해당하는 요소를 뽑은 경우
		List<String> clothes = clothesByCategory.get(depth - 1);
		int count = 0;
		used[depth - 1] = true;
		int selectedCount = getCombination3(n, r, depth + 1, used, clothesByCategory);
		count += selectedCount * clothes.size();
		used[depth - 1] = false;//특정 요소를 뽑은 경우를 모두 처리했으므로 원복
		
		//depth에 해당하는 요소를 안뽑은 경우
		count += getCombination3(n, r, depth + 1, used, clothesByCategory);
		return count;
	}

	public static int findCasesCanBeWorn2(String[][] clothes) {
		Map<String, List<String>> clothesByCategory = classifyClothes2(clothes);
		
		int totalCount = 0;
		for (int i = 1; i <= clothesByCategory.size(); i++) {
			
			int count = getCombination2(clothesByCategory.size(), i, 1, new boolean[clothesByCategory.size()], clothesByCategory);
			totalCount += count;
		}
		
		return totalCount;
	}
	
	private static Map<String, List<String>> classifyClothes2(String[][] clothes) {
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
		
		return clothesByCategory;
	}
	
	//n개 중에 r개 뽑기
	//주어진 depth에서 발생할 수 있는 경우(뽑은 경우, 안뽑는 경우)를 처리한다.
	private static int getCombination2(int n, int r, int depth, boolean[] used, Map<String, List<String>> clothesByCategory) {
		int usedCnt = 0;
		for (boolean isUsed : used) {
			if (isUsed) usedCnt++;
		}
		if (usedCnt == r) {//r개가 이미 뽑혔니?
			//System.out.println(result.stream().collect(Collectors.joining(",")));
			return 1;
		}
		
		if (depth > n && usedCnt < r) {//끝에 다다랐는데 r개를 못채웠니?
			return 0;
		}
		
		//depth에 해당하는 요소를 뽑은 경우
		List<String> clothes = new ArrayList<List<String>>(clothesByCategory.values()).get(depth - 1);
		int count = 0;
		used[depth - 1] = true;
		for (int i = 0; i < clothes.size(); i++) {
			String colth = clothes.get(i);
			count += getCombination2(n, r, depth + 1, used, clothesByCategory);
		}
		used[depth - 1] = false;//특정 요소를 뽑은 경우를 모두 처리했으므로 원복
		
		//depth에 해당하는 요소를 안뽑은 경우
		count += getCombination2(n, r, depth + 1, used, clothesByCategory);
		
		return count;
	}
}
