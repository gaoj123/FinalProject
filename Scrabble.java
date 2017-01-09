import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class Scrabble{
    public static ArrayList<String> dictWordList=new ArrayList<String>();
    private ArrayList<Player> players;
    private Board gameBoard;
    private TileBag tileBag;
    private String scorekeeper;
    
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

    private String extraSpacesNeeded(String string, int max){
	String spacesNeeded = "";
	if(string.length() < max){
	    for(int difference = max - string.length(); difference > 0; difference--){
		spacesNeeded += " ";
	    }
	}
	return spacesNeeded;
    }

    private void overwriteScorekeeper(boolean isLastOverwrite){
	String lastColLabel = "Current Round";
	if(isLastOverwrite){
	    lastColLabel = "After Deductions";
	}
	scorekeeper = "Scoreboard";
	String rowDivider = "\n+";
	int maxNameLength = 6;
	for(int player = 0; player < players.size(); player++){
	    if(players.get(player).getName().length() > maxNameLength){
		maxNameLength = players.get(player).getName().length();
	    }
	}
	for(int i = 0; i < maxNameLength; i++){
	    rowDivider += "-";
	}
	rowDivider += "+-----+----------------+";
	scorekeeper += rowDivider + "\n|Player|Total|" + lastColLabel + extraSpacesNeeded(lastColLabel, 16) + "|" + rowDivider;
	for(int player = 0; player < players.size(); player++){
	    scorekeeper += "\n|" + players.get(player).getName() + extraSpacesNeeded(players.get(player).getName(), maxNameLength) +
		"|" + players.get(player).getTotalScore() + extraSpacesNeeded(Integer.toString(players.get(player).getTotalScore()), 5) +
		"|" + players.get(player).getRoundScore() + extraSpacesNeeded(Integer.toString(players.get(player).getRoundScore()), 16) +
		"|" + rowDivider;
	}
    }
    
    private void endRoundScoring(){
	for(int player = 0; player < players.size(); player++){
	    players.get(player).setTotalScore(players.get(player).getTotalScore() + players.get(player).getRoundScore());
	    players.get(player).setRoundScore(0);
	}
    }

    private void endGameScoring(){
	endRoundScoring();
	for(int player = 0; player < players.size(); player++){
	    players.get(player).setRoundScore(players.get(player).getTotalScore());
	    for(int tilesLeft = players.get(player).getRackSize() - 1; tilesLeft >= 0; tilesLeft--){
		players.get(player).setRoundScore(players.get(player).getRoundScore() - players.get(player).getPointsOfTileInRack(tilesLeft));
	    }
	}
    }

    public Scrabble(String nameP1, String nameP2){
	initializeArrayList();
	players = new ArrayList<Player>(0);
	double P1Roll = Math.random();
	double P2Roll = Math.random();
	if(P1Roll < P2Roll){
	    players.add(new Player(nameP1));
	    players.add(new Player(nameP2));
	}else{
	    players.add(new Player(nameP2));
	    players.add(new Player(nameP1));
	}
	gameBoard = new Board();
	tileBag = new TileBag();
	tileBag.refillRack(players.get(0));
	tileBag.refillRack(players.get(1));
	overwriteScorekeeper(false);
    }

    private static String welcomeInstructions(){
	return "To run a Scrabble game with the default settings, type 'default' as an argument after the command. After that, include 2 one-word player names as such: java Scrabble default <name of Player 1> <name of Player 2>";
    }

    private static String instructions(){
	return "Commands:\nTo attempt to place a word, enter <word> <x-cor> <y-cor> <direction>\nDirection can be indicated by typing 'h' for horizontal or 'v' for vertical\n\nTo attempt to exchange tiles, enter the positions of the tiles you would like to exchange, leftmost being 1 and rightmost being 7\n\nTo pass, enter 0";
    }
    
    //need the calculateScore method, but that depends on the words placed and intersections

    //need to add keyboard listener

    //need to add runGame method

    //need to add endGame method

    
    public static void main(String[] args){
	/*Scrabble a=new Scrabble();
	a.initializeArrayList();
	//System.out.println(a.dictWordList);
	Tile b=new Tile("b");
	Tile e=new Tile("e");
	Tile d=new Tile("d");
	Tile x=new Tile("x");
	Tile y=new Tile("y");
	Tile z=new Tile("z");
	Tile blank=new Tile();
	blank.differentiate("a");
	TileBag tileBag=new TileBag();
	Player jen=new Player("Jenny");
	jen.addToRack(b);
	jen.addToRack(e);
	jen.addToRack(d);
	jen.addToRack(x);
	jen.addToRack(y);
	jen.addToRack(z);
	jen.addToRack(blank);
        System.out.println(jen);
	tileBag.refillRack(jen);
	System.out.println(jen);
	//System.out.println(wordValidityCheck("bed"));
        jen.placeWord(a,"bead",0,13,"v");
	System.out.println(jen);
	tileBag.refillRack(jen);
	System.out.println(jen);
	System.out.println(jen.getEndTurn());*/
	if(args.length == 3 && args[0].equals("default")){
	    Scrabble a = new Scrabble("Jenn", "Winn");
	    System.out.println(a.gameBoard);
	    System.out.println(a.players);
	    a.endGameScoring();
	    a.overwriteScorekeeper(true);
	    System.out.println(a.scorekeeper);
	    System.out.println(instructions());
	}else if(args.length < 3){
	    System.out.println(welcomeInstructions());
	    
	}
    }
}
