
/**
 * @author vigsharm
 * Global constants for SearchX
 */

public class config {
	public static final String HOME_DIR = "C:/Users/vigsharm/workspace/SearchX/";
	public static final String CORPUS = HOME_DIR+"corpusTest/";
	public static final String OBJECT_DUMP = HOME_DIR+"obj/";
	public static final String DOCID_FILE = HOME_DIR+"obj/docIds.txt";
	public static final String STOP_WORD_FILE = HOME_DIR+"src/stop-words.txt";
	public static final String PAGE_TABLE = "pageTable.obj";
	
	public static final int MAX_TERMS = 1000;		// Max terms in 1 dump of index
	public static final int MAX_POSTING_SIZE = 9;	// Max docs in 1 index dump list
	public static final boolean DEBUG=true;
	
	public static final String DELIM = " ;&{}[]/|+:";
	
	public static final String TITLES = HOME_DIR+"obj/Titles.txt";
}
