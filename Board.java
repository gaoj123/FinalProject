public class Board{
    private Square[][] board;

    //methods for accessing and mutating board/Squares

    public Board(){
	board = new Square[15][15];
	int max = board.length - 1;
	int midpt = max / 2;
	int q1 = Math.floorDiv(midpt, 2);
	int q3 = max - q1;
	for(int row = 0; row < board.length; row++){
	    for(int col = 0; col < board[0].length; col++){
		if(row == 0 && col == 0 ||
		   row == 0 && col == midpt ||
		   row == 0 && col == max ||
		   row == midpt && col == 0 ||
		   row == midpt && col == max ||
		   row == max && col == 0 ||
		   row == max && col == midpt ||
		   row == max && col == max){
		    board[row][col] = new Square("triple word");
		}else if(row == midpt + 6 && col == midpt - 2 ||
			 row == midpt + 6 && col == midpt + 2 ||
			 row == midpt + 2 && col == midpt - 6 ||
			 row == midpt + 2 && col == midpt - 2 ||
			 row == midpt + 2 && col == midpt + 2 ||
			 row == midpt + 2 && col == midpt + 6 ||
			 row == midpt - 6 && col == midpt - 2 ||
			 row == midpt - 6 && col == midpt + 2 ||
			 row == midpt - 2 && col == midpt - 6 ||
			 row == midpt - 2 && col == midpt - 2 ||
			 row == midpt - 2 && col == midpt + 2 ||
			 row == midpt - 2 && col == midpt + 6){
		    board[row][col] = new Square("triple letter");
		}else if(row == 0 && (col == q1 || col == q3) ||
			 row == q1 - 1 && (col == midpt - 1 || col == midpt + 1) ||
			 row == q1 && col == midpt ||
			 row == max && (col == q1 || col == q3) ||
			 row == q3 + 1 && (col == midpt - 1 || col == midpt + 1) ||
			 row == q3 && col == midpt ||
			 col == 0 && (row == q1 || row == q3) ||
			 col == q1 - 1 && (row == midpt - 1 || row == midpt + 1) ||
			 col == q1 && row == midpt ||
			 col == max && (row == q1 || row == q3) ||
			 col == q3 + 1 && (row == midpt - 1 || row == midpt + 1) ||
			 col == q3 && row == midpt){
		    board[row][col] = new Square("double letter");
		}else if(row == col ||
			 row == max - col){
		    board[row][col] = new Square("double word");
		}else{
		    board[row][col] = new Square("regular");
		}
		//opt: find better more condensed placement function/formula
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
	int ycorMax = 14;
	String fullBoard = rowDivide;
	for(int row = 0; row < board.length; row++){
	    fullBoard += "\n";
	    if(ycorMax - row < 10){
		fullBoard += "0" + (ycorMax - row);
	    }else{
		fullBoard += (ycorMax - row);
	    }
	    fullBoard += "|";
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
