package exercise.all.lvl2.telNoList.parkkwangmin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TelNoList {

	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		TelNoList telNoList = new TelNoList();
		boolean answer = telNoList.solution(phone_book);
		System.out.println(answer);
	}

	private boolean solution(String[] phone_book) {
		if (phone_book.length == 1) return true;
		//Supplier<List<String>> supplier = () -> new LinkedList<>();
		List<String> phoneBook = Arrays.stream(phone_book)
									.sorted((a, b) -> (a.length() > b.length() ? 1 : -1))
									.collect(Collectors.toList());
									//.collect(Collectors.toCollection(supplier));
		
		int size = phoneBook.size();
		for (int i = 0; i < size - 1; i++) {
			String telNo = phoneBook.get(i);
			for (int j = i + 1; j < size; j++) {
				if (phoneBook.get(j).contains(telNo))
					return false;
			}
		}
		
//		boolean answer = true;//어떤 번호가 다른 번호의 접두어인 경우가 없음
//		while(true) {
//			if (phoneBook.size() <= 1)
//				break;
//			
//			String telNo = phoneBook.remove(0);
//			if (phoneBook.contains(telNo)) {
//				answer = false;
//				break;
//			}
//		}
		
		return true;
	}

}