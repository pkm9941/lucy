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
		int m = 6;
		int n = 4;
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		
//		int m = 5, n = 5;
//		int[][] picture = {{1,2,3,4,5}
//						,  {1,2,3,4,5}
//						,  {1,2,6,4,6}
//						,  {6,2,6,4,6}
//						,  {6,6,6,6,6}
//						};
//		int m = 100; int n = 100;
//		int[][] picture = new int[100][100];
//		for (int i = 0; i < 100; i++) {
//			for (int j = 0; j < 100; j++) {
//				picture[i][j] = (int) Math.round(1 + Math.random()*0.1);
//			}
//		}
		
		ColoringBook coloringBook = new ColoringBook(m, n, picture);
		int[] answer = coloringBook.solution();
		System.out.println("numberOfArea : " + answer[0] + ", maxSizeOfOneArea : " + answer[1]);
	}

	public int[] solution() {
		
		while(true) {
			int[] startPosition = findStartPosition();
			if (startPosition[0] < 0)
				break;
			
			int color = picture[startPosition[0]][startPosition[1]];
			int size = getSizeOfConnectedOneColor2(startPosition, color);
			if (size == 0)
				break;
			System.out.println("color : " + color + ", size : " + size);
			numberOfArea += 1;
			if (maxSizeOfOneArea < size)
				maxSizeOfOneArea = size;
		}
		
		int[] answer = {numberOfArea, maxSizeOfOneArea};
		return answer;
	}

	private int getSizeOfConnectedOneColor2(int[] startPosition, int color) {
		int size = 0;
		Stack<int[]> positions = new Stack<int[]>();
		positions.push(startPosition);
		while (!positions.isEmpty()) {
			int[] position = positions.pop();
			if (position[0] < 0 || position[0] >= m || position[1] < 0 || position[1] >= n)
				continue;
			
			int thisColor = picture[position[0]][position[1]];
			if (thisColor != color)
				continue;
			
			size++;
			picture[position[0]][position[1]] = 0;
			
			positions.push(new int[]{position[0] - 1, position[1]});
			positions.push(new int[]{position[0] + 1, position[1]});
			positions.push(new int[]{position[0], position[1] - 1});
			positions.push(new int[]{position[0], position[1] + 1});
		}
		
		return size;
	}

	private int[] findStartPosition() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (picture[i][j] > 0)
					return new int[]{i, j};
			}
		}
		return new int[]{-1, -1};
	}

	private int getSizeOfConnectedOneColor(int[] position, int color) {
		if (position[0] < 0 || position[0] >= m || position[1] < 0 || position[1] >= n)
			return 0;
		
		int thisColor = picture[position[0]][position[1]];
		
		if (thisColor != color)
			return 0;
		
		int matchedColorCnt = 1;
		picture[position[0]][position[1]] = 0;
		
		matchedColorCnt += getSizeOfConnectedOneColor(new int[]{position[0] - 1, position[1]}, color)//위
						+  getSizeOfConnectedOneColor(new int[]{position[0] + 1, position[1]}, color)//아래
						+  getSizeOfConnectedOneColor(new int[]{position[0], position[1] - 1}, color)//왼쪽
						+  getSizeOfConnectedOneColor(new int[]{position[0], position[1] + 1}, color)//오른쪽
		;
		
		return matchedColorCnt;
	}

}