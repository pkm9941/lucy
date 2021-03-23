package exercise.all.lvl1.craneGame.parkkwangmin;

import java.util.Stack;

public class CraneGame {
	
	private int[][] board = null;
	private int boardDepth = 0;
	
	public CraneGame(int[][] board) {
		this.board = board;
		this.boardDepth = board[0].length;
	}

	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		CraneGame game = new CraneGame(board);
		
		int[] moves = {1,5,3,5,1,2,1,4};
		int explodedDollCnt = game.playCraneGame(moves);
		System.out.println("explodedDollCnt : " + explodedDollCnt);
	}

	public int playCraneGame(int[] moves) {
		Stack<Integer> dollsOfBasket = new Stack<>();//가장 나중에 담긴 인형과 일치 여부를 비교해야 하므로 stack 선택
		int explodedDollCnt = 0;
		
		//moves 만큼 반복해서 크레인 동작
		for (int cranePosition : moves) {
			//크레인 위치 잡고 아래로 내려가면서 최초로 잡히는 인형 찾기
			for (int craneDepth = 1; craneDepth <= boardDepth; craneDepth++) {
				if (board[craneDepth - 1][cranePosition - 1] == 0) continue; //인형 없으면 한 칸 더 깊히 이동
				
				int raisedDoll = board[craneDepth - 1][cranePosition - 1];
				
				if (dollsOfBasket.isEmpty()) {
					dollsOfBasket.push(raisedDoll);
					board[craneDepth - 1][cranePosition - 1] = 0;
					break;
				}
				
				int poppedDoll = dollsOfBasket.pop();
				if (raisedDoll == poppedDoll) {
					explodedDollCnt += 2;
				} else {
					dollsOfBasket.push(poppedDoll);
					dollsOfBasket.push(raisedDoll);
				}
				board[craneDepth - 1][cranePosition - 1] = 0;
				break;
			}
		}
		
		return explodedDollCnt;
	}
}
