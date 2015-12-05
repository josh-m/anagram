package anagram.util;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import anagram.util.LengthMap;
import anagram.util.CharFrequencies;

public class AnagramChecker{
	private LengthMap master_list = null;
	
	public AnagramChecker(LengthMap length_map){
		master_list = length_map;
	}
	
	/*	checkForAnagrams:
		Returns a list of anagrams for the given String, str.
	*/
	public ArrayList<String> checkForAnagrams(String str){
		ArrayList<String> anagrams = new ArrayList<String>();
		
		//Get the list of words of the same length as str
		ArrayList<String> candidate_words = master_list.get(str.length());
		
		CharFrequencies my_freq = new CharFrequencies(str);
		
		for (String word: candidate_words){
			if (!word.equals(str)){
				CharFrequencies this_freq = new CharFrequencies(word);
				
				if (isAnagram(this_freq, my_freq))
					anagrams.add(word);
			}
		}
		
		return anagrams;
	}
	
	/*	isAnagram:
		Tests whether two character frequencies are identical.
		If so, the corresponding strings are anagrams as long
		as the strings themselves are not identical.
	*/
	private Boolean isAnagram(CharFrequencies freqA, CharFrequencies freqB){
		if (freqA.equals(freqB))
			return true;
		else
			return false;
	}
}

