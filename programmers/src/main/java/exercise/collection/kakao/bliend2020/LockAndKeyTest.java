package exercise.collection.kakao.bliend2020;

public class LockAndKeyTest {

	public static void main(String[] args) {
		int[][] key = {{0, 0, 0}, {1, 1, 1}, {1, 1, 1}};
		int[][] lock = {{1, 0, 1}, {1, 0, 1}, {1, 0, 1}};
		//boolean result = canUnlock(key, lock);
		//System.out.println('a' == "abcdefg".charAt(0)); 
		//System.out.println("a" == new ReturnA().returnA());
		char c = '\u0000';
		System.out.println(c);
		//System.out.println("abcdefg".charAt(0));
	}

	private static boolean canUnlock(int[][] key, int[][] lock) {
		int[][] rotatedKey = key;
		for (int rotateCnt = 0; rotateCnt < 4; rotateCnt++) {
			if (canUnlockToRotatedKey(rotatedKey, lock))
				return true;
			
			rotatedKey = rotateTo90(rotatedKey);
		}
		return false;
	}

	private static boolean canUnlockToRotatedKey(int[][] rotatedKey, int[][] lock) {
		int maxLeftTopPositionToMoveKey = -(rotatedKey.length - 1);
		int maxRightBottomPositionToMoveKey = lock.length + (rotatedKey.length - 1);
		
		for (int horizonStartKeyPosition = maxLeftTopPositionToMoveKey; horizonStartKeyPosition < maxRightBottomPositionToMoveKey; horizonStartKeyPosition++) {
			for (int verticalStartKeyPosition = maxLeftTopPositionToMoveKey; verticalStartKeyPosition < maxRightBottomPositionToMoveKey; verticalStartKeyPosition++) {
				
				int[][] movedAndExpandedKey = movePositionAndExpandToLockSize(rotatedKey, horizonStartKeyPosition, verticalStartKeyPosition, lock.length);
				int[][] mergedArray = putTheKeyInTheLock(lock, movedAndExpandedKey);
				if (isUnlocked(mergedArray)) {
					return true;
				}
			}
		}
		return false;
	}

	private static int[][] movePositionAndExpandToLockSize(int[][] array, int horizonPositionToMove, int verticalPositionToMove, int groundSize) {
		int[][] movedAndExpandedArray = new int[groundSize][groundSize];
		for (int i = 0; i < groundSize; i++) {
			for (int j = 0; j < groundSize; j++) {
				int horizonCellIndex = i - horizonPositionToMove;
				int verticalCellIndex = j - verticalPositionToMove;
				if (locaatedInArray(array, horizonCellIndex, verticalCellIndex)) {
					movedAndExpandedArray[i][j] = array[horizonCellIndex][verticalCellIndex];
				} else {
					movedAndExpandedArray[i][j] = 0;//배열 영역 바깥이므로 홈으로 세팅
				}
			}
		}
		return movedAndExpandedArray;
	}

	private static boolean locaatedInArray(int[][] array, int horizonCellIndex, int verticalCellIndex) {
		if (horizonCellIndex < 0 || horizonCellIndex > array.length -1)
			return false;
		
		if (verticalCellIndex < 0 || verticalCellIndex > array.length -1)
			return false;
		
		return true;
	}

	private static int[][] putTheKeyInTheLock(int[][] lock, int[][] key) {
		int[][] mergedKey = new int[lock.length][lock.length];
		for (int i = 0; i < mergedKey.length; i++) {
			for (int j = 0; j < mergedKey.length; j++) {
				mergedKey[i][j] = lock[i][j] + key[i][j];
			}
		}
		return mergedKey;
	}

	private static boolean isUnlocked(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] != 1)
					return false;
			}
		}
		return true;
	}

	private static int[][] rotateTo90(int[][] array) {
		int[][] rotatedArray = new int[array.length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				rotatedArray[i][j] = array[(array.length - 1) - j][i];
			}
		}
		return rotatedArray;
	}

}
