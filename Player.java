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
    public boolean getEndTurn(){
	return endTurn;
    }
    public void placeWord(Board board, Scrabble game, String word,int x,int y,String direction){
    	boolean haveTilesOrNotInRack=true;
	int col=x;
	int row=y;
	int arrayrow=14-row;
	int arraycol=col;
    	for(int i=0;i<word.length();i++){
    	    int in=0;
    	    for(int j=0;j<7;j++){
		Tile lookingAt=rack.get(j);
		String letterInWord=word.substring(i,i+1);
		if(letterInWord.toUpperCase().equals(lookingAt.getLetter())){
    		    in=1;
    		}
    		if(j==6&&in==0){
    		    haveTilesOrNotInRack=false;
    		}
    	    }
    	}
	System.out.println("in rack? "+haveTilesOrNotInRack);
    	if (haveTilesOrNotInRack){
    		boolean validOrNot=game.wordValidityCheck(word);
		System.out.println("valid word? "+validOrNot);
    		int wordLength=word.length();
    		if(validOrNot&&(x>=0&&x<=14)&&(y>=0&&y<=14)){
		    	System.out.println("x and y between 0 and 14, so on board");

			//additional check if square is not occupied (on all horizontal/vertical tiles that matter)
			//if (){
			//else{
			//System.out.println("Cannot place word on position because some squares on the board are already occupied");
    		    if((direction.equals("h")&&x+wordLength<=15)||(direction.equals("v")&&y-wordLength>=-1)){
			boolean onAnyOccupiedSquares=false;
			if(direction.equals("v")){
			    for(int rowCheck=0;rowCheck<word.length();rowCheck++){
				if(board.squareOccupied(arrayrow+rowCheck,arraycol)==true){
				    onAnyOccupiedSquares=true;
				}
			    }
			}
			else if(direction.equals("h")){
			    for(int colCheck=0;colCheck<word.length();colCheck++){
				if(board.squareOccupied(arrayrow,arraycol+colCheck)==true){
				    onAnyOccupiedSquares=true;
				}
			    }
			}
		        if(!onAnyOccupiedSquares){
    			int totalPointValue=0;
    			for(int p=0;p<wordLength;p++){
    			    int pointForTile=0;
    			    int tileIndexInRack=0;
			    for(int start=0;start<rack.size();start++){
				String letterLookingAt=word.substring(p,p+1).toUpperCase();
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
			    if(direction.equals("h")){
				board.setTileOfSquare(arrayrow,arraycol,ofInterest);
				arraycol+=1;
			    }
			    else{
				board.setTileOfSquare(arrayrow,arraycol,ofInterest);
				arrayrow+=1;
			    }
			    System.out.println("point for letter: "+pointForTile);
			    totalPointValue+=pointForTile;
			    removeFromRack(ofInterest);
			    
    			}
			System.out.println("total points: "+totalPointValue);
			endTurn=true;
			}
			else if(onAnyOccupiedSquares){
			    System.out.println("When laid out, word will be on an occupied square (square already has a tile).  Please try again and choose an appropriate x-cor and y-cor and direction");
			}
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
		else{
		    System.out.println("Position is off the board so cannot place word on board.  Please try again");
		    endTurn=false;
		}
	}
	else{
	    System.out.println("Word contains letters not in your tile rack.  Please try again with only valid letters");
	    endTurn=false;
	}
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
	if (timesCanExchangeLeft>0){
	    tilebag.exchange(this,indexToExchange);
	    timesCanExchangeLeft-=1;
	    roundScore=0;
	    endTurn=true;
	}
	else{
	    System.out.println("You can only exchange three times.  You've already used up all those.");
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
