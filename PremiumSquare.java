public class PremiumSquare extends Square{
    private String effect; //"double word", "triple letter", etc.
    private int color; //refer to Cmd.java

    public String getEffect(){
	return effect;
    }

    public int getColor(){
	return color;
    }

    public void setEffect(String Effect){
	effect = Effect;
    }

    public void setColor(int Color){
	color = Color;
    }

    /*public PremiumSquare(String Effect){
	super();
	super.setIsPremium(true);
	effect = Effect;
	if(Effect.equals("double letter")){
	    
	}else if(){

	}
    }*/



    public static void main(String[] args){
	
    }
}
