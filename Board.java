public class Board{
    private Square[][] board;

    public Square[][] getBoard(){
	return board;
    }

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
			 col == q3 && row == midpt ||
			 row == midpt - 1 && (col == midpt - 1 || col == midpt + 1) ||
			 row == midpt + 1 && (col == midpt - 1 || col == midpt + 1)){
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

    public Tile getTileOfSquare(int row, int col){
	return board[row][col].getTile();
    }

    public String getEffectOfSquare(int row, int col){
	return board[row][col].getEffect();
    }

    public void setTileOfSquare(int row, int col, Tile newTile){
	board[row][col].setTile(newTile);
    }

    public void setEffectOfSquare(int row, int col, String Effect){
	board[row][col].setEffect(Effect);
    }
    
    //opt: make function to abbreviate/directly access and modify Squares
    

    
    public static void main(String[] args){
	Board scrabble = new Board();
	System.out.println(scrabble.getBoard()[7][7].getTile());
	System.out.println(scrabble.getTileOfSquare(7, 7));
	Tile tile100 = new Tile("B");
	//scrabble.getBoard()[14][1].setTile(tile100);
	scrabble.setTileOfSquare(14, 4, tile100);
	Tile tile101 = new Tile("?");
	//scrabble.getBoard()[14][0].setTile(tile101);
	scrabble.setTileOfSquare(14, 0, tile101);
	//scrabble.getBoard()[14][0].setEffect("regular");
	scrabble.setEffectOfSquare(14, 0, "regular");
	System.out.println(scrabble);
    }
}
