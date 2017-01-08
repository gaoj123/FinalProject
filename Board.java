public class Board{
    private Square[][] board;

    public Board(){
	board = new Square[15][15];
	for(int row = 0; row < board.length; row++){
	    for(int col = 0; col < board[0].length; col++){
		board[row][col] = new Square("regular");
		//must place premium squares
	    }
	}
    }

    public String toString(){
	String rowDivide = "  +";
	String xAxis = "\n  ";
	for(int col = 0; col < board[0].length; col++){
	    rowDivide += "--+";
	    if(col < 10){
		xAxis += " 0" + col;
	    }else{
		xAxis += " " + col;
	    }
	}
	String fullBoard = rowDivide;
	for(int row = 0; row < board.length; row++){
	    fullBoard += "\n" + "|";
	    //must place y-cor labels
	    for(int col = 0; col < board[0].length; col++){
		fullBoard += board[row][col] + "|";
	    }
	    fullBoard += "\n" + rowDivide;
	}
	fullBoard += xAxis;
	return fullBoard;
    }


    
    public static void main(String[] args){
	Board scrabble = new Board();
	System.out.println(scrabble);
	
    }
}
