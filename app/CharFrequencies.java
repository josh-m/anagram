package anagram.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/*	
	CharFrequencies counts the number of each
	unique character in a string. 
*/
class CharFrequencies extends HashMap<Character, Integer>{
	public String str = null;
	
	public CharFrequencies(String word){
		countCharacters(word);
	}
	
	private void countCharacters(String word){
		Integer count;
		for (int i=0, n=word.length(); i<n; i++){
			Character c = word.charAt(i);
			if ((count = get(c)) != null)
				put(c, count+1);
			else
				put(c, 1);
		}	
	}
}