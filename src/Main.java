import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Testing Tokenizer
		Tokenizer tok = new Tokenizer("C:/Users/vigsharm/workspace/SearchX/files/ferrari.txt");
		
		BufferedWriter br = new BufferedWriter(new FileWriter("C:/Users/vigsharm/workspace/SearchX/output/tokens.txt"));
		for(String s: tok)
			br.write(s+"\n");
		
		System.out.println("Done!!");
	}

}
