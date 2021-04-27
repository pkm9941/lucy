package exercise.all.lvl2.largestNumber.nohhyungrae;

import java.util.LinkedList;
import java.util.List;

public class LargestNumber {
	
	public static void main(String[] args) {
		
		int[] num = {3, 30, 34, 5, 9};
		int[] num2 = {6, 10, 2, 0};
		int[] num3 = {1000, 90, 200, 534, 530, 22, 29, 4};
		int[] num4 = {1000, 200, 534, 530, 22, 29, 4}; //534530429222001000
//		int[] num4 = {534, 4, 29, 22, 530, 200, 1000}; //534530429222001000
		int[] num5 = {0, 0, 0, 0, 0};
		System.out.println("solution1 : "+solution(num));
		System.out.println("solution2 : "+solution(num2));
		System.out.println("solution3 : "+solution(num3));
		System.out.println("solution4 : "+solution(num4));
		System.out.println("solution5 : "+solution(num5));
	}
	
	
	public static String solution(int[] numbers) {
        String answer = "";
        String numb ="";
        List<Integer> numlist = new LinkedList<>();
        if(numbers.length <= 100000){
        	for(int i =0; i<numbers.length; i++) {
        		if(numlist.isEmpty()) {
//        			System.out.println("numbers[i] : "+numbers[i]);
//        			System.out.println("isEmpty : "+numlist.toString());
        			numlist.add(numbers[i]);
        		} else {
        			for(int j=0;j<numlist.size(); j++) {
//        				System.out.println("numbers[i] : "+numbers[i]+", numlist.get(j) : "+numlist.get(j));
//            			System.out.println("isNotEmpty : "+numlist.toString());
        				Long a = Long.parseLong(numbers[i]+""+numlist.get(j));
        				Long b = Long.parseLong(numlist.get(j)+""+numbers[i]);
        				if(a >= b) {
        					numlist.add(j, numbers[i]);
    						break;
        				}else {
        					if(numlist.size() == 1) {
        						numlist.add(i, numbers[i]);
        					} else {
            					tf(numbers[i], j+1, numlist);
        					}
        					break;
        				}
        					
        			}
        		}
        	}
        	
        	System.out.println(numlist.toString());
        	for(int a : numlist) { numb += a; }
        	//numlist.forEach(e ->  { numb += e; });
        }
        
        if(Double.parseDouble(numb) == 0) numb = "0";
       
        return numb;
    }
//	
//	public static int[] tf(int number, int count, int[] outputNum) {
//		for(int z=count; z<outputNum.length; z++) {
//			if(Long.parseLong(number+""+outputNum[z]) >= Long.parseLong(outputNum[z]+""+number)) {
////				numlist.add(z, number);
//				outputNum[z] = number;
//				break;
//			}else {
//				//if(numlist.size() == z+1 ) {
//					//numlist.add(z+1, number);
//					outputNum[z+1] = number;
//					break;
//				//}
//			}
//		}
//		return outputNum;
//	}
	public static List<Integer> tf(int number, int count, List<Integer> numlist) {
		for(int z=count; z<numlist.size(); z++) {
			Long a = Long.parseLong(number+""+numlist.get(z));
			Long b = Long.parseLong(numlist.get(z)+""+number);
			if(a >= b) {
				numlist.add(z, number);
				break;
			}else {
				tf(number,count+1,numlist);
				if(numlist.size() == z+1 ) 
					numlist.add(z+1, number);
				break;
			}
		}
		return numlist;
	}
	
//    public static String solution(int[] numbers) {
//    	String answer = "";
//        List<Integer> numlist = new LinkedList<>();
//        if(numbers.length <= 100000){
//        	for(int i =0; i<numbers.length; i++) {
//        		if(numlist.isEmpty()) {
//        			numlist.add(numbers[i]);
//        		} else {
//        			for(int j=0;j<numlist.size(); j++) {
//        				if(Long.parseLong(numbers[i]+""+numlist.get(j)) >= Long.parseLong(numlist.get(j)+""+numbers[i])) {
//        					numlist.add(j, numbers[i]);
//    						break;
//        				}else {
//        					if(numlist.size() == 1) numlist.add(i, numbers[i]);
//        					else {
//            					tf(numbers[i], j+1, numlist);
//        					}
//        					break;
//        				}
//        					
//        			}
//        		}
//        	}
//        	for(int a : numlist) {
//        		answer += a+"";
//        	}
//        }
//        return answer;
//    }
//	
//	public static List<Integer> tf(int number, int count, List<Integer> numlist) {
//		for(int z=count; z<numlist.size(); z++) {
//			if(Long.parseLong(number+""+numlist.get(count)) >= Long.parseLong(numlist.get(count)+""+number)) {
//				numlist.add(count, number);
//				break;
//			}else {
//				if(numlist.size() == z+1 ) {
//					numlist.add(z+1, number);
//					break;
//				}
//			}
//		}
//		return numlist;
//	}
	
//	
//	public static void checkValue(int af, int bf) {
//		String first = ""+af;
//		String second = ""+bf;
//		
//		if(first.length() > second.length()) {
//
//			for(int i=0; i<second.length(); i++) {
//				if(second.length() ==1) {
////					numlist.add(second);
////					numlist.add(first);
//					break;
//				}
//			}
//		}else if(second.length() > first.length()){
//			
//		}else {
//			
//		}
//	}
//	
//	if(numlist.size() == numbers.length) break;
//	
//	String a = ""+numbers[i];
//	for(int j=i+1; j<numbers.length; j++) {
//		String b = ""+numbers[j];
//		if(numlist.isEmpty()) {
//			System.out.println("a : "+a+", b : "+b);
//			System.out.println("isEmpty : "+numlist.toString());
//			if(Long.parseLong(a+b) > Long.parseLong(b+a)) {
//				numlist.add(a);
//				numlist.add(b);
//			}else {
//				numlist.add(b);
//				numlist.add(a);
//			}
//			break;
//		}else {
//			System.out.println("a : "+a+", b : "+b);
//			System.out.println("isNotEmpty : "+numlist.toString());
//			if(Long.parseLong(a+b) > Long.parseLong(b+a)) {
//				for(int z=i+1; z<numlist.size(); z++) {
//					if(Long.parseLong(b+numlist.get(z)) >= Long.parseLong(numlist.get(z)+b)) {
//						numlist.add(j, b);
//						break;
//					}else {
//						continue;
//					}
//				}
//			}else {
//
//				for(int z=0; z<numlist.size(); z++) {
//					if(Long.parseLong(b+numlist.get(z)) >= Long.parseLong(numlist.get(z)+b)) {
//						numlist.add(z, b);
//						break;
//					}else {
//						continue;
//					}
//				}
//				
//			}
//			
//		}
//	}
//}
//System.out.println(numlist.toString());
//numlist.forEach(e -> {numb += e;});
	
