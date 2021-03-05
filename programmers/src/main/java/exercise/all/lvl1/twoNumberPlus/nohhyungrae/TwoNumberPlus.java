package exercise.all.lvl1.twoNumberPlus.nohhyungrae;

import java.util.ArrayList;
import java.util.List;

public class TwoNumberPlus {
	public static void main(String[] args) {
		int[] numbers = {2,1,3,4,1};
		System.out.println("result : "+solution(numbers));
	}

	/* 
		Collection 
		 - 컬렉션의 최고 조상인 Collection에 stream()이 정의되어 있어서 Collection의 자손인 List와 Set을 구현한 컬렉션 클래스들은 모두 stream()으로 스트림을 생성할 수 있다.
		 
	 	Stream
	 	 - 스트림은 데이터 소스로 부터 데이터를 읽기만 할 뿐, 변경하지 않는다.
		 - 스트림은 한번 사용하면 닫혀서 다시 사용할 수 없다.
		 - 스트림은 작업을 내부 반복으로 처리한다.
	 */
	public static int[] solution(int[] numbers) {
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i= 0 ; i <numbers.length-1; i++) {
            for(int j=i+1; j<numbers.length; j++){
            	if(!list.contains(numbers[i] + numbers[j]))
            		list.add(numbers[i] + numbers[j]); 
            }
		}
		
		list.stream().sorted().forEach(System.out::println);
        return list.stream().sorted().mapToInt(i->i).toArray();
	}
}
