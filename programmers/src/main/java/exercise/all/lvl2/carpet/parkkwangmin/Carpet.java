package exercise.all.lvl2.carpet.parkkwangmin;

public class Carpet {
	
	public static void main(String[] args) {
		int brown = 10;
		int yellow = 2;
		
		//int[] result = getSquareSize(brown, yellow);
		int[] result = getSquareSize2(brown, yellow);
		System.out.println(result[0] + "," + result[1]);
	}
	
	// x * y = brown + yellow
	// 2x + 2y - 4 = brown
	// x와 y는 정수
	private static int[] getSquareSize2(int brown, int yellow) {
		int area = brown + yellow;
		int maxLength = new Double(Math.sqrt(area)).intValue();
		for (int x = 3; x <= maxLength; x++) {
			if (area % x > 0)
				continue;
			
			int y = area / x;
			if (2*x + 2*y -4 == brown) {
				return new int[] {Math.max(x,  y), Math.min(x,  y)};
			}
		}
		return null;
	}

	// brown = 2x + 2y - 4; yellow = xy - brown;
	// 2x^2 - (a+4) * x + 2 * brown + 2 * yellow = 0 
	private static int[] getSquareSize(int brown, int yellow) {
		int x = new Double(Math.sqrt(brown * brown / (double)16 - brown/(double)2 - yellow + 1) + (brown + 4) / (double)4).intValue();
		System.out.println(x);
		int area = brown + yellow;
		int y = area / x;
		return new int[]{x, y};
	}
}