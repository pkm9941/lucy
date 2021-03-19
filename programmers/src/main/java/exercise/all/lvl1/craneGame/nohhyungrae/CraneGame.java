package exercise.all.lvl1.craneGame.nohhyungrae;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CraneGame {
	
	static int[][] board = {
			 {9,0,0,0,0,0,9},
	         {9,0,0,0,0,0,9},
	         {9,0,0,1,0,3,9},
	         {9,0,2,5,0,1,9},
	         {9,4,2,4,4,2,9},
	         {9,3,5,1,3,1,9},
	         {9,9,9,9,9,9,9}
	};
	
	static Scanner sc = new Scanner(System.in);
	
	static int x = 1, bx = 0, pic = 0;
	
	static List<Integer> basketList = new LinkedList<>();
	
	static String exit = "EXIT";
	
	public static void main(String[] args) {
		move();
	}
	
	public static boolean map(int x) {
		boolean result = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				if (i == 0 && j == x) {
					System.out.print("㉿\t");
				} else if (board[i][j] == 0) {
					System.out.print("□\t");
				} else if (board[i][j] == 9) {
					System.out.print("▩\t");
				} else if (board[i][j] == 1) {
					System.out.print("♥\t");
				} else if (board[i][j] == 2) {
					System.out.print("★\t");
				} else if (board[i][j] == 3) {
					System.out.print("☆\t");
				} else if (board[i][j] == 4) {
					System.out.print("●\t");
				} else if (board[i][j] == 5) {
					System.out.print("◎\t");
				}
			}
			
			System.out.println();
		}
		return result;
	}
	
	public static void move() {
		//초기 맵 그리기
		map(x);
		//잡은 아이템 표시
		while(true) {
			String input = sc.next();
			//System.out.print(" : "+input.toUpperCase());
			System.out.println();
			//집게의 전위치 세팅
			bx = x;
			switch (input.toUpperCase()) {
			case "S": // 집기
				picker(x);
				break;
			case "A": // 왼쪽
				x--;
				break;
			case "D": // 오른쪽
				x++;
				break;
			default:			
				break;
			}
			
			System.out.println("바구니 : "+basketList.toString());
			
			//집게가 범위밖으로 나가지 못하게 하기위한 예외처리
			if(x == 0 || x == 6) {
				x = bx;
			}
			map(x);
			
			if (input.toUpperCase().equals(exit)) {
				System.out.println("게임이 끝났습니다.");
				break;
			} 

		}
	}
	
	public static int picker(int x) {
		int item = 0;
		
		for(int i=1;i<board.length-1; i++) {
			if(board[i][x] != 0) {
				pic =board[i][x];
				if(basketList.size() >1) {
					checkBasket(pic);
				}else {
					basketList.add(pic);
				}
				board[i][x] = 0;
				break;
			}
		}
		
		return item;
	}
	
	public static void checkBasket(int picItem) {
		if(basketList.get(basketList.size()-1).equals(picItem)) {
			basketList.remove(basketList.size()-1);
		}else {
			basketList.add(pic);
		}
	}
}
