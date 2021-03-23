package exercise.all.lvl1.craneGame.parkkwangmin;

import java.util.Stack;

public class CraneGame {
	
	private int[][] board = null;
	private int boardDepth = 0;
	Stack<Integer> dollsOfBasket = new Stack<>();//가장 나중에 담긴 인형과 일치 여부를 비교해야 하므로 stack 선택
	private int explodedDollCnt = 0;
	
	public CraneGame(int[][] board) {
		this.board = board;
		this.boardDepth = board[0].length;
	}

	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		CraneGame game = new CraneGame(board);
		
		int[] moves = {1,5,3,5,1,2,1,4};
		game.playCraneGame(moves);
		System.out.println("explodedDollCnt : " + game.getExplodedDollCnt());
	}

	public void playCraneGame(int[] moves) {
		explodedDollCnt = 0;
		
		//moves 만큼 반복해서 크레인 동작
		for (int cranePosition : moves) {
			dropCrane(cranePosition);
		}
	}
	
	private void dropCrane(int cranePosition) {
		//크레인 위치 잡고 아래로 하강
		for (int craneDepth = 1; craneDepth <= boardDepth; craneDepth++) {
			if (board[craneDepth - 1][cranePosition - 1] == 0) continue; //인형 없으면 한 칸 더 아래로 이동
			
			moveDollToBasket(craneDepth, cranePosition);
			
			if (isSameDollsInTopOfBasket())
				explodeTowDollInTopOfBasket();
			
			break;
		}
	}

	public int getExplodedDollCnt() {
		return this.explodedDollCnt;
	}

	private void explodeTowDollInTopOfBasket() {
		dollsOfBasket.pop();
		explodedDollCnt++;
		
		dollsOfBasket.pop();
		explodedDollCnt++;
	}

	private boolean isSameDollsInTopOfBasket() {
		if (dollsOfBasket.size() < 2) return false;
		
		int topDoll = dollsOfBasket.get(dollsOfBasket.size() - 1);
		int nextDoll = dollsOfBasket.get(dollsOfBasket.size() - 2);
		
		if (topDoll == nextDoll) return true;
		
		return false;
	}

	private void moveDollToBasket(int craneDepth, int cranePosition) {
		int doll = board[craneDepth - 1][cranePosition - 1];
		board[craneDepth - 1][cranePosition - 1] = 0;
		dollsOfBasket.push(doll);
	}
}
