public class PremiumSquare extends Square{
    private String effect;//"double word", "triple letter", etc.
    private boolean status;
    private String color;//"red", "green", etc.

    public String getEffect(){
	return effect;
    }

    public boolean getStatus(){
	return status;
    }

    public String getColor(){
	return color;
    }

    public void setEffect(String Effect){
	effect = Effect;
    }

    public void setStatus(boolean Status){
	status = Status;
    }

    public void setColor(String Color){
	color = Color;
    }

    public PremiumSquare(String Effect){
	effect = Effect;
	status = true;
	if(Effect.equals("double letter")){
	    
	}else if(){

	}
    }



    public static void main(String[] args){
	
    }
}
