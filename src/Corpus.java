import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * Works on data corpus. 
 * Methods to build document Id index. 
 */
public class Corpus {

	public static ArrayList<String> scanCorpus(){
		ArrayList<String> docIds = new ArrayList<String>();

		/*	Only fool proof way to add into a list while iterating through it:
		 * 	Use LinkedList as used below.
		 */
		
		LinkedList<String> dirs = new LinkedList<String>();	// To hold sub folders in corpus
		dirs.addLast(config.CORPUS);	//Start scanning at corpus root
		
		while(!dirs.isEmpty()){
			String dir = dirs.removeFirst();
//			System.out.println("Current Dir: "+dir);
			File folder = new File(dir);	// Creating a file object out of current folder to scan
			for(File entry: folder.listFiles()){
				if(entry.isDirectory()){
					dirs.addLast(dir+"/"+entry.getName());	// Add sub-folder to list of folders to scan
//					System.out.println("Added "+config.CORPUS+"/"+entry.getName());
				}
				else{
					docIds.add(dir+"/"+entry.getName());	// Add file to docIds List. Adding absolute path
//					System.out.println("Added file: "+dir+"/"+entry.getName());
				}
			}
		}
		
		return docIds;
	}
	
	public static String[] getDocumentIds(){
//		try{
			File docfile = new File(config.DOCID_FILE);
			if(!docfile.exists()){
				System.out.println("Generating Doc Ids and writing to disk.");
				DiskIO.writeDocIdsToDisk(scanCorpus());
				System.out.println("Doc Ids generated.");
			}
				
			return DiskIO.readDocIdsFromDisk();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
	}
	
}
