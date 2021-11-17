package exercise.all.lvl2.rankSearch.parksohyun;

import java.util.ArrayList;
import java.util.List;

public class RankSearch {

	public static void main(String[] args) {
		String[] info= {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query= {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		System.out.println(solution(info,query));
	}

	private static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		String[] strQueryArr = {};
		
		for(int i=0;i<query.length;i++) {
			strQueryArr=splitQueryArr(query[i]);
			answer[i] = compareQuery(strQueryArr,info);
		}		
		
		return answer;
	}

	private static int compareQuery(String[] strQueryArr, String[] info) {
		String[] strInfoArr = {};
		int tmpAnswer=0;		

		List<String> list = new ArrayList<>();
		List<String> querylist = new ArrayList<>();

		int queryScore= Integer.parseInt(strQueryArr[strQueryArr.length-1]); //마지막 점수 
		
		for(int k=0;k<strQueryArr.length-1;k++) {
			querylist.add(strQueryArr[k]);
		}
		
		for(int i=0;i<info.length;i++) {
			strInfoArr=splitArr(info[i]);
		    int score= Integer.parseInt(strInfoArr[strInfoArr.length-1]); //마지막 점수 
			
		    list = new ArrayList<>();
		    for(int j=0;j<strInfoArr.length-1;j++) {
				list.add(strInfoArr[j]);
			}		   

		   if(score>=queryScore) {
			   tmpAnswer+= getTmpAnswer(list,querylist);
		   }
		}		
		
		return tmpAnswer;
	}

	public static int getTmpAnswer(List<String> list, List<String> querylist) {
		for(int i=0;i<querylist.size();i++) {
			if(!list.contains(querylist.get(i))) {
				return 0;
			}
		}
		return 1;
	}

	
	private static String[] splitQueryArr(String query) {
		String queryRep =query.replace("- and ","").replace("- ","").replace("and ","");
		String[] qArr = queryRep.split(" ");
		return qArr;
	}

	private static String[] splitArr(String info) {
		String[] infoArr = info.split(" ");
		return infoArr;
	}

}