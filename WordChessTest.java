import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class WordChessTest {

	
	@Test
	void testFindPath() {
		Dictionary dictionary = new Dictionary();
		WordChessImp wordChess = new WordChessImp();
		String wordOne = "screen";
		String wordTwo = "paused";
		System.out.println(Arrays.toString(wordChess.findPath(dictionary.words, wordOne, wordTwo)));
		//assertEquals("Not yet implemented");
	}
	

	@Test
	void testAreNeighbours() {
		String word1 = "CAT";
		String word2 = "MAT";
		String word3 = "COT";
		assertEquals(true, WordChessImp.areNeighbours(word1, word2));
		assertEquals(false, WordChessImp.areNeighbours(word2, word3));
		assertEquals(true, WordChessImp.areNeighbours(word1,  word3));
		
		
	}
	
	@Test
	void testChartMoves() {
		
		//String[] words = {"ONE", "TWO", "THREE", "FOUR"};
		//System.out.println(Arrays.toString(words));
		//int[] paths = {0, 1, 0, 1};
		//System.out.println(Arrays.toString(paths));
		//System.out.println(Arrays.toString(WordChessImp.chartMoves(words, paths, 2, 3)));
		
	}
	
	
}
