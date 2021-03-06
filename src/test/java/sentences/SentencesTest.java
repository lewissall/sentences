package sentences;

import sentences.Sentences.NotSingleSentenceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.lang.Number;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
*  Unit tests for the Sentences parser app.
*/

public class SentencesTest{


	/**
	* Tests from the "Word" sub-suite.
	*/
	@Nested
	public class WordTests{

		@ParameterizedTest
		@CsvFileSource(resources = "/Word/Case-Sentitivity/Verify words are matched regardless of case.csv") 
		@DisplayName ("Verify words are matched regardless of case.")
		@Tag ("Unit")
		public void matchAnyCaseTest(String sentence, int expectedLength, String longestWord){

			Map<String, Object> results = null;


			Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
			Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


			List expectedLongestWords = new ArrayList<String>();

			while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
				String expectedWordMatch = expectedWordMatcher.group(1);			
				expectedLongestWords.add(expectedWordMatch);
			}
	

			Sentences sentences = new Sentences();
			try {
				results = sentences.parseSentence(sentence);
			}
			catch(NotSingleSentenceException notSingleSentenceException){
				notSingleSentenceException.printStackTrace();
			}

			Integer sentenceLength = (Integer)results.get("Length");
			List longestWords = (ArrayList<?>)results.get("Longest Words");

			assertEquals(longestWords.size(), expectedLongestWords.size());

			for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

				long sameWordCount = ((ArrayList<String>)longestWords).stream()
							.filter((String returnedWord)->{
								return expectedWord.equals(returnedWord);
							})
							.count();
				assertEquals(sameWordCount, 1);					// Only one match, no more.
			}
		

