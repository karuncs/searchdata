package wn;

import java.io.FileInputStream;
import java.util.ArrayList;


import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Pointer;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;


/**
 * This class gets synonym sets from the Wordnet dictionary files.
 */
public class SynonymsEqui {
	
		public static void main(String[] args) throws JWNLException {
		
			configureJWordNet();
			Dictionary dictionary = Dictionary.getInstance();
			IndexWord word = dictionary.lookupIndexWord(POS.NOUN, "work");
			
			findRelatedWordsDemo(word,PointerType.SIMILAR_TO);
			
			System.out.println("Senses of the word 'work':");
			
			Synset[] senses = word.getSenses();
			for (int i=0; i<senses.length; i++) {
			  Synset sense = senses[i];
			  System.out.println((i+1) + ". " + sense.getGloss());
			  Pointer[] holo = sense.getPointers(PointerType.PART_HOLONYM);
			  for (int j=0; j<holo.length; j++) {
			    Synset synset = (Synset) (holo[j].getTarget());
			    Word synsetWord = synset.getWord(0);
			    System.out.print("  -part-of-> " + synsetWord.getLemma());
			    System.out.println(" = " + synset.getGloss());
			  }
			}
			
					
		}
		
	    // This function lists related words of type of relation for a given word
	    public static void findRelatedWordsDemo(IndexWord w, PointerType type) throws JWNLException {
	        System.out.println("\n\nI am now going to find related words for " + w.getLemma() + ", listed in groups by sense.");
	        System.out.println("We'll look for relationships of type " + type.getLabel() + ".");
	        
	        // Call a function that returns an ArrayList of related senses
	        ArrayList a = WordNetHelper.getRelated(w,type);
	        if (a == null || a.isEmpty()) {
	            System.out.println("Hmmm, I didn't find any related words.");
	        } else {
	            // Display the words for all the senses
	            for (int i = 0; i < a.size(); i++) {
	                Synset s = (Synset) a.get(i);
	                Word[] words = s.getWords();
	                for (int j = 0; j < words.length; j++ ) {
	                    System.out.print(words[j].getLemma());
	                    if (j != words.length-1) System.out.print(", ");
	                }
	                System.out.println();
	            }
	        }
	    }
	    
	    
		
		public static void configureJWordNet() {
			// WARNING: This still does not work in Java 5!!!
			try {
				// initialize JWNL (this must be done before JWNL can be used)
				// See the JWordnet documentation for details on the properties file
				JWNL.initialize(new FileInputStream("resources/file_properties.xml"));
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}
		
		
		// lookupIndexWord(), lookupAllIndexWords(), and getIndexWordIterator(). 
		
		
	
    }
