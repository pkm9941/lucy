package lucy.noSubject.lucyFirst;

public final class StringUtil {
	private static final String[] BYTE2HEX_PAD;
	private static final String[] BYTE2HEX_NOPAD;
	
	public static void print(String val) {
		System.out.println(val);
	}
	
	static {
		System.out.println("static 실행");
		BYTE2HEX_PAD = new String[256];
		BYTE2HEX_NOPAD = new String[256];
		int i = 0;
		for (i = 0; i < 10; ++i) {
			BYTE2HEX_PAD[i] = "0" + i;
			BYTE2HEX_NOPAD[i] = String.valueOf(i);
		}
		for (; i < 16; ++i) {
			char c = (char) (97 + i - 10);
			BYTE2HEX_PAD[i] = "0" + c;
			BYTE2HEX_NOPAD[i] = String.valueOf(c);
		}
		for (; i < BYTE2HEX_PAD.length; ++i) {
			String str = Integer.toHexString(i);
			BYTE2HEX_PAD[i] = str;
			BYTE2HEX_NOPAD[i] = str;
		}
		
		System.out.println("static 종료");
	}
}
