
//import classes for file input - scanner etc.
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
//import implementing set (eg. TreeSet)

public class WordProcessor {
	private static <E> String displaySet(Set<E> inputSet){
		//implement this static method to create a
		// String representation of set - 5 comma separated elements per line
		// assume that type E has a toString method
		String output = "";
		int newLine = 0;
		for (E elem : inputSet) {
			output+= elem.toString()+", ";
			newLine++;
			if(newLine % 5 == 0){
				output += "\n";
			}
		}
		return output;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create a set of type String called wordSet
		Set<String> wordSet = new TreeSet<String>();
		//create a set of type CountedElement<String> called countedWordSet
		Set<CountedElement> countedWordSet = new TreeSet<CountedElement>();

		//for each input file (assume 3 arguments, each the name of a file)
		for(String file : args){
			try {
				BufferedReader fileIn = new BufferedReader(new FileReader(file));
				String line = fileIn.readLine();
				while (line != null) {
					String[] wordsInLine = line.split( "[^\\w']+" );
					for (String word : wordsInLine){
						//  for each word w
						if(!wordSet.contains(word)){
							//     if wordset doesnt contain w:
							//        add w to wordset
							//        add new element to countedWordSet
							wordSet.add(word);
							countedWordSet.add(new CountedElement(word, 1));
						}
						else{
							//     else
							//        increment numeric part of element in countedWordSet containing w
							CountedElement incremElem = new CountedElement(word);
							for(CountedElement elem : countedWordSet){
								if(elem.compareTo(incremElem)==0){
									elem.setCount(elem.getCount()+1);
								}
							}
						}
					}
					line  = fileIn.readLine();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(displaySet(countedWordSet));

	}
}
