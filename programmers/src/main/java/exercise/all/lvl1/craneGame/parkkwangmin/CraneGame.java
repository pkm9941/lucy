package exercise.all.lvl1.craneGame.parkkwangmin;

import java.util.Stack;

public class CraneGame {
	public static void main(String[] args) {
		
		CraneGame game = new CraneGame();
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		int explodedDollCnt = game.playCraneGame(board, moves);
		System.out.println("explodedDollCnt : " + explodedDollCnt);
	}

	public int playCraneGame(int[][] board, int[] moves) {
		//바구니의 인형들은 무엇에 담을까? 가장 나중에 담긴 인형과 일치 여부를 비교해야 하므로 stack에 담자. dollsOfBasket
		Stack<Integer> dollsOfBasket = new Stack<>();
		int explodedDollCnt = 0;
		
		int height = board[0].length;
		
		//moves 만큼 반복해서 크레인 동작
		for (int cranePosition : moves) {
			//크레인 위치 잡고 아래로 내려가면서 최초로 잡히는 인형 찾기
			for (int j = 0; j < height; j++) {
				if (board[j][cranePosition - 1] > 0) {//인형 있음
					int raisedDoll = board[j][cranePosition - 1];
					if (dollsOfBasket.isEmpty()) {
						dollsOfBasket.push(raisedDoll);
						board[j][cranePosition - 1] = 0;
						break;
					}
					
					int poppedDoll = dollsOfBasket.pop();
					if (raisedDoll == poppedDoll) {
						explodedDollCnt += 2;
					} else {
						dollsOfBasket.push(poppedDoll);
						dollsOfBasket.push(raisedDoll);
					}
					board[j][cranePosition - 1] = 0;
					break;
				}
			}
		}
		
		return explodedDollCnt;
	}
}
