package exercise.all.lvl2.telNoList.parkkwangmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TelNoList {

	public static void main(String[] args) {
		String[] phone_book = {"97674223"};//, "1195524421", "119"};
		phone_book = new String[1000000];
		for (int i = 0; i < 1000000; i++) {
			if (i < 500000) phone_book[i] = String.valueOf(10000000 + i);
			else {
				phone_book[i] = String.valueOf(120000000 + (i - 499999) * 3);
			}
		}
		
		TelNoList telNoList = new TelNoList();
		//boolean answer = telNoList.sotredList(phone_book);
		//boolean answer = telNoList.toMap(phone_book);
		//boolean answer = telNoList.toNestedMap(phone_book);
		//boolean answer = telNoList.toListIndexOf(phone_book);
		boolean answer = telNoList.toSet(phone_book);
		System.out.println(answer);
	}


	private boolean toSet(String[] phone_book) {
		Map<Integer, List<String>> phoneBook = 
				Arrays.stream(phone_book)
				.collect(Collectors.groupingBy(str -> str.length(), Collectors.toList()));
		
		List<Integer> lengths = new ArrayList<Integer>(phoneBook.keySet());
		//if (lengths.size() <= 1) return true;
		
		//lengths.remove(Collections.max(lengths));
		Map<Integer, Set<String>> prefixs = new HashMap<>();
		for (int length : lengths) {
			prefixs.put(length, new HashSet<String>());
		}
		for (String telNo : phone_book) {
			int telNoLength = telNo.length();
			for (int length : lengths) {
				if (telNoLength >= length) {
					if (!prefixs.get(length).add(telNo.substring(0, length))) {
						return false;
					}
				}
			}
		}
		
		return true;
	}


	private boolean toListIndexOf(String[] phone_book) {
		Map<Integer, List<String>> phoneBook = 
				Arrays.stream(phone_book)
				.collect(Collectors.groupingBy(str -> str.length(), Collectors.toList()));
		
		List<Integer> lengths = new ArrayList<Integer>(phoneBook.keySet());
		//if (lengths.size() <= 1) return true;
		
		//lengths.remove(Collections.max(lengths));
		Set<String> prefixs = new HashSet<>();
		for (String telNo : phone_book) {
			int telNoLength = telNo.length();
			for (int length : lengths) {
				if (telNoLength >= length) {
					if (!prefixs.add(telNo.substring(0, length))) {
						return false;
					}
				}
			}
		}
		
		return true;
	}

	private boolean toNestedMap(String[] phone_book) {
		Map<Integer, Map<Integer, List<String>>> classifiedMap = new HashMap<>();
		for (String telNo : phone_book) {
			Integer telNoLength = telNo.length();
			Integer firstNum = Integer.valueOf(telNo.substring(0, 1));
			if (!classifiedMap.containsKey(telNoLength)) {
				classifiedMap.put(telNoLength, new HashMap<Integer, List<String>>());
			}
			
			Map<Integer, List<String>> theLengthMap = classifiedMap.get(telNoLength);
			
			if (!theLengthMap.containsKey(firstNum)) {
				theLengthMap.put(firstNum, new ArrayList<String>());
			}
			
			theLengthMap.get(firstNum).add(telNo);
		}
		
		if (classifiedMap.size() <= 1) return true;
		
		List<Integer> lengths = new ArrayList<Integer>(classifiedMap.keySet());
		int cnt = 0;
		for (int i = 0; i < lengths.size() - 1; i++) {
			Map<Integer, List<String>> prefixLengthMap = classifiedMap.get(lengths.get(i));
			for (Integer prefixFirstNum : prefixLengthMap.keySet()) {
				List<String> prefixList = prefixLengthMap.get(prefixFirstNum);
				for (String prefix : prefixList) {
					for (int j = i + 1; j < lengths.size(); j++) {
						Map<Integer, List<String>> lengthMap = classifiedMap.get(lengths.get(j));
						List<String> telNoList = lengthMap.get(prefixFirstNum);
						
						if (telNoList == null) continue;
						System.out.println("prefixLength : " + lengths.get(i) +  ", prefix : " + prefix + ", length : " + lengths.get(j) + ", prefixFirstNum : " + prefixFirstNum);
						
						for (String telNo : telNoList) {
							if (telNo.indexOf(prefix) == 0) return false;
						}
					}
				}
			}
		}
		
		return true;
	}

	private boolean toMap(String[] phone_book) {
		Map<Integer, List<String>> phoneBook = 
				Arrays.stream(phone_book)
				.collect(Collectors.groupingBy(str -> str.length(), Collectors.toList()));
		
		if (phoneBook.size() <= 1) return true;

		List<Integer> lengths = new ArrayList<Integer>(phoneBook.keySet());
		for (int i = 0; i < lengths.size() - 1; i++) {
			for (String prefix : phoneBook.get(lengths.get(i))) {
				for (int j = i + 1; j < lengths.size(); j++) {
					List<String> telNos = phoneBook.get(lengths.get(j));
					for (String telNo : telNos) {
						if (telNo.indexOf(prefix) == 0) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}

//	private boolean sotredList(String[] phone_book) {
//		if (phone_book.length == 1) return true;
//		
//		List<String> phoneBook = Arrays.stream(phone_book)
//									//.sorted((a, b) -> (a.length() > b.length() ? 1 : -1))
//									.collect(Collectors.toList());
//		
//		phoneBook.sort((a, b) -> (a.length() > b.length() ? 1 : -1));
//		int size = phoneBook.size();
//		for (int i = 0; i < size - 1; i++) {
//			String telNo = phoneBook.get(i);
//			for (int j = i + 1; j < size; j++) {
//				if (phoneBook.get(j).indexOf(telNo) == 0)
//					return false;
//			}
//		}
//		
//		return true;
//	}

}