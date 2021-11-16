package exercise.all.lvl2.rankSearch.nohhyungrae;

import java.util.ArrayList;
import java.util.List;

/*
 * 코딩테스트 참여 개발언어 항목에 cpp, java, python 중 하나를 선택해야 합니다.
 * 지원 직군 항목에 backend와 frontend 중 하나를 선택해야 합니다.
 * 지원 경력구분 항목에 junior와 senior 중 하나를 선택해야 합니다.
 * 선호하는 소울푸드로 chicken과 pizza 중 하나를 선택해야 합니다.
 * 
 * [문제] 
 * 지원자가 지원서에 입력한 4가지의 정보와 획득한 코딩테스트 점수를 하나의 문자열로 구성한 값의 배열 info, 개발팀이 궁금해하는 문의조건이 문자열 형태로 담긴 배열 query가 매개변수로 주어질 때, 
 * 각 문의조건에 해당하는 사람들의 숫자를 순서대로 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
 * 
 * [제한사항]
 * info 배열의 크기는 1 이상 50,000 이하입니다.
 * info 배열 각 원소의 값은 지원자가 지원서에 입력한 4가지 값과 코딩테스트 점수를 합친 "개발언어 직군 경력 소울푸드 점수" 형식입니다.
 * 		개발언어는 cpp, java, python 중 하나입니다.
 * 		직군은 backend, frontend 중 하나입니다.
 *		경력은 junior, senior 중 하나입니다.
 *		소울푸드는 chicken, pizza 중 하나입니다.
 *		점수는 코딩테스트 점수를 의미하며, 1 이상 100,000 이하인 자연수입니다.
 *		각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
 * query 배열의 크기는 1 이상 100,000 이하입니다.
 * query의 각 문자열은 "[조건] X" 형식입니다.
 *		[조건]은 "개발언어 and 직군 and 경력 and 소울푸드" 형식의 문자열입니다.
 *		언어는 cpp, java, python, - 중 하나입니다.
 *		직군은 backend, frontend, - 중 하나입니다.
 *		경력은 junior, senior, - 중 하나입니다.
 *		소울푸드는 chicken, pizza, - 중 하나입니다.
 *		'-' 표시는 해당 조건을 고려하지 않겠다는 의미입니다.
 *		X는 코딩테스트 점수를 의미하며 조건을 만족하는 사람 중 X점 이상 받은 사람은 모두 몇 명인 지를 의미합니다.
 *		각 단어는 공백문자(스페이스 바) 하나로 구분되어 있습니다.
 *		예를 들면, "cpp and - and senior and pizza 500"은 "cpp로 코딩테스트를 봤으며, 
 *		경력은 senior 이면서 소울푸드로 pizza를 선택한 지원자 중 코딩테스트 점수를 500점 이상 받은 사람은 모두 몇 명인가?"를 의미합니다.
 */
public class RankSearch {

	public static void main(String[] args) {
		String[]  info = {"java backend junior pizza 150"
				,"python frontend senior chicken 210"
				,"python frontend senior chicken 150"
				,"cpp backend senior pizza 260"
				,"java backend junior chicken 80"
				,"python backend senior chicken 50"};
		
		String[] query = {"java and backend and junior and pizza 100"
				,"python and frontend and senior and chicken 200"
				,"cpp and - and senior and pizza 250"
				,"- and backend and senior and - 150"
				,"- and - and - and chicken 100"
				,"- and - and - and - 150"};
		
		String[]  info2 = {};
		
		String[] query2 = {};
		
		System.out.println("solution : "+solution(info,query));
		
	}
	
	public static int[] solution(String[] info, String[] query) {
		
        int[] answer = new int[query.length];
        List<String[]> queryList = new ArrayList<>();
        String[] queryArr = new String[5];
        for(String str : query) {
        	queryArr = str.replace(" and ", " ").split(" ");
        	queryList.add(queryArr);
        }
        
        List<String[]> infoList = new ArrayList<>();
        String[] infoArr = new String[5];
        for(String str : info) {
        	infoArr = str.split(" ");
        	infoList.add(infoArr);
        }
        
        
        int index=0;
        for(String str : query) {
        	boolean tf = true;
        	int queryScore = Integer.parseInt(queryList.get(index)[4]);
        	for(int i=0; i<infoList.size(); i++) {
        		int infoScore = Integer.parseInt(infoList.get(i)[4]);
        		
        		for(int j=0;j<infoList.get(i).length-1; j++) {
        			String queryStr = queryList.get(index)[j];
        			if(infoScore >= queryScore) {
	        			if(queryStr.equals("-") || queryStr.equals(infoList.get(i)[j])) {
	        				tf = true;
	        			}else {
	        				tf = false;
	        				break;
	        			}
        			}else {
    					tf = false;
        				break;
        			}
        		}
        		
        		if(tf) {
        			answer[index]++;
        		}
        		
        	}
        	index++;
        }
        
        return answer;
    }

}