import java.util.*;
public class Player{
    String name;
    ArrayList<Tile> rack;
    int totalScore;
    int roundScore;
    boolean endTurn;
    boolean tryAgain=false;
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
    public void placeWord(Scrabble game, String word,int x,int y,String direction){
    	boolean haveTilesOrNotInRack=true;
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
			    System.out.println("point for letter: "+pointForTile);
			    totalPointValue+=pointForTile;
			    removeFromRack(ofInterest);
			    
    			}
			System.out.println("total points: "+totalPointValue);
    			//works, find pt value and lay out
    		    }
		    else{
			System.out.println("Position is on the board but cannot place word in position");
			//also check if a tile is already there after Square.java written
		    }
    		}
    		else if(validOrNot==false){
    		    System.out.println("Invalid word.  Next player's turn.");
    		}
		else{
		    System.out.println("Cannot place word at position.  Position is off the board");
		}
    		endTurn=true;
	}
	else{
	    boolean tryAgain=true;
	    boolean endTurn=false;
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
    public void requestExchange(){
	roundScore=0;
	endTurn=true;
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
