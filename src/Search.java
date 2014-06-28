import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * For lookup in inverted index.
 * Merges relevant postings lists and obtains relevance for each result.
 */
public class Search implements Serializable {

	private Indexer index;
	private LinkedList<Result> results;
	private HashMap<Integer, HashMap<String, TreeSet<Posting>>> inMemoryPages;
	private LinkedList<Integer> lru;
	
	public Search(){
		index = new Indexer();
		results = new LinkedList<Result>();
		inMemoryPages = new HashMap<Integer, HashMap<String, TreeSet<Posting>>>();
		lru = new LinkedList<Integer>();
	}
	
	public void lookUp(String query){
		StringTokenizer tok = new StringTokenizer(query, config.DELIM);
		HashMap<String, TreeSet<Posting>> buffer;
		
		while(tok.hasMoreTokens()){
			String word = tok.nextToken();
			TreeSet<Posting> p = new TreeSet<Posting>();
			if(!index.pageTable.containsKey(word))
				continue;
			
			for(int page: index.pageTable.get(word)){	// for all pages of that term
				if(!inMemoryPages.containsKey(page)){
					if(lru.size() == config.MAX_PAGES_IN_MEMORY){
						int pageToRemove = lru.removeFirst();
						if(config.DEBUG)
							System.out.println("Page Replacement...");
						inMemoryPages.remove(pageToRemove);
//						lru.addLast(page);
					}
					if(config.DEBUG){
						System.out.println("Page Fetch:");
						System.out.println("Total pages in memory: "+inMemoryPages.size()+" "+lru.size());
					}
					inMemoryPages.put(page, (HashMap<String, TreeSet<Posting>>) Serializer.readObject("indexPage"+page));
				}
				else
					lru.remove((Integer)page);		//Will add to end
				lru.addLast(page);
				buffer = inMemoryPages.get(page);		//get map for that word
				p.addAll(buffer.get(word));				// add posting list for that word. collect all such lists.
				
				if(config.DEBUG)
					System.out.println("Total pages in memory: "+inMemoryPages.size()+" "+lru.size());
			}
			
			mergePostings(p);
		}
		
	}
	
	/**
	 * Merges Posting lists to give more relevance to intersection of search tokens.
	 * Calculates relevance for the final set.
	 */

	public void mergePostings(TreeSet<Posting> t){
		if(t==null)
			return;
		if(results.isEmpty()){
			for(Posting p: t){
				results.addLast(new Result(p));
			}
		}
		else{
			int i=0;
			Iterator<Posting> treeIt = t.iterator();
			Posting p=null;
			if(treeIt.hasNext())
				p = treeIt.next();
			while(treeIt.hasNext() && i<results.size()){
				if(results.get(i).id < p.getId())
					i++;
				else if(results.get(i).id == p.getId()){
					Result r = results.get(i);
					r.addPosting(p);
					results.set(i, r);
					i++;
					if(treeIt.hasNext())
						p = treeIt.next();
				}
				else{
					results.add(i, new Result(p));
					i++;
					if(treeIt.hasNext())
						p = treeIt.next();
				}
			}
			while(treeIt.hasNext())
				results.addLast(new Result(treeIt.next()));
		}
	}

	public void displayResults(){
		System.out.println("_____________\tResults:\t_________________");
		if(results.isEmpty()){
			System.out.println("** No match found for this query. Please try a different phrase. **\n");
			return;
		}
			
		Collections.sort(results);
		int i=1;
		for(Result r: results){
			System.out.println(i+".\t"+index.docs[r.id]+"\t\t("+r.relevance+")"+"\tmatches="+r.matches+"  title="+r.inTitle);
			i++;
		}
//			System.out.println(r.id+" -- "+r.relevance);
		System.out.println("\n");
		results.clear();
	}
	
	public void close(){
		index.close();
	}
}
