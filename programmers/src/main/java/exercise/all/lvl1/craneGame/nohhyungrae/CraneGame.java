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
	
	static int x = 1, bx = 0, pic = 0, count = 0, picCount = 0;
	
	static List<Integer> basketList = new LinkedList<>();
	
	static String exit = "EXIT";
	
	public static void main(String[] args) {
		//start();
		
		int[][] boards = {
				 
		         {0,0,0,0,0},
		         {0,0,1,0,3},
		         {0,2,5,0,1},
		         {4,2,4,4,2},
		         {3,5,1,3,1}
		};
		
		int [] moves = {1,5,3,5,1,2,1,4};
		
		System.out.println("solution() : "+ solution(boards, moves));
	}
	
	public static void map(int x) {

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
	}
	
	public static void start() {
		//초기 맵 그리기
		map(x);
		
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
			if(x == 0 || x == 6)
				x = bx;

			map(x);
			
			//하드코딩;;; 인형 집은 갯수
			if(input.toUpperCase().equals(exit) || picCount > 14 && checkFinished() == 0) {
				System.out.println("총 "+count+"쌍을 맞추었습니다.");
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
				if(basketList.size() > 0) {
					checkBasket(pic);
				}else {
					basketList.add(pic);
					picCount++;
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
			count++;
		}else {
			basketList.add(pic);
		}
		picCount++;
	}
	
	public static int checkFinished() {
		int reminder = 0;
		for(int i=1;i<board.length-1; i++) {
			for(int j=1; j<board.length-1; j++) {
				if(board[i][j] != 0 && board[i][j] != 9) {
					reminder++;
				}
			}
		}
		return reminder;
	}
	
	//----------문제풀이--------------------------------------------------------------------------------------------------
	
	static List<Integer> basket = new LinkedList<>();
	
    public static int solution(int[][] board, int[] moves) {
    	
    	int count=0;
        for(int item : moves){
        	count += picker(board ,item-1);
        }
        
        return count;
    }
    
    public static int picker(int[][] board,int x) {
    	int pic=0, add = 0;
		for(int i=0;i<board.length; i++) {
			if(board[i][x] != 0) {
				pic =board[i][x];
				if(basket.size() > 0) {
					add = checkBaskets(pic);
				}else {
					basket.add(pic);
				}
				board[i][x] = 0;
				break;
			}
		}
		return add;
	}
    
	public static int checkBaskets(int picItem) {
		int add = 0;
		if(basket.get(basket.size()-1).equals(picItem)) {
			basket.remove(basket.size()-1);
			add =  2;
		}else {
			basket.add(picItem);
		}
		return add;
	}

}
