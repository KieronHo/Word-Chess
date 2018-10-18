import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DictionaryTest {

	@Test
	void testGetWords() {
		Dictionary newDictionary = new Dictionary();
		assertEquals("AARDVARK", newDictionary.words[0]);
		assertEquals("ZULUS", newDictionary.words[newDictionary.words.length - 1]);
		
	}

}
