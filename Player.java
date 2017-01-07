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
    public void placeWord(String word,int x,int y,String direction){
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
