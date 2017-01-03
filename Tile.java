import java.util.*;
public class Tile{
    private String letter;
    private int points;
    private static String[] alphabet=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private static ArrayList<String> alphabetLetters=new ArrayList<String>();
   //  alphabetLetters.add("A");
   //  alphabetLetters.add("B");
   //  alphabetLetters.add("C");
   // alphabetLetters.add("D");
   //  alphabetLetters.add("E");
   //  alphabetLetters.add("F");
   //   alphabetLetters.add("G");
   //  alphabetLetters.add("H");
   //  alphabetLetters.add("I");
   //   alphabetLetters.add("J");
   //  alphabetLetters.add("K");
   //  alphabetLetters.add("L");
   //   alphabetLetters.add("M");
   //  alphabetLetters.add("N");
   //  alphabetLetters.add("O");
   //   alphabetLetters.add("P");
   //  alphabetLetters.add("Q");
   //  alphabetLetters.add("R");
   //   alphabetLetters.add("S");
   //  alphabetLetters.add("T");
   //  alphabetLetters.add("U");
   //   alphabetLetters.add("V");
   //  alphabetLetters.add("W");
   //  alphabetLetters.add("X");
   //   alphabetLetters.add("Y");
   //  alphabetLetters.add("Z");
    public void InitializeArrList(){
    	for(int i=0;i<26;i++){
    	    alphabetLetters.add(alphabet[i]);
    	}
    }
    private static int[] pointValues=new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    public Tile(String a){
	this.InitializeArrList();
	letter=a;
	int alphaPos=alphabetLetters.indexOf(a);
	points=pointValues[alphaPos];
    }
    public static void main(String[] args){
	Tile c=new Tile("B");
	System.out.println(c.letter);
	//c.InitializeArrList();
	System.out.println(c.alphabetLetters);
	System.out.println(c.points);
    }
				       
}
