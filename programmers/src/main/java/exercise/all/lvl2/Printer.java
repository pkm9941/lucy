package exercise.all.lvl2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Printer {
	
	public int getOrderMyPrint(int[] priorities, int location) {
		if (!passedRestrictions(priorities, location))
		return -1;
		
		Queue<int[]> printWaitingQueue = generatePrintWatingQueue(priorities);
		
		Stack<Integer> maxPriorities = generateSortedPriorities(priorities);
		
		while(!printWaitingQueue.isEmpty()) {
			int[] headElement = printWaitingQueue.poll();
			int maxPriority = maxPriorities.pop();
			
			if (maxPriority(headElement[0], maxPriority)) {
				if (isThePrint(headElement[1], location))
					return sumPrinted(priorities.length, printWaitingQueue.size());
				
			} else {
				printWaitingQueue.add(headElement);
				maxPriorities.push(maxPriority);
			}
		}
		
		return -1;
	}

	private Queue<int[]> generatePrintWatingQueue(int[] priorities) {
		Queue<int[]> printWaitingQueue = new LinkedList<int[]>();
		for(int i = 0; i < priorities.length; i++) {
			printWaitingQueue.add(new int[]{priorities[i], i});
		}
		return printWaitingQueue;
	}

	private Stack<Integer> generateSortedPriorities(int[] priorities) {
		int[] sortedPriorities = priorities;
		Arrays.sort(sortedPriorities);
		Stack<Integer> maxPriorities = new Stack<>();
		for(int i = 0; i < priorities.length; i++) {
			maxPriorities.push(priorities[i]);
		}
		return maxPriorities;
	}

	private boolean maxPriority(int headPrintPriority, int maxPriority) {
		return headPrintPriority == maxPriority;
	}

	private boolean isThePrint(int location, int headLocation) {
		return headLocation == location;
	}

	private int sumPrinted(int totalPrint, int remainedPrint) {
		return totalPrint - remainedPrint;
	}

	

	

	

	

//	public int getOrderMyPrint(int[] priorities, int location) {
//        int myPrintOrder = 0;
//        int currentLocation = location;
//
//        Queue<Integer> que = new LinkedList<Integer>();
//        for(int i : priorities){
//            que.add(i);
//        }
//        int[] sortedPriorities = priorities;
//        Arrays.sort(sortedPriorities);
//        int size = priorities.length-1;
//
// 
//
//        while(!que.isEmpty()){
//            Integer priority = que.poll();
//            if(priority == priorities[size - myPrintOrder]){//가장 큰 값이면
//                myPrintOrder++;
//                currentLocation--;
//                if(currentLocation <0)//내 프린트물이면
//                    break;
//            }else{
//                que.add(priority);
//                currentLocation--;
//                if(currentLocation<0)
//                    currentLocation=que.size()-1;
//            }
//        }
//
//        return myPrintOrder;
//    }

//	public int getOrderMyPrint(int[] priorities, int location) {
//		if (!passedRestrictions(priorities, location))
//			return -1;
//		
//		int[][] prioritiesWithLocation = makePrioritiesWithLocation(priorities);
//		
//		int[][] orderedPrioritiesWithLocation = sortPriorities(prioritiesWithLocation, 0);
//		
//		return getOrderTheLocation(orderedPrioritiesWithLocation, location);
//	}

	private boolean passedRestrictions(int[] priorities, int location) {
		if (priorities == null || priorities.length == 0)
			return false;
		
		if (priorities.length > 100)
			return false;
		
		if (location < 0)
			return false;
		
		if (location >= priorities.length)
			return false;
		
		return true;
	}

	private int[][] makePrioritiesWithLocation(int[] priorities) {
		int[][] prioritiesWithLocation = new int[priorities.length][2];
		for (int i = 0; i < priorities.length; i++) {
			prioritiesWithLocation[i][0] = priorities[i];
			prioritiesWithLocation[i][1] = i;
		}
		return prioritiesWithLocation;
	}

	private int[][] sortPriorities(int[][] priorities, int baseIndex) {
		if (baseIndex == priorities.length - 1)
			return priorities;
		
		if (isGreaterThanRightPriorities(priorities, baseIndex)) {
			return sortPriorities(priorities, ++baseIndex);
		} else {
			int[][] movedPriorities = moveToTail(priorities, baseIndex);
			return sortPriorities(movedPriorities, baseIndex);
		}
	}

	private boolean isGreaterThanRightPriorities(int[][] priorities, int baseIndex) {
		int basePriority = priorities[baseIndex][0];
		for (int i = baseIndex + 1; i < priorities.length; i++) {
			if (basePriority < priorities[i][0])
				return false;
		}
		return true;
	}

	private int[][] moveToTail(int[][] priorities, int baseIndex) {
		int[][] movedPriorities = new int[priorities.length][priorities[0].length];
		for (int i = 0; i < priorities.length; i++) {
			if (i == priorities.length - 1) {
				movedPriorities[i] = priorities[baseIndex];
				break;
			}
			
			if (i < baseIndex) {
				movedPriorities[i] = priorities[i];
			} else {
				movedPriorities[i] = priorities[i + 1];
			}
		}
		return movedPriorities;
	}

	private int getOrderTheLocation(int[][] priorities, int location) {
		for (int i = 0; i < priorities.length; i++) {
			if (priorities[i][1] == location)
				return i + 1;
		}
		return -1;
	}

}
