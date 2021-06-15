package exercise.all.lvl2.hIndex.parkkwangmin;

import java.util.Arrays;
import java.util.Collections;

public class HIndex {
	
	private int[] originCitations;
	private Integer[] citations;
	
	public HIndex(int[] citations) {
		this.originCitations = citations;
		this.citations = Arrays.stream(originCitations).boxed().sorted((a, b) -> a > b ? -1 : 1).toArray(Integer[]::new);
		Arrays.sort(citations);
	}

	public static void main(String[] args) {
	}

	public int getHIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected Integer getHIndexOfAPaper(int citation) {
		if (citation > citations.length) return 0;
		return 1;
	}

	public Integer[] getcitations() {
		return citations;
	}

}