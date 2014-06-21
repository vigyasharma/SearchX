import java.util.Iterator;

/**
 * 
 */

/**
 * @author vigsharm
 * Returns all tokens for a given file.
 * Removes stop words, handles input file format specs before returning tokens.
 * Stemming / Pruning may be added later to this class.
 */

public class Tokenizer implements Iterable<Token> {

	private TokenizerIterator it;
	
	public Tokenizer(){
		it = new TokenizerIterator();
		it.readStopWords();
	}
	
	public void setFile(String fname){
		it.tokenize(fname);
	}
	
	@Override
	public Iterator<Token> iterator() {
		// TODO Auto-generated method stub
		
		return it;
	}
	
}
