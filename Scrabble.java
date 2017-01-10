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
	Board testboard=new Board();
	Scrabble a=new Scrabble();
	a.initializeArrayList();
	//System.out.println(a.dictWordList);
	Tile b=new Tile("b");
	Tile e=new Tile("e");
	Tile d=new Tile("d");
	Tile t=new Tile("t");
	Tile t2=new Tile("t");
	Tile e3=new Tile("e");
	Tile r=new Tile("r");
	//Tile e2=new Tile("e");
	Tile o=new Tile("o");
	Tile blank=new Tile();
	blank.differentiate("a");
	Tile a2=new Tile("a");
	TileBag tileBag=new TileBag();
	Player jen=new Player("Jenny");
	jen.addToRack(b);
	jen.addToRack(e);
	jen.addToRack(e3);
	jen.addToRack(t);
	jen.addToRack(r);
	jen.addToRack(t2);
	//	jen.addToRack(blank);
	jen.addToRack(a2);
        System.out.println(jen);
	tileBag.refillRack(jen);
	System.out.println(jen);
	//System.out.println(wordValidityCheck("bed"));
        jen.placeWord(testboard,a,"bet",6,8,"v");
	System.out.println("Round Score: "+jen.getRoundScore());
	System.out.println("Total Score: "+jen.getTotalScore());
	System.out.println(jen);
	tileBag.refillRack(jen);
	System.out.println(jen);
	System.out.println(jen.getEndTurn());
	System.out.println(testboard);
	jen.placeWord(testboard,a,"better",6,8,"v");
	System.out.println("Round Score: "+jen.getRoundScore());
	System.out.println("Total Score: "+jen.getTotalScore());
	System.out.println(testboard);
	System.out.println(jen);
	jen.requestExchange(tileBag,3);
	System.out.println(jen);
	// Player winnie=new Player("Winnie");
	// winnie.addToRack(e);
	// winnie.addToRack(b);
	// winnie.addToRack(d);
	// tileBag.refillRack(winnie);
	// winnie.placeWord(testboard,a,"bed",6,8,"h");
	// System.out.println("Round Score: "+winnie.getRoundScore());
	// System.out.println("Total Score: "+winnie.getTotalScore());
	// System.out.println(winnie);
	// tileBag.refillRack(winnie);
	// System.out.println(winnie);
	// System.out.println(testboard);
    }
}
