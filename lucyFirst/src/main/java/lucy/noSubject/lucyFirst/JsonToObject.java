package lucy.noSubject.lucyFirst;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToObject {

	public static void main(String[] args) {
		JsonToObject test = new JsonToObject();
		test.doTest();
	}

	@SuppressWarnings("unchecked")
	private void doTest() {
		//역슬래시 -> \\\\ , 큰따옴표 -> \"
		//String jsonStr = "{\"message\":\"success\",\"result\":[{\"res_0\":[{\"tierCd\":\"EXC\",\"spousLunarGbnCd\":\"\",\"custNm\":\"박완기\",\"mbrSbscDt\":\"\",\"marryAniv\":\"20171130\",\"mbrStatCd\":\"10\",\"spousBthday\":\"\",\"hhpNoB2B\":\"010-4018-****\",\"taskRid\":\"b7ecfccad67d11e7a39c002248056f2a\",\"sbscPointPrvdYn\":\"N\",\"limitAmt\":\"700,000\",\"cardNo\":\"0021-****-****-1753\",\"pntUserPw\":\"suANVQivPbedr3sn7HMIxQ==\",\"withdrawPntDelYn\":\"F\",\"autoRdmPoint\":\"0\",\"memDiv\":\"P\",\"hhpNo\":\"010-4018-****\",\"lunarGbnCd\":\"20\",\"retryCnt\":0,\"marryYn\":\"20\",\"firstCardRegDt\":\"\",\"cardPwdLockYn\":\"N\",\"birthDt\":\"\",\"limitYn\":\"Y\",\"carNo\":\"10가1235\",\"identiVal\":\"1JAFN33dLCmBWq5OW0a0avtqMUVZrO00Tw\\\\/kbuKurkM5Jy2iryILTL5aD9renY6N5udBUbjTk2pQkGEWyBsx9g==\",\"mbrRid\":\"1-E0Z9M\",\"genGbnCd\":\"M\",\"autoRdmAgreeYn\":\"N\",\"mbrNo\":\"10002480\",\"execReqDt\":\"2017-12-01 18:55:10\"}]},{\"res_1\":[{\"dt\":\"2017-12-04 09:38:39.0\",\"TranSerialNum\":\"112040000072\",\"TranDate\":\"20171204\",\"TranTime\":\"093839\"}]},{\"res_2\":[{\"compnUserYn\":\"N\",\"pointApplyPrice\":0,\"bizrNo\":\"1148116585\",\"prodId\":\"999\",\"chnlNm\":\"주식회사 E1\",\"desc1\":\"회원탈퇴시 포인트 소멸처리를 위해 사용\",\"chnlNo\":\"10000\"}]}],\"success\":\"true\"}{\"message\":\"success\",\"result\":[{\"res_0\":[{\"tierCd\":\"EXC\",\"spousLunarGbnCd\":\"\",\"custNm\":\"박완기\",\"mbrSbscDt\":\"\",\"marryAniv\":\"20171130\",\"mbrStatCd\":\"10\",\"spousBthday\":\"\",\"hhpNoB2B\":\"010-4018-****\",\"taskRid\":\"b7ecfccad67d11e7a39c002248056f2a\",\"sbscPointPrvdYn\":\"N\",\"limitAmt\":\"700,000\",\"cardNo\":\"0021-****-****-1753\",\"pntUserPw\":\"suANVQivPbedr3sn7HMIxQ==\",\"withdrawPntDelYn\":\"F\",\"autoRdmPoint\":\"0\",\"memDiv\":\"P\",\"hhpNo\":\"010-4018-****\",\"lunarGbnCd\":\"20\",\"retryCnt\":0,\"marryYn\":\"20\",\"firstCardRegDt\":\"\",\"cardPwdLockYn\":\"N\",\"birthDt\":\"\",\"limitYn\":\"Y\",\"carNo\":\"10가1235\",\"identiVal\":\"1JAFN33dLCmBWq5OW0a0avtqMUVZrO00Tw\\/kbuKurkM5Jy2iryILTL5aD9renY6N5udBUbjTk2pQkGEWyBsx9g==\",\"mbrRid\":\"1-E0Z9M\",\"genGbnCd\":\"M\",\"autoRdmAgreeYn\":\"N\",\"mbrNo\":\"10002480\",\"execReqDt\":\"2017-12-01 18:55:10\"}]},{\"res_1\":[{\"dt\":2017-12-04 09:38:39.0,\"TranSerialNum\":\"112040000072\",\"TranDate\":\"20171204\",\"TranTime\":\"093839\"}]},{\"res_2\":[{\"compnUserYn\":\"N\",\"pointApplyPrice\":0,\"bizrNo\":\"1148116585\",\"prodId\":\"999\",\"chnlNm\":\"주식회사 E1\",\"desc1\":\"회원탈퇴시 포인트 소멸처리를 위해 사용\",\"chnlNo\":\"10000\"}]}],\"success\":\"true\"}";
		String jsonStr = "{\"message\":\"success\",\"result\":[{\"res_0\":[{}]},{\"res_1\":[{\"dt\":\"2017-12-07 13:35:03.0\",\"TranTime\":\"133503\",\"TranSerialNum\":\"112070000077\",\"TranDate\":\"20171207\"}]},{\"res_2\":[{\"compnUserYn\":\"N\",\"pointApplyPrice\":0,\"bizrNo\":\"1148116585\",\"prodId\":\"999\",\"chnlNm\":\"주식회사 E1\",\"desc1\":\"회원탈퇴시 포인트 소멸처리를 위해 사용\",\"chnlNo\":\"10000\"}]}],\"success\":\"true\"}";

		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map;
		try {
			map = mapper.readValue(jsonStr, HashMap.class);
		
			List<HashMap<String,Object>> result = (List<HashMap<String,Object>>)map.get("result");
			HashMap<String,Object> dataMap_0 = result.get(0);
			List<HashMap<String,Object>> res_0 = (List<HashMap<String,Object>>)dataMap_0.get("res_0");
			HashMap<String,Object> mbrInfo = res_0.get(0);
			printType(mbrInfo, "mbrInfo");
			HashMap<String,Object> dataMap_1 = result.get(1);
			List<HashMap<String,Object>> res_1 = (List<HashMap<String,Object>>)dataMap_1.get("res_1");
			HashMap<String,Object> tranSerialNo = res_1.get(0);
			printType(tranSerialNo, "tranSerialNo");
		
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public void printType(HashMap<String, Object> obj, String objName) {
		Set<Entry<String, Object>> set = obj.entrySet();
		Iterator<Entry<String, Object>> iterator = set.iterator();
		System.out.println(objName + "필드 타입 시작");
		while(iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String paramKey = entry.getKey();
			Object paramValue = entry.getValue();
			String type = paramValue == null ? "null" : paramValue.getClass().getName();
			System.out.println("param name : "+paramKey+", value : "+paramValue+", type : "+type);
		}
		System.out.println(objName + "필드 타입 종료");
	}

}
