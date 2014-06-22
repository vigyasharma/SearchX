
/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * The result object. Holds relevance.
 */
public class Result implements Comparable<Result> {
	
	public int id;		//Doc Id
	public int matches;		// No of terms in query present in this doc
	public int inTitle;
	public double tfidf;
	public double relevance;
	
	public Result(Posting p){
		id=p.getId();
		matches = 1;
		inTitle = p.isInTitle()?1:0;
		relevance = matches+inTitle;
	}
	
	public void addPosting(Posting p){
		if(this.id==p.getId()){
			this.matches+=1;
			this.inTitle += p.isInTitle()?1:0;
			this.relevance = this.matches + this.inTitle;
		}
	}
	
	@Override
	public int compareTo(Result o) {
		// TODO Auto-generated method stub
		if(this.relevance < o.relevance)
			return 1;
		if(this.relevance > o.relevance)
			return -1;
		return 0;
	}

}
