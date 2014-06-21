import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class TokenizerIterator implements Iterator<Token> {
	private ArrayList<Token> tokens;
	private int curr;
	private HashSet<String> stopwords;
	
	public TokenizerIterator(){
		tokens = new ArrayList<Token>();
		curr = 0;
		stopwords = new HashSet<String>();
	}
	
	public void readStopWords(){
		try{
			BufferedReader br = new BufferedReader(new FileReader(config.STOP_WORD_FILE));
			String word;
			while((word=br.readLine())!=null){
				stopwords.add(word);
			}
			br.close();
//			System.out.println("Stop Words Read.");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean hasNext(){
		boolean more =  curr < tokens.size();
		if(!more){
			tokens.clear();
			curr=0;
		}
		return more;
	}
	
	public Token next(){
		return tokens.get(curr++);
	}
	
	public void tokenize(String fileName) {
		
//		readStopWords();
		
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(fileName));
			
			doc.getDocumentElement().normalize();
			String delim = " ;&{}[]/|";
			
			//		System.out.println(title.getLength());
			String title = doc.getElementsByTagName("title").item(0).getTextContent();
			String text = doc.getElementsByTagName("text").item(0).getTextContent();
			
			StringTokenizer tok = new StringTokenizer(title, delim);
			while(tok.hasMoreTokens()){
				tokens.add(new Token(tok.nextToken().trim(), true));
			}
			
			tok = new StringTokenizer(text, delim);
			while(tok.hasMoreTokens()){
				tokens.add(new Token(tok.nextToken().trim(), false));
			}
			
		 }catch (SAXParseException err) {
	        System.out.println ("** Parsing error" + ", line " 
	             + err.getLineNumber () + ", uri " + err.getSystemId ());
	        System.out.println(" " + err.getMessage ());
	
	    }catch (SAXException e) {
	        Exception x = e.getException ();
	        ((x == null) ? e : x).printStackTrace ();
	
	    }catch (Throwable t) {
	        t.printStackTrace ();
	    }
		
				
	}

	@Override
	public void remove() {
		tokens.remove(--curr);
	}
	
}
