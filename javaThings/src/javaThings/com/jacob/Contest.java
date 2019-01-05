package javaThings.com.jacob;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Contest {
	
public boolean isAlienSorted(String[] words, String order) {

	String[] orderLetters = order.split("");
    
	HashMap<String, Integer>  hash = new HashMap<String, Integer>();
     
	for(int i = 0; i < orderLetters.length; i++) {
		hash.put(orderLetters[i], i);
	}
     
	for(int i = 0; i < words.length - 1; i++) {
		String wordLettersfirst = words[i].substring(0, 1);
		String wordLetterssecond = words[i + 1].substring(0, 1);
		
		if(hash.get(wordLettersfirst) > hash.get(wordLetterssecond)) {
			return false; 
		}
		
			if(hash.get(wordLettersfirst) < hash.get(wordLetterssecond)) {
				continue; 
			}

			if(hash.get(wordLettersfirst) < hash.get(wordLetterssecond) || ((hash.get(wordLettersfirst) == hash.get(wordLetterssecond)) && (words[i].length() > words[i + 1].length()))) {
				System.out.println("middle");

				return false; 
			}
			
			String[] first = words[i].split("");
			String[] second = words[i + 1].split("");

			for(int j = 0; j < first.length; j++) {
				
				if((first.length <= second.length) && hash.get(first[j]) > hash.get(second[j])) {
					return false;
				}
			}
			
	}
	

	
	return true; 
    }

	public static void main(String[] args) {
		
		Contest test = new Contest();
//		String[] words = {"kuvp","q"};
//		String order = "ngxlkthsjuoqcpavbfdermiywz";
		
//		String[] words = {"word","world","row"};
//		String order = "worldabcefghijkmnpqstuvxyz";
//		

//				String[] words = {"hello","leetcode"};
//		String order = "hlabcdefgijkmnopqrstuvwxyz";
//		
		
		String[] words = {"my","f"};
	String order = "gelyriwxzdupkjctbfnqmsavho";
	
	
		System.out.println(test.isAlienSorted(words, order));
		
		
//		String[] words = {"apple","app"};
//		String order = "abcdefghijklmnopqrstuvwxyz";
		
//		
//		String[] words = {"word","world","row"};
//		String order = "worldabcefghijkmnpqstuvxyz";
//				
//		
//		String name = "jacob"; 
//		System.out.println(name.substring(0, 1));
		
			
	}
}
