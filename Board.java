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
	for(int col = 0; col < board[0].length; col++){
	    rowDivide += "--+";
	}
	String fullBoard = rowDivide;
	for(int row = 0; row < board.length; row++){
	    fullBoard += "\n" + "|";
	    //must place x-cor and y-cor labels
	    for(int col = 0; col < board[0].length; col++){
		fullBoard += board[row][col] + "|";
	    }
	    fullBoard += "\n" + rowDivide;
	}
	return fullBoard;
    }


    
    public static void main(String[] args){
	Board scrabble = new Board();
	System.out.println(scrabble);
	
    }
}
