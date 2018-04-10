package systemIn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemInTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 2; i++) {
			System.out.print("문자열 입력:");
			String bufferstr = in.readLine();
			System.out.println("문자열 출력:" + bufferstr);
		}
	}
}
