import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Kieron Ho 20500057
 * 
 * @version 05/09/2018
 *
 */
public class WordChessImp implements WordChess {

	 /**
	  * Finds a shortest sequence of words in the dictionary such that the first word is the startWord, 
	  * the last word is the endWord, and each word is equal to the previous word with one letter changed.
	  * If no sequence is possible, an empty array is returned.
	  * This implementation uses a Breadth first search method
	  * @param dictionary The set of words that can be used in the sequence
	  * @param startWord the first word on the sequence.
	  * @param goalWord the last word in the sequence.
	  * @return an array containing a shortest sequence from startWord to endWord, in order, 
	  * using only words from the dictionary that differ by a single character.
	  */
	public String[] findPath(String[] dictionary, String startWord, String goalWord) {
		startWord = startWord.toUpperCase();
		goalWord = goalWord.toUpperCase();
		
		//Check for start and end word lengths mismatch
		if(startWord.length() != goalWord.length()) {
			return new String[0];
		}
		
		int[] paths;
		String[] words;
		int initialIndex = 0;
		Queue<Integer> initQueue = new LinkedList<Integer>();
		
		
		//populate a list of legitimate moves ie. words of appropriate size
		ArrayList<String> legitWords = new ArrayList<String>();
		for(int i = 0 ; i < dictionary.length ; i ++) {
			if(dictionary[i].length() == startWord.length()) {
			legitWords.add(dictionary[i]);
			}
		}
		
		
		//Check if start and end words are in the dictionary, then populate words array
		boolean initialExists = false;
		boolean goalExists = false;
		words = new String[legitWords.size()];
		for(int i = 0 ; i < words.length ; i++) {
			words[i] = legitWords.get(i);
			if(startWord.equals(words[i])) {
				initialIndex = i;
				initialExists = true;
			}
			else if(goalWord.equals(words[i])) {
				goalExists = true;
			}
		}
		
		
		//check if start and end words are in the dictionary
		if(!initialExists || !goalExists) {
			return new String[0];
		}
		
		
		//Make an array to record the word paths
		paths = new int[words.length];

		
		//prepare to start the search from the start to the end goal
		initQueue.add(initialIndex);
		paths[initialIndex] = initialIndex;
		
		
		//Create a set of existing possible words indexes to reduce searching times
		HashSet<Integer> legitWordsIndex = new HashSet<Integer>();
		for(int i = 0 ; i < legitWords.size(); i ++){
			legitWordsIndex.add(i);
		}
		
		
		//Start the search
		while(!initQueue.isEmpty()) {
			
			
			//make a list of indices to ignore after each iteration
			ArrayList<Integer> discard = new ArrayList<Integer>();
			

			if(!initQueue.isEmpty()) {
				int x = initQueue.remove();
				Iterator<Integer> legitWordsIndexIterator = legitWordsIndex.iterator();
				
				
				//search for neighbours to add to queue
				while(legitWordsIndexIterator.hasNext()) {
					int index = legitWordsIndexIterator.next();
					if(areNeighbours(words[x], words[index]) && !words[index].equals(startWord) && !discard.contains(index)) {
						initQueue.add(index);

						
						//check if the path meets the goal
						if(words[index].equals(goalWord)) {
							paths[index] = x;
							return chartMoves(words, paths, index);
						}
						
						
						//Otherwise, Chart the path to this new neighbour word
						paths[index] = x;
						discard.add(index);
					}
				}
			}
			legitWordsIndex.removeAll(discard);
			
		}
		//if no path is found
		return new String[0];
	}

	
	/**
	 * Checks if two words are considered neighbours in the case of one character difference
	 * @param wordOne the first word
	 * @param wordTwo the second word
	 * @return true if the two words may be considered neighbours
	 */
	public static boolean areNeighbours(String wordOne, String wordTwo) {
		int difference = 0;
		
		char[] wordOneArray = wordOne.toCharArray();
		char[] wordTwoArray = wordTwo.toCharArray();
		
		for(int i = 0 ; i < wordOne.length(); i ++) {
			if(wordOneArray[i] != wordTwoArray[i]) {
				difference++;
			}
			if(difference > 1) {
				return false;
			}
		}
		
		return difference == 1;
	}
	
	
	/**
	 * Designed to extract the generated path resulting from discovering a path to the goal
	 * @param words is an array holding possible words for moving
	 * @param paths is an array that has a record of all the search paths generated
	 * @param goalIndex is the index for the word that was sought after
	 * @return an array of strings based on the generated paths from the start word to the end word
	 */
	public static String[] chartMoves(String[] words, int[] paths, int goalIndex) {
		
		
		int current = goalIndex;
		int next = paths[goalIndex];
		ArrayList<Integer> startPath = new ArrayList<Integer>();
		startPath.add(current);
		ArrayList<Integer> goalPath = new ArrayList<Integer>();
		while(current != next) {
			startPath.add(next);
			current = next;
			next = paths[current];
		}

		
		int[] pathIndices = new int[startPath.size() + goalPath.size()];
		String[] pathStrings = new String[pathIndices.length];
		int position = 0;
		

		while(!startPath.isEmpty()) {
			pathIndices[position] = startPath.remove(startPath.size() - 1);
			position++;
		}
		
		
		//Convert the pathIndices into pathStrings
		for(int i = 0 ; i < pathIndices.length ; i++) {
			pathStrings[i] = words[pathIndices[i]];
		}
		
		return pathStrings;
	}
}
