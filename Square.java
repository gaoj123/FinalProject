public class Square{
    private Tile tile;
    private boolean isPremium;
    private String effect; //"regular", "double word", "triple letter", etc.

    public Tile getTile(){
	return tile;
    }

    public boolean getIsPremium(){
	return isPremium;
    }

    public String getEffect(){
	return effect;
    }

    public void setTile(Tile newTile){
	tile = newTile;
    }

    public void setEffect(String Effect){
	effect = Effect;
	if(Effect.equals("regular")){
	    isPremium = false;
	}else{
	    isPremium = true;
	}
    }

    public Square(String Effect){
        tile = new Tile();
	effect = Effect;
	if(Effect.equals("regular")){
	    isPremium = false;
	}else{
	    isPremium = true;
	}
    }

    public String toString(){
	int BGColor = Cmd.BLACK;
	if(effect.equals("double letter")){
	    BGColor = Cmd.BLUE;
	}else if(effect.equals("triple letter")){
	    BGColor = Cmd.GREEN;
	}else if (effect.equals("double word")){
	    BGColor = Cmd.RED;
	}else if (effect.equals("triple word")){
	    BGColor = Cmd.YELLOW;
	}
	return Cmd.bgColor(BGColor) + tile.getLetter() + " " + Cmd.bgColor(Cmd.BLACK);
    }


    
    public static void main(String[] args){
	Square s00 = new Square("regular");
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
	s10.setEffect("regular");
	System.out.println(s10.getIsPremium());
	System.out.println(s10.getEffect());
    }
}
