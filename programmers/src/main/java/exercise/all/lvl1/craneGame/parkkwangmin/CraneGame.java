package exercise.all.lvl1.craneGame.parkkwangmin;

import java.util.Stack;

/**
 * 2차원 상에서 크레인으로 인형을 뽑는 게임
 * 뽑은 인형을 바구니에 순차적으로 쌓는다.
 * 같은 인형이 연속하여 위치하는 경우 두 인형은 사라진다.
 * 
 * @author 박광민
 * @since 2021. 3. 23.
 */
public class CraneGame {
	
	private int[][] board = null; //인형이 들어있는 2차원 공간
	Stack<Integer> dollsOfBasket = new Stack<>(); //뽑은 인형을 담는 바구니
	private int explodedDollCnt = 0; //조건을 만족하여 폭발하여 없어진 인형 수
	
	public CraneGame(int[][] board) {
		this.board = board;
	}

	/**
	 * 인형뽑기 게임을 시작한다.
	 * @param moves 2차원 배열 속에서 크레인이 작동할 위치에 대한 배열
	 * @author 박광민
	 * @since 2021. 3. 23.
	 */
	public void playCraneGame(int[] moves) {
		explodedDollCnt = 0;
		
		for (int cranePosition : moves) {
			dropCrane(cranePosition);
		}
	}
	
	/**
	 * 크레인을 하강시켜 인형을 잡는다. 비어있지 않다면 인형을 잡다가 실패하는 경우는 없음.
	 * @param cranePosition 크레인을 하강시킬 위치
	 * @author 박광민
	 * @since 2021. 3. 23.
	 */
	private void dropCrane(int cranePosition) {
		for (int craneDepth = 1; craneDepth <= board[0].length; craneDepth++) {
			if (emptyThisPosition(cranePosition, craneDepth)) continue; //인형 없으면 한 칸 더 아래로 이동
			
			moveDollToBasket(craneDepth, cranePosition);
			
			if (isSameDollsInTopOfBasket())
				explodeTowDollInTopOfBasket();
			
			break;
		}
	}

	private boolean emptyThisPosition(int cranePosition, int craneDepth) {
		return board[craneDepth - 1][cranePosition - 1] == 0;
	}

	private void moveDollToBasket(int craneDepth, int cranePosition) {
		int doll = board[craneDepth - 1][cranePosition - 1];
		board[craneDepth - 1][cranePosition - 1] = 0;
		dollsOfBasket.push(doll);
	}

	private boolean isSameDollsInTopOfBasket() {
		if (dollsOfBasket.size() < 2) return false;
		
		int topDoll = dollsOfBasket.get(dollsOfBasket.size() - 1);
		int nextDoll = dollsOfBasket.get(dollsOfBasket.size() - 2);
		
		if (topDoll == nextDoll) return true;
		
		return false;
	}

	private void explodeTowDollInTopOfBasket() {
		dollsOfBasket.pop();
		explodedDollCnt++;
		
		dollsOfBasket.pop();
		explodedDollCnt++;
	}

	public int getExplodedDollCnt() {
		return this.explodedDollCnt;
	}

	public static void main(String[] args) {
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		CraneGame game = new CraneGame(board);
		
		int[] moves = {1,5,3,5,1,2,1,4};
		game.playCraneGame(moves);
		System.out.println("explodedDollCnt : " + game.getExplodedDollCnt());
	}
}
