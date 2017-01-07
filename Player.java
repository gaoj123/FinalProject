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
    // public void placeWord(Scrabble game, String word,int x,int y,String direction){
    // 	boolean haveTilesOrNotInRack=true;
    // 	for(int i=0;i<word.length();i++){
    // 	    int in=0;
    // 	    for(int j=0;j<7;j++){
    // 		if(word.substring(i,i+1).equals(rack.get(j))){
    // 		    in=1;
    // 		}
    // 		if(j==6&&in==0){
    // 		    haveTilesOrNotInRack=false;
    // 		}
    // 	    }
    // 	}
    // 	if (haveTilesOrNotInRack){
    // 		boolean validOrNot=game.wordValidityCheck(word);
    // 		int wordLength=word.length();
    // 		if(validOrNot&&(x>=0&&x<=14)&&(y>=0&&y<=14)){
    // 		    if((direction.equals("h")&&x+wordLength<=15)||(direction.equals("v")&&y-wordLength>=-1)){
    // 			int totalPointValue=0;
    // 			for(int p=0;p<wordLength;p++){
    // 			    int pointForTile=0;
    // 			    int tileIndexInRack=0;
    // 			    tileIndexInRack=rack.indexOf(word.substring(p,p+1));
    // 			    Tile ofInterest=rack.get(tileIndexInRack);
    // 			    pointForTile=ofInterest.getPoints();
    // 			    totalPointValue+=pointForTile;
    // 			}
    // 			//works, find pt value and lay out
    // 		    }
    // 		}
    // 		else{
    // 		    System.out.println("Invalid word.  Next player's turn.");
    // 		}
    // 		endTurn=true;
    // 	    }
    //}
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
