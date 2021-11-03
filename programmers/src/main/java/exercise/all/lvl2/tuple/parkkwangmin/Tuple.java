package exercise.all.lvl2.tuple.parkkwangmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//차집합을 어떻게 쉽게 찾아낼 수 있을까?
public class Tuple {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(getTuple("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
		System.out.println(Arrays.toString(getTuple("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
		System.out.println(Arrays.toString(getTuple("{{20,111},{111}}")));
		System.out.println(Arrays.toString(getTuple("{{123}}")));
		System.out.println(Arrays.toString(getTuple("{{4,2,3},{3},{2,3,4,1},{2,3}}")));
		System.out.println(Arrays.toString(getTuple("{1234}")));
		
		System.out.println(Arrays.toString(getTuple2("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
	}

	private static int[] getTuple2(String str) {
		List<List<String>> nestedSetList = convertStr(str);
		sortBySize(nestedSetList);
		
		int[] tuple = new int[nestedSetList.size()];
		tuple[0] = Integer.valueOf(nestedSetList.get(0).get(0));
		
		List<String> prevNestedSet = nestedSetList.get(0);
		for (int i = 1; i < nestedSetList.size(); i++) {
			tuple[i] = getNextTupleAtom(prevNestedSet, nestedSetList.get(i));
			prevNestedSet = nestedSetList.get(i);
		}
		
		return tuple;
	}

	private static List<List<String>> convertStr(String str) {
		String[] nestedSetStrArray = str.substring(2, str.length() - 2).split("[}][,][{]");
		
		List<List<String>> nestedSetList = new ArrayList<>();
		for (String nestedSetStr : nestedSetStrArray) {
			nestedSetList.add(Arrays.asList(nestedSetStr.split(",")));
		}
		return nestedSetList;
	}

	private static void sortBySize(List<List<String>> nestedSetList) {
		nestedSetList.sort((a, b) -> a.size() > b.size() ? 1 : -1);
	}

	private static int getNextTupleAtom(List<String> prevNestedSet, List<String> nextNestedSet) {
		List<String> diffList = new ArrayList<String>();
		diffList.addAll(nextNestedSet);
		diffList.removeAll(prevNestedSet);
		return Integer.valueOf(diffList.get(0));
	}

	private static int[] getTuple(String setStr) {
		String[] splitSetStr = setStr.substring(2, setStr.length() - 2).split("[}][,][{]");
		Arrays.sort(splitSetStr, (a, b) -> a.length() > b.length() ? 1 : -1);
		List<List<String>> setList = new ArrayList<>();
		for (String atomStr : splitSetStr) {
			setList.add(Arrays.asList(atomStr.split(",")));
		}
		List<String> leftArr = setList.get(0);
		int[] tuple = new int[setList.size()];
		tuple[0] = Integer.valueOf(leftArr.get(0));
		for (int i = 1; i < setList.size(); i++) {
			for (String atom : setList.get(i)) {
				if (!leftArr.contains(atom)) {
					tuple[i] = Integer.valueOf(atom);
					break;
				}
			}
			
			leftArr = setList.get(i);
		}
		
		return tuple;
	}

}