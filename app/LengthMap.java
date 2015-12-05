package anagram.util;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;


public class LengthMap extends HashMap<Integer, ArrayList<String>>{

	public LengthMap(){
		readFile();
	}

	/*	readFile:
		Reads all words into a HashMap, with word lengths as keys to
		a list of words of that length.
		
		Returns true on success, false if the file was not read.
	*/	
	private Boolean readFile(){
		FileInputStream infile = null;
		BufferedReader buff = null;
		
		try{
			infile = new FileInputStream("wordsEn.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
			return false;
		}
		
		buff = new BufferedReader(new InputStreamReader(infile));
		
		String word;
		int word_length;
		ArrayList<String> word_list;
		
		try{
			while ((word = buff.readLine()) != null){
				//Sanitize by trimming any whitespace, converting to lowercase
				word = word.trim();
				word = word.toLowerCase();
			
				word_length = word.length();
				//Null or 1 character words cannot have anagrams
				if (word_length > 1){
					word_list = get(word_length);
					if (word_list == null){
						//Start new list for this length
						put(word_length, new ArrayList<String>(Arrays.asList(word)));
					}
					else{
						word_list.add(word);
					}
				}
			}
		}
		catch(IOException e){
			return false;
		}
		
		try{
			buff.close();
			infile.close();
		}
		catch(IOException e){}
		
		return true;
	}

	public Boolean hasWord(String word){
		int len = word.length();
		ArrayList<String> ls = get(len);
		
		if (ls.contains(word))
			return true;
		else
			return false;
	}
}