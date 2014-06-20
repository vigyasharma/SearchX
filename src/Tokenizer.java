import java.io.IOException;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author vigsharm
 *
 */
public class Tokenizer implements Iterable<String> {

	private TokenizerIterator it;
	
	public Tokenizer(String fname) throws IOException{
		it = new TokenizerIterator(fname);
		it.tokenize();
	}
	
	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return it;
	}
	
}
