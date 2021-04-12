package exercise.all.lvl2.square.parkkwangmin;

import java.math.BigInteger;

public class Square {
	
	public static void main(String[] args) {
		int w = 100000000;
		int h = 99999999;
//		long result = solution(w, h);
		long result = solution2(w, h);
		System.out.println(result);
	}

	private static long solution(int w, int h) {
		long foldedSquareCnt = 0; 
		for (int theHeight = 1; theHeight <= h; theHeight++) {
			Double widthOfTheHeight = ((long)w * (long)theHeight) / (double)h;
			
			long longWidth = widthOfTheHeight.longValue();
			
			if ((double)longWidth == widthOfTheHeight)
				continue;
			
			foldedSquareCnt++;
		}
		System.out.println(foldedSquareCnt);
		for (int theWidth = 1; theWidth <= w; theWidth++)
			foldedSquareCnt++;
		
		System.out.println(foldedSquareCnt);
		return (long)w * (long)h - (long)foldedSquareCnt;
	}
	
	private static long solution2(int w, int h) {
		if (w == h) return (long)w;
		
		long shortAxis = (long)0;
		long longAxis = (long)0;
		if (w > h) {
			shortAxis = (long)h;
			longAxis = (long)w;
		} else {
			shortAxis = (long)w;
			longAxis = (long)h;
		}
		int commonCnt = 0;
		for (int i = 1; i <= shortAxis; i++) {
//			if ((longAxis * (long)i) % shortAxis == 0)
//				commonCnt++;
			
			if (BigInteger.valueOf(longAxis).multiply(BigInteger.valueOf((long)i))
										.remainder(BigInteger.valueOf(shortAxis))
										.longValue() == 0) {
				commonCnt++;
			}
		}
		//return (long)w * (long)h - ((long)w + (long)h - (long)commonCnt);
		return BigInteger.valueOf(w).multiply(BigInteger.valueOf(h))
									.subtract(BigInteger.valueOf((long)w + (long)h - (long)commonCnt))
									.longValue();
	}
	
}
