package exercise.all.lvl2.menuRenewal.parkkwangmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuRenewal {
	
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		
		String[] popularCourse = getPopularCourse(orders, course);
		System.out.println(Arrays.asList(popularCourse).stream().collect(Collectors.joining(",")));
	}

	private static String[] getPopularCourse(String[] orders, int[] course) {
		List<String> popularCourseList = new ArrayList<>();
		
		for (int menuCntInCourse : course) {
			Set<String> combinableCourse = getCombinableCourse(orders, menuCntInCourse);
			
			if (combinableCourse.isEmpty())
				 continue;
			
			Map<String, Integer> orderInfoPerCourse = getNumberOfCombinableCourseOrdered(orders, combinableCourse);
			
			//가장 많은 주문수를 구한다.
			int maxOrderCnt = orderInfoPerCourse.entrySet().stream().
								mapToInt(t -> t.getValue().intValue()).max().orElse(0);
			
			if (maxOrderCnt < 2)
				continue;
			
			//가장 많은 주문수의 코스 목록을 구한다.
			List<String> theMaxOrdedCourseList =  orderInfoPerCourse.entrySet().stream().filter(t -> t.getValue() == maxOrderCnt)
											.map(t -> t.getKey())
											.collect(Collectors.toList());
			
			popularCourseList.addAll(theMaxOrdedCourseList);
		}
		
		List<String> orderedCourseList = popularCourseList.stream().sorted().collect(Collectors.toList());
		
		return orderedCourseList.toArray(new String[0]);
	}

	private static Map<String, Integer> getNumberOfCombinableCourseOrdered(String[] orders, Set<String> combinableCourse) {
		Map<String, Integer> orderInfoPerCourse = new HashMap<>();
		//조합된 코스별로 주문한 수를 계산한다
		for (String theCourse : combinableCourse) {
			for (String order : orders) {
				boolean contains = true;
				for (int theSingleMenu : theCourse.chars().toArray()) {
					if (order.indexOf(theSingleMenu) == -1) {
						contains = false;
						break;
					}
				}
				if (contains)
					orderInfoPerCourse.put(theCourse, orderInfoPerCourse.getOrDefault(theCourse, 0) + 1);
			}
		}
		
		return orderInfoPerCourse;
	}

	private static Set<String> getCombinableCourse(String[] orders, int menuCntInCourse) {
		Set<String> combinableCourse = new HashSet<>();
		for (String order : orders) {
			//단품메뉴 수만큼 경우의수를 어떻게 만들 것인가? 어떻게 순회할 것인가?
			int orderMenuCnt = order.length();
			if (orderMenuCnt < menuCntInCourse) continue;
			
			int[] cursor = new int[menuCntInCourse];//오더를 순회할 기준이 되는, 현재 순회중인 오더의 인덱스 정보를 가짐
			for (int i = 0; i < menuCntInCourse; i++) {//첫 커서 생성
				cursor[i] = i;
			}
			
			addCombinableCourse(order, cursor, combinableCourse);//조합된 코스를 담는다(재귀)
		}
		
		System.out.println("singleMenuCnt : " + menuCntInCourse);
		System.out.println(combinableCourse.stream().sorted().collect(Collectors.joining(",")));
		System.out.println("=========");
		return combinableCourse;
	}

	//전달받은 커서 정보로 코스조합을 만들어 저장 후, 다음 커서를 설정하여 자기자신을 호출한다.
	private static void addCombinableCourse(String order, int[] cursor, Set<String> combinableCourse) {
		//커서의 마지막에 담긴 인덱스가 오더의 마지막 인덱스를 넘는 경우를 대비해 체크
		if (cursor[cursor.length - 1] > order.length() - 1) return;
		
		String courseByCursor = "";
		for (int index : cursor) {
			courseByCursor += String.valueOf(order.charAt(index));//커서정보로 코스조합 생성
		}
		//코스문자열을 알파벳순으로 정렬
		String orderedCourse = courseByCursor.chars().boxed().sorted().map(t -> String.valueOf(Character.toChars(t))).collect(Collectors.joining(""));
		combinableCourse.add(orderedCourse);//코스후보군 맵에 저장
		//다음 커서 지정
		boolean movable = false;
		int movableCursorIndex = 0;
		for (int cursorIndex = cursor.length - 1; cursorIndex >= 0; cursorIndex--) {
			int orderIndex = cursor[cursorIndex];
			int cursorLastIndex = cursor.length - 1;
			if (cursorIndex < cursorLastIndex) {//커서의 다음 인덱스가 존재하는지 체크, 존재하면 다음 인덱스가 제한조건이 된다.
				int orderIndexOfNextCursor = cursor[cursorIndex + 1];
				if (orderIndex + 1 >= orderIndexOfNextCursor)
					continue;
			} else {
				int orderLastIndex = order.length() - 1;
				if (orderIndex + 1 > orderLastIndex)
					continue;
			}
			
			movable = true;
			movableCursorIndex = cursorIndex;
			break;
		}
		
		if (!movable) return;
		
		for (int i = 0; i < cursor.length; i++) {
			if (i == movableCursorIndex)
				cursor[movableCursorIndex] += 1;
			
			if (i > movableCursorIndex)
				cursor[i] = cursor[i - 1] + 1;
		}
		
		addCombinableCourse(order, cursor, combinableCourse);
	}
	
}
