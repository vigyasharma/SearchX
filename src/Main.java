//import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Testing Tokenizer
//		Tokenizer tok = new Tokenizer(config.CORPUS+"/298.xml");
//		Tokenizer tok = new Tokenizer("C:/Users/vigsharm/workspace/SearchX/files/ferrari.txt");
		
		// Writing tokens to an output file for testing
//		BufferedWriter br = new BufferedWriter(new FileWriter("C:/Users/vigsharm/workspace/SearchX/output/tokens.txt"));
//		for(String s: tok)
//			br.write(s+"\n");
		
//		ArrayList<String> docs = Corpus.getDocumentIds();
//		DiskIO.writeDocIdsToDisk(docs);
		
//		String docs[] = Corpus.getDocumentIds();
//		Tokenizer tokIt = new Tokenizer();
//		for(int i=0; i<2; i++){
//			System.out.println(i+"\t"+docs[i]);
//			tokIt.setFile(docs[i]);
//			for(Token tok:tokIt){
//				//if(tok.inTitle)
//				System.out.println(tok.word+" - Title:"+tok.inTitle);
//			}
//			System.out.println("###############################################################");
//		}
		
		Indexer index = new Indexer();
		index.buildIndex();
		
		System.out.println(index.map.size());
		for(String word: index.map.keySet()){
			System.out.print(word+" :- ");
			for(Posting p: index.map.get(word)){
				System.out.print(p.getId()+","+p.getFreq()+" ");
			}
			System.out.println();
		}
		
		System.out.println("Done!!");
	}

}
