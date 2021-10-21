package exercise.all.lvl2.coloringBook.parkkwangmin;

import java.util.Stack;

public class ColoringBook {
	
	private int numberOfArea = 0;
	private int maxSizeOfOneArea = 0;
	private int m, n = 0;
	private int[][] picture = null;
	
	public ColoringBook(int m, int n, int[][] picture) {
		this.picture = deepCopyPicture(picture);
		this.m = m;
		this.n = n;
	}

	private int[][] deepCopyPicture(int[][] picture) {
		int[][] copyed = new int[picture.length][picture[0].length];
		for (int i = 0; i < picture.length; i++) {
			for (int j = 0; j < picture[0].length; j++) {
				copyed[i][j] = picture[i][j];
			}
		}
		
		return copyed;
	}

	public static void main(String[] args) {
		//sample 1
//		int m = 6;
//		int n = 4;
//		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		//sample 2
//		int m = 5, n = 5;
//		int[][] picture = {{1,2,3,4,5}
//						,  {1,2,3,4,5}
//						,  {1,2,6,4,6}
//						,  {6,2,6,4,6}
//						,  {6,6,6,6,6}
//						};
		//sample 3 - 최대 크기
		int m = 100; int n = 100;
		int[][] picture = new int[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				picture[i][j] = (int) Math.round(1 + Math.random()*1);
			}
		}
		
		ColoringBook coloringBook = new ColoringBook(m, n, picture);
		int[] answer = coloringBook.solution();//스택
		System.out.println("스택 - numberOfArea : " + answer[0] + ", maxSizeOfOneArea : " + answer[1]);
		ColoringBook coloringBook2 = new ColoringBook(m, n, picture);
		int[] answer2 = coloringBook2.solution2();//재귀
		System.out.println("재귀 - numberOfArea : " + answer2[0] + ", maxSizeOfOneArea : " + answer2[1]);
	}

	public int[] solution() {
		
		while(true) {
			int[] startBlock = findStartBlock();
			if (startBlock[0] < 0)
				break;
			
			int color = picture[startBlock[0]][startBlock[1]];
			int size = getSizeOfConnectedSectionByStack(startBlock, color);
			if (size == 0)
				break;
			
			numberOfArea += 1;
			if (size > maxSizeOfOneArea)
				maxSizeOfOneArea = size;
		}
		
		int[] answer = {numberOfArea, maxSizeOfOneArea};
		return answer;
	}
	
	public int[] solution2() {
		
		while(true) {
			int[] startBlock = findStartBlock();
			if (startBlock[0] < 0)
				break;
			
			int color = picture[startBlock[0]][startBlock[1]];
			int size = getSizeOfConnectedSectionByRecursive(startBlock, color);
			if (size == 0)
				break;
			
			numberOfArea += 1;
			if (size > maxSizeOfOneArea)
				maxSizeOfOneArea = size;
		}
		
		int[] answer = {numberOfArea, maxSizeOfOneArea};
		return answer;
	}

	private int[] findStartBlock() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (picture[i][j] > 0)
					return new int[]{i, j};
			}
		}
		return new int[]{-1, -1};
	}

	private int getSizeOfConnectedSectionByStack(int[] startBlock, int color) {
		int sizeOfConnectedSection = 0;
		Stack<int[]> blocksToSearch = new Stack<int[]>();
		blocksToSearch.push(startBlock);
		
		while (!blocksToSearch.isEmpty()) {
			int[] block = blocksToSearch.pop();
			if (isOutsideOfPicture(block))
				continue;
			
			if (!isSameColor(block, color))
				continue;
			
			sizeOfConnectedSection++;
			removeColor(block);
			
			pushSideBlocksOf(block, blocksToSearch);
		}
		
		return sizeOfConnectedSection;
	}

	private boolean isOutsideOfPicture(int[] block) {
		return block[0] < 0 || block[0] >= m || block[1] < 0 || block[1] >= n;
	}

	private boolean isSameColor(int[] block, int color) {
		return color == picture[block[0]][block[1]];
	}

	private void removeColor(int[] block) {
		picture[block[0]][block[1]] = 0;
	}

	private void pushSideBlocksOf(int[] block, Stack<int[]> blocksToSearch) {
		blocksToSearch.push(new int[]{block[0] - 1, block[1]});
		blocksToSearch.push(new int[]{block[0] + 1, block[1]});
		blocksToSearch.push(new int[]{block[0], block[1] - 1});
		blocksToSearch.push(new int[]{block[0], block[1] + 1});
	}

	private int getSizeOfConnectedSectionByRecursive(int[] block, int color) {
		if (isOutsideOfPicture(block))
			return 0;
		
		if (!isSameColor(block, color))
			return 0;
		
		removeColor(block);
		
		int sizeOfConnectedSection = 1 + getSideBlocks(block, color);
		
		return sizeOfConnectedSection;
	}

	private int getSideBlocks(int[] block, int color) {
		return getSizeOfConnectedSectionByRecursive(new int[]{block[0] - 1, block[1]}, color)//위
			 +  getSizeOfConnectedSectionByRecursive(new int[]{block[0] + 1, block[1]}, color)//아래
			 +  getSizeOfConnectedSectionByRecursive(new int[]{block[0], block[1] - 1}, color)//왼쪽
			 +  getSizeOfConnectedSectionByRecursive(new int[]{block[0], block[1] + 1}, color)//오른쪽
		;
	}

}