import java.util.*;
public class Player{
    String name;
    ArrayList<Tile> rack;
    int totalScore;
    int roundScore;
    boolean endTurn;
    public Player(String name){
	this.name=name;
	totalScore=0;
	roundScore=0;
	rack=new ArrayList<Tile>();
	endTurn=false;
    }
    public boolean getEndTurn(){
	return endTurn;
    }
    public String getName(){
	return name;
    }
    public int getTotalScore(){
	return totalScore;
    }
    public int getRoundScore(){
	return roundScore;
    }
    public int getPointsOfTileInRack(int index){
	return rack.get(index).getPoints();
    }
    public void setTotalScore(int toAdd){
	totalScore += toAdd;
    }
    public void setRoundScore(int newRoundScore){
	roundScore = newRoundScore;
    }
    public boolean lettersInRack(String word1){
	boolean haveTilesOrNotInRack=true;
    	for(int i=0;i<word1.length();i++){
    	    int in=0;
    	    for(int j=0;j<7;j++){
		Tile lookingAt=rack.get(j);
		String letterInWord=word1.substring(i,i+1);
		if(letterInWord.toUpperCase().equals(lookingAt.getLetter())){
    		    in=1;
    		}
		if(j==6&&in==0){
    		    haveTilesOrNotInRack=false;
    		}
	    }
	}
	return haveTilesOrNotInRack;
    }
    public void setWordRackTiles(Board board1,String word1,int x1,int y1,String dir){
	int totalPointValue=0;
	int wordLength=word1.length();
	int col=x1;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean doubleWord=false;
	boolean tripleWord=false;
	for(int p=0;p<wordLength;p++){
	    int pointForTile=0;
	    int tileIndexInRack=0;
	    for(int start=0;start<rack.size();start++){
		String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		if(letterLookingAt.equals(rack.get(start).getLetter())){
		    tileIndexInRack=start;
		}
	    }
	    Tile ofInterest=rack.get(tileIndexInRack);
	    if(ofInterest.getBlankOrNot()==true){
		pointForTile=0;
	    }
	    else{
		pointForTile=ofInterest.getPoints();
	    }
	    String effect=board1.getEffectOfSquare(arrayrow,arraycol);
	    if(effect.equals("double letter")){
		pointForTile=pointForTile*2;
	    }
	    else if(effect.equals("triple letter")){
		pointForTile=pointForTile*3;
	    }
	    if(dir.equals("h")){
		int col2=x1;
		int row2=y1;
		int arrayrow2=14-row2;
		int arraycol2=col2;
		for(int colCheck2=0;colCheck2<word1.length();colCheck2++){
		    Tile ofConcern=board1.getTileOfSquare(arrayrow,arraycol2+colCheck2);
		    String effect2=board1.getEffectOfSquare(arrayrow,arraycol2+colCheck2);
		    if(effect2.equals("double word")){
			doubleWord=true;
		    }
		    if(effect2.equals("triple word")){
			tripleWord=true;
		    }
		}
		//board1.setTileOfSquare(arrayrow,arraycol,ofInterest);
		board1.setTileOfSquare(arrayrow,arraycol,ofInterest);
		if(doubleWord==true){
		    pointForTile=pointForTile*2;
		}
		if(tripleWord==true){
		    pointForTile=pointForTile*3;
		}
		board1.setEffectOfSquare(arrayrow,arraycol,"regular");				
		arraycol+=1;
	    }
	    else{
		int col2=x1;
		int row2=y1;
		int arrayrow2=14-row2;
		int arraycol2=col2;
		for(int rowCheck2=0;rowCheck2<word1.length();rowCheck2++){
		    Tile ofConcern=board1.getTileOfSquare(arrayrow2+rowCheck2,arraycol2);
		    String effect2=board1.getEffectOfSquare(arrayrow2+rowCheck2,arraycol2);
		    if(effect2.equals("double word")){
			doubleWord=true;
		    }
		    if(effect2.equals("triple word")){
			tripleWord=true;
		    }
		}
		board1.setTileOfSquare(arrayrow,arraycol,ofInterest);
		if(doubleWord==true){
		    pointForTile=pointForTile*2;
		}
		if(tripleWord==true){
		    pointForTile=pointForTile*3;
		}
		board1.setEffectOfSquare(arrayrow,arraycol,"regular");
		arrayrow+=1;
	    }
	    // if(doubleWord==true){
	    // 	pointForTile=pointForTile*2;
	    // }
	    // if(tripleWord==true){
	    // 	pointForTile=pointForTile*3;
	    // }
	    System.out.println("point for letter: "+pointForTile+ " "+"row "+arrayrow);
	    totalPointValue+=pointForTile;
	    removeFromRack(ofInterest);
	    
			    
	}
	if(word1.length()==7){
	    setRoundScore(totalPointValue+50);
	    setTotalScore(totalPointValue+50);
	}
	else{
	    setRoundScore(totalPointValue);
	    setTotalScore(totalPointValue);
	}
	System.out.println("total points: "+totalPointValue);
	// setRoundScore(totalPointValue);
	// setTotalScore(totalPointValue);
	endTurn=true;
    }
    public void setWordHor(Board board1,String word1,int x1,int y1){
	//int pointForTile=0;
	int totalPointValue=0;
	int wordLength=word1.length();
	int col=x1;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean doubleWord=false;
	boolean tripleWord=false;
	for(int colCheck2=0;colCheck2<word1.length();colCheck2++){
	    Tile ofConcern2=board1.getTileOfSquare(arrayrow,arraycol+colCheck2);
	    String effect2=board1.getEffectOfSquare(arrayrow,arraycol+colCheck2);
	    if(effect2.equals("double word")){
		doubleWord=true;
	    }
	    else if(effect2.equals("triple word")){
		tripleWord=true;
	    }
	}	    
	for(int colCheck=0;colCheck<word1.length();colCheck++){
	    Tile ofConcern=board1.getTileOfSquare(arrayrow,arraycol+colCheck);
	    int pointForTile=0;
	    if(word1.substring(colCheck,colCheck+1).toUpperCase().equals(ofConcern.getLetter())){
		pointForTile=ofConcern.getPoints();
		//arraycol+=1;
		// for(int p=0;p<word1.length();p++){
		//     int tileIndexInRack=0;
		//     for(int start=0;start<rack.size();start++){
		// 	String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		// 	if(letterLookingAt.equals(rack.get(start).getLetter())){
		// 	    tileIndexInRack=start;
		// 	    Tile ofInterest=rack.get(tileIndexInRack);
		// 	    removeFromRack(ofInterest);
		// 	}
		//     }
		// }
		// Tile ofInterest=rack.get(tileIndexInRack);
		// removeFromRack(ofInterest);
	    }
	    else if(ofConcern.getLetter().equals(" ")){
		Tile createTile=new Tile(word1.substring(colCheck,colCheck+1));
		//don't permit ? as letter
		pointForTile=createTile.getPoints();
		board1.setTileOfSquare(arrayrow,arraycol+colCheck,createTile);
		//arraycol+=1;
		for(int p=0;p<word1.length();p++){
		    int tileIndexInRack=0;
		    for(int start=0;start<rack.size();start++){
			String letterLookingAt=word1.substring(p,p+1).toUpperCase();
			if(letterLookingAt.equals(rack.get(start).getLetter())){
			    tileIndexInRack=start;
			    Tile ofInterest=rack.get(tileIndexInRack);
			    removeFromRack(ofInterest);
			}
		    }
		}
	    }
	    String effect=board1.getEffectOfSquare(arrayrow,arraycol+colCheck);
	    System.out.println(effect);
	    if(effect.equals("double letter")){
		pointForTile=pointForTile*2;
	    }
	    else if(effect.equals("triple letter")){
		pointForTile=pointForTile*3;
	    }
	    if(doubleWord==true){
		pointForTile=pointForTile*2;
	    }
	    if(tripleWord==true){
		pointForTile=pointForTile*3;
	    }
	    // for(int colCheck2=0;colCheck2<word1.length();colCheck2++){
	    // 	Tile ofConcern2=board1.getTileOfSquare(arrayrow,arraycol+colCheck2);
	    // 	String effect2=board1.getEffectOfSquare(arrayrow,arraycol+colCheck2);
	    // 	if(effect2.equals("double word")){
	    // 	    doubleWord=true;
	    // 	}
	    // 	else if(effect2.equals("triple word")){
	    // 	    tripleWord=true;
	    // 	}
	    // }
	    // if(doubleWord==true){
	    // 	pointForTile=pointForTile*2;
	    // }
	    // if(tripleWord==true){
	    // 	pointForTile=pointForTile*3;
	    // }
	    board1.setEffectOfSquare(arrayrow,arraycol+colCheck,"regular");	
	    System.out.println("point for letter: "+pointForTile+" col"+arraycol);
	    totalPointValue+=pointForTile;
	}
	if(word1.length()==7){
	    setRoundScore(totalPointValue+50);
	    setTotalScore(totalPointValue+50);
	}
	else{
	    setRoundScore(totalPointValue);
	    setTotalScore(totalPointValue);
	}
	System.out.println("total points: "+totalPointValue);
	// setRoundScore(totalPointValue);
	// setTotalScore(totalPointValue);
	endTurn=true;
    }
    public void setWordVert(Board board1, String word1,int x1, int y1){
	int col=x1;
	int totalPointValue=0;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean ret=true;
	boolean doubleWord=false;
	boolean tripleWord=false;
	for(int rowCheck2=0;rowCheck2<word1.length();rowCheck2++){
	    Tile ofConcern2=board1.getTileOfSquare(arrayrow+rowCheck2,arraycol);
	    String effect2=board1.getEffectOfSquare(arrayrow+rowCheck2,arraycol);
	    if(effect2.equals("double word")){
		doubleWord=true;
	    }
	    else if(effect2.equals("triple word")){
		tripleWord=true;
	    }
	}
	for(int rowCheck=0;rowCheck<word1.length();rowCheck++){
	    Tile ofConcern=board1.getTileOfSquare(arrayrow+rowCheck,arraycol);
	    int pointForTile=0;
	    if(word1.substring(rowCheck,rowCheck+1).toUpperCase().equals(ofConcern.getLetter())){
		System.out.println("using board's tile");
		//int pointForTile=0;
		pointForTile=ofConcern.getPoints();
		//arrayrow+=1;
		// for(int p=0;p<word1.length();p++){
		//     int tileIndexInRack=0;
		//     for(int start=0;start<rack.size();start++){
		// 	String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		// 	if(letterLookingAt.equals(rack.get(start).getLetter())){
		// 	    tileIndexInRack=start;
		// 	    Tile ofInterest=rack.get(tileIndexInRack);
		// 	    removeFromRack(ofInterest);
		// 	}
		//     }
		// }
		// Tile ofInterest=rack.get(tileIndexInRack);
		// removeFromRack(ofInterest);
	    }
	    else if(ofConcern.getLetter().equals(" ")){
		Tile createTile=new Tile(word1.substring(rowCheck,rowCheck+1));
		System.out.println("using own tile");
		//don't permit ? as letter
		pointForTile=createTile.getPoints();
		//board1.setTileOfSquare(arrayrow,arraycol,createTile);
		//	arrayrow+=1;
		board1.setTileOfSquare(arrayrow+rowCheck,arraycol,createTile);
		for(int p=0;p<word1.length();p++){
		    int tileIndexInRack=0;
		    for(int start=0;start<rack.size();start++){
			String letterLookingAt=word1.substring(p,p+1).toUpperCase();
			if(letterLookingAt.equals(rack.get(start).getLetter())){
			    tileIndexInRack=start;
			    Tile ofInterest=rack.get(tileIndexInRack);
			    removeFromRack(ofInterest);
			}
		    }
		}
	    }
	    else{
		System.out.println("none"+ofConcern.getLetter());
	    }
	    String effect=board1.getEffectOfSquare(arrayrow+rowCheck,arraycol);
	    System.out.println(effect);
	    if(effect.equals("double letter")){
		pointForTile=pointForTile*2;
	    }
	    else if(effect.equals("triple letter")){
		pointForTile=pointForTile*3;
	    }
	    if(doubleWord==true){
		pointForTile=pointForTile*2;
	    }
	    if(tripleWord==true){
		pointForTile=pointForTile*3;
	    }
	    board1.setEffectOfSquare(arrayrow+rowCheck,arraycol,"regular");
	    System.out.println("point for letter: "+pointForTile);
	    totalPointValue+=pointForTile;
	}
	// for(int rowCheck2=0;rowCheck2<word1.length();rowCheck2++){
	//     Tile ofConcern2=board1.getTileOfSquare(arrayrow+rowCheck2,arraycol);
	//     String effect2=board1.getEffectOfSquare(arrayrow+rowCheck2,arraycol);
	//     if(effect2.equals("double word")){
	// 	doubleWord=true;
	//     }
	//     else if(effect2.equals("triple word")){
	// 	tripleWord=true;
	//     }
	// }
	// if(doubleWord==true){
	//     pointForTile=pointForTile*2;
	// }
	// if(tripleWord==true){
	//     pointForTile=pointForTile*3;
	// }
	// board1.setEffectOfSquare(arrayrow+rowCheck,arraycol,"regular");
	if(word1.length()==7){
	    setRoundScore(totalPointValue+50);
	    setTotalScore(totalPointValue+50);
	}
	else{
	    setRoundScore(totalPointValue);
	    setTotalScore(totalPointValue);
	}
	System.out.println("total points: "+totalPointValue);
	// setRoundScore(totalPointValue);
	// setTotalScore(totalPointValue);
	endTurn=true;
    }