			assertEquals(sentenceLength, Integer.valueOf(expectedLength));
		}

		@ParameterizedTest
		@CsvFileSource(resources = "/Word/Case-Sentitivity/Verify words in capital letters only are matched.csv") 
		@DisplayName ("Verify words in capital letters only are matched")
		@Tag ("Unit")
		public void matchOnlyCapsTest(String sentence, int expectedLength, String longestWord){

			Map<String, Object> results = null;


			Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
			Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


			List expectedLongestWords = new ArrayList<String>();

			while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
				String expectedWordMatch = expectedWordMatcher.group(1);
				expectedLongestWords.add(expectedWordMatch);
			}
	

			Sentences sentences = new Sentences();
			try {
				results = sentences.parseSentence(sentence);
			}
			catch(NotSingleSentenceException notSingleSentenceException){
				notSingleSentenceException.printStackTrace();
			}


			Integer sentenceLength = (Integer)results.get("Length");
			List longestWords = (ArrayList<?>)results.get("Longest Words");

			for (String actualWord : (ArrayList<String>)longestWords){
				System.out.println("Actual: " + actualWord);
			}

			assertEquals(longestWords.size(), expectedLongestWords.size());

			for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

				long sameWordCount = ((ArrayList<String>)longestWords).stream()
							.filter((String returnedWord)->{
								return expectedWord.equals(returnedWord);
							})
							.count();
				assertEquals(sameWordCount, 1);					// Only one match, no more.
			}
		

			assertEquals(sentenceLength, Integer.valueOf(expectedLength));
		}

	}




	/**
	* Tests from the "Spaces" sub-suite.
	*/

	@Nested
	public class SpacesTests{

		@ParameterizedTest
		@CsvFileSource(resources = "/Spaces/Spaces/Verify any number of spaces between words will not affect matching.csv") 
		@DisplayName ("Verify any number of spaces between words will not affect matching")
		@Tag ("Unit")
		public void variableSpaceCountTest(String sentence, int expectedLength, String longestWord){		

			Map<String, Object> results = null;


			Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
			Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


			List expectedLongestWords = new ArrayList<String>();

			while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
				String expectedWordMatch = expectedWordMatcher.group(1);
				expectedLongestWords.add(expectedWordMatch);
			}
	

			Sentences sentences = new Sentences();
			try {
				results = sentences.parseSentence(sentence);
			}
			catch(NotSingleSentenceException notSingleSentenceException){
				notSingleSentenceException.printStackTrace();
			}


			Integer sentenceLength = (Integer)results.get("Length");
			List longestWords = (ArrayList<?>)results.get("Longest Words");


			assertEquals(longestWords.size(), expectedLongestWords.size());

			for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

				long sameWordCount = ((ArrayList<String>)longestWords).stream()
							.filter((String returnedWord)->{
								return expectedWord.equals(returnedWord);
							})
							.count();
				assertEquals(sameWordCount, 1);					// Only one match, no more.
			}
		

			assertEquals(sentenceLength, Integer.valueOf(expectedLength));
		}

	}

	/**
	* Tests from the "Punctuation" sub-suite.
	*/

	@Nested
	public class PunctuationTests{

		@Nested
		public class PeriodsTests{
	
			@ParameterizedTest
			@CsvFileSource(resources = "/Punctuation/Periods/Verify a sentence period is not included as the longest word.csv") 
			@DisplayName ("Verify a sentence period is not included as the longest word")
			@Tag ("Unit")
			public void periodsNotIncludedTest(String sentence, int expectedLength, String longestWord){		

				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				
				String periodChar = ".";
	
				for (String actualLongestWord : (ArrayList<String>)longestWords){
					assertNotEquals(actualLongestWord.contains(periodChar), true);		// Longest words do not contain periods
				}

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

		}


		/**
		* Tests from the "Punctuation" sub-suite's "Commas ellipses" sub-suite.
		*/

		@Nested
		public class CommasEllipsesTests{
	
					
			@DisplayName ("Verify words are matched even when they are followed by commas - Multiple Match")
			@Tag ("Unit")
			@Test
			public void wordsMatchedWithCommasMultipleMatchTest(){		
				String sentence = "I had cake, milk, soda, and tea.";

				int expectedLength = 7;
				
				String longestWord = "cake milk soda";


				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
	

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

			@DisplayName ("Verify words are matched even when they are followed by commas One Match")
			@Tag ("Unit")
			@Test
			public void wordsMatchedWithCommasOneMatchTest(){		
	
				String sentence = "I had cake.";

				int expectedLength = 3;
				
				String longestWord = "cake";




				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
	

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

			@DisplayName ("Verify words are matched when followed by commas but the commas are not matched - Multiple match")
			@Tag ("Unit")
			@Test
			public void commasNotIncludedMultipleMatchTest(){		



				String sentence = "I had cake, milk, soda, and tea.";

				int expectedLength = 7;
				
				String longestWord = "cake milk soda";




				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				
				String commaChar = ",";
	
				for (String actualLongestWord : (ArrayList<String>)longestWords){
					assertNotEquals(actualLongestWord.contains(commaChar), true);		// Longest words do not contain periods
				}

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

			@DisplayName ("Verify words are matched when followed by commas but the commas are not matched - one match")
			@Tag ("Unit")
			@Test
			public void commasNotIncludedOneMatchTest(){		


				String sentence = "I had cake.";

				int expectedLength = 3;
				
				String longestWord = "cake";





				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				
				String commaChar = ",";
	
				for (String actualLongestWord : (ArrayList<String>)longestWords){
					assertNotEquals(actualLongestWord.contains(commaChar), true);		// Longest words do not contain periods
				}

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}
	
			@ParameterizedTest
			@CsvFileSource(resources = "/Punctuation/Commas ellipses/Verify words are matched even when they are followed by ellipses.csv") 
			@DisplayName ("Verify words are matched even when they are followed by ellipses")
			@Tag ("Unit")
			public void matchFollowedByEllipsesTest(String sentence, int expectedLength, String longestWord){		

				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

			@ParameterizedTest
			@CsvFileSource(resources = "/Punctuation/Commas ellipses/Verify words are matched even when they are preceded by ellipses.csv") 
			@DisplayName ("Verify words are matched even when they are preceded by ellipses")
			@Tag ("Unit")
			public void matchPrecededByEllipsesTest(String sentence, int expectedLength, String longestWord){		

				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
			

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

			@ParameterizedTest
			@CsvFileSource(resources = "/Punctuation/Commas ellipses/Verify words are matched when followed by ellipses but the ellipses are not matched.csv") 
			@DisplayName ("Verify words are matched when followed by ellipses but the ellipses are not matched")
			@Tag ("Unit")
			public void ellipsesNotIncludedTest(String sentence, int expectedLength, String longestWord){		

				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				
				String ellipsesString = "...";
	
				for (String actualLongestWord : (ArrayList<String>)longestWords){
					assertNotEquals(actualLongestWord.contains(ellipsesString), true);		// Longest words do not contain periods
				}

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}


		}

	}

	@Nested
	public class SentenceTests{

		@Nested
		public class SingleSentencesTests{
	
					
			@ParameterizedTest
			@CsvFileSource(resources = "/Sentence/Single sentence/Verify the proper word count and longest word is returned for a single sentence.csv") 
			@DisplayName ("Verify the proper word count and longest word is returned for a single sentence")
			@Tag ("Unit")
			@Tag ("Smoke")
			public void singleSentenceMatchTest(String sentence, int expectedLength, String longestWord){		

				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				
		

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

		}

		@Nested
		public class MultipleSentenceTests{
	
			@ParameterizedTest
			@CsvFileSource(resources = "/Sentence/Multiple sentence/Cannot parse words when more than one sentence is provided.csv") 
			@DisplayName ("Cannot parse words when more than one sentence is provided")
			@Tag ("Unit")
			public void multipleSentenceExceptionTest(String sentence, int expectedLength, String longestWord){		

				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();

				Throwable notSingleSentenceException = assertThrows(Sentences.NotSingleSentenceException.class, ()-> {
					sentences.parseSentence(sentence);			
				});

				assertEquals("Multiple sentences were provided.", notSingleSentenceException.getMessage());

				
			}

		}


		@Nested
		public class NoSentencePunctuationTests{
	
					
			@ParameterizedTest
			@CsvFileSource(resources = "/Sentence/No sentence punctuation/Verify the proper word count and longest word is returned even when no sentence punctuation is present.csv") 
			@DisplayName ("Verify the proper word count and longest word is returned even when no sentence punctuation is present")
			@Tag ("Unit")
			public void noSentencePunctuationMatchTest(String sentence, int expectedLength, String longestWord){		

				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				
		

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

		}


	}

	@Nested
	public class LengthTests{

		@Nested
		public class NoCharactersTests{
	
					
			@DisplayName ("Cannot parse a blank sentence")
			@Tag ("Unit")
			@Test
			public void noCharactersTest(){		


			
				String sentence = "";

				Sentences sentences = new Sentences();

				Throwable notSingleSentenceException = assertThrows(Sentences.NotSingleSentenceException.class, ()-> {
					sentences.parseSentence(sentence);			
				});

				assertEquals("No sentences were provided. Nothing was passed to parser.", notSingleSentenceException.getMessage());

			}

		}

		@Nested
		public class MultipleLongestWordsTests{
	
					
			@ParameterizedTest
			@CsvFileSource(resources = "/Length/Multiple longest words/Verify multiple longest words are returned when they are the same length.csv") 
			@DisplayName ("Verify multiple longest words are returned when they are the same length")
			@Tag ("Unit")
			public void multipleLongestWordsMatchTest(String sentence, int expectedLength, String longestWord){		

				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				
		

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}

		}

	}

	@Nested
	public class NumbersTests{

	
					
		@ParameterizedTest
		@CsvFileSource(resources = "/Numbers/Numbers/Can include numbers in sentences without affecting word count or longest words.csv") 
		@DisplayName ("Can include numbers in sentences without affecting word count or longest words")
		@Tag ("Unit")
		public void numbersIncludedTest(String sentence, int expectedLength, String longestWord){		
			Map<String, Object> results = null;


			Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
			Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


			List expectedLongestWords = new ArrayList<String>();

			while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
				String expectedWordMatch = expectedWordMatcher.group(1);
				expectedLongestWords.add(expectedWordMatch);
			}
	

			Sentences sentences = new Sentences();
			try {
				results = sentences.parseSentence(sentence);
			}
			catch(NotSingleSentenceException notSingleSentenceException){
				notSingleSentenceException.printStackTrace();
			}


			Integer sentenceLength = (Integer)results.get("Length");
			List longestWords = (ArrayList<?>)results.get("Longest Words");
				
		

			assertEquals(longestWords.size(), expectedLongestWords.size());

			for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

				long sameWordCount = ((ArrayList<String>)longestWords).stream()
							.filter((String returnedWord)->{
								return expectedWord.equals(returnedWord);
							})
							.count();
				assertEquals(sameWordCount, 1);					// Only one match, no more.
			}
		

			assertEquals(sentenceLength, Integer.valueOf(expectedLength));
		}
				
	}

	@Nested
	public class CharacterSetTests{

		@Nested
		public class ANSITests{
	
					
			@ParameterizedTest
			@CsvFileSource(resources = "/Character set/ANSI/Verify words with ANSI characters are supported and matched.csv") 
			@DisplayName ("Verify words with ANSI characters are supported and matched")
			@Tag ("Unit")
			public void ansiSupportedMatchTest(String sentence, int expectedLength, String longestWord){		
				Map<String, Object> results = null;


				Pattern expectedWordPattern = Pattern.compile("([^\\s]+)(\\s)?");		// Tokenize by spaces
				Matcher expectedWordMatcher = expectedWordPattern.matcher(longestWord);


				List expectedLongestWords = new ArrayList<String>();

				while (expectedWordMatcher.find()){		// Parse expectedLongestWords arguments into ArrayList
					String expectedWordMatch = expectedWordMatcher.group(1);
					expectedLongestWords.add(expectedWordMatch);
				}
	

				Sentences sentences = new Sentences();
				try {
					results = sentences.parseSentence(sentence);
				}
				catch(NotSingleSentenceException notSingleSentenceException){
					notSingleSentenceException.printStackTrace();
				}


				Integer sentenceLength = (Integer)results.get("Length");
				List longestWords = (ArrayList<?>)results.get("Longest Words");
				
		

				assertEquals(longestWords.size(), expectedLongestWords.size());

				for (String expectedWord :  (ArrayList<String>)expectedLongestWords){

					long sameWordCount = ((ArrayList<String>)longestWords).stream()
								.filter((String returnedWord)->{
									return expectedWord.equals(returnedWord);
								})
								.count();
					assertEquals(sameWordCount, 1);					// Only one match, no more.
				}
		

				assertEquals(sentenceLength, Integer.valueOf(expectedLength));
			}
				
		}
	}


}