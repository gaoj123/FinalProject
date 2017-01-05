public class BlankTile extends Tile{
    public BlankTile(){
	super("?");
    }
    public void differentiate(String x){
	setLetter(x);
    }
    public static void main(String[] args){
	BlankTile a=new BlankTile();
	System.out.println(a.getLetter());
	System.out.println(a.getPoints());
	a.differentiate("b");
	System.out.println(a.getLetter());
	System.out.println(a.getPoints());
    }
    //FILE IS USELESS.  EVERYTHING SHOULD WORK WITH JUST TILE.JAVA FILE.
}