    public boolean checkHor(Board board1,String word1,int x1,int y1){
	int col=x1;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean ret=true;
	for(int colCheck=0;colCheck<word1.length();colCheck++){
	    Tile ofConcern=board1.getTileOfSquare(arrayrow,arraycol+colCheck);
	    if(!(ofConcern.getLetter().equals(" "))&&(!word1.substring(colCheck,colCheck+1).toUpperCase().equals(ofConcern.getLetter()))){
		ret=false;
	    }
	}
	return ret;
    }
    public boolean checkVert(Board board1,String word1,int x1,int y1){
	int col=x1;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean ret=true;
	for(int rowCheck=0;rowCheck<word1.length();rowCheck++){
	    Tile ofConcern=board1.getTileOfSquare(arrayrow+rowCheck,arraycol);
	    if(!(ofConcern.getLetter().equals(" "))&&(!word1.substring(rowCheck,rowCheck+1).toUpperCase().equals(ofConcern.getLetter()))){
		ret=false;
				   
	    }
	}
	return ret;
    }
    public void placeWord(Board board, Scrabble game, String word,int x,int y,String direction, boolean emptyBoard){
    	boolean haveTilesOrNotInRack=true;
	boolean empty=emptyBoard;
	int col=x;
	int row=y;
	int arrayrow=14-row;
	int arraycol=col;
	boolean okayToLay=false;
	int wordLength=word.length();
	boolean intersectAtLeastOnce=false;
	if(!(x>=0&&x<=14)||!(y>=0&&y<=14)){
	    System.out.println("Position is off the board.  Please try again");
	    endTurn=false;
	}
	else{
	    if((direction.equals("h")&&!(x+wordLength<=15))||((direction.equals("v")&&!(y-wordLength>=-1)))){
		System.out.println("Position is on the board but can't place word.  Please try again");
		endTurn=false;
	    }
	    else{
		if(direction.equals("h")){
		    int col2=x;
		    int row2=y;
		    int arrayrow2=14-row2;
		    int arraycol2=col2;
		    int numIntersections=0;
		    for(int colCheck2=0;colCheck2<word.length();colCheck2++){
			Tile ofConcern2=board.getTileOfSquare(arrayrow2,arraycol2+colCheck2);
			System.out.println("occupied? "+board.squareOccupied(arrayrow2,arraycol2+colCheck2)+" same letter? "+word.substring(colCheck2,colCheck2+1).toUpperCase().equals(ofConcern2.getLetter()));
			if (board.squareOccupied(arrayrow2,arraycol2+colCheck2)&&word.substring(colCheck2,colCheck2+1).toUpperCase().equals(ofConcern2.getLetter())){
			    numIntersections+=1;
			}
		    }
		    if(numIntersections>=1){
			intersectAtLeastOnce=true;
		    }
		}
		else{
		    int col2=x;
		    int row2=y;
		    int arrayrow2=14-row2;
		    int arraycol2=col2;
		    int numIntersections=0;
		    for(int rowCheck2=0;rowCheck2<word.length();rowCheck2++){
			Tile ofConcern2=board.getTileOfSquare(arrayrow2+rowCheck2,arraycol2);
			System.out.println("occupied? "+board.squareOccupied(arrayrow2+rowCheck2,arraycol2)+" same letter? "+word.substring(rowCheck2,rowCheck2+1).toUpperCase().equals(ofConcern2.getLetter()));
			if(board.squareOccupied(arrayrow2+rowCheck2,arraycol2)&&word.substring(rowCheck2,rowCheck2+1).toUpperCase().equals(ofConcern2.getLetter())){
			    numIntersections+=1;				   
			}
		    }
		    if(numIntersections>=1){
			intersectAtLeastOnce=true;
		    }
		}
		if(intersectAtLeastOnce==false&&empty==false){
		    System.out.println(intersectAtLeastOnce+ " "+empty);
		    System.out.println("Word must use one of the letters already on the board.   Please try again");
		    endTurn=false;
		}
		else{
		    boolean startingCenter=false;
		    if(empty){
			if(direction.equals("h")){
			    int col2=x;
			    int row2=y;
			    int arrayrow2=14-row2;
			    int arraycol2=col2;
			    int numIntersections=0;
			    for(int colCheck2=0;colCheck2<word.length();colCheck2++){
				if (arraycol2+colCheck2==7&&arrayrow==7){
				    startingCenter=true;
				}
			    }
			}
			else{
			    int col2=x;
			    int row2=y;
			    int arrayrow2=14-row2;
			    int arraycol2=col2;
			    int numIntersections=0;
			    for(int rowCheck2=0;rowCheck2<word.length();rowCheck2++){		
				if(arrayrow2+rowCheck2==7&&arraycol==7){
				    startingCenter=true;				   
				}
			    }
			}
		    }
		    System.out.println("starting center? "+startingCenter);
		    if(empty&&!startingCenter){
			System.out.println("First word must have a letter on the center square.  Please try again");
			endTurn=false;
		    }
		    else{
			if(!(x>=0&&x<=14)||!(y>=0&&y<=14)){
			    System.out.println("Position is off the board.  Please try again");
			    endTurn=false;
			}
			else{
			    if((direction.equals("h")&&!(x+wordLength<=15))||((direction.equals("v")&&!(y-wordLength>=-1)))){
				System.out.println("Position is on the board but can't place word.  Please try again");
				endTurn=false;
			    }
			    else{
				haveTilesOrNotInRack=lettersInRack(word);
				System.out.println("in rack? "+haveTilesOrNotInRack);
				if(!lettersInRack(word)){
				    if(direction.equals("v")){
					if(checkVert(board,word,x,y)){
					    okayToLay=true;
					}
					else{
					    System.out.println("Cannot lay such a word at such a position.  Some tiles that are not in rack are also not on the board at appropriate positions (when word is laid out).   Please try again.");
					    endTurn=false;
					}
				    }
				    else if(direction.equals("h")){
					if(checkHor(board,word,x,y)){
					    okayToLay=true;
					}
					else{
					    System.out.println("Cannot lay such a word at such a position.  Some tiles that are not in rack are also not on the board at appropriate positions (when word is laid out).   Please try again.");
					    endTurn=false;
					}
				    }
				}//also condition when square is occupied and tile is not in rack.  but maybe already checked in checkHor
				else{
				    if(direction.equals("v")){
					if(checkVert(board,word,x,y)){
					    okayToLay=true;
					}
					else{
					    System.out.println("Tiles are in rack but some squares on board are already occupied (when word is laid out).  Please try again.");
					    endTurn=false;
					}
				    }
				    else if(direction.equals("h")){
					if(checkHor(board,word,x,y)){
					    okayToLay=true;
					}
					else{
					    System.out.println("Tiles are in rack but some squares on board are already occupied (when word is laid out).  Please try again.");
					    endTurn=false;
					}
				    }
				    //okayToLay=true;
				}
				if(okayToLay){
				    boolean validOrNot=game.wordValidityCheck(word);
				    System.out.println("valid word? "+word+" "+validOrNot);
				    //int wordLength=word.length();
				    if(validOrNot&&(x>=0&&x<=14)&&(y>=0&&y<=14)){
					System.out.println("x and y between 0 and 14, so on board");
					if((direction.equals("h")&&x+wordLength<=15)||(direction.equals("v")&&y-wordLength>=-1)){
					    if(haveTilesOrNotInRack){
						setWordRackTiles(board,word,x,y,direction);
					    }
					    else if(direction.equals("h")){
						setWordHor(board,word,x,y);
					    }
					    else if(direction.equals("v")){
						setWordVert(board,word,x,y);
					    }
					    // System.out.println("point for letter: "+pointForTile);
					    // totalPointValue+=pointForTile;
					    // //removeFromRack(ofInterest);			  		
					    // System.out.println("total points: "+totalPointValue);
					    // endTurn=true;
					}
					else{
					    System.out.println("Position is on the board but cannot place word in position.  Please try again");
					    endTurn=false;
		    
					}
				    }
				    else if(validOrNot==false){
					System.out.println("Invalid word.  Next player's turn.");
					endTurn=true;
				    }
				    else if(!(x>=0&&x<=14)||!(y>=0&&y<=14)){
					System.out.println("Position is off the board so cannot place word on board. Please try again");
					endTurn=false;
				    }
				}
			    }
			}
		    }
		}
	    }
	}
	// else{
	// 	System.out.println("Word contains letters not in your tile rack.  Please try again with only valid letters");
	// 	endTurn=false;
	// }
    }

