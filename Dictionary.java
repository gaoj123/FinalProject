import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class Dictionary{
    public static boolean wordValidityCheck(String playersWord){
	boolean status=false;
	try{
	    Scanner in = new Scanner(new File("WordList.txt"));
	    while(in.hasNext()){
		String word = in.next();
		String wordUpperCase=word.toUpperCase();
		if(wordUpperCase.equals(playersWord.toUpperCase())){
		    status=true;
		}
		//System.out.println(word.toUpperCase());
	    }
	    return status;
	}
	catch(FileNotFoundException e){
    	    System.out.println("Invalid filename or path");
    	    System.exit(1);
	    return status;
     	}
    }
    public static void main(String[] args){
	System.out.println(wordValidityCheck("abbacc"));
    }
}
