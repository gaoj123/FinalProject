public class Square{
    private String letter;

    public String getLetter(){
	return letter;
    }

    public void setLetter(String newLetter){
	letter = newLetter;
    }

    public Square(){
	letter = "";
    }

    public static void main(String[] args){
	Square s00 = new Square();
	System.out.println("'" + s00.getLetter() + "'");
	s00.setLetter("?");
	System.out.println("'" + s00.getLetter() + "'");
    }
}
