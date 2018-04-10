package cipherkey;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class CryptoManager {

    private static Key keyObject = null;
    private static SecretKey skeyObject = null;
    private static String keyFileName = "local/truekey.dat";
    private static String SECURITY_KEY;
    //f7a990fd780dae5dd765b7a4ee87ca860c170354fcad6ee2604d708a26d8aee9
	public static void main(String[] args) throws Exception {
		
		CryptoManager.makeOracleKeyFile("key.dat");
		//SECURITY_KEY = CryptoManager.loadCipherKey("conf/key.dat");
		//System.out.println(SECURITY_KEY);
	}
    
    
	
	/**
	 * key파일명을 설정
	 * @param String keyFileName : key파일명
	 * @return void
	 * @throws Exception 
	 */
	public static void setKeyFileName(String keyFileName) throws Exception {
		CryptoManager.keyFileName = keyFileName;
		CryptoManager.SECURITY_KEY = CryptoManager.loadCipherKey(keyFileName);
	}
	
	
	/**
	 * Mysql용 AES key 생성
	 * @param String key : 데이타 암/복호화용 key 값
	 * @param String key : key에 대한 charset
	 * @return SecretKeySpec : Key Object
	 */
	private static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for(byte b : key.getBytes(encoding))
				finalKey[i++%16] ^= b;			
			return new SecretKeySpec(finalKey, "AES");
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Mysql용 데이타 암/복호화 키를 생성하는 함수
	 * @param String key : 데이타 암/복호를 위한 키(mysql은 256비트 지원안하므로 26자리, oracle은 32자리로 사용해야 함)
	 * @param String fileName : 키를 저장할 파일명
	 * @return void
	 */
	private static void makeMysqlKeyFile(String fileName) throws Exception {
		
		int keyLength=26;
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		
		//키를 콘솔에서 입력받기
		String inputKey = getKeyByConsole( keyLength, bufferedReader );
		
		//키를 암호화할 비밀키를 콘솔에서 입력 받기
		String keyPassword = getPasswordKeyByConsole( keyLength, bufferedReader );
		
		if(inputKey.getBytes().length!=keyLength) {
			throw new Exception("Key length must be "+keyLength+" bits.");
		}
		
		if(keyPassword.getBytes().length!=keyLength) {
			throw new Exception("Password length must be "+keyLength+" bits.");
		}
		
		//key를 암호화 시킴
		final Cipher encryptCipher = Cipher.getInstance("AES");	        				
		encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(keyPassword, "UTF-8"));
		byte[] encodedKey = encryptCipher.doFinal(inputKey.getBytes("UTF-8"));
		String hexKey = byteArrayToHex(encodedKey);
		
		byte[] base64Key = Base64.decodeBase64(hexKey);
		SecretKey secretKey = new SecretKeySpec(base64Key, "AES");
		byte[] keyByte = secretKey.getEncoded();
		
		
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		fileOutputStream.write(keyByte);
		fileOutputStream.close();
	}
	
	/**
	 * Oracle용 데이타 암/복호화 키를 생성하는 함수
	 * @param String key : 요청 받은 Key를 입력한다.
	 * @param String fileName : 키를 저장할 파일명
	 * @return void
	 */
	private static void makeOracleKeyFile(String fileName) throws Exception {
		
		int keyLength = 16;
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		//키를 콘솔에서 입력받기
		String inputKey = getKeyByConsoleOracle( bufferedReader );
		
		/*if(inputKey.getBytes().length!=keyLength) {
			throw new Exception("Key length must be "+keyLength+" bits.");
		}*/
		
		byte[] base64Key = Base64.decodeBase64(inputKey);
		SecretKey secretKey = new SecretKeySpec(base64Key, "AES");
		byte[] keyByte = secretKey.getEncoded();
		
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		fileOutputStream.write(keyByte);
		fileOutputStream.close();
	}
	
	/**
	 * 파일에서 Key값을 읽는 함수
	 * @param String fileName : 키가 있는 파일명
	 * @return String : 키값
	 */
    private static String loadCipherKey(String fileName) throws Exception {
    	
        if (fileName==null) {
            throw new Exception("Value for key file name parameter is not set.");
        }
    	
    	String stringKey = "";
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    	InputStream  inputStream = classLoader.getResourceAsStream(fileName);
    	
        if (inputStream==null) {
            throw new Exception("Securety Key File does not exist.");
        }
        
    	byte[] keyByte = CryptoManager.getBytes(inputStream);
    	inputStream.close();
    	//추가.
    	System.out.println("keyByteNotEncoded : " + new String(keyByte));
    	System.out.println("keyByte : " + Base64.encodeBase64String(keyByte));
    	//추가 끝.
        CryptoManager.keyObject = new SecretKeySpec(keyByte, "AES");
        if (CryptoManager.keyObject != null) {
        	byte[] keyBate2 = CryptoManager.keyObject.getEncoded();
        	
        	//Base64.getEncoder().encodeToString(keyBate2);
        	Base64.encodeBase64String(keyBate2);
            //stringKey = Base64.getEncoder().encodeToString(CryptoManager.keyObject.getEncoded());
        	stringKey = Base64.encodeBase64String(CryptoManager.keyObject.getEncoded());
        	
        	//개행문자 제거
        	stringKey = stringKey.replace(System.getProperty("line.separator"), "");
        }
        return stringKey;
    }
    
    
    
    
    public static String getCipherKey() throws Exception {
    	//String keyFileName = ICNCommon.getInstance().getSecurityKeyFilePath();
    	
    	if(CryptoManager.SECURITY_KEY==null || CryptoManager.SECURITY_KEY.equals("")) {
    		CryptoManager.SECURITY_KEY = CryptoManager.loadCipherKey(keyFileName);
    	}
    	return CryptoManager.SECURITY_KEY;
    }
    
	/**
	 * 키를 콘솔에서 입력받기 Oracle
	 * @param BufferedReader bufferedReader : 콘솔에서 키를 입력받기 위한 BufferedReader
	 * @return String : 키
	 */
    private static String getKeyByConsoleOracle(BufferedReader bufferedReader) {
        int     iTemp       = 0;
        boolean isOk        = false;
        String  inputKey   = "";
        String  reinpuKey = "";
        String  rtnValue    = "";
        String  sReFlag     = "";
        
        try {

        	iTemp = 1;
            isOk  = false;
            while(!isOk) {
            	iTemp++;
		    	System.out.print( "데이타 암/복호화에 사용할 Key를 "+ sReFlag +" 입력해 주세요.  : " );
		        inputKey = bufferedReader.readLine();
		        inputKey = inputKey == null ? "" : inputKey;
		        
		        if( inputKey == null || "".equals(inputKey) ) {
                    System.out.println("Key가 입력되지 않았습니다.");
                } else {
                	isOk = true;
                    break;
                }
		        
                if( iTemp > 3 ) {
                        System.out.println("======== key 입력이 3회 잘못되었습니다. ======== ");
                        System.out.println("======== 종료합니다. ========= ");
                        System.exit(1);
                }
            }
            
            iTemp   = 1;
            isOk    = false;
            sReFlag = "한번더";
            while(!isOk) {
                iTemp++;
                System.out.print( "확인을 위해 Key를 "+ sReFlag +" 입력해 주세요 : " );
                reinpuKey = bufferedReader.readLine();
                reinpuKey = reinpuKey == null ? "" : reinpuKey;

                if( reinpuKey == null || "".equals(reinpuKey) ) {
                    System.out.println("Key를 입력해 주세요.");
                } else {

                    if( !inputKey.equals(reinpuKey) ) {
                        System.out.println("========= Key가 서로 맞지 않습니다.=========");
                        rtnValue = "";
                        sReFlag = "다시";
                    } else {
                        rtnValue = reinpuKey;
                        isOk     = true;
                        break;
                    }
                }

                if( iTemp > 3 ) {
                    System.out.println("========= key 확인 입력이 3회 잘못되었습니다. =========");
                    System.out.println("========= 종료합니다.========= ");
                    System.exit(1);
                }
            }
        
        }  catch (Exception ex) {
            System.out.println("key 입력 오류.(Exception)");
            ex.printStackTrace();
            System.exit(1);
        }

        return rtnValue;
    }

    
	/**
	 * 키를 콘솔에서 입력받기
	 * @param int keyLength : Key 사이즈
	 * @param BufferedReader bufferedReader : 콘솔에서 키를 입력받기 위한 BufferedReader
	 * @return String : 키
	 */
    private static String getKeyByConsole( int keyLength, BufferedReader bufferedReader ) {
        int     iTemp       = 0;
        boolean isOk        = false;
        String  inputKey   = "";
        String  reinpuKey = "";
        String  rtnValue    = "";
        String  sReFlag     = "";
        
        try {

        	iTemp = 1;
            isOk  = false;
            while(!isOk) {
                iTemp++;
                System.out.print( "데이타 암/복호화에 사용할 "+ keyLength +"자리 Key를 "+ sReFlag +" 입력해 주세요.  : " );
                inputKey = bufferedReader.readLine();
                inputKey = inputKey == null ? "" : inputKey;

                if( inputKey == null || "".equals(inputKey) ) {
                    System.out.println("Key가 입력되지 않았습니다.");
                } else {

                    if( inputKey.getBytes().length != keyLength ) {
                        System.out.println("Key는 "+ keyLength +"자리여야 합니다.  입력한 Key는 " + inputKey.getBytes().length + "자리입니다. ");
                        sReFlag = " 다시";
                    } else {
                    	isOk = true;
                        break;
                    }
                }

                if( iTemp > 3 ) {
                        System.out.println("======== key 입력이 3회 잘못되었습니다. ======== ");
                        System.out.println("======== 종료합니다. ========= ");
                        System.exit(1);
                }
            }


            /* 암호키 확인 */
            iTemp   = 1;
            isOk    = false;
            sReFlag = "한번더";
            while(!isOk) {
                iTemp++;
                System.out.print( "확인을 위해 Key를 "+ sReFlag +" 입력해 주세요 : " );
                reinpuKey = bufferedReader.readLine();
                reinpuKey = reinpuKey == null ? "" : reinpuKey;

                if( reinpuKey == null || "".equals(reinpuKey) ) {
                    System.out.println("Key를 입력해 주세요.");
                } else {

                    if( !inputKey.equals(reinpuKey) ) {
                        System.out.println("========= Key가 서로 맞지 않습니다.=========");
                        rtnValue = "";
                        sReFlag = "다시";
                    } else {
                        rtnValue = reinpuKey;
                        isOk     = true;
                        break;
                    }
                }

                if( iTemp > 3 ) {
                    System.out.println("========= key 확인 입력이 3회 잘못되었습니다. =========");
                    System.out.println("========= 종료합니다.========= ");
                    System.exit(1);
                }
            }


        }  catch (Exception ex) {
            System.out.println("key 입력 오류.(Exception)");
            ex.printStackTrace();
            System.exit(1);
        }

        return rtnValue;
    }
    
    
	/**
	 * 키를 암호화할 비밀키를 콘솔에서 입력 받기
	 * @param int keyLength : Key 사이즈
	 * @param BufferedReader bufferedReader : 콘솔에서 키를 입력받기 위한 BufferedReader
	 * @return String : 키를 암호화할 비밀번호
	 */
    private static String getPasswordKeyByConsole( int keyLength, BufferedReader bufferedReader ) {
        int     iTemp       = 0;
        boolean isOk        = false;
        String  inputKey   = "";
        String  reinpuKey = "";
        String  rtnValue    = "";
        String  sReFlag     = "";
        
        try {

        	iTemp = 1;
            isOk  = false;
            while(!isOk) {
                iTemp++;
                System.out.print( "입력한 Key를 암호화하기 위해 사용할 "+ keyLength +"자리 비밀번호를 "+ sReFlag +" 입력해 주세요.  : " );
                inputKey = bufferedReader.readLine();
                inputKey = inputKey == null ? "" : inputKey;

                if( inputKey == null || "".equals(inputKey) ) {
                    System.out.println("Key가 입력되지 않았습니다.");
                } else {

                    if( inputKey.getBytes().length != keyLength ) {
                        System.out.println("비밀번호는 "+ keyLength +"자리여야 합니다.  입력한 비밀번호는 " + inputKey.getBytes().length + "자리입니다. ");
                        sReFlag = " 다시";
                    } else {
                    	isOk = true;
                        break;
                    }
                }

                if( iTemp > 3 ) {
                        System.out.println("======== 비밀번호 입력이 3회 잘못되었습니다. ======== ");
                        System.out.println("======== 종료합니다. =========");
                        System.exit(1);
                }
            }


            /* 암호키 확인 */
            iTemp   = 1;
            isOk    = false;
            sReFlag = "한번더";
            while(!isOk) {
                iTemp++;
                System.out.print( "확인을 위해 비밀번호를 "+ sReFlag +" 입력해 주세요 : " );
                reinpuKey = bufferedReader.readLine();
                reinpuKey = reinpuKey == null ? "" : reinpuKey;

                if( reinpuKey == null || "".equals(reinpuKey) ) {
                    System.out.println("Key를 입력해 주세요.");
                } else {

                    if( !inputKey.equals(reinpuKey) ) {
                        System.out.println("========= 비밀번호가 서로 맞지 않습니다.=========");
                        rtnValue = "";
                        sReFlag = "다시";
                    } else {
                        rtnValue = reinpuKey;
                        isOk     = true;
                        break;
                    }
                }

                if( iTemp > 3 ) {
                    System.out.println("========= 비밀번호 확인 입력이 3회 잘못되었습니다. =========");
                    System.out.println("=========종료합니다.========= ");
                    System.exit(1);
                }
            }


        }  catch (Exception ex) {
            System.out.println("비밀번호 입력 오류.(Exception)");
            ex.printStackTrace();
            System.exit(1);
        }

        return rtnValue;
    }
    
    
	/**
	 * InputStream에서 읽은 Byte 값을 읽음
	 * @param InputStream inputStream : Byte를 읽기위한 InputStream
	 * @return byte[] : InputStream에서 읽은 바이트
	 */
    private static byte[] getBytes(InputStream inputStream) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        if (inputStream instanceof ByteArrayInputStream) {
          size = inputStream.available();
          buf = new byte[size];
          len = inputStream.read(buf, 0, size);
        } else {
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          buf = new byte[size];
          while ((len = inputStream.read(buf, 0, size)) != -1)
            bos.write(buf, 0, len);
          buf = bos.toByteArray();
        }
        return buf;
      }
    
	/**
	 * Hexa값을 Byte로 변환
	 * @param String hexValue : Byte로 변환할 Hexa값
	 * @return byte[] : 변환된 Byte
	 */
    private static byte[] hexToByteArray(String hexValue) {
        if( hexValue == null || hexValue.length() == 0 ) {
            return null;
        }
        byte[] ba = new byte[hexValue.length()/2];
        for(int i=0; i<ba.length; i++) {
            ba[i] = (byte)Integer.parseInt(hexValue.substring(2*i, 2*i+2), 16);
        }
        return ba;
    }

    
	/**
	 * Byte값을 Hexa로 변환
	 * @param String hexValue : Hexa로 변환할 Byte값
	 * @return String : 변환된 Hexa
	 */
    private static String byteArrayToHex(byte[] byteValue) {
        if( byteValue == null || byteValue.length == 0 ) {
            return null;
        }
        StringBuffer sb = new StringBuffer(byteValue.length*2);
        String hexNumber;
        for(int x=0; x<byteValue.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & byteValue[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2 ));
        }
        return sb.toString();
    }
}