    public Tile tileAtRackIndex(int indexx){
	return rack.get(indexx);
    }
    public void addToRack(Tile fromBag){
	rack.add(fromBag);
    }
    public void addToRack(int pos, Tile fromBag){
	rack.add(pos,fromBag);
    }
    public void removeFromRack(Tile toRemove){
	rack.remove(toRemove);
    }
    public int getRackSize(){
	return rack.size();
    }
    public void requestExchange(TileBag tilebag,int indexToExchange){
	tilebag.exchange(this,indexToExchange);;
	roundScore=0;
	endTurn=true;
    }
    public void requestDifferentiate(int index,String letter){
	System.out.println("index: "+index);
	System.out.println("letter: "+letter);
	if(tileAtRackIndex(index).getLetter().equals("?")){
	    int ascii=(int)letter.charAt(0);
	    if((ascii>='a'&&ascii<='z')||(ascii>='A'&&ascii<='Z')){
		Tile inRack=this.tileAtRackIndex(index);
		this.removeFromRack(inRack);
		Tile rackTile=new Tile("?");
		rackTile.differentiate(letter);
		this.addToRack(index,rackTile);
	    }
	    else{
		System.out.println("Invalid letter. Letter must be a letter in the alphabet.");
	    }
	}
	else{
	    System.out.println("Tile at that index in your rack is not a blank tile");
	}
	endTurn=false;
    }
    public void pass(){
	roundScore=0;
	endTurn=true;
    }
    public String toString(){
	String retStr="Your tiles: ";
	for(int i=0;i<rack.size();i++){
	    Tile displayTile=rack.get(i);
	    String letterCaps=displayTile.getLetter();
	    letterCaps=letterCaps.toUpperCase();
	    retStr=retStr+letterCaps+" ";
	}
	return retStr;
    }
    public static void main(String[] args){
    }

}
