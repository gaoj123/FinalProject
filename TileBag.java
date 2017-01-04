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
}
