public class Square{
    private Tile tile;
    private boolean isPremium;

    public Tile getTile(){
	return tile;
    }

    public boolean getIsPremium(){
	return isPremium;
    }

    public void setTile(Tile newTile){
	tile = newTile;
    }

    public void setIsPremium(boolean premium){
	isPremium = premium;
    }	

    public Square(){
        tile = new Tile();
	isPremium = false; 
    }

    public String toString(){
	return tile.getLetter() + " ";
    }

    public static void main(String[] args){
    }
}
