package javaThings.com.jacob;

import java.util.ArrayList;
import java.util.List;

public class NewContest {
	
	public boolean meth(int[] input) {
		
		List<List<Integer>> permute = permute(input);
		
		
		for(int i = 0; i < permute.size(); i++) {
			
			List<Integer> ourList = permute.get(i);
			
			for(int j = 0; j < (ourList.size() / 2); j++) {
				
				if(ourList.get(2 * j + 1) == 2 * ourList.get(2 * j)) {
					return true; 
				}
				
			}
			
		}
		
		return false; 
		
	}
	
	public List<List<Integer>> permute(int[] arr) {
		List<List<Integer>> list = new ArrayList<>();
		permuteHelper(list, new ArrayList<>(), arr);
		return list;
	}
 
	private void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int [] arr){
 
		if(resultList.size() == arr.length){
			list.add(new ArrayList<>(resultList));
		} 
		else{
			for(int i = 0; i < arr.length; i++){ 
 
				if(resultList.contains(arr[i])) 
				{
					continue; 
				}

				resultList.add(arr[i]);

				permuteHelper(list, resultList, arr);

				resultList.remove(resultList.size() - 1);
			}
		}
	} 
	
	
	public static void main(String[] args) {
		NewContest test = new NewContest(); 

		int[] arr = {2,1,2,6};

		
		
		
		System.out.println(test.meth(arr));
		
		System.out.println(2 * 0 + 1);
		
		
	}
	
		
		
	}

//
//class Solution {
//    public boolean canReorderDoubled(int[] A) {
//        
//        if(A.length < 1) {
//            return true; 
//        }
//        
//        int first = A[0];
//        boolean flag = true; 
//        for(int i = 1; i < A.length; i++) {
//            if(A[i] != first) {
//                flag = false; 
//            }
//        
//        }
//        
//        if (flag == true) {
//            return true; 
//        }
//		
//		List<List<Integer>> permute = permute(A);
//		
//		
//		for(int i = 0; i < permute.size(); i++) {
//			
//			List<Integer> ourList = permute.get(i);
//			
//			for(int j = 0; j < (ourList.size() / 2); j++) {
//				
//				if(ourList.get(2 * j + 1) == 2 * ourList.get(2 * j)) {
//					return true; 
//				}
//				
//			}
//			
//		}
//		
//		return false; 
//		
//	}
//	
//	public List<List<Integer>> permute(int[] arr) {
//		List<List<Integer>> list = new ArrayList<>();
//		permuteHelper(list, new ArrayList<>(), arr);
//		return list;
//	}
// 
//	private void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int [] arr){
// 
//		if(resultList.size() == arr.length){
//			list.add(new ArrayList<>(resultList));
//		} 
//		else{
//			for(int i = 0; i < arr.length; i++){ 
// 
//				if(resultList.contains(arr[i])) 
//				{
//					continue; 
//				}
//
//				resultList.add(arr[i]);
//
//				permuteHelper(list, resultList, arr);
//
//				resultList.remove(resultList.size() - 1);
//			}
//		}
//	} 
//    }
//
//
