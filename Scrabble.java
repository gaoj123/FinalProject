import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception


public class Scrabble{
    public static ArrayList<String> dictWordList=new ArrayList<String>();
    private ArrayList<Player> players;
    private Board gameBoard;
    private TileBag tileBag;
    private String scorekeeper;
    private ArrayList<Integer> totalRoundScores;

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
	scorekeeper += rowDivider + "\n|Player" + extraSpacesNeeded("Player", maxNameLength) + "|Total|" + lastColLabel + extraSpacesNeeded(lastColLabel, 16) + "|" + rowDivider;
	for(int player = 0; player < players.size(); player++){
	    scorekeeper += "\n|" + players.get(player).getName() + extraSpacesNeeded(players.get(player).getName(), maxNameLength) +
		"|" + players.get(player).getTotalScore() + extraSpacesNeeded(Integer.toString(players.get(player).getTotalScore()), 5) +
		"|" + players.get(player).getRoundScore() + extraSpacesNeeded(Integer.toString(players.get(player).getRoundScore()), 16) +
		"|" + rowDivider;
	}
    }
    
    private void endRoundScoring(){
	int totalRoundScore = 0;
	for(int player = 0; player < players.size(); player++){
	    totalRoundScore += players.get(player).getRoundScore();
	    //players.get(player).setTotalScore(players.get(player).getTotalScore() + players.get(player).getRoundScore());
	    //players.get(player).setRoundScore(0);
	}
	totalRoundScores.add(Integer.valueOf(totalRoundScore));
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
	totalRoundScores = new ArrayList<Integer>(0);
    }

    private static String welcomeInstructions(){
	return "To run a Scrabble game with the default settings, type 'default' as an argument after the command. After that, include 2 one-word player names as such: java Scrabble default <name of Player 1> <name of Player 2>";
    }

    private static String instructions(){
	return "Commands:\nTo attempt to place a word, enter <word> <x-cor> <y-cor> <direction>\nSingle digit x-cor and y-cor values should be entered as a single digit\nDirection can be indicated by typing 'h' for horizontal or 'v' for vertical\n\nTo attempt to exchange tiles, enter the positions of the tiles you would like to exchange, leftmost being 1 and rightmost being 7\n\nTo turn a blank tile into any letter of the alphabet, enter <position of the '?' tile> <desired letter>\n\nTo pass, enter 0";
    }

    private String rewriteGame(Player currentPlayer){
	overwriteScorekeeper(false);
        return gameBoard + "\n" + scorekeeper + "\nCurrent Player: " + currentPlayer.getName() + "\n" + currentPlayer + "\n" + instructions();
    }

    private void overwriteGame(Player currentPlayer){
	//System.out.println(Cmd.HIDE_CURSOR);
	System.out.println(Cmd.CLEAR_SCREEN);
	System.out.println(Cmd.go(1,1));
	System.out.println(rewriteGame(currentPlayer));
    }

    public void runGame(){
	int round = 0;
	int turn = 0;
	Scanner input = new Scanner(System.in);
        while(true){ //looping through/counting rounds
	    for(turn = 0; turn < players.size(); turn++){ //looping through players
		overwriteGame(players.get(turn)); //print the display for currentPlayer
		String currentInput = input.nextLine();
		while(currentInput == null){
		    currentInput = input.nextLine();
		}
		boolean isEmpty=true;
		for(int i=0;i<15;i++){
		    for(int j=0;j<15;j++){
			if(gameBoard.squareOccupied(i,j)){
			    isEmpty=false;
			}
		    }
		}
		if(currentInput.length()==1&&currentInput.charAt(0) == '0'){
		    players.get(turn).pass();
		}
		else if(currentInput.length()>1&&(int)currentInput.charAt(0)<=(int)'7'&&(int)currentInput.charAt(0)>=(int)'1'&&((currentInput.charAt(2)>='a'&&currentInput.charAt(2)<='z')||(currentInput.charAt(2)>='A'&&currentInput.charAt(2)<='Z'))){
		    int indexx=0;
		    indexx=Integer.parseInt(currentInput.substring(0,1));
		    String letterToChangeInto="";
		    letterToChangeInto=currentInput.substring(2,3);
		    players.get(turn).requestDifferentiate(indexx-1,letterToChangeInto);
		}		
		else if((int) '1' <= (int)currentInput.charAt(0) &&
			players.get(turn).getRackSize() >= (int)currentInput.charAt(0)){
		    // for(int i = 0; i < currentInput.length() - 1; i++){
		    for(int i = 0; i < currentInput.length(); i++){
			if((int) '1' <= (int)currentInput.charAt(i) &&
			   (int) '7' >= (int)currentInput.charAt(i)){
			    System.out.println(currentInput.charAt(i));
			    players.get(turn).requestExchange(tileBag, Integer.parseInt(currentInput.substring(i, i + 1)));
			}
		    }
		}
		else{ //need code for retry if invalid character
		    int nextSpace = currentInput.indexOf(" ");
		    String word = currentInput.substring(0, nextSpace);
		    currentInput = currentInput.substring(nextSpace + 1);
		    nextSpace = currentInput.indexOf(" ");
		    int xCor = Integer.parseInt(currentInput.substring(0, nextSpace));
		    currentInput = currentInput.substring(nextSpace + 1);
		    nextSpace = currentInput.indexOf(" ");
		    int yCor = Integer.parseInt(currentInput.substring(0, nextSpace));
		    String dir = currentInput.substring(nextSpace + 1, nextSpace + 2);
		    if(isEmpty){
			players.get(turn).placeWord(gameBoard, this, word, xCor, yCor, dir,true);
		    }else{
			players.get(turn).placeWord(gameBoard, this, word, xCor, yCor, dir,false);
		    }
		    tileBag.refillRack(players.get(turn));
		}
		if(!players.get(turn).getEndTurn()){
		    turn--;
		}
				
		if(tileBag.getSize() == 0 && players.get(turn).getRackSize() == 0){ 
		    endGame(); //deals with the whole end-game sequence
		}
	    }
	    endRoundScoring();
	    if(round > 1 && totalRoundScores.get(round) + totalRoundScores.get(round - 1) + totalRoundScores.get(round - 2) == 0){
		endGame();
	    }
	    round++;
	}
    }

    public void endGame(){
	System.out.println(Cmd.CLEAR_SCREEN);
	System.out.println(Cmd.go(1,1));
	System.out.println("The Game Has Reached Its End"); //add an end-game msg
	endGameScoring();
	overwriteScorekeeper(true);
	System.out.println(scorekeeper);
	System.exit(0);
    }
    
    //should we include a chart of letter values on the bottom or top of the screen?

    
    
    public static void main(String[] args){
	if(args.length == 3 && args[0].equals("default")){
	    Scrabble a = new Scrabble(args[1], args[2]);
	    a.runGame();
	}else if(args.length < 3){
	    System.out.println(welcomeInstructions());
	}
    }
}
