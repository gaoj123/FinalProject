public class PremiumSquare extends Square{
    private String effect;//"double word", "triple letter", etc.
    private String color;//"red", "green", etc.

    public String getEffect(){
	return effect;
    }

    public String getColor(){
	return color;
    }

    public void setEffect(String Effect){
	effect = Effect;
    }

    public void setColor(String Color){
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