	/****************************************************************************************************/
	//팩토리얼 구하기
//	public static int fact(int n) {
//		if (n <= 1)
//			return n;
//		else 
//			return fact(n-1) * n;
//	}
	
	/****************************************************************************************************/
	//재귀1-1
//	public static String solution11(int[] numbers) {
//		String answer = "";
//		boolean[] isVisit = new boolean[numbers.length];
//		int[] output = new int[numbers.length];
//		if(numbers.length <= 100000){
//			
//			for(int i=0 ;i<=numbers.length; i++) {
//				addNum(numbers,isVisit, output,0,numbers.length ,i);
//			}
//			
//		}
//		System.out.println(numlist.toString());
//		return Collections.max(numlist);
//	}
//	
//	public static void addNum(int[] numbers,boolean[] isVisit,int[] output,int depth, int length, int count) {
//		if(count==0) {
//			if(output.length == length) {
//				for(int a : output) {
//					numb += a;
//				}
//				numlist.add(numb);
//				numb ="";
//			}
//			return;
//		}
//		for(int i=0; i<length; i++) {
//			if(output[depth] == 0) {
//				isVisit[i]=true;
//				output[depth] = numbers[i];
//				addNum(numbers,isVisit ,output, depth+1, length, count-1);
//				isVisit[i] = false;
//			}
//		}
//	}
	/****************************************************************************************************/
	//재귀 1-2
//	public static String solution12(int[] numbers) {
//        String answer = "";
//        boolean[] isVisit = new boolean[numbers.length];
//        int[] output = new int[numbers.length];
//        if(numbers.length <= 100000){
//        	for(int i =1; i<=numbers.length; i++) {
//        		addCnt(numbers, isVisit, output, new boolean[numbers.length], 0, numbers.length);
//        	}
//        }
//        return numb;
//    }
//	
//	public static void addCnt(int[] input, boolean[] isOutput, int[] output, boolean[] isDepth, int depth, int count) {
//		if(count==0) {
//			String nextNumb = "";
//			for(int i=0;  i<output.length; i++) {
//				if(isOutput[i]) {
//					if(numb == "") numb +=output[i];
//					else nextNumb +=output[i];
//				}
//			}
//			if(nextNumb !="" && Long.parseLong(nextNumb) > Long.parseLong(numb)) {
//				numb = nextNumb;
//			}
//			
//			return;
//		}
//		
//		for(int i=0; i<output.length; i++) {
//			if(isDepth[i]!=true) {
//				isDepth[i] = true;
//				output[depth] = input[i];
//				isOutput[depth] = true;
//				
//				addCnt(input, isOutput, output, isDepth, depth+1, count-1);
//				isDepth[i] = false;
//				isOutput[depth] = false;
//			}
//		}
//	}
	
