import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class Scrabble{
    public static ArrayList<String> dictWordList=new ArrayList<String>();
    public void initializeArrayList(){
	try{
	    Scanner in = new Scanner(new File("WordList.txt"));
	    while(in.hasNext()){
		String word = in.next();
		String wordUpperCase=word.toUpperCase();
		dictWordList.add(wordUpperCase);
	    }
	}
	catch(FileNotFoundException e){
	    System.out.println("Invalid filename or path");
	    System.exit(1);
	}
    }
    public static boolean wordValidityCheck(String playersWord){
	boolean status=false;
	int listSize=dictWordList.size();
	for(int i=0;i<listSize;i++){
	    if(dictWordList.get(i).equals(playersWord.toUpperCase())){
		status=true;
	    }
	    //System.out.println(word.toUpperCase());
	}
	return status;
    }
    public static void main(String[] args){
	Scrabble a=new Scrabble();
	a.initializeArrayList();
	//System.out.println(a.dictWordList);
	Tile b=new Tile("b");
	Tile e=new Tile("e");
	Tile d=new Tile("d");
	Tile blank=new Tile();
	blank.differentiate("a");
	TileBag tileBag=new TileBag();
	Player jen=new Player("Jenny");
	jen.addToRack(b);
	jen.addToRack(e);
	jen.addToRack(d);
	jen.addToRack(blank);
        System.out.println(jen);
	tileBag.refillRack(jen);
	System.out.println(jen);
	//System.out.println(wordValidityCheck("bed"));
        jen.placeWord(a,"bead",0,13,"v");
	System.out.println(jen);
    }
}
