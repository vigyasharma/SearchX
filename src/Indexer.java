import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;


/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * Class to Build inverted index on scanned tokens.
 */
public class Indexer implements Serializable {

	public String[] docs;
	public HashMap<String, TreeSet<Posting>> map;
	private int docLen;
	public HashMap<String, ArrayList<Integer>> pageTable;
	private int pageNo;
	
	public Indexer(){
		docs = Corpus.getDocumentIds();
		docLen = docs.length;
		map = new HashMap<String, TreeSet<Posting>>();
		
		File f = new File(config.OBJECT_DUMP+config.PAGE_TABLE);
		if(f.exists()){
			System.out.println("Loading Page Table...");
			pageTable = (HashMap<String, ArrayList<Integer>>) Serializer.readObject(config.PAGE_TABLE);
		}
		else{
			pageTable = new HashMap<String, ArrayList<Integer>>();
			pageNo = 0;
			System.out.println("No previous index found.\nBuilding Index:");
			buildIndex();
		}
		
	}
	
	public void pushToPageTable(){
		pageNo++;
		for(String term: map.keySet()){
			ArrayList<Integer> l;
			
			if(pageTable.containsKey(term))
				l = pageTable.get(term);
			else
				l = new ArrayList<Integer>();
			
			l.add(pageNo);
			pageTable.put(term, l);
		}
		
		if(config.DEBUG)
			System.out.println("Page Table Populated for page: "+pageNo);
	}
	
	public void buildIndex(){
		Tokenizer tokIt = new Tokenizer();
		int docsScanned = 0;
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
			docsScanned++;
			
			
			/* Dump map if size limit exceeded */
			if((map.size() > config.MAX_TERMS || docsScanned % config.MAX_POSTING_SIZE==0) && !map.isEmpty()){
				pushToPageTable();
				Serializer.dumpObject(map, "indexPage"+pageNo);
				map.clear();
			}
			
		}	// next doc
		
		if(!map.isEmpty()){
			pushToPageTable();
			Serializer.dumpObject(map, "indexPage"+pageNo);
			map.clear();
		}
		
	}	//End of buildIndex
	
	public void close(){
		File f = new File(config.OBJECT_DUMP+config.PAGE_TABLE);
		if(!f.exists()){
			System.out.println("Dumping Page Table to disk...");
			Serializer.dumpObject(pageTable, config.PAGE_TABLE);
		}
	}
	
}
