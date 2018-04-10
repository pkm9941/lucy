package lucy.noSubject.lucyFirst;

import java.sql.Timestamp;

public class TimestampTest {
	public String originDate = "1999/11/11 12:12:12";
	public final static String SLASH = "/";
	public final static String HYPHEN = "-";

	public static void main(String[] args) {
		TimestampTest test = new TimestampTest();
		
		String convertedDate = test.doTest();
/*		
		String convertedDate = originDate.replace("/", "-");
		String[] tokens = convertedDate.split(":");
		System.out.println("tokens.length : " + tokens.length);
		*/
		/*
		if (tokens.length > 3) {
			convertedDate = tokens[0] + ":" + tokens[1] + ":" + tokens[2] + "." + tokens[3];
		}
		*/
/*		
		int millisecPos = convertedDate.lastIndexOf(":");
		if (millisecPos > -1) {
			convertedDate = convertedDate.substring(0, millisecPos)
					+ "." + convertedDate.substring(millisecPos + 1);
		}
		*/
		System.out.println("convertedDate : " + convertedDate);
		
		
		Timestamp timestamp = Timestamp.valueOf(convertedDate);
		System.out.println("timestamp.toString() : " + timestamp.toString());

	}

	private String doTest() {
		convertTimestampFormat(originDate, TimestampTest.SLASH);
		return null;
	}

	private String convertTimestampFormat(String originDate, String delimiter) {//DemonUtility.SLASH) {
		return originDate.replace(delimiter, TimestampTest.HYPHEN);
		
	}

}
