package exercise.all.lvl2.rankSearch.nohhyungrae;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


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
		
		String[] query = {"java and backend and junior and pizza 60"
				,"python and frontend and senior and chicken 80"
				,"cpp and - and senior and pizza 250"
				,"- and backend and senior and - 150"
				,"- and - and - and chicken 50"
				,"- and - and - and - 150"};
		
		String[]  info2 = {"java backend junior pizza 150"
				,"python frontend senior chicken 210"
				,"python frontend senior chicken 150"
				,"cpp backend senior pizza 260"
				,"java backend junior chicken 80"
				,"python backend senior chicken 50"};
		
		String[] query2 = {"java and backend and junior and pizza 100"
				,"python and frontend and senior and chicken 200"
				,"cpp and - and senior and pizza 250"
				,"- and backend and senior and - 150"
				,"- and - and - and chicken 100"
				,"- and - and - and - 150"};
		
		System.out.println("solution : "+solution(info2,query2));
		
	}
	 
	public static int[]  solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        List<Integer> infoScoreList = new LinkedList<>();
        List<String[]> infoArrList = new LinkedList<>();
        List<String> infoList = new LinkedList<>();
        List<Integer> queryScoreList = new LinkedList<>();
        List<String[]> queryList = new LinkedList<>();

        int indexNum = 0;
        for(String str : info) {
        	int score = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			if(infoScoreList.isEmpty()) {
				infoScoreList.add(score);
				infoArrList.add(str.replace(str.replaceAll("[^0-9]", ""), "").split(" "));
				infoList.add(str.replace(str.replaceAll("[^0-9]", ""), ""));
			}else {
				for(int i = 0; i<infoScoreList.size(); i++) {
					indexNum = infoScoreList.get(i);
					if(score <= indexNum) {
						infoScoreList.add(i, score);
						infoArrList.add(i, str.replace(str.replaceAll("[^0-9]", ""), "").split(" "));
						infoList.add(i, str.replace(str.replaceAll("[^0-9]", ""), ""));
						break;
					}else {
						if(i == infoArrList.size()-1) {
							infoScoreList.add(i+1, score);
							infoArrList.add(i+1, str.replace(str.replaceAll("[^0-9]", ""), "").split(" "));
							infoList.add(i+1, str.replace(str.replaceAll("[^0-9]", ""), ""));
							break;
						}
					}
					
				}
			}
		}
        
        for(String str : query) {
        	int score = Integer.parseInt(str.replaceAll("[^0-9]", ""));
        	queryScoreList.add(score);
        	queryList.add(str.replace("- ", "").replace("and ", "").replace(str.replaceAll("[^0-9]", ""), "").trim().split(" "));
        }
        
        int headIndex = 0;
        int index = 0;
        boolean tf = true;
        	
    	for(int x=0; x<queryList.size(); x++) {
    		headIndex = 0;
    		tf = true;
    		int queryScore = queryScoreList.get(x);
    		headIndex = findHead(queryScore,infoScoreList);
    		
    		for(int i=headIndex; i<infoList.size(); i++) {
    			if(queryList.get(x).equals("")) {
    				answer[index]++;
    			}else {
    				for(String queryStr : queryList.get(x)) {
        				if(infoList.get(i).contains(queryStr)) {
        					tf = true;
        				}else {
        					tf = false;
        					break;
        				}
        			}
    			}
    			
    			if(tf) answer[index]++;
    		}
    		index++;
    	}


        return answer;
    }
	
	public static int[]  solution5(String[] info, String[] query) {
		int[] answer = new int[query.length];
		
		List<Integer> infoScoreList = new LinkedList<>();
		List<String[]> infoArrList = new LinkedList<>();
		List<String> infoList = new LinkedList<>();
		List<Integer> queryScoreList = new LinkedList<>();
		List<String[]> queryList = new LinkedList<>();
//        Map<Integer, String[]> queryMap = new LinkedHashMap<>();
		
		int indexNum = 0;
		for(String str : info) {
			int score = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			if(infoScoreList.isEmpty()) {
				infoScoreList.add(score);
				infoArrList.add(str.replace(str.replaceAll("[^0-9]", ""), "").split(" "));
				infoList.add(str.replace(str.replaceAll("[^0-9]", ""), ""));
			}else {
				for(int i = 0; i<infoScoreList.size(); i++) {
					indexNum = infoScoreList.get(i);
					if(score <= indexNum) {
						infoScoreList.add(i, score);
						infoArrList.add(i, str.replace(str.replaceAll("[^0-9]", ""), "").split(" "));
						infoList.add(i, str.replace(str.replaceAll("[^0-9]", ""), ""));
						break;
					}else {
						if(i == infoArrList.size()-1) {
							infoScoreList.add(i+1, score);
							infoArrList.add(i+1, str.replace(str.replaceAll("[^0-9]", ""), "").split(" "));
							infoList.add(i+1, str.replace(str.replaceAll("[^0-9]", ""), ""));
							break;
						}
					}
					
				}
			}
		}
		
		for(String str : query) {
			int score = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			queryScoreList.add(score);
			queryList.add(str.replace("- ", "").replace("and ", "").replace(str.replaceAll("[^0-9]", ""), "").trim().split(" "));
//        	queryMap.put(score, str.replace("- ", "").replace("and ", "").replace(str.replaceAll("[^0-9]", ""), "").trim().split(" "));
		}
		
		int headIndex = 0;
		int index = 0;
		boolean tf = true;
		
		
		
		
		for(int x=0; x<queryList.size(); x++) { //String[] queryArrStr :
			headIndex = 0;
			tf = true;
			int queryScore = queryScoreList.get(x);
			headIndex = findHead(queryScore,infoScoreList);
			
			for(int i=headIndex; i<infoList.size(); i++) {
				if(queryList.get(x).equals("")) {
					answer[index]++;
				}else {
					for(String queryStr : queryList.get(x)) {
						if(infoList.get(i).contains(queryStr)) {
							tf = true;
						}else {
							tf = false;
							break;
						}
					}
				}
				
				if(tf) answer[index]++;
			}
			index++;
		}
		
		
		
		for(int a : answer) {
			System.out.print(a);
		}
		System.out.println();
		
		return answer;
	}
	
	public static int findHead(int queryScore, List<Integer> infoScoreList) {
		int head = infoScoreList.size();
		
		for(int i=0; i<infoScoreList.size(); i++) {
			if(queryScore > infoScoreList.get(i)) {
				continue;
			}else {
				head = i;
				break;
			}
		}
		
		return head;
	}
	
	public static int[]  solution4(String[] info, String[] query) {
		int[] answer = new int[query.length];
		
//        List<Map<Integer, String>> infoList = new ArrayList<>();
		TreeMap<Integer, String> infoMap = new TreeMap<>();
		for(String str : info) {
			//Map<Integer, String> map = new HashMap<>();
			int score = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			infoMap.put(score, str.replace(str.replaceAll("[^0-9]", ""), "").trim());
			//map.put(score, str.replace(str.replaceAll("[^0-9]", ""), "").trim());
		}
		
		Map<Integer, String[]> queryMap = new HashMap<>();
		
		for(String str : query) {
			int score = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			queryMap.put(score, str.replace("- ", "").replace("and ", "").replace(str.replaceAll("[^0-9]", ""), "").trim().split(" "));
		}
		int headIndex = 0;
		int index = 0;
		boolean tf = true;
		
		for(Entry<Integer, String[]> qEntry : queryMap.entrySet()) {
			index = 0;
//        	for(int i=headIndex; i<infoMap.size(); i++) {
			for(Entry<Integer, String> iEntry : infoMap.entrySet()) {
				tf = true;
				if(iEntry.getKey() >= qEntry.getKey()) {
					//if(infoList.get(i).firstKey() >= qEntry.getKey()) {
					for(String q : qEntry.getValue()) {
						if(!iEntry.getValue().contains(q)) {
							tf = false;
							break;
						}
					}
				}else {
					headIndex++;
					tf = false;
				}
				
				if(tf) answer[index++]++;
				
			}
		}
		
//      for(Entry<Integer, String[]> qEntry : queryMap.entrySet()) {
//    	boolean tf = true;
//    	int queryScore = qEntry.getKey();
//    	TreeMap<Integer, String> newMap = infoMap;
//    	List<Integer> minuse = new ArrayList<>();
//    	for(Entry<Integer, String> iEntry : newMap.entrySet()) {
//    		tf = true;
//    		if(iEntry.getKey() >= queryScore) {
//    			for(String queryItem : qEntry.getValue()) {
//        			if(!iEntry.getValue().contains((queryItem))) {
//        				tf = false;
//        				break;
//        			}
//        		}
//    		}else {
//    			minuse.add(iEntry.getKey());
//    			tf = false;
//    		}
//    		
//    		if(tf) answer[index]++;
//
//    	}
//    	
//    	for(int a : minuse) {
//    		newMap.remove(a);
//    	}
//    	
//    	
//    	index++;
//
//    }
		
		for(int a : answer) {
			System.out.print(a);
		}
		System.out.println();
		
		return answer;
	}
	

	
	public static LinkedHashMap<Integer,String> sortMapByKey(Map<Integer,String> map){
		List<Map.Entry<Integer, String>> entries = new LinkedList<Map.Entry<Integer,String>>(map.entrySet());
		Collections.sort(entries, (s1,s2) -> s1.getKey().compareTo(s2.getKey()));
		
		LinkedHashMap<Integer,String> result = new LinkedHashMap<>();
		for(Map.Entry<Integer, String> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	//실패사례
	public static int[] solution3(String[] info, String[] query) {
		int[] answer = new int[query.length];
		int indexNum = 0;
		List<Map<Integer , String[]>> queryList = new ArrayList<>();
		String[] queryArr = new String[5];
		int index = 1;
//		for(String str : query) {
//			Map<Integer, String[]> map = new HashMap<>();
//			map.put(index++, str.replace(" and ", " ").split(" "));
//			//queryArr = str.replace(" and ", " ");
//			queryList.add(map);
//		}
		
		for(String str : query) {
			Map<Integer, String[]> map = new HashMap<>();
			int num = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			map.put(index++, str.replace(" and ", " ").split(" "));
			if(queryList.isEmpty()) {
				queryList.add(map);
			}else {
				for(int i = 0; i<queryList.size(); i++) {
					Iterator<Integer> keys = queryList.get(i).keySet().iterator();
					indexNum = Integer.parseInt(queryList.get(i).get(keys.next())[4]);
					if(num <= indexNum) {
						queryList.add(i, map);
						break;
					}else {
						if(i == queryList.size()-1) {
							queryList.add(i+1, map);
							break;
						}
					}
					
				}
			}
		}
		
		List<String> infoList = new ArrayList<>();
		for(String str : info) {
			int num = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			if(infoList.isEmpty()) {
				infoList.add(str.replace(" and ", " "));
			}else {
				for(int i = 0; i<infoList.size(); i++) {
					indexNum = Integer.parseInt(infoList.get(i).replaceAll("[^0-9]", ""));
					if(num <= indexNum) {
						infoList.add(i, str.replace(" and ", " "));
						break;
					}else {
						if(i == infoList.size()-1) {
							infoList.add(i+1, str.replace(" and ", " "));
							break;
						}
					}
					
				}
			}
		}
		
		String[] infoArr = new String[5];
		for(int i=0; i<queryList.size(); i++) {
			boolean tf = true;
			int queryScore = Integer.parseInt(queryList.get(i).get(i)[4]);
			for(int j=0; j<infoList.size(); j++) {
				int infoScore = Integer.parseInt(infoList.get(j).replaceAll("[^0-9]", ""));
				if( infoScore >=  queryScore) {
					infoArr = infoList.get(j).split(" ");
					for(int k=0; k<infoArr.length-1; k++) {
						if(infoScore >= queryScore) {
							if(queryArr[k].equals("-") || queryArr[k].equals(infoArr[k])) {
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
						Iterator<Integer> keys = queryList.get(i).keySet().iterator();
						answer[keys.next()]++;
					}
				}
//        		else {
//        			infoList.remove(j);
//        			j=0;
//        			continue;
//        		}
			}
		}
		
		return answer;
	}
	
	public static int[] solution2(String[] info, String[] query) {
		
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
		for(String[] qStr : queryList) {
			boolean tf = true;
			int queryScore = Integer.parseInt(qStr[4]);
			for(String[] iStr : infoList) {
				int infoScore = Integer.parseInt(iStr[4]);
				
				for(int i=0;i<iStr.length-1; i++) {
					String queryStr = qStr[i];
					if(infoScore >= queryScore) {
						if(queryStr.equals("-") || queryStr.equals(iStr[i])) {
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
	
	Node rootNode = null;
	
	public void insertNode(int element) {
		if(rootNode == null) {
			rootNode = new Node(element);
		}else {
			Node head = rootNode;
			Node currentNode;
			
			while(true) {
				currentNode = head;
			
				if(head.value > element) {
					head = head.leftChild;
					
					if(head == null) {
						currentNode.leftChild = new Node(element);
						break;
					}
				}else {
					head = head.rightChild;
					
					if(head == null) {
						currentNode.rightChild = new Node(element);
						break;
					}
				}
			}
		}
	}
	
	public boolean removeNode(int element) {
        Node removeNode = rootNode;
        Node parentOfRemoveNode = null;

        while (removeNode.value != element) {
            parentOfRemoveNode = removeNode;

            /* 삭제할 값이 현재 노드보다 작으면 왼쪽을 탐색한다. */
            if (removeNode.value > element) {
                removeNode = removeNode.leftChild;
            } else {
                removeNode = removeNode.rightChild;
            }

            /*
             * 값 대소를 비교하며 탐색했을 때
             * 잎 노드(Leaf node)인 경우 삭제를 위한 탐색 실패
             */
            if (removeNode == null)
                return false;

        }

        /* 자식 노드가 모두 없을 때 */
        if (removeNode.leftChild == null && removeNode.rightChild == null) {
            /* 삭제 대상이 트리의 루트일 때 */
            if (removeNode == rootNode) {
                rootNode = null;
            } else if (removeNode == parentOfRemoveNode.rightChild) {
                parentOfRemoveNode.rightChild = null;
            } else {
                parentOfRemoveNode.leftChild = null;
            }
        }

        /* 오른쪽 자식 노드만 존재하는 경우 */
        else if (removeNode.leftChild == null) {
            if (removeNode == rootNode) {
                rootNode = removeNode.rightChild;
            } else if (removeNode == parentOfRemoveNode.rightChild) {
                /*
                 * 삭제 대상의 오른쪽 자식 노드를 삭제 대상 위치에 둔다.
                 */
                parentOfRemoveNode.rightChild = removeNode.rightChild;
            } else {
                parentOfRemoveNode.leftChild = removeNode.rightChild;
            }
        }

        /* 왼쪽 자식 노드만 존재하는 경우 */
        else if (removeNode.rightChild == null) {
            if (removeNode == rootNode) {
                rootNode = removeNode.leftChild;
            } else if (removeNode == parentOfRemoveNode.rightChild) {
                parentOfRemoveNode.rightChild = removeNode.leftChild;
            } else {
                /*
                 * 삭제 대상의 왼쪽 자식을 삭제 대상 위치에 둔다.
                 */
                parentOfRemoveNode.leftChild = removeNode.leftChild;
            }
        }

        /*
         * 두 개의 자식 노드가 존재하는 경우
         * 삭제할 노드의 왼쪽 서브 트리에 있는 가장 큰 값 노드를 올리거나
         * 오른쪽 서브 트리에 있는 가장 작은 값 노드를 올리면 된다.
         * 구현 코드는 2번째 방법을 사용한다.
         */
        else {
            /* 삭제 대상 노드의 자식 노드 중에서 대체될 노드(replaceNode)를 찾는다. */
            Node parentOfReplaceNode = removeNode;

            /* 삭제 대상의 오른쪽 서브 트리 탐색 지정 */
            Node replaceNode = parentOfReplaceNode.rightChild;

            while (replaceNode.leftChild != null) {
                /* 가장 작은 값을 찾기 위해 왼쪽 자식 노드로 탐색한다. */
                parentOfReplaceNode = replaceNode;
                replaceNode = replaceNode.leftChild;
            }

            if (replaceNode != removeNode.rightChild) {
                /* 가장 작은 값을 선택하기 때문에 대체 노드의 왼쪽 자식은 빈 노드가 된다. */
                parentOfReplaceNode.leftChild = replaceNode.rightChild;

                /* 대체할 노드의 오른쪽 자식 노드를 삭제할 노드의 오른쪽으로 지정한다. */
                replaceNode.rightChild = removeNode.rightChild;
            }

            /* 삭제할 노드가 루트 노드인 경우 대체할 노드로 바꾼다. */
            if (removeNode == rootNode) {
                rootNode = replaceNode;
            } else if (removeNode == parentOfRemoveNode.rightChild) {
                parentOfRemoveNode.rightChild = replaceNode;
            } else {
                parentOfRemoveNode.leftChild = replaceNode;
            }

            /* 삭제 대상 노드의 왼쪽 자식을 잇는다. */
            replaceNode.leftChild = removeNode.leftChild;
        }

        return true;
    }

    /**
     * 중위 순회
     */
    public void inorderTree(Node root, int depth) {
        if (root != null) {
            inorderTree(root.leftChild, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("ㄴ");
            }
            System.out.println(root.value);
            inorderTree(root.rightChild, depth + 1);
        }
    }

    /**
     * 후위 순회
     */
    public void postorderTree(Node root, int depth) {
        if (root != null) {
            postorderTree(root.leftChild, depth + 1);
            postorderTree(root.rightChild, depth + 1);
            for (int i = 0; i < depth; i++) {
                System.out.print("ㄴ");
            }
            System.out.println(root.value);
        }
    }

    /**
     * 전위 순회
     */
    public void preorderTree(Node root, int depth) {
        if (root != null) {
            for (int i = 0; i < depth; i++) {
                System.out.print("ㄴ");
            }
            System.out.println(root.value);
            preorderTree(root.leftChild, depth + 1);
            preorderTree(root.rightChild, depth + 1);
        }
    }

}

class Node{
	int value;
	Node leftChild;
	Node rightChild;
	
	public Node(int value) {
		this.value = value;
		this.leftChild = null;
		this.rightChild = null;
	}
}