package exercise.all.lvl2.menuRenewal.parkkwangmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuRenewal {
	
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		
		String[] favorateCourse = getFavorateCourse(orders, course);
	}

	private static String[] getFavorateCourse(String[] orders, int[] course) {
		List<String> courseList = new ArrayList<>();
		
		for (int singleMenuCnt : course) {
			Map<String, Integer> availableCourse = new HashMap<>();
			for (String order : orders) {
				//단품메뉴 수만큼 경우의수를 어떻게 만들 것인가? 어떻게 순회할 것인가?
				int orderMenuCnt = order.length();
				if (orderMenuCnt < singleMenuCnt) continue;
				
				int[] cursor = new int[singleMenuCnt];//오더를 순회할 기준이 되는, 현재 순회중인 오더의 인덱스 정보를 가짐
				for (int i = 0; i < singleMenuCnt; i++) {//첫 커서 생성
					cursor[i] = i;
				}
				
				addAvailableCourse(order, cursor, availableCourse);//조합된 코스를 담는다(재귀)
			}
			
			System.out.println("singleMenuCnt : " + singleMenuCnt);
			System.out.println(availableCourse.keySet().stream().sorted().collect(Collectors.joining(",")));
			System.out.println("=========");
			
			if (availableCourse.isEmpty())
				 continue;
			
			//조합된 코스별로 주문한 수를 계산한다
			for (String theCourse : availableCourse.keySet()) {
				for (String order : orders) {
					boolean contains = true;
					for (int theSingleMenu : theCourse.chars().toArray()) {
						if (order.indexOf(theSingleMenu) == -1) {
							contains = false;
							break;
						}
					}
					if (contains)
						availableCourse.put(theCourse, availableCourse.get(theCourse) + 1);
					
				}
			}
			
			//가장 많은 주문수를 구한다.
			int maxOrderCnt = availableCourse.entrySet().stream().
								mapToInt(t -> t.getValue().intValue()).max().orElse(0);
			
			if (maxOrderCnt < 2)
				continue;
			
			//가장 많은 주문수의 코스 목록을 구한다.
			List<String> theMaxOrdedCourseList =  availableCourse.entrySet().stream().filter(t -> t.getValue() == maxOrderCnt)
											.map(t -> t.getKey())
//											.sorted()
											.collect(Collectors.toList());
			
			courseList.addAll(theMaxOrdedCourseList);
		}
		
		List<String> orderedCourseList = courseList.stream().sorted().collect(Collectors.toList());
		System.out.println(orderedCourseList.stream().collect(Collectors.joining(",")));
		
		return orderedCourseList.toArray(new String[0]);
	}

	//전달받은 커서 정보로 코스조합을 만들어 저장 후, 다음 커서를 설정하여 자기자신을 호출한다.
	private static void addAvailableCourse(String order, int[] cursor, Map<String, Integer> availableCourse) {
		//커서의 마지막에 담긴 인덱스가 오더의 마지막 인덱스를 넘는 경우를 대비해 체크
		if (cursor[cursor.length - 1] > order.length() - 1) return;
		
		String courseByCursor = "";
		for (int index : cursor) {
			courseByCursor += String.valueOf(order.charAt(index));//커서정보로 코스조합 생성
		}
		//코스문자열을 알파벳순으로 정렬
		String orderedCourse = courseByCursor.chars().boxed().sorted().map(t -> String.valueOf(Character.toChars(t))).collect(Collectors.joining(""));
		availableCourse.put(orderedCourse, 0);//코스후보군 맵에 저장
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
		
		addAvailableCourse(order, cursor, availableCourse);
	}
	
}
