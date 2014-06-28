//import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.jar.JarFile;


public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("\t\tWelcome to SearchX: Search Engine on Wiki Dump\n\t\tPress exit() to quit.\n\n");
		
		long start = System.nanoTime();
		System.out.println("Initializing Objects...");
		Search bot = new Search();
		long end = System.nanoTime();
		double timeElapsed = (double)(end-start)/1000000000;
		System.out.println("Load Time: "+timeElapsed+"secs");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String query="";
		System.out.println("\t\tWelcome to SearchX: Search Engine on Wiki Dump\n\t\tPress exit() to quit.\n\n");
		
		while(true){
			System.out.println("Enter Search Query:\t");
			query = br.readLine().trim().toLowerCase();
			if(query.equalsIgnoreCase("exit()"))
				break;
			
			start = System.nanoTime();
			bot.lookUp(query);
			end = System.nanoTime();
			bot.displayResults();
			timeElapsed = (double)(end-start)/1000000000;
			System.out.println("Queried in: "+timeElapsed+"secs");
			
		}
		System.out.println("*** Thank You. ***");
		bot.close();
		System.out.println("Bye!");
	}

}
