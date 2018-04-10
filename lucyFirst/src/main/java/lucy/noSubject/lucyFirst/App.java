package lucy.noSubject.lucyFirst;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("regionMatches : " + "abcdef".regionMatches(1, "bcdefg", 0, 5));
    	/*
    	System.out.println("toUnsignedString : " + Integer.toUnsignedLong(-2));
    	System.out.println("null to decimal : " + (BigDecimal)null);
    	System.out.println("aa-bb cc".replaceAll("-", "").replaceAll(" ", ""));
    	System.out.println(Math.round(0.6));
    	System.out.println(new BigDecimal("000000"));
    	System.out.println("".equals(null));
    	System.out.println("47c34662bf9d3399fed31577f2d213f6\r".indexOf("\r"));
    	System.out.println("47c34662bf9d3399fed31577f2d213f6\r".replace("\r", ""));
    	System.out.println("47c34662bf9d3399fed31577f2d213f6\r".replace( System.getProperty( "line.separator" ), "" ));
		int a = 3;
		Object obj = a;
		System.out.println(obj.toString());
		System.out.println(Integer.parseInt(obj.toString()));
    	
    	System.out.println(Integer.valueOf("0001000"));
    	String msgValid = "D,문자전송 지연 미발송";
    	
		if (!"S".equals(msgValid)) {
			System.out.println("rsltCd" + msgValid.split(",")[0]);
			System.out.println("rsltMsg" + msgValid.split(",")[1]);
		} else {
			System.out.println("S 아님");
		};
    	
    	System.out.println("indexOf: " + "/168.63.129.16".indexOf("168.63.129.16"));
    	Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    	System.out.println(timeStamp.toString());
    	
    	String daemonName = "E1TxnInsert0";
    	System.out.println(daemonName.substring(daemonName.length() - 1, daemonName.length()));
//035007006120180109191202101090015386 10000388 PVIP김미자1 HpDnJg3awq4sRZjB7YYa6A== 1020090211M201962021310 20100512N 0000000000NNN00000000000001051851589 1-TCPN 
    	test();
    	Collections.EMPTY_LIST.get(2);
        System.out.println( "Hello World! first commit! !!" );
        System.out.println(Integer.parseInt("01"));
        System.out.println(BigDecimal.valueOf(Double.valueOf("123.123456789")).toString());
        */
    }

	private static void test() {
		String msg = "0421021015V1000010201801031159421010300149253021                                                                 000000000000�ŷ�������ȣ �ߺ�                                                                                                                                                                                                                                                                                           ";
		System.out.println("rsltCdOfSend : " + msg.substring(44, 48));
		
		System.out.println(Integer.valueOf("0010"));
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("", "S");
		System.out.println(testMap.get(""));
	}
}