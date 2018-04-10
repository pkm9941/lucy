package cipherkey;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;


public class CipherKeyTest {
	private static String cipherKey = "f7a990fd780dae5dd765b7a4ee87ca860c170354fcad6ee2604d708a26d8aee9";

	public static void main(String[] args) throws Exception {
		//getBase64DecodedStr(cipherKey);
		//createKeyFile(cipherKey);
		getCipherKeyFromKeyFile();
		//getBase64EncodedStr(cipherKey);

	}

	private static void getCipherKeyFromKeyFile() throws Exception {
		String cipherKeyFromFile = CryptoManager.getCipherKey();
		System.out.println(cipherKeyFromFile + " : cipherKeyFromFile");
		System.out.println(cipherKey + " : cipherKey");
	}

	@SuppressWarnings("unused")
	private static void createKeyFile(String key) throws IOException {
		String decodedKey = getBase64DecodedStr(key);
		FileOutputStream fileOutputStream = new FileOutputStream("key.dat");
		fileOutputStream.write(decodedKey.getBytes());
		fileOutputStream.close();
	}

	private static String getBase64DecodedStr(String key) {
		String decodedKey = null;
		try {
			decodedKey = new String(Base64.decodeBase64(key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(decodedKey);
		return decodedKey;
	}
	
	private static String getBase64EncodedStr(String key) {
		String encodedKey = null;
		try {
			encodedKey = new String(Base64.encodeBase64(key.getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(encodedKey);
		return encodedKey;
	}
}
