import java.util.*;
public class Player{
    String name;
    ArrayList<Tile> rack;
    int totalScore;
    int roundScore;
    public Player(String name){
	this.name=name;
	totalScore=0;
	roundScore=0;
	rack=new ArrayList<Tile>();
    }
    public void addToRack(Tile fromBag){
	rack.add(fromBag);
    }
    public void removeFromRack(Tile toRemove){
	rack.remove(toRemove);
    }
    public int getRackSize(){
	return rack.size();
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
