package exercise.all.lvl2.menuRenewal.yujongwook;

import javax.xml.crypto.Data;
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

	static Map<String, Integer> analysisMap = new HashMap<String, Integer>();

	public static void main(String[] args) {

		MenuRenewal mr = new MenuRenewal();
		Data pData = mr.prepareData(1);
		//System.out.println(Arrays.toString(pData.orders));
		//System.out.println(Arrays.toString(pData.course));

		String[] rslt = mr.solution(pData.orders, pData.course);
		System.out.println(Arrays.toString(rslt));

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

	public String[] solution(String[] orders, int[] course) {

		System.out.println("===========================");
		System.out.println(Arrays.toString(orders));
		System.out.println(Arrays.toString(course));
		System.out.println("===========================");

		MenuRenewal mr = new MenuRenewal();


		for (int i = 0; i < orders.length; i++) {
			for (int j = 0; j < course.length; j++) {
				System.out.println("## | orders: " + orders[i] + " | course: " + course[j]);
				mr.reculsion(Arrays.asList(orders[i].split("")), new ArrayList<String>(), 0, orders[i].length(), course[j]);
			}
			System.out.println();
		}

		System.out.println("-------------------------------------------------------");
		analysisMap.forEach((k,v) -> System.out.println("key: "+k+" value:"+v));
		System.out.println("-------------------------------------------------------");

		List<String> analysisList = analysisMap.entrySet().stream().filter(v -> v.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());

		//선별된 대상 중 타 후보군에서 내포하고 있으면 제외










		String[] answer = analysisList.toArray(new String[analysisList.size()]);

		return answer;
	}

	/**
	 * 조합 구하기
	 * @param arr    : 기준 리스트
	 * @param index  : 반복문 시작 인덱스
	 * @param n      : 전체 갯수
	 * @param r      : 뽑을 갯수
	 */
	public void reculsion(List<String> arr, List<String> result, int index, int n, int r) {

		if (r == 0) {

			System.out.println(result.toString());

			String comb = result.stream().sorted().map(String::valueOf).collect(Collectors.joining());
			if (!analysisMap.containsKey(comb)){
				analysisMap.put(comb, 1);
			} else {
				analysisMap.put(comb, analysisMap.get(comb) + 1);
			}

			return;
		}

		for (int i = index; i < n; i++) {

			result.add(arr.get(i));
			reculsion(arr, result,i + 1, n, r - 1);
			result.remove(result.size() - 1);
		}
	}

	/**
	 * 조합수 (not use)
	 *
	 * @param n
	 * @param r
	 * @return
	 */
	int combi(int n, int r){
		if(r==0 || r==n)
			return 1;
		else
			return combi(n-1, r-1) + combi(n-1, r);
	}

}
