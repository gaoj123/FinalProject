public class Cmd{
    public static final int BLACK = 30;
    public static final int RED = 31;
    public static final int GREEN = 32;
    public static final int YELLOW = 33;
    public static final int BLUE = 34;
    public static final int MAGENTA = 35;
    public static final int CYAN = 36;
    public static final int WHITE = 37;
    public static final String CLEAR_SCREEN =  "\033[2J";
    public static final String HIDE_CURSOR =  "\033[?25l";
    public static final String SHOW_CURSOR =  "\033[?25h";
    public static String go(int x,int y){
        return ("\033[" + x + ";" + y + "H");
    }
    public static String bgColor(int bgColor){
        return ("\033[0;" + (bgColor + 10) + "m");
    }
    public static String color(int textColor, int bgColor){
        return ("\033[0;" + textColor + ";" + (bgColor + 10) + "m");
    }
    public static void wait(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
        }
    }

    public static void main(String[] args){
	
    }
}
