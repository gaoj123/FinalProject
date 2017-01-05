import java.util.*;
public class Player{
    String name;
    ArrayList<Tile> rack;
    int totalScore;
    int roundScore;
    public Player(String name){
	this.name=name;

    }
    public void addToRack(Tile fromBag){
	rack.add(fromBag);
    }
    public String toString(){
	for(int i=0;i<rack.size();i++){
	    Tile displayTile=rack.get(i);
	    System.out.println(displayTile.getLetter());
    }
    public static void main(String[] args){
    }

}
