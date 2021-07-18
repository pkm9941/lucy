package exercise.all.lvl2.lifeboat.parkkwangmin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 순서가 있는 조합 -> 하나씩 실행
 * 두명씩 짝을 지을 수 있는 경우의 수 -> 100
 * 몸무게순으로 정렬 -> 가장 가벼운 사람부터 시작 -> 두 사람의 몸무게의 합이 100에 가장 가까운 사람을 택함
 * 
 * @author 박광민
 * @since 2021. 7. 19.
 */
public class Lifeboat {

	public static void main(String[] args) {
		Lifeboat lifeboat = new Lifeboat();
		//int[] people = {70, 50, 80, 50};
		int[] people = {70, 80, 50};
		int limit = 100;
		int answer = lifeboat.countBoat(people, limit);
		System.out.println(answer);
	}

	private int countBoat(int[] peopleArray, int limit) {
		
		if (peopleArray.length == 1)
			return 1;
		
		List<Integer> sortedPeople = Arrays.stream(peopleArray).boxed()
											.sorted().collect(Collectors.toCollection(LinkedList::new));
		
		int boats = 0;
		
		int lightestWeight = sortedPeople.remove(0);
		int heaviestWeight = sortedPeople.remove(sortedPeople.size() - 1);
		while(true) {
			if (lightestWeight + heaviestWeight > limit) {
				boats++;//뚱뚱한 사람 보트 태우기
				if (!sortedPeople.isEmpty()) {
					heaviestWeight = sortedPeople.remove(sortedPeople.size() - 1);
				} else {
					boats++;//더이상 짝이 없으므로 날씬한 사람 보트 태우기
					break;
				}
				
			} else {
				boats++;//둘을 보트에 태우기
				
				if (sortedPeople.size() >= 2) {
					lightestWeight = sortedPeople.remove(0);
					heaviestWeight = sortedPeople.remove(sortedPeople.size() - 1);
				} else {
					boats += sortedPeople.size();//짝 구성 인원이 없으므로 남은 인원(1명 or 0명) 보트에 태우기
					break;
				}
			}
		}
		
		return boats;
	}

}