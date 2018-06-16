package enumStudy;

import enumStudy.EnumStash.TAG_CODE;

public class EnumTest {
	public static void main(String[] agrgs) {
		String tagStr = "00";
		
		TAG_CODE[] tagCodes = TAG_CODE.values();
		for (TAG_CODE tagCode : tagCodes) {
			if (tagStr.equals(tagCode.getTrCode())) {
				System.out.println(tagCode.getTagType().toString());
			}
		}
		
	}
}
