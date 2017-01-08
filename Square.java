public class Square{
    private Tile tile;
    private boolean isPremium;
    private String effect; //"normal", "double word", "triple letter", etc.
    //private int color; //refer to constants from Cmd.java

    //will probably move color solely to toString area

    public Tile getTile(){
	return tile;
    }

    public boolean getIsPremium(){
	return isPremium;
    }

    public String getEffect(){
	return effect;
    }

    //public int getColor(){
    //return color;
    //}

    public void setTile(Tile newTile){
	tile = newTile;
    }

    //public void setIsPremium(boolean premium){
    //isPremium = premium;
    //}

    public void setEffect(String Effect){
	effect = Effect;
	if(Effect.equals("normal")){
	    isPremium = false;
	}else{
	    isPremium = true;
	}
    }

    //public void setColor(int Color){
    //color = Color;
    //}

    public Square(String Effect){
        tile = new Tile();
	effect = Effect;
	if(Effect.equals("normal")){
	    isPremium = false;
	}else{
	    isPremium = true;
	}
    }

    public String toString(){
	return tile.getLetter() + " ";
    }


    
    public static void main(String[] args){
	Square s00 = new Square("normal");
	Square s10 = new Square("double word");
	System.out.println("'" + s00 + "'");
	System.out.println("'" + s10 + "'");
	System.out.println(s00.getTile());
	System.out.println(s00.getIsPremium());
	System.out.println(s10.getIsPremium());
	System.out.println(s00.getEffect());
	System.out.println(s10.getEffect());
	Tile tile0 = new Tile("?");
	Tile tile1 = new Tile("B");
	s00.setTile(tile0);
	s10.setTile(tile1);
	System.out.println("'" + s00 + "'");
	System.out.println("'" + s10 + "'");
	s10.setEffect("normal");
	System.out.println(s10.getIsPremium());
	System.out.println(s10.getEffect());
    }
}
