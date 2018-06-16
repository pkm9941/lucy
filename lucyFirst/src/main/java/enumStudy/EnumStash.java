package enumStudy;

public class EnumStash {
	
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
	
	public enum TAG_TYPE {
		/**
		 * 메인
		 */
		MAIN_TAG,
		/**
		 * 현금
		 */
		CASH_TAG,
		/**
		 * 신용
		 */
		CREDIT_TAG,
		/**
		 * 포인트 적립
		 */
		PNT_ACRL_TAG,
		/**
		 * 포인트 사용
		 */
		PNT_RDM_TAG,
		/**
		 * 충전소 포인트 사용
		 */
		STN_PNT_RDM_TAG,
		/**
		 * 복합제품 포인트 사용
		 */
		CMPLX_PNT_RDM_TAG,
		/**
		 * 쿠폰사용
		 */
		COUPON_TAG
	}
}
