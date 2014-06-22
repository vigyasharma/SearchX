import java.util.HashMap;
import java.util.TreeSet;


/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * Class to Build inverted index on scanned tokens.
 */
public class Indexer {

	public String[] docs;
	public HashMap<String, TreeSet<Posting>> map;
	private int docLen;
	
	public Indexer(){
		docs = Corpus.getDocumentIds();
		docLen = docs.length;
		map = new HashMap<String, TreeSet<Posting>>();
	}
	
	public void buildIndex(){
		Tokenizer tokIt = new Tokenizer();
		for(int id=0; id<docLen; id++){
			tokIt.setFile(docs[id]);
			for(Token tok: tokIt){
				Posting p = new Posting(id, tok.inTitle, 1);
				if(!map.containsKey(tok.word)){								// New word. Create TreeSet
					TreeSet<Posting> t = new TreeSet<Posting>();
					t.add(p);
					map.put(tok.word, t);
				}
				else{														// TreeSet already exists.
					TreeSet<Posting> t = map.get(tok.word);					// Get TreeSet for this word.
					if(t.contains(p)){										// TreeSet contains p
						Posting old = t.floor(p);
						if(old.getId()==p.getId()){ 						// Found posting with same docid
							t.remove(p);									// Remove and reinsert
							p.setInTitle(old.isInTitle() || tok.inTitle);	// Set title
							p.setFreq(old.getFreq()+1);						// Incr freq of this token in this document
							t.add(p);										// Reinsert
						}
						else{												// Something wrong with contains. Got Posting with id less than p
							System.out.println("Posting contained but floor returned mismatching id "+old.getId()+" new: "+p.getId());
							t.add(p);
						}
					}
					else{													// TreeSet does not contain p
						t.add(p);
					}
				}
			}	// done for all token in 1 doc
		}	// next doc
		
	}
	
}
