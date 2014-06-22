import java.util.LinkedList;


/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * Test class for unit tests.
 */
public class Test {
	
	public static void runTest(){
		//Testing Tokenizer
	//	Tokenizer tok = new Tokenizer(config.CORPUS+"/298.xml");
	//	Tokenizer tok = new Tokenizer("C:/Users/vigsharm/workspace/SearchX/files/ferrari.txt");
		
		// Writing tokens to an output file for testing
	//	BufferedWriter br = new BufferedWriter(new FileWriter("C:/Users/vigsharm/workspace/SearchX/output/tokens.txt"));
	//	for(String s: tok)
	//		br.write(s+"\n");
		
	//	ArrayList<String> docs = Corpus.getDocumentIds();
	//	DiskIO.writeDocIdsToDisk(docs);
		
	//	String docs[] = Corpus.getDocumentIds();
	//	Tokenizer tokIt = new Tokenizer();
	//	for(int i=0; i<2; i++){
	//		System.out.println(i+"\t"+docs[i]);
	//		tokIt.setFile(docs[i]);
	//		for(Token tok:tokIt){
	//			//if(tok.inTitle)
	//			System.out.println(tok.word+" - Title:"+tok.inTitle);
	//		}
	//		System.out.println("###############################################################");
	//	}
		
//		Indexer index = new Indexer();
//		index.buildIndex();
//		
//		System.out.println(index.map.size());
//		for(String word: index.map.keySet()){
//			System.out.print(word+" :- ");
//			for(Posting p: index.map.get(word)){
//				System.out.print(p.getId()+","+p.getFreq()+" ");
//			}
//			System.out.println();
//		}
		
//		int a[] = {1,3,6,8,9};
		int b[] = {2,3,4,5,12,13,14,15};
		
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(3);
		l.add(6);
		l.add(8);
		l.add(9);
		
		int i=0; int k=0;
		while(i<l.size() && k<b.length){
			if(l.get(i)<b[k])
				i++;
			else if(l.get(i)==b[k]){
				i++;
				k++;
			}
			else{
				l.add(i, b[k]);
				i++;
				k++;
			}
		}
		for(; k<b.length; k++)
			l.addLast(b[k]);
		
		System.out.println(l.size());
		System.out.println(l);
		
		
		System.out.println("Done!!");
	}
}
