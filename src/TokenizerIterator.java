import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class TokenizerIterator implements Iterator<String> {
	private ArrayList<String> tokens;
	private int curr;
	private String fileName;
	
	public TokenizerIterator(String fname){
		tokens = new ArrayList<String>();
		curr = 0;
		fileName = fname;
	}
	
	public boolean hasNext(){
		return curr < tokens.size();
	}
	
	public String next(){
		return tokens.get(curr++);
	}
	
	public void tokenize() throws IOException{
		BufferedReader br = new BufferedReader( new FileReader(fileName));
//		File input = new File(fileName);
//		Document doc = Jsoup.parse(input, "UTF-8", "http://en.wikipedia.org/wiki/");
		
//		System.out.println(doc.body().text());
		
		String line;
//		int count=0;
//		int openang=0;
//		int script=0;
//		int style=0;
		while( (line=br.readLine()) != null){
			int s=0,i;
			for(i=0; i<line.length(); i++){
				if(line.charAt(i)==' '){
					tokens.add(line.substring(s,i));
					s=i+1;
				}
			}
			if(s<i)
				tokens.add(line.substring(s,i));
		}
				
				
//				System.out.println(openang+" "+script+" "+style+" "+line.charAt(i));
//				if(line.charAt(i)=='<'){
//					if(i+7 <= line.length() && line.substring(i+1, i+7).equalsIgnoreCase("script"))
//						script++;
//					if(i+8<=line.length() && line.substring(i+1, i+8).equalsIgnoreCase("/script"))
//						script--;
//					if(i+6 <= line.length() && line.substring(i+1, i+6).equalsIgnoreCase("style")){
//						style++;
//						System.out.println(++count);
//					}
//					if(i+7<=line.length() && line.substring(i+1, i+7).equalsIgnoreCase("/style"))
//						style--;
//					
//					if(openang==0 && s<i && script==0 && style==0){
//						tokens.add(line.substring(s,i));
//					}
//					openang++;
//				}
//					
//				if(line.charAt(i)=='>'){
//					openang--;
//					if(openang==0)
//						s=i+1;
//				}
//				
//				if(openang==0){
//					if(line.charAt(i)==' ' && script==0 && style ==0){
//						tokens.add(line.substring(s,i));
//						s=i+1;
//					}
//				}
//				
//			}
//			if(s<i && openang==0 && script==0 && style==0)
//				tokens.add(line.substring(s,i));
//		}
	}

	@Override
	public void remove() {
		tokens.remove(--curr);
	}
	
}
