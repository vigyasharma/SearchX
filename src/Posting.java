import java.io.Serializable;


/**
 * @author Vigya Sharma
 * @email vigyasharma@outlook.com
 * A single posting object. Contains attribute to be stored with doc id in each index element.
 */

public class Posting implements Comparable<Posting>, Serializable {
	private int id;
	private boolean inTitle;
	private int freq;
	
	public Posting(){
	}
	
	public Posting(int id, boolean inTitle, int freq){
		this.id=id;
		this.inTitle = inTitle;
		this.freq = freq;
	}

	@Override
	public int compareTo(Posting arg0) {
		// TODO Auto-generated method stub
		int argId = arg0.getId();
		if(id < argId)
			return -1;
		if(id > argId)
			return 1;
		return 0;
	}
	
	public boolean equals(Posting arg){
		return arg.getId()==id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isInTitle() {
		return inTitle;
	}

	public void setInTitle(boolean inTitle) {
		this.inTitle = inTitle;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

}