	/****************************************************************************************************/
	//재귀
//	public static String solution3(int[] numbers) {
//        String answer = "";
//        boolean[] isVisit = new boolean[numbers.length];
//        int[] output = new int[numbers.length];
//        if(numbers.length <= 100000){
//            
//            for(int i=0 ;i<=numbers.length; i++) {
//            	addNum(numbers,output,isVisit,0,numbers.length ,i);
//            }
//            
//         }
//        System.out.println(numlist.toString());
//        return Collections.max(numlist);
//    }
//	
//	public static void addNum(int[] numbers,int[] output,boolean[] isVisit,int depth, int length, int count) {
//		if(count==0) {
//			if(output.length == length) {
//				for(int a : output) {
//					numb += a;
//				}
//				if(!numlist.contains(numb)) {
//					numlist.add(numb);
//				}
//				numb ="";
//			}
//			return;
//		}
//		for(int i=0; i<length; i++) {
//			if(isVisit[i]!=true) {
//				isVisit[i] = true;
//				output[depth] = numbers[i];
//				addNum(numbers, output, isVisit, depth+1, length, count-1);
//				isVisit[i] = false;
//			}
//		}
//	}
	
	/****************************************************************************************************/
	//char 체크1
//	public String solution4(int[] numbers) {
//        String answer = "";
//        if(numbers.length < 100000){
//           List<String> num = new ArrayList<String>();
//            for(int a :  numbers) { 
//                if(a <= 1000) num.add(""+a); 
//            }
//
//            for(int i=0; i<num.size(); i++) {
//                for(int j= i+1; j<num.size(); j++ ) {
//                    num = characterCheck1(i, j, num);
//                }
//            }
//
//            for(String a :  num) {
//                answer += a;
//            } 
//        }
//        
//        return answer;
//    }
//	
//	public static List<String> characterCheck1(int i, int j, List<String> num) {
//		String af = num.get(i);
//		String bf = num.get(j);
//		if(af.length() > bf.length()){
//			characterCheck2(af, bf,i,j,num);
//		}else {
//			characterCheck2(bf, af,i,j,num);
//		}
//		
//		return num;
//	}
//	
//	public static void characterCheck2(String bigLen, String lowLen,int i,int j, List<String> num) {
//		for(int x = 0; x<lowLen.length(); x++) {
//			int af = Integer.parseInt(""+num.get(i).charAt(x));
//			int bf = Integer.parseInt(""+num.get(j).charAt(x));
//			System.out.println("af : "+af+" , bf : "+bf);
//			if(af < bf) {
//				String a = num.get(i);
//				num.set(i, num.get(j));
//				num.set(j, a);
//				break;
//			}else  if(af == bf) {
//				if(num.get(i).length() < num.get(i).length() && bf < Integer.parseInt(""+num.get(j).charAt(x+1))) {
//					String a = num.get(i);
//					num.set(i, num.get(j));
//					num.set(j, a);
//					break;
//				}
//			}else {
//				break;
//			}
//		}
//	}
	
/****************************************************************************************************/
	//char 체크2
//	public static void characterCheck2(String bigLen, String lowLen,int i,int j, List<String> num) {
//		for(int x = 0; x<lowLen.length(); x++) {
//			if (!characterCheck3(i,j,x, num)) {
//				String a = num.get(i);
//				num.set(i, num.get(j));
//				num.set(j, a);
//				break;
//			}
//			
//		}
//	}
//	
//	public static boolean characterCheck3(int i, int j, int x, List<String> num) {
//		boolean ch = true;
//		int af = Integer.parseInt(""+num.get(i).charAt(x));
//		int bf = Integer.parseInt(""+num.get(j).charAt(x));
//		System.out.println("af : "+af+" , bf : "+bf);
//		if(af < bf) {
//			ch = false;
//		}else  if(af == bf) {
//			if(num.get(i).length() < num.get(i).length() && bf < Integer.parseInt(""+num.get(j).charAt(x+1))) {
//				ch = false;
//			}
//		}
//		return ch;
//	}

	
}
