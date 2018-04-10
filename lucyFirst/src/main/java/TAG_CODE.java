

public enum TAG_CODE {
	/**
	 * 콜센터
	 */
	CASH_APPR("00", TAG_TYPE.CASH_TAG);
	
	final private String trCode;
	final private TAG_TYPE tagType;
	
	private TAG_CODE(String trCode, TAG_TYPE tagType) { //enum에서 생성자 같은 역할
		this.trCode = trCode;
		this.tagType = tagType;
	}
	public String getTrCode() { // 문자를 받아오는 함수
		return trCode;
	}
	public TAG_TYPE getTagType() { // 문자를 받아오는 함수
		return tagType;
	}
}
