//import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		Test.runTest();
		
		Search bot = new Search();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String query="";
		System.out.println("\t\tWelcome to SearchX: Search Engine on Wiki Dump\n\t\tPress exit() to quit.\n\n");
		
		while(true){
			System.out.println("Enter Search Query:\t");
			query = br.readLine().trim().toLowerCase();
			if(query.equalsIgnoreCase("exit()"))
				break;
			
			bot.lookUp(query);
			bot.displayResults();
			
		}
		System.out.println("*** Thank You. ***");
	}

}
