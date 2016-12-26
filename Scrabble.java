import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class Scrabble{
    public static ArrayList<String> dictionaryWordList=new ArrayList<String>();
    public void initializeArrayList(){
	try{
	    Scanner in = new Scanner(new File("WordList.txt"));
	    while(in.hasNext()){
		String word = in.next();
		String wordUpperCase=word.toUpperCase();
		dictionaryWordList.add(wordUpperCase);
	    }
	}
	catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path");
	    System.exit(1);
	}
    }
    public static boolean wordValidityCheck(String playersWord){
	boolean status=false;
	int listSize=dictionaryWordList.size();
	for(int i=0;i<listSize;i++){
	    if(dictionaryWordList.get(i).equals(playersWord.toUpperCase())){
		status=true;
	    }
	    //System.out.println(word.toUpperCase());
	}
	return status;
    }
    public static void main(String[] args){
	Scrabble a=new Scrabble();
	a.initializeArrayList();
	//System.out.println(a.dictionaryWordList);
	System.out.println(wordValidityCheck("hello"));
    }
}
