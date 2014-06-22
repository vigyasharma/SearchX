import java.util.Arrays;
import java.util.Collections;
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
public class Search {

	private Indexer index;
	private LinkedList<Result> results;
	
	public Search(){
		index = new Indexer();
		index.buildIndex();
		results = new LinkedList<Result>();
	}
	
	/**
	 * Merges Posting lists to give more relevance to intersection of search tokens.
	 * Calculates relevance for the final set.
	 */
	public void lookUp(String query){
		StringTokenizer tok = new StringTokenizer(query, config.DELIM);
		
		while(tok.hasMoreTokens()){
			String word = tok.nextToken();
			TreeSet<Posting> t = index.map.get(word);
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
		System.out.println("Total Results obtained: "+results.size());
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
	
}
