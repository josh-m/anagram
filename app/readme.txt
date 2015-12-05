Anagram Program
Josh Minor
Written in Java SE 7, uses only standard libraries.

To run (from the 'app' folder):
java anagram.Program

To build:
javac -d . CharFrequencies.java
javac -d . LengthMap.java
javac -d . AnagramChecker.java
javac -d . Program.java

Space Complexity:
-Keeps a list of all n words in the dictionary. O(n)
-Counting the k characters in candidate words uses a hashmap
with up to k keys and k values. O(k)

Time Complexity:
-Before starting the input loop, the n words in the dictionary are read. O(n)
-Testing words of matching length k to see if they are anagrams is accomplished
by retrieving the list of words with the same length O(1) and counting the frequencies. O(n*k)