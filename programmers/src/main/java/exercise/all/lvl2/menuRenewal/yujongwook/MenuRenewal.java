package exercise.all.lvl2.menuRenewal.yujongwook;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 *     	orders 													course 			result
 * 		["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"] 		[2,3,4] 		["AC", "ACDE", "BCFG", "CDE"]
 * 		["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"] 		[2,3,5] 		["ACD", "AD", "ADE", "CD", "XYZ"]
 * 		["XYZ", "XWY", "WXA"] 									[2,3,4] 		["WX", "XY"]
 * </pre>
 *
 * @package : exercise.all.lvl2.menuRenewal.yujongwook
 * @name : MenuRenewal.java
 * @date : 2021-04-05
 * @author : jw.yu
 * @description :
 */
public class MenuRenewal {

	public static void main(String[] args) {

		MenuRenewal mr = new MenuRenewal();
		Data pData = mr.prepareData(1);
		//System.out.println(Arrays.toString(pData.orders));
		//System.out.println(Arrays.toString(pData.course));

		String[] result = mr.solution(pData.orders, pData.course);
		System.out.println(Arrays.toString(result));
	}

	class Data{
		String[] orders;
		int[] course;
	}

	public Data prepareData (int caseNum){
		Data data = new Data();
		switch (caseNum) {
			case 1:
				data.orders = new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
				data.course = new int[] {2,3,4};
				//data.course = new int[] {2};
				break;
			case 2:
				data.orders = new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
				data.course = new int[] {2,3,5};
				break;
			case 3:
				data.orders = new String[] {"XYZ", "XWY", "WXA"};
				data.course = new int[] {2,3,4};
				break;
		}
		return data;
	}

	public String[] generateStr(String order, int course){

		String[] result;
		int combinationCount = (order.length() - course) + 1;

		System.out.println("===========================");
		System.out.println("# order : " + order);
		System.out.println("# course : " + course);
		System.out.println("# combinationCount : " + combinationCount);
		System.out.println("===========================");

		if (combinationCount > 0){

			result = new String[combinationCount];
			int beginIdx = 0;

			for (int i = 0; i < combinationCount; i++) {

				int endIdx = beginIdx + course;

				if (endIdx < order.length() + 1){
					System.out.println("# order : " + order + " / beginIdx : " + beginIdx + " / endIdx : " + endIdx);
					result[i] = order.substring(beginIdx, endIdx);
					System.out.println(result[i]);
					beginIdx = beginIdx + 1;
				}
			}
		} else {
			result = new String[0];
		}
		return result;
	}

	public String[] solution(String[] orders, int[] course) {

		System.out.println("===========================");
		System.out.println(Arrays.toString(orders));
		System.out.println(Arrays.toString(course));
		System.out.println("===========================");

		MenuRenewal mr = new MenuRenewal();

		Map<String, Integer> analysisMap = new HashMap<String, Integer>();

		for (int i = 0; i < orders.length; i++) {
			for (int j = 0; j < course.length; j++) {
				String[] comb = mr.generateStr(orders[i],course[j]);
				if (comb.length != 0){
					for (int z = 0; z < comb.length; z++){
						if (!analysisMap.containsKey(comb[z])){
							analysisMap.put(comb[z], 1);
						} else {
							analysisMap.put(comb[z], analysisMap.get(comb[z]) + 1);
						}
					}
				}
			}
		}

		System.out.println("##############################");
		System.out.println(analysisMap.toString());
		System.out.println("##############################");

		List<String> analysisList = new ArrayList<>();
		analysisList = analysisMap.entrySet().stream().filter(v -> v.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
		String[] answer = analysisList.toArray(new String[analysisList.size()]);

		return answer;
	}


}
