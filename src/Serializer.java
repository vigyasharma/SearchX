import java.io.*;

public class Serializer {

	public static boolean dumpObject(Object o, String fname){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(config.OBJECT_DUMP+fname));
			out.writeObject(o);
			out.close();
			return true;
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static Object readObject(String fname){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(config.OBJECT_DUMP+fname));
			Object ob = in.readObject();
			in.close();
			return ob;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
