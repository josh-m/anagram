package anagram;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.ArrayList;

import anagram.util.LengthMap;
import anagram.util.AnagramChecker;


public class Program{
	
	private static LengthMap sorted_words = null;
	private static AnagramChecker anagrams = null;

	public static void main(String[] args){
		//Contains a list of all known words
		sorted_words = new LengthMap(); 
		anagrams = new AnagramChecker(sorted_words);
		
		process_input();
	}
	
	//Performs the input loop
	private static void process_input(){
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String str = null;

		while(true){
			Boolean checking_str = true;
			
			System.out.print("Enter a word or <Enter> to exit: ");
			try{
				str = console.readLine().trim();
				
				if (str.contains(" "))
					throw new MultipleWordsException("Input a single word.");
				if (str.isEmpty())
					throw new NoWordException();
			}
			catch (IOException|NoWordException e){
				String err_msg = e.getMessage();
				if (err_msg != null)
					System.out.println(e.getMessage());
				break;
			}
			catch (MultipleWordsException e){
				String err_msg = e.getMessage();
				if (err_msg != null)
					System.out.println(e.getMessage());
				checking_str = false;
			}
			
			if (checking_str){
				String sanitized_str = str.toLowerCase();
				
				//If the word is in the word list, request its anagrams
				if (sorted_words.hasWord(sanitized_str)){
					ArrayList<String> found_anagrams = anagrams.checkForAnagrams(sanitized_str);
					int len = found_anagrams.size();
					for (int i=0; i<len; i++){
						found_anagrams.set(i, new String(match_case(str,found_anagrams.get(i))));
					}
					System.out.println(found_anagrams);
				}
				else
					System.out.println("The word \""+str+"\" is not in the word list.");

			}
		}
	}
	
	//Returns a string with the characters of src matching the case of cmp
	private static String match_case(String src, String cmp){
		int length = src.length();
		if (length != cmp.length())
			return null;
		
		char[] ret = new char[length]; 
		for (int i=0; i<length; i++){
			if (Character.isUpperCase(src.charAt(i))){
				ret[i] = Character.toUpperCase(cmp.charAt(i));
			}
			else
				ret[i] = Character.toLowerCase(cmp.charAt(i));
		}
		
		return new String(ret);
	}
}

class MultipleWordsException extends Exception{
	public MultipleWordsException(){}
    public MultipleWordsException(String message){super(message);}
    public MultipleWordsException(Throwable cause){super(cause);}
    public MultipleWordsException(String message, Throwable cause){super(message,cause);}
}

class NoWordException extends Exception{
	public NoWordException(){}
    public NoWordException(String message){super(message);}
    public NoWordException(Throwable cause){super(cause);}
    public NoWordException(String message, Throwable cause){super(message,cause);}
}

