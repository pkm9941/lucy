package exercise.all.lvl2.openChat.parkkwangmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 관리자가 최종적으로 보는 채팅방 기록의 조건
 * 1. 해당 유저가 가장 나중에 변경한 닉네임으로 모든 채팅기록을 변경한다.
 * 2. 닉네임 변경은 들어올 때와 채팅방 내에서 변경할 수 있다.
 * 
 * @author 박광민
 * @since 2021. 5. 18.
 */
public class OpenChat {
	
	public static void main(String[] args) {
		//채팅방에 입장한 유저 아이디와 최종 닉네임 정보를 구하기
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		Map<String, String> idAndNickNameMap = new HashMap<>();
		for (int i = record.length - 1; i >= 0; i--) {
			String aRecord = record[i];
			if (aRecord.startsWith("Leave"))
					continue;
			
			String[] split = aRecord.split(" ");
			String id = split[1];
			String nickName = split[2];
			
			idAndNickNameMap.putIfAbsent(id, nickName);
		}
		
		idAndNickNameMap.entrySet().forEach(t -> System.out.println(t.getKey() + ":" + t.getValue()));
		System.out.println();
		
		List<String> validRecords = Arrays.stream(record)
										.filter(s -> !s.startsWith("Change"))
										.collect(Collectors.toList());
		
		List<String> adminLogs = new ArrayList<>();
		for (String validRecord : validRecords) {
			String[] split = validRecord.split(" ");
			String action = split[0];
			String id = split[1];
			
			if ("Enter".equals(action)) {
				adminLogs.add(idAndNickNameMap.get(id) + "님이 들어왔습니다.");
			} else {
				adminLogs.add(idAndNickNameMap.get(id) + "님이 나갔습니다.");
			}
		}
		
		adminLogs.toArray(new String[0]);
		for (String adminLog : adminLogs) {
			System.out.println(adminLog);
		}
    }
}
