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

    private int findPlace(Player lostPlayer, int index){
	while(index >= 0 && lostPlayer.getRoundScore() >= players.get(index).getRoundScore()){
	    if(lostPlayer.getRoundScore() == players.get(index).getRoundScore()){
		if(lostPlayer.getTotalScore() > players.get(index).getTotalScore()){
		    index--;
		}else{
		    return index;
		}
	    }else{
		index--;
	    }
	}
	return index;
    }

    private void rankPlayers(){ //by insertion sort
	for(int i = 1; i < players.size(); i++){
	    Player lostPlayer = players.get(i);
	    int index = i - 1;
	    index = findPlace(lostPlayer, index);
	    players.add(index + 1, players.remove(i));
	}
    }

    private void overwriteScorekeeper(boolean isLastOverwrite){
	String lastColLabel = "";
	if(isLastOverwrite){
	    lastColLabel = "After Deductions";
	    rankPlayers();
	    scorekeeper = "The game has ended.\nCongratulations on winning, " + players.get(0).getName() + "!"; //add possibility of tie for top
	}else{
	    lastColLabel = "Current Round";
	    scorekeeper = "Scoreboard";
	}
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
	if(isLastOverwrite){
	    rowDivider += "+-----+----------------+";
	}else{
	    rowDivider += "+-----+-------------+";
	}
	//add ranking numbers next to player names in addition to possibility for ties
	scorekeeper += rowDivider + "\n|Player" + extraSpacesNeeded("Player", maxNameLength) + "|Total|" + lastColLabel + extraSpacesNeeded(lastColLabel, lastColLabel.length()) + "|" + rowDivider;
	for(int player = 0; player < players.size(); player++){
	    scorekeeper += "\n|" + players.get(player).getName() + extraSpacesNeeded(players.get(player).getName(), maxNameLength) +
		"|" + players.get(player).getTotalScore() + extraSpacesNeeded(Integer.toString(players.get(player).getTotalScore()), 5) +
		"|" + players.get(player).getRoundScore() + extraSpacesNeeded(Integer.toString(players.get(player).getRoundScore()), lastColLabel.length()) +
		"|" + rowDivider;
	}
    }
    
    private void endRoundScoring(){
	int totalRoundScore = 0;
	for(int player = 0; player < players.size(); player++){
	    totalRoundScore += players.get(player).getRoundScore();
	    players.get(player).setRoundScore(0);
	}
	totalRoundScores.add(Integer.valueOf(totalRoundScore));
    }

    private void endGameScoring(){
	endRoundScoring();
	int totalDeductions = 0;
	for(int player = 0; player < players.size(); player++){
	    players.get(player).setRoundScore(players.get(player).getTotalScore());
	    for(int tilesLeft = players.get(player).getRackSize() - 1; tilesLeft >= 0; tilesLeft--){
		players.get(player).setRoundScore(players.get(player).getRoundScore() - players.get(player).getPointsOfTileInRack(tilesLeft));
		totalDeductions += players.get(player).getPointsOfTileInRack(tilesLeft);
	    }
	}
	for(int player = 0; player < players.size(); player++){
	    if(players.get(player).getRackSize() == 0){
		players.get(player).setRoundScore(players.get(player).getRoundScore() + totalDeductions);
	    }
	}
    }
    
    public Scrabble(String playerNames){
	initializeArrayList();
	players = new ArrayList<Player>(0);
	Random randgen = new Random();
	int nextSpace = 0;
	int i = 1;
	while(playerNames.indexOf(" ") > -1){
	    nextSpace = playerNames.indexOf(" ");
	    players.add(randgen.nextInt(i), new Player(playerNames.substring(0, nextSpace)));
	    playerNames = playerNames.substring(nextSpace);
	    if(playerNames.length() == 1){
		playerNames = "";
	    }else{
		playerNames = playerNames.substring(1);
	    }
	    i++;
	}
	gameBoard = new Board();
	tileBag = new TileBag();
	for(int index = 0; index < players.size(); index++){
	    tileBag.refillRack(players.get(index));
	}
	overwriteScorekeeper(false);
	totalRoundScores = new ArrayList<Integer>(0);
    }

    private static String welcomeInstructions(){
	return "To run a Scrabble game with the default settings, type 'default' as an argument after the command. After that, include 2 or more one-word player names as such: java Scrabble default <name of Player 1> <name of Player 2> [<name of Player 3>] [<name of Player 4>] [<name of Player 5>]\n\nTo run a Scrabble game with custom settings for how many rounds the game should last, type: custom-rounds <number of rounds> <name of Player 1> <name of Player 2> [<name of Player 3>] [<name of Player 4>] ...etc.";
    }

    private static String instructions(){

	return "Commands:\nTo attempt to place a word, enter <word> <x-cor> <y-cor> <direction>\nSingle digit x-cor and y-cor values should be entered as a single digit\nDirection can be indicated by typing 'h' for horizontal or 'v' for vertical\n\nTo attempt to exchange tiles, enter the positions of the tiles you would like to exchange, leftmost being 1 and rightmost being 7\n\nTo turn a blank tile into any letter of the alphabet, enter <position of the '?' tile> <desired letter>\n\nTo pass, enter 0";

    }

    private String rewriteGame(Player currentPlayer){
	overwriteScorekeeper(false);
        return gameBoard + "\n" + scorekeeper + "\nCurrent Player: " + currentPlayer.getName() + "\n" + currentPlayer + "\n" + instructions();
    }

    private void overwriteGame(Player currentPlayer, String report){
	System.out.println(Cmd.CLEAR_SCREEN);
	System.out.println(Cmd.go(1,1));
	System.out.println(report);
	System.out.println(rewriteGame(currentPlayer));
    }

    public void runGame(int roundLimit){
	int round = 0;
	int turn = 0;
	String report = "";
	Scanner input = new Scanner(System.in);
	if(roundLimit <= 0){
	    while(true){ //looping through/counting rounds
		for(turn = 0; turn < players.size(); turn++){ //looping through players
		    overwriteGame(players.get(turn), report); //print the display for currentPlayer
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

		    
		    if(currentInput.charAt(0) == '-'){
			report = "Given invalid character. Please try again.";
		    }else if(currentInput.length()==1&&currentInput.charAt(0) == '0'){
			players.get(turn).pass();
		    }else if(isStringInt(Character.toString(currentInput.charAt(0)))){
			if((currentInput.length() >= 3) &&
			   ((currentInput.charAt(2)>='a'&& currentInput.charAt(2)<='z') ||
			    (currentInput.charAt(2)>='A'&& currentInput.charAt(2)<='Z'))){
			    int indexx=0;
			    indexx=Integer.parseInt(currentInput.substring(0,1));
			    String letterToChangeInto=currentInput.substring(2,3);
			    report = players.get(turn).requestDifferentiate(indexx-1,letterToChangeInto);
			}else{
			    for(int i = 0; i < currentInput.length(); i++){
				if(isStringInt(Character.toString(currentInput.charAt(i)))){
				    System.out.println(currentInput.charAt(i));
				    report = players.get(turn).requestExchange(tileBag, Integer.parseInt(currentInput.substring(i, i + 1)));
				}
			    }
			}
		    }
		    
		    else{ 
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
			    report = players.get(turn).placeWord(gameBoard, this, word, xCor, yCor, dir,true);
			}else{
			    report = players.get(turn).placeWord(gameBoard, this, word, xCor, yCor, dir,false);
			}
			tileBag.refillRack(players.get(turn));
		    }


		    //should make a check for the format of placing a word too and a return like "invalid format of a command/invalid command format" if "else"
		
		
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
	}else{
	    while(roundLimit > round){ 
		for(turn = 0; turn < players.size(); turn++){ //looping through players
		    overwriteGame(players.get(turn), report); //print the display for currentPlayer
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

		    else if('1' <= currentInput.charAt(0) &&
			    Character.forDigit(players.get(turn).getRackSize(), 10) >= currentInput.charAt(0)){ 
			if((currentInput.length() >= 3) &&
			   ((currentInput.charAt(2)>='a'&& currentInput.charAt(2)<='z') ||
			    (currentInput.charAt(2)>='A'&& currentInput.charAt(2)<='Z'))){
			    int indexx=0;
			    indexx=Integer.parseInt(currentInput.substring(0,1));
			    String letterToChangeInto=currentInput.substring(2,3);
			    report = players.get(turn).requestDifferentiate(indexx-1,letterToChangeInto);
			}else{
			    for(int i = 0; i < currentInput.length(); i++){
				if('1' <= currentInput.charAt(i) &&
				   Character.forDigit(players.get(turn).getRackSize(), 10) >= currentInput.charAt(i)){
				    System.out.println(currentInput.charAt(i));
				    report = players.get(turn).requestExchange(tileBag, Integer.parseInt(currentInput.substring(i, i + 1)));
				}

			    }
			}
		    }
		    else{ 
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
			    report = players.get(turn).placeWord(gameBoard, this, word, xCor, yCor, dir,true);
			}else{
			    report = players.get(turn).placeWord(gameBoard, this, word, xCor, yCor, dir,false);
			}
			tileBag.refillRack(players.get(turn));
		    }


		    //should make a check for the format of placing a word too and a return like "invalid format of a command/invalid command format" if "else"
		
		
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
	    endGame();
	}
    }

    public void endGame(){
	System.out.println(Cmd.CLEAR_SCREEN);
	System.out.println(Cmd.go(1,1));
	endGameScoring();
	overwriteScorekeeper(true);
	System.out.println(scorekeeper);
	System.exit(0);
    }

    public static boolean isStringInt(String string){
	try{
	    Integer.parseInt(string);
	    return true;
	}catch(NumberFormatException e){
	    return false;
	}
    }
    
    //should we include a chart of letter values on the bottom or top of the screen?

    
    
    public static void main(String[] args){
	if(args.length >= 3 && args[0].equals("default")){
	    String stringOfPlayerNames = "";
	    for(int i = 1; i < args.length; i++){
		stringOfPlayerNames += args[i] + " ";
	    }
	    Scrabble a = new Scrabble(stringOfPlayerNames); 
	    a.runGame(0);
	}else if(args.length >= 4 && args[0].equals("custom-rounds") && isStringInt(args[1])){
	    int numRounds = Integer.parseInt(args[1]);
	    String stringOfPlayerNames = "";
	    for(int i = 2; i < args.length; i++){
		stringOfPlayerNames += args[i] + " ";
	    }
	    Scrabble a = new Scrabble(stringOfPlayerNames); 
	    a.runGame(numRounds);
	}else{
	    System.out.println(welcomeInstructions());
	}
    }
}
