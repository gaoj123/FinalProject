import java.util.*;
public class Player{
    String name;
    ArrayList<Tile> rack;
    int totalScore;
    int roundScore;
    boolean endTurn;
    int timesCanExchangeLeft=3;
    public Player(String name){
	this.name=name;
	totalScore=0;
	roundScore=0;
	rack=new ArrayList<Tile>();
	endTurn=false;
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
    public void setTotalScore(int addTo){
	totalScore += addTo;
    }
    public void setRoundScore(int newRoundScore){
	roundScore = newRoundScore;
    }
    public boolean getEndTurn(){
	return endTurn;
    }
    public String getName(){
	return name;
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
	    if(dir.equals("h")){
		board1.setTileOfSquare(arrayrow,arraycol,ofInterest);
		arraycol+=1;
	    }
	    else{
		board1.setTileOfSquare(arrayrow,arraycol,ofInterest);
		arrayrow+=1;
	    }
	    System.out.println("point for letter: "+pointForTile);
	    totalPointValue+=pointForTile;
	    removeFromRack(ofInterest);
			    
	}
	System.out.println("total points: "+totalPointValue);
	setRoundScore(totalPointValue);
	setTotalScore(totalPointValue);
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
	    System.out.println("point for letter: "+pointForTile);
	    totalPointValue+=pointForTile;
	}
	System.out.println("total points: "+totalPointValue);
	setRoundScore(totalPointValue);
	setTotalScore(totalPointValue);
	endTurn=true;
    }
    public void setWordVert(Board board1, String word1,int x1, int y1){
	int col=x1;
	int totalPointValue=0;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean ret=true;
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
	    System.out.println("point for letter: "+pointForTile);
	    totalPointValue+=pointForTile;
	}
	System.out.println("total points: "+totalPointValue);
	setRoundScore(totalPointValue);
	setTotalScore(totalPointValue);
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
    public void placeWord(Board board, Scrabble game, String word,int x,int y,String direction){
    	boolean haveTilesOrNotInRack=true;
	int col=x;
	int row=y;
	int arrayrow=14-row;
	int arraycol=col;
	boolean okayToLay=false;
	int wordLength=word.length();
	if(!(x>=0&&x<=14)||!(y>=0&&y<=14)){
	    System.out.println("Position is off the board.  Please try again");
	    endTurn=false;
	}
	else{
	    if((direction.equals("h")&&!(x+wordLength<=15))||((direction.equals("v")&&!(y-wordLength>=-1)))){
		System.out.println("Position is on the board but can't place word.");
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
			}
		    }
		    else if(direction.equals("h")){
			if(checkHor(board,word,x,y)){
			    okayToLay=true;
			}
			else{
			    System.out.println("Cannot lay such a word at such a position.  Some tiles that are not in rack are also not on the board at appropriate positions (when word is laid out).   Please try again.");
			}
		    }
		}
		else{
		    if(direction.equals("v")){
			if(checkVert(board,word,x,y)){
			    okayToLay=true;
			}
			else{
			    System.out.println("Tiles are in rack but some squares on board are already occupied (when word is laid out).  Please try again.");
			}
		    }
		    else if(direction.equals("h")){
			if(checkHor(board,word,x,y)){
			    okayToLay=true;
			}
			else{
			    System.out.println("Tiles are in rack but some squares on board are already occupied (when word is laid out).  Please try again.");
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
    // else{
    // 	System.out.println("Word contains letters not in your tile rack.  Please try again with only valid letters");
    // 	endTurn=false;
    // }
    }


//START OLD STUFF
    // 	if (haveTilesOrNotInRack){
    // 		boolean validOrNot=game.wordValidityCheck(word);
    // 		System.out.println("valid word? "+word+" "+validOrNot);
    // 		int wordLength=word.length();
    // 		if(validOrNot&&(x>=0&&x<=14)&&(y>=0&&y<=14)){
    // 		    	System.out.println("x and y between 0 and 14, so on board");
    // 		    if((direction.equals("h")&&x+wordLength<=15)||(direction.equals("v")&&y+wordLength>=-1)){
    // 			// boolean onAnyOccupiedSquares=false;
    // 			// if(direction.equals("v")){
    // 			//     for(int rowCheck=0;rowCheck<word.length();rowCheck++){
    // 			// 	Tile ofConcern=board.getTileOfSquare(arrayrow+rowCheck,arraycol);
    // 			// 	if(board.squareOccupied(arrayrow+rowCheck,arraycol)==true&&(!(word.substring(rowCheck,rowCheck+1).toUpperCase().equals(ofConcern.getLetter())))){
    // 			// 	    onAnyOccupiedSquares=true;
    // 			// 	}
    // 			//     }
    // 			// }
    // 			// else if(direction.equals("h")){
    // 			//     for(int colCheck=0;colCheck<word.length();colCheck++){
    // 			// 	Tile ofConcern=board.getTileOfSquare(arrayrow,arraycol+colCheck);
    // 			// 	if(board.squareOccupied(arrayrow,arraycol+colCheck)==true&&(!(word.substring(colCheck,colCheck+1).toUpperCase().equals(ofConcern.getLetter())))){
    // 			// 	    onAnyOccupiedSquares=true;
    // 			// 	}
    // 			//     }
    // 			// }
    // 		        //if(!onAnyOccupiedSquares){
    // 			int totalPointValue=0;
    // 			for(int p=0;p<wordLength;p++){
    // 			    int pointForTile=0;
    // 			    int tileIndexInRack=0;
    // 			    boolean gotPoint=false;
    // 			    if(direction.equals("v")){
    // 				// for(int rowCheck=0;rowCheck<word.length();rowCheck++){
    // 				//     Tile ofConcern=board.getTileOfSquare(arrayrow+rowCheck,arraycol);
    // 				//     System.out.println(ofConcern.getWordUsingTileNotInRack());
    // 				//     if(ofConcern.getWordUsingTileNotInRack()==true){
    // 				// 	pointForTile=ofConcern.getPoints();
    // 				// 	gotPoint=true;
    // 				// 	arrayrow+=1;
    // 				//     }
    // 				// }
    // 				Tile ofConcern=board.getTileOfSquare(arrayrow+p,arraycol);
    // 				System.out.println("board letter "+ofConcern.getLetter());
    // 				if(ofConcern.getWordUsingTileNotInRack()==true){
    // 				    System.out.println("overlap");
    // 				    pointForTile=ofConcern.getPoints();
    // 				    gotPoint=true;
    // 				    arrayrow+=1;
    // 				}
    // 				else{
    // 				    System.out.println("2");
    // 				    for(int start=0;start<rack.size();start++){
    // 					String letterLookingAt=word.substring(p,p+1).toUpperCase();
    // 					if(letterLookingAt.equals(rack.get(start).getLetter())){
    // 					    tileIndexInRack=start;
    // 					}
    // 				    }
    // 				    Tile ofInterest=rack.get(tileIndexInRack);		       
		      
    // 				    if(ofInterest.getBlankOrNot()==true){
    // 					pointForTile=0;
    // 				    }
    // 				    else{
    // 					pointForTile=ofInterest.getPoints();
    // 				    }
    // 				    if(direction.equals("h")){
    // 					board.setTileOfSquare(arrayrow,arraycol,ofInterest);
    // 					arraycol+=1;
    // 				    }
    // 				    else{
    // 					board.setTileOfSquare(arrayrow,arraycol,ofInterest);
    // 					arrayrow+=1;
    // 				    }
    // 				    removeFromRack(ofInterest);
    // 				}
    // 				//arrayrow+=1;
    // 			    }
    // 			    else if(direction.equals("h")){
    // 				// for(int colCheck=0;colCheck<word.length();colCheck++){
    // 				//     Tile ofConcern=board.getTileOfSquare(arrayrow,arraycol+colCheck);
    // 				//     System.out.println(ofConcern.getWordUsingTileNotInRack());
    // 				//     if(ofConcern.getWordUsingTileNotInRack()==true){
    // 				// 	pointForTile=ofConcern.getPoints();
    // 				// 	gotPoint=true;
    // 				// 	arraycol+=1;
    // 				//     }
    // 				// }
    // 				 Tile ofConcern=board.getTileOfSquare(arrayrow,arraycol+p);
    // 				 System.out.println("board letter "+ofConcern.getLetter());
    // 				if(ofConcern.getWordUsingTileNotInRack()==true){
    // 				    System.out.println("overlap");
    // 				    pointForTile=ofConcern.getPoints();
    // 				    gotPoint=true;
    // 				    arraycol+=1;
    // 				}
    // 				else{
    // 				    System.out.println("2");
    // 				    for(int start=0;start<rack.size();start++){
    // 					String letterLookingAt=word.substring(p,p+1).toUpperCase();
    // 					if(letterLookingAt.equals(rack.get(start).getLetter())){
    // 					    tileIndexInRack=start;
    // 					}
    // 				    }
    // 				    Tile ofInterest=rack.get(tileIndexInRack);					      
    // 				    if(ofInterest.getBlankOrNot()==true){
    // 					pointForTile=0;
    // 				    }
    // 				    else{
    // 					pointForTile=ofInterest.getPoints();
    // 				    }
    // 				    if(direction.equals("h")){
    // 					board.setTileOfSquare(arrayrow,arraycol,ofInterest);
    // 					arraycol+=1;
    // 				    }
    // 				    else{
    // 					board.setTileOfSquare(arrayrow,arraycol,ofInterest);
    // 					arrayrow+=1;
    // 				    }
    // 				    removeFromRack(ofInterest);
    // 				}
    // 				//arraycol+=1;
    // 			    }
							      
    // 			    // if(gotPoint==false){
    // 			    // 	System.out.println("2");
    // 			    // 	for(int start=0;start<rack.size();start++){
    // 			    // 	    String letterLookingAt=word.substring(p,p+1).toUpperCase();
    // 			    // 	    if(letterLookingAt.equals(rack.get(start).getLetter())){
    // 			    // 		tileIndexInRack=start;
    // 			    // 	    }
    // 			    // 	}
    // 			    // 	Tile ofInterest=rack.get(tileIndexInRack);		       		      
    // 			    // 	if(ofInterest.getBlankOrNot()==true){
    // 			    // 	    pointForTile=0;
    // 			    // 	}
    // 			    // 	else{
    // 			    // 	    pointForTile=ofInterest.getPoints();
    // 			    // 	}
    // 			    // 	if(direction.equals("h")){
    // 			    // 	    board.setTileOfSquare(arrayrow,arraycol,ofInterest);
    // 			    // 	    arraycol+=1;
    // 			    // 	}
    // 			    // 	else{
    // 			    // 	    board.setTileOfSquare(arrayrow,arraycol,ofInterest);
    // 			    // 	    arrayrow+=1;
    // 			    // 	}
    // 			    // 	removeFromRack(ofInterest);
    // 			    // }
    // 			    System.out.println("point for letter: "+pointForTile);
    // 			    totalPointValue+=pointForTile;
    // 			    //removeFromRack(ofInterest);
			    
    // 			}
    // 			System.out.println("total points: "+totalPointValue);
    // 			endTurn=true;
    // 			}
    // 			else if(onAnyOccupiedSquares){
    // 			    System.out.println("When laid out, word will be on an occupied square (square already has a tile).  Please try again and choose an appropriate x-cor and y-cor and direction");
    // 			}
    // 		    }
    // 		    else{
    // 			System.out.println("Position is on the board but cannot place word in position.  Please try again");
    // 			endTurn=false;
		    
    // 		    }
    // 		}
    // 		else if(validOrNot==false){
    // 		    System.out.println("Invalid word.  Next player's turn.");
    // 		    endTurn=true;
    // 		}
    // 		else{
    // 		    System.out.println("Position is off the board so cannot place word on board.  Please try again");
    // 		    endTurn=false;
    // 		}
    // 	}
    // 	else{
    // 	    System.out.println("Word contains letters not in your tile rack.  Please try again with only valid letters");
    // 	    endTurn=false;
    // 	}
    // }
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
	if (timesCanExchangeLeft>0){
	    tilebag.exchange(this,indexToExchange);
	    timesCanExchangeLeft-=1;
	    roundScore=0;
	    endTurn=true;
	}
	else{
	    System.out.println("You can only exchange three times.  You've already used up all of those.  Please try again");
	    endTurn=false;
	}
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
