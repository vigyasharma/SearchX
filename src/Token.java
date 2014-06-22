
/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * Object returned by tokenizer. Can hold token attributes like isInTitle
 */
public class Token {
	public String word;
	public boolean inTitle;
	
	public Token(String w, boolean t){
		word=w;
		inTitle=t;
	}
}
