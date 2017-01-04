import java.util.*;
public class TileBag{
    private ArrayList<Tile> a;
    private static String[] alphabet=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public TileBag(){
	this.a=new ArrayList<Tile>();
	for(int A=0;A<9;A++){
	    Tile aa=new Tile("a");
	    a.add(aa);
	}
    }
    public drawTile(){
	if(a.size()==0){
	    System.out.println("Notification: There are no more tiles left in the bag");
	}
	else{
	}		
    }
    public static void main(String[] args){
    }
}
