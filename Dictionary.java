import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Dictionary {

	public String[] words;
	
	public Dictionary() {
		words = getWords();
	}
	
	
	
	public static String[] getWords() {
		String[] wordArray = null;
		ArrayList<String> wordList = new ArrayList<String>();
		File file = new File("Dictionary.txt");
		BufferedReader reader = null;
		
		//System.out.println("ready to read");
		try {
			reader = new BufferedReader(new FileReader(file));
			String newWord;
			while ((newWord = reader.readLine()) != null) {
				wordList.add(newWord);
				//System.out.println(newWord);
			}
			//System.out.println("finished populating");

			wordArray = new String[wordList.size()];
			for(int i = 0 ; i < wordList.size(); i++) {
				wordArray[i] = wordList.get(i);
			}
			//wordArray = (String[]) wordList.toArray();
			
			//System.out.println("Time to print words");
			//System.out.println(wordArray[0]);
			//System.out.println(wordArray[wordArray.length - 1]);
			
			
			reader.close();
		}
		catch (Exception e) {
			System.out.println("An error somewhere");
		}
		
		
		return wordArray;
	}
	
}
