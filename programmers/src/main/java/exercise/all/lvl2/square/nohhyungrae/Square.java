package exercise.all.lvl2.square.nohhyungrae;

public class Square {
	
	public static void main(String[] args) {
		int w = 9000;
		int h= 121;
		System.out.println("solution : "+solution(w,h));
	
	}
	
	public static long solution(int w, int h) {
		long hol =  (long) w*h;
		long line = (long) w+h - getGCD(Math.max(w, h), Math.min(w, h));
		System.out.println("전체 갯수 : "+hol+", 없어진 갯수 : "+line);
		return  hol - line;
	}
	
	//최대공약수
	public static long getGCD(long a, long b) {
		System.out.println("MAX : "+a+", MIN : "+b);
		while(b>0) {
			System.out.println("a : "+a+", b : "+b);
			long temp = a;
			a = b;
			b = temp%b;
		}
		return a;
	}
	
}
