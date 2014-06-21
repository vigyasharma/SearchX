import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;


/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * For Storing and retrieving index dumps from disk.
 */
public class DiskIO {
	
	public static void writeDocIdsToDisk(ArrayList<String> docIds){
		try{
			BufferedWriter br = new BufferedWriter(new FileWriter(config.DOCID_FILE));
			int len = docIds.size();
			br.write(len+"\n");
			for(int i=0; i<len; i++)
				br.write(i+"\t"+docIds.get(i)+"\n");
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static String[] readDocIdsFromDisk(){
		String ids[];
		try{
			BufferedReader br = new BufferedReader(new FileReader(config.DOCID_FILE));
			int len = Integer.parseInt(br.readLine());
			ids = new String[len];
			
			String str;
			while((str=br.readLine())!=null){
				String line[] = str.split("\t");
				ids[Integer.parseInt(line[0])] = line[1];
			}
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
			ids=null;
		}
		return ids;
	}
	
}
