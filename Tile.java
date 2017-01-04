import java.util.*;
public class Tile{
    private String letter;
    private int points;
    private static String[] alphabet=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static ArrayList<String> alphabetLetters=new ArrayList<String>();
    public void InitializeArrList(){
    	for(int i=0;i<26;i++){
    	    alphabetLetters.add(alphabet[i]);
    	}
    }
    private static int[] pointValues=new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    public Tile(String a){
	this.InitializeArrList();
	letter=a;
	if(!(a.equals("?"))){
	    int alphaPos=alphabetLetters.indexOf(a);
	    points=pointValues[alphaPos];
	}
	else{
	    points=0;
	}
    }
    public String getLetter(){
	return letter;
    }
    public int getPoints(){
	return points;
    }
    public void setLetter(String x){
	letter=x;
    }
    public void setPoints(int y){
	points=y;
    }
    public static void main(String[] args){
	Tile c=new Tile("B");
	System.out.println(c.letter);
	//c.InitializeArrList();
	System.out.println(c.alphabetLetters);
	System.out.println(c.points);
    }
				       
}
