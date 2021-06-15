package exercise.all.lvl2.hIndex.parkkwangmin;

public class HIndex {
	
	private int[] citations;
	
	public HIndex(int[] citations) {
		this.citations = citations;
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

}