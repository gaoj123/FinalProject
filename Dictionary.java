import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class Dictionary{
    //private ArrayList<String> wordToCheck;
    private ArrayList<String> dictionaryWordList;
    public Dictionary(){
	dictionaryWordList=new ArrayList<String>();
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
    //wordToCheck=new ArrayList<String>();
    //wordToCheck.add(wordInputted);
    public boolean wordValidityCheck(String playersWord){
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
    // public static boolean wordValidityCheck(String playersWord){
    //     boolean status=false;
    //     try{
    // 	Scanner in = new Scanner(new File("WordList.txt"));
    // 	while(in.hasNext()){
    // 	    String word = in.next();
    // 	    String wordUpperCase=word.toUpperCase();
    // 	    if(wordUpperCase.equals(playersWord.toUpperCase())){
    // 		status=true;
    // 	    }
    // 	    //System.out.println(word.toUpperCase());
    // 	}
    // 	return status;
    //     }
    //     catch(FileNotFoundException e){
    // 	System.out.println("Invalid filename or path");
    // 	System.exit(1);
    // 	return status;
    //     }
    // }
    public static void main(String[] args){
	Dictionary a=new Dictionary();
	//System.out.println(a.dictionaryWordList);
	System.out.println(a.wordValidityCheck("abbacc"));
    }
}
