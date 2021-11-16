package exercise.all.lvl2.rankSearch.parkkwangmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RankSearch {
	
	private List<String[]> infoList;
	private Map<String, Object> classifiedInfo = new HashMap<>();
	private Map<String, List<String>> category = new LinkedHashMap<>();
	private int infoDepth;
	private List<String> categoryList = Arrays.asList("lang", "position", "career", "soulFood");
	
	public RankSearch(String[] info) {
		category = genCategory();
		infoDepth = category.size();
		infoList = genInfoList(info);
		System.out.println("genInfoList end");
		classifiedInfo = classifyInfo();
		System.out.println("classifyInfo end");
	}

	public static Map<String, List<String>> genCategory() {
		Map<String, List<String>> category = new LinkedHashMap<>();
		category.put("lang", Arrays.asList("cpp", "java", "python", "-"));
		category.put("position", Arrays.asList("backend", "frontend", "-"));
		category.put("career", Arrays.asList("junior", "senior", "-"));
		category.put("soulFood", Arrays.asList("chicken", "pizza", "-"));
		return category;
	}

	private List<String[]> genInfoList(String[] info) {
		infoList = new ArrayList<>();
		for (int i = 0; i < info.length; i++) {
			infoList.add(info[i].split(" "));
		}
		
		infoList.sort((a, b) -> Integer.parseInt(a[infoDepth]) > Integer.parseInt(b[infoDepth]) ? 1 : -1);
		return infoList;
	}

	private Map<String, Object> classifyInfo() {
		findSatisfied(0, infoList, classifiedInfo);
		return classifiedInfo;
	}


	private void findSatisfied(int categoryIndex, List<String[]> infoList, Map<String, Object> classifiedInfo) {
		for(String element : category.get(categoryList.get(categoryIndex))) {
			if (categoryIndex >= infoDepth - 1) {
				classifiedInfo.put(element, findScore(categoryIndex, infoList, element));
				continue;
			}
			
			Map<String, Object> value = new HashMap<>();
			classifiedInfo.put(element, value);
			findSatisfied(categoryIndex + 1,findInfo(categoryIndex, infoList, element) , value);
		}
	}

	private List<Integer> findScore(int categoryIndex, List<String[]> infoList, String element) {
		if ("-".equals(element)) {
			List<Integer> scores = new ArrayList<>(); 
			for (int i = 0; i < infoList.size(); i++)
				scores.add(Integer.valueOf(infoList.get(i)[infoDepth]));
			
			return scores;
		}
		
		List<Integer> scores = new ArrayList<>();
		for (int i = 0; i < infoList.size(); i++) {
			if (element.equals(infoList.get(i)[categoryIndex]))
				scores.add(Integer.valueOf(infoList.get(i)[infoDepth]));
		}
		return scores;
	}

	private List<String[]> findInfo(int categoryIndex, List<String[]> infoList, String element) {
		if ("-".equals(element))
			return infoList;
		
		List<String[]> filteredInfoList = new ArrayList<>();
		for (String[] info : infoList) {
			if (element.equals(info[categoryIndex]))
				filteredInfoList.add(info);
		}
		return filteredInfoList;
	}

	public int[] executeQuery(String[] query) {
		int[] result = new int[query.length];
		String[][] queryArray = genQueryArray(query);
		for (int i = 0; i < query.length; i++) {
			Map<String, Object> filteredInfo = classifiedInfo;
			for (int j = 0; j < infoDepth - 1; j++) {
				filteredInfo = (Map<String, Object>) filteredInfo.get(queryArray[i][j]);
			}
			
			List<Integer> filteredList = (List<Integer>) filteredInfo.get(queryArray[i][infoDepth - 1]);
			if (filteredList.isEmpty()) {
				result[i] = 0;
				continue;
			}
				
			int baseScore = Integer.parseInt(queryArray[i][infoDepth]);
			
			//result[i] = getCntByLoop(filteredList, baseScore);
			result[i] = getCntByBinarySearch(filteredList, baseScore);
		}
		return result;
	}

	private int getCntByBinarySearch(List<Integer> filteredList, int baseScore) {
		int binarySearchResult = Collections.binarySearch(filteredList, baseScore);
		if (binarySearchResult >= 0)
			return filteredList.size() - filteredList.indexOf(filteredList.get(binarySearchResult));
		
		int minIndex = -binarySearchResult - 1;
		return filteredList.size() - minIndex;
	}

	private int getCntByLoop(int[] filteredList, int baseScore) {
		int cnt = 0;
		for (int score : filteredList) {
			if (score >= baseScore)
				cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) {
//		String[] info = genInfo(10);//최대50000//{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
//		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] info = {"java backend junior pizza 150","java backend junior pizza 150","java backend junior pizza 150","java backend junior pizza 170","java backend junior pizza 170","java backend junior pizza 170"};
		System.out.println(Arrays.toString(info));
//		String[] query = genQuery(20);//최대10만{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
//		//String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		String[] query = {"java and backend and junior and pizza 160"};
		System.out.println(Arrays.toString(query));
		
		RankSearch rankSearch = new RankSearch(info);
		int[] result = rankSearch.executeQuery(query);
		System.out.println(Arrays.toString(result));
		
		int[] answer = getResult(info, query);
		System.out.println(Arrays.toString(answer));
		
		System.out.println(Arrays.toString(result).equals(Arrays.toString(answer)));
	}
	
	private static String[] genQuery(int size) {
		Map<String, List<String>> category = RankSearch.genCategory();
		Random random = new Random();
		String[] query = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(random.nextInt(4) == 3 ? "-" : category.get("lang").get(random.nextInt(3)));
			sb.append(" and ");
			sb.append(random.nextInt(3) == 2 ? "-" : category.get("position").get(random.nextInt(2)));
			sb.append(" and ");
			sb.append(random.nextInt(3) == 2 ? "-" : category.get("career").get(random.nextInt(2)));
			sb.append(" and ");
			sb.append(random.nextInt(3) == 2 ? "-" : category.get("soulFood").get(random.nextInt(2)));
			sb.append(" ");
			sb.append(random.nextInt(99999) + 1);
			query[i] = sb.toString();
		}

		return query;
	}

	private static String[] genInfo(int size) {
		Map<String, List<String>> category = RankSearch.genCategory();
		Random random = new Random();
		String[] info = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(category.get("lang").get(random.nextInt(3)));
			sb.append(" ");
			sb.append(category.get("position").get(random.nextInt(2)));
			sb.append(" ");
			sb.append(category.get("career").get(random.nextInt(2)));
			sb.append(" ");
			sb.append(category.get("soulFood").get(random.nextInt(2)));
			sb.append(" ");
			sb.append(random.nextInt(99999) + 1);
			info[i] = sb.toString();
		}

		return info;
	}

	private static int[] getResult(String[] info, String[] query) {
		String[][] infoArray = genInfoArray(info);
		List<String[]> infoList = new ArrayList<>();
		for (String[] unit : infoArray)
			infoList.add(unit);
		
		String[][] queryArray = genQueryArray(query);
		int[] countArray = new int[query.length];
		for (int i = 0; i < queryArray.length; i++) {
			List<String[]> filteredList = countPassedTest(infoList, Integer.valueOf(queryArray[i][4]));
			for (int j = 0; j <= 3; j++) {
				if ("-".equals(queryArray[i][j]))
					continue;
				
				filteredList = countSatisfiedCondition(filteredList, queryArray[i][j], j);
			}
			
			countArray[i] = filteredList.size();
		}
		
		return countArray;
	}

	private static List<String[]> countSatisfiedCondition(List<String[]> infoList, String condition, int index) {
		List<String[]> filteredList = new ArrayList<>();
		for (String[] info : infoList) {
			if (condition.equals(info[index]))
				filteredList.add(info);
		}
		return filteredList;
	}

	private static List<String[]> countPassedTest(List<String[]> infoList, int baseScore) {
		List<String[]> filteredList = new ArrayList<>();
		for (String[] info : infoList) {
			if (Integer.valueOf(info[4]) >= baseScore)
				filteredList.add(info);
		}
		return filteredList;
	}

	private static String[][] genQueryArray(String[] query) {
		String[][] queryArray = new String[query.length][5];
		for (int i = 0; i < query.length; i++) {
			String[] aArray = new String[5];
			int j = 0;
			for (String atom : query[i].split(" ")) {
				if ("and".equals(atom))
					continue;
				
				aArray[j++] = atom;
			}
			queryArray[i] = aArray;
		}
		
		for (String[] aQuery : queryArray) {
			//System.out.println(Arrays.toString(aQuery));
		}
		
		return queryArray;
	}

	private static String[][] genInfoArray(String[] info) {
		String[][] infoArray = new String[info.length][5];
		for (int i = 0; i < info.length; i++) {
			infoArray[i] = info[i].split(" ");
		}
		
		for (String[] aMan : infoArray) {
			//System.out.println(Arrays.toString(aMan));
		}
		
		return infoArray;
	}


}