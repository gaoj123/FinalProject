import java.util.*;
public class Player{
    String name;
    ArrayList<Tile> rack;
    int totalScore;
    int roundScore;
    boolean endTurn;
    public Player(String name){
	this.name=name;
	totalScore=0;
	roundScore=0;
	rack=new ArrayList<Tile>();
	endTurn=false;
    }
    public boolean getEndTurn(){
	return endTurn;
    }
    public String getName(){
	return name;
    }
    public int getTotalScore(){
	return totalScore;
    }
    public int getRoundScore(){
	return roundScore;
    }
    public int getPointsOfTileInRack(int index){
	return rack.get(index).getPoints();
    }
    public void setTotalScore(int toAdd){
	totalScore += toAdd;
    }
    public void setRoundScore(int newRoundScore){
	roundScore = newRoundScore;
    }
public boolean lettersInRack(String word1){
	boolean haveTilesOrNotInRack=true;
    	for(int i=0;i<word1.length();i++){
    	    int in=0;
    	    for(int j=0;j<7;j++){
		Tile lookingAt=rack.get(j);
		String letterInWord=word1.substring(i,i+1);
		if(letterInWord.toUpperCase().equals(lookingAt.getLetter())){
    		    in=1;
    		}
		if(j==6&&in==0){
    		    haveTilesOrNotInRack=false;
    		}
	    }
	}
	return haveTilesOrNotInRack;
    }
    public void setWordRackTiles(Board board1,String word1,int x1,int y1,String dir,int scoreFromIntersections){
	int totalPointValue=0;
	int wordLength=word1.length();
	int col=x1;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean doubleWord=false;
	boolean tripleWord=false;
	System.out.println("input intersection points: "+scoreFromIntersections);
	for(int p=0;p<wordLength;p++){
	    boolean toRemove=true;
	    int pointForTile=0;
	    int tileIndexInRack=0;
	    for(int start=0;start<rack.size();start++){
		String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		if(letterLookingAt.equals(rack.get(start).getLetter())){
		    tileIndexInRack=start;
		}
	    }
	    //Tile ofInterest=rack.get(tileIndexInRack);
	    Tile ofInterest=rack.get(tileIndexInRack);
	    System.out.println("index in rack "+tileIndexInRack);
	    System.out.println("in and on horiz "+horOnBoardAndInRack(board1,p,word1,x1,y1));
	    System.out.println("in and on vert "+vertOnBoardAndInRack(board1,p,word1,x1,y1));
	    if(dir.equals("h")&&horOnBoardAndInRack(board1,p,word1,x1,y1)){
		Tile ofConcern2=board1.getTileOfSquare(arrayrow,arraycol+p);
		pointForTile=ofConcern2.getPoints();
	    }
	    else if(dir.equals("v")&&vertOnBoardAndInRack(board1,p,word1,x1,y1)){
		Tile ofConcern2=board1.getTileOfSquare(arrayrow+p,arraycol);
		pointForTile=ofConcern2.getPoints();
	    }
	    else{
		if(ofInterest.getBlankOrNot()==true){
		    pointForTile=0;
		}
		else{
		    pointForTile=ofInterest.getPoints();
		}
	    }
	    //String effect="";
	    // if(dir.equals("h")){
	    // 	effect=board1.getEffectOfSquare(arrayrow,arraycol+p);
	    // }
	    // else{
	    // 	effect=board1.getEffectOfSquare(arrayrow+p,arraycol);
	    // }
	    String effect=board1.getEffectOfSquare(arrayrow,arraycol);
	    if(effect.equals("double letter")){
		pointForTile=pointForTile*2;
	    }
	    else if(effect.equals("triple letter")){
		pointForTile=pointForTile*3;
	    }
	    if(dir.equals("h")){
		int col2=x1;
		int row2=y1;
		int arrayrow2=14-row2;
		int arraycol2=col2;
		for(int colCheck2=0;colCheck2<word1.length();colCheck2++){
		    Tile ofConcern=board1.getTileOfSquare(arrayrow2,arraycol2+colCheck2);
		    String effect2=board1.getEffectOfSquare(arrayrow2,arraycol2+colCheck2);
		    if(effect2.equals("double word")){
			doubleWord=true;
		    }
		    if(effect2.equals("triple word")){
			tripleWord=true;
		    }
		}
		//board1.setTileOfSquare(arrayrow,arraycol,ofInterest);
		if(horOnBoardAndInRack(board1,p,word1,x1,y1)){
		    toRemove=false;
		}
		else{
		    board1.setTileOfSquare(arrayrow,arraycol,ofInterest);
		}
		if(doubleWord==true){
		    pointForTile=pointForTile*2;
		}
		if(tripleWord==true){
		    pointForTile=pointForTile*3;
		}
		board1.setEffectOfSquare(arrayrow,arraycol,"regular");				
		arraycol+=1;
	    }
	    else{
		int col2=x1;
		int row2=y1;
		int arrayrow2=14-row2;
		int arraycol2=col2;
		for(int rowCheck2=0;rowCheck2<word1.length();rowCheck2++){
		    Tile ofConcern=board1.getTileOfSquare(arrayrow2+rowCheck2,arraycol2);
		    String effect2=board1.getEffectOfSquare(arrayrow2+rowCheck2,arraycol2);
		    if(effect2.equals("double word")){
			doubleWord=true;
		    }
		    if(effect2.equals("triple word")){
			tripleWord=true;
		    }
		}
		if(vertOnBoardAndInRack(board1,p,word1,x1,y1)){
		    toRemove=false;
		}
		else{
		    board1.setTileOfSquare(arrayrow,arraycol,ofInterest);
		}
		if(doubleWord==true){
		    pointForTile=pointForTile*2;
		}
		if(tripleWord==true){
		    pointForTile=pointForTile*3;
		}
		board1.setEffectOfSquare(arrayrow,arraycol,"regular");
		arrayrow+=1;
	    }
	    // if(doubleWord==true){
	    // 	pointForTile=pointForTile*2;
	    // }
	    // if(tripleWord==true){
	    // 	pointForTile=pointForTile*3;
	    // }
	    System.out.println("point for letter: "+pointForTile+ " "+"row "+arrayrow);
	    totalPointValue+=pointForTile;
	    // if(dir.equals("h")){
	    // 	System.out.println("hor in and on "+horOnBoardAndInRack(board1,p,word1,x1,y1));
	    // 	if(!horOnBoardAndInRack(board1,p,word1,x1,y1)){
	    // 	    removeFromRack(ofInterest);
	    // 	}
	    // }
	    // else{
	    // 	System.out.println("vert in and on  "+vertOnBoardAndInRack(board1,p,word1,x1,y1));
	    // 	if(!vertOnBoardAndInRack(board1,p,word1,x1,y1)){
	    // 	    removeFromRack(ofInterest);
	    // 	}
	    // }
	    if(toRemove==true){
		removeFromRack(ofInterest);
	    }
	    //removeFromRack(ofInterest);
	    
			    
	}
	System.out.println("total points from letters: "+totalPointValue);
	System.out.println("points from intersections: "+scoreFromIntersections);
	setRoundScore(totalPointValue+scoreFromIntersections);
	setTotalScore(totalPointValue+scoreFromIntersections);
	endTurn=true;
    }
    public void setWordHor(Board board1,String word1,int x1,int y1, int scoreFromIntersections){
	//int pointForTile=0;
	int totalPointValue=0;
	int wordLength=word1.length();
	int col=x1;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean doubleWord=false;
	boolean tripleWord=false;
	System.out.println("score from intersections input: "+scoreFromIntersections);
	for(int colCheck2=0;colCheck2<word1.length();colCheck2++){
	    Tile ofConcern2=board1.getTileOfSquare(arrayrow,arraycol+colCheck2);
	    String effect2=board1.getEffectOfSquare(arrayrow,arraycol+colCheck2);
	    if(effect2.equals("double word")){
		doubleWord=true;
	    }
	    else if(effect2.equals("triple word")){
		tripleWord=true;
	    }
	}	    
	for(int colCheck=0;colCheck<word1.length();colCheck++){
	    Tile ofConcern=board1.getTileOfSquare(arrayrow,arraycol+colCheck);
	    int pointForTile=0;
	    if(word1.substring(colCheck,colCheck+1).toUpperCase().equals(ofConcern.getLetter())){
		pointForTile=ofConcern.getPoints();
		//arraycol+=1;
		// for(int p=0;p<word1.length();p++){
		//     int tileIndexInRack=0;
		//     for(int start=0;start<rack.size();start++){
		// 	String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		// 	if(letterLookingAt.equals(rack.get(start).getLetter())){
		// 	    tileIndexInRack=start;
		// 	    Tile ofInterest=rack.get(tileIndexInRack);
		// 	    removeFromRack(ofInterest);
		// 	}
		//     }
		// }
		// Tile ofInterest=rack.get(tileIndexInRack);
		// removeFromRack(ofInterest);
	    }
	    else if(ofConcern.getLetter().equals(" ")){
		// Tile createTile=new Tile(word1.substring(colCheck,colCheck+1));
		// //don't permit ? as letter
		// pointForTile=createTile.getPoints();
		// board1.setTileOfSquare(arrayrow,arraycol+colCheck,createTile);
		//arraycol+=1;
		String letterLookingAt=word1.substring(colCheck,colCheck+1).toUpperCase();
		int tileIndexInRack=0;
		for(int start=0;start<rack.size();start++){
		    if(letterLookingAt.equals(rack.get(start).getLetter())){
			tileIndexInRack=start;
			Tile ofInterest=rack.get(tileIndexInRack);
			pointForTile=ofInterest.getPoints();
			board1.setTileOfSquare(arrayrow,arraycol+colCheck,ofInterest);
			removeFromRack(ofInterest);
		    }
		}
		
		// for(int p=0;p<word1.length();p++){
		//     int tileIndexInRack=0;
		//     for(int start=0;start<rack.size();start++){
		// 	String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		// 	if(letterLookingAt.equals(rack.get(start).getLetter())){
		// 	    tileIndexInRack=start;
		// 	    Tile ofInterest=rack.get(tileIndexInRack);
		// 	    pointForTile=ofInterest.getPoints();
		// 	    board1.setTileOfSquare(arrayrow,arraycol+colCheck,ofInterest);
		// 	    removeFromRack(ofInterest);
		// 	}
		//     }
		// }
	    }
	    String effect=board1.getEffectOfSquare(arrayrow,arraycol+colCheck);
	    System.out.println(effect);
	    if(effect.equals("double letter")){
		pointForTile=pointForTile*2;
	    }
	    else if(effect.equals("triple letter")){
		pointForTile=pointForTile*3;
	    }
	    if(doubleWord==true){
		pointForTile=pointForTile*2;
	    }
	    if(tripleWord==true){
		pointForTile=pointForTile*3;
	    }
	    // for(int colCheck2=0;colCheck2<word1.length();colCheck2++){
	    // 	Tile ofConcern2=board1.getTileOfSquare(arrayrow,arraycol+colCheck2);
	    // 	String effect2=board1.getEffectOfSquare(arrayrow,arraycol+colCheck2);
	    // 	if(effect2.equals("double word")){
	    // 	    doubleWord=true;
	    // 	}
	    // 	else if(effect2.equals("triple word")){
	    // 	    tripleWord=true;
	    // 	}
	    // }
	    // if(doubleWord==true){
	    // 	pointForTile=pointForTile*2;
	    // }
	    // if(tripleWord==true){
	    // 	pointForTile=pointForTile*3;
	    // }
	    board1.setEffectOfSquare(arrayrow,arraycol+colCheck,"regular");	
	    System.out.println("point for letter: "+pointForTile+" col"+arraycol);
	    totalPointValue+=pointForTile;
	}
	System.out.println("total points: "+totalPointValue);
	System.out.println("points from intersections: "+scoreFromIntersections);
	setRoundScore(totalPointValue+scoreFromIntersections);
	setTotalScore(totalPointValue+scoreFromIntersections);
	// setRoundScore(totalPointValue);
	// setTotalScore(totalPointValue);
	endTurn=true;
    }
    public void setWordVert(Board board1, String word1,int x1, int y1, int scoreFromIntersections){
	int col=x1;
	int totalPointValue=0;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean ret=true;
	boolean doubleWord=false;
	boolean tripleWord=false;
	System.out.println("score from intersections input: "+scoreFromIntersections);
	for(int rowCheck2=0;rowCheck2<word1.length();rowCheck2++){
	    Tile ofConcern2=board1.getTileOfSquare(arrayrow+rowCheck2,arraycol);
	    String effect2=board1.getEffectOfSquare(arrayrow+rowCheck2,arraycol);
	    if(effect2.equals("double word")){
		doubleWord=true;
	    }
	    else if(effect2.equals("triple word")){
		tripleWord=true;
	    }
	}
	for(int rowCheck=0;rowCheck<word1.length();rowCheck++){
	    Tile ofConcern=board1.getTileOfSquare(arrayrow+rowCheck,arraycol);
	    int pointForTile=0;
	    if(word1.substring(rowCheck,rowCheck+1).toUpperCase().equals(ofConcern.getLetter())){
		System.out.println("using board's tile");
		//int pointForTile=0;
		pointForTile=ofConcern.getPoints();
		//arrayrow+=1;
		// for(int p=0;p<word1.length();p++){
		//     int tileIndexInRack=0;
		//     for(int start=0;start<rack.size();start++){
		// 	String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		// 	if(letterLookingAt.equals(rack.get(start).getLetter())){
		// 	    tileIndexInRack=start;
		// 	    Tile ofInterest=rack.get(tileIndexInRack);
		// 	    removeFromRack(ofInterest);
		// 	}
		//     }
		// }
		// Tile ofInterest=rack.get(tileIndexInRack);
		// removeFromRack(ofInterest);
	    }
	    else if(ofConcern.getLetter().equals(" ")){
		//Tile createTile=new Tile(word1.substring(rowCheck,rowCheck+1));
		System.out.println("using own tile");
		//don't permit ? as letter
		
		//pointForTile=createTile.getPoints();
		
		//board1.setTileOfSquare(arrayrow,arraycol,createTile);
		//	arrayrow+=1;
		
		//board1.setTileOfSquare(arrayrow+rowCheck,arraycol,createTile);
		String letterLookingAt=word1.substring(rowCheck,rowCheck+1).toUpperCase();
		int tileIndexInRack=0;
		for(int start=0;start<rack.size();start++){
		    if(letterLookingAt.equals(rack.get(start).getLetter())){
			tileIndexInRack=start;
			Tile ofInterest=rack.get(tileIndexInRack);
			pointForTile=ofInterest.getPoints();
			board1.setTileOfSquare(arrayrow+rowCheck,arraycol,ofInterest);
			removeFromRack(ofInterest);
		    }
		}
		// for(int p=0;p<word1.length();p++){
		//     int tileIndexInRack=0;
		//     for(int start=0;start<rack.size();start++){
		// 	String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		// 	if(letterLookingAt.equals(rack.get(start).getLetter())){
		// 	    tileIndexInRack=start;
		// 	    Tile ofInterest=rack.get(tileIndexInRack);
		// 	    pointForTile=ofInterest.getPoints();
		// 	    board1.setTileOfSquare(arrayrow+rowCheck,arraycol,ofInterest);
		// 	    removeFromRack(ofInterest);
		// 	}
		//     }
		// }
	    }
	    else{
		System.out.println("none"+ofConcern.getLetter());
	    }
	    String effect=board1.getEffectOfSquare(arrayrow+rowCheck,arraycol);
	    System.out.println(effect);
	    if(effect.equals("double letter")){
		pointForTile=pointForTile*2;
	    }
	    else if(effect.equals("triple letter")){
		pointForTile=pointForTile*3;
	    }
	    if(doubleWord==true){
		pointForTile=pointForTile*2;
	    }
	    if(tripleWord==true){
		pointForTile=pointForTile*3;
	    }
	    board1.setEffectOfSquare(arrayrow+rowCheck,arraycol,"regular");
	    System.out.println("point for letter: "+pointForTile);
	    totalPointValue+=pointForTile;
	}
	// for(int rowCheck2=0;rowCheck2<word1.length();rowCheck2++){
	//     Tile ofConcern2=board1.getTileOfSquare(arrayrow+rowCheck2,arraycol);
	//     String effect2=board1.getEffectOfSquare(arrayrow+rowCheck2,arraycol);
	//     if(effect2.equals("double word")){
	// 	doubleWord=true;
	//     }
	//     else if(effect2.equals("triple word")){
	// 	tripleWord=true;
	//     }
	// }
	// if(doubleWord==true){
	//     pointForTile=pointForTile*2;
	// }
	// if(tripleWord==true){
	//     pointForTile=pointForTile*3;
	// }
	// board1.setEffectOfSquare(arrayrow+rowCheck,arraycol,"regular");
	System.out.println("total points: "+totalPointValue);
	System.out.println("points from intersections: "+scoreFromIntersections);
	setRoundScore(totalPointValue+scoreFromIntersections);
	setTotalScore(totalPointValue+scoreFromIntersections);
	// setRoundScore(totalPointValue);
	// setTotalScore(totalPointValue);
	endTurn=true;
    }

    public boolean checkHor(Board board1,String word1,int x1,int y1){
	int col=x1;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean ret=true;
	for(int colCheck=0;colCheck<word1.length();colCheck++){
	    Tile ofConcern=board1.getTileOfSquare(arrayrow,arraycol+colCheck);
	    if(!(ofConcern.getLetter().equals(" "))&&(!word1.substring(colCheck,colCheck+1).toUpperCase().equals(ofConcern.getLetter()))){
		ret=false;
	    }
	}
	return ret;
    }
    public boolean horOnBoardAndInRack(Board board2,int index,String word2,int x2,int y2){
    	int col=x2;
    	int row=y2;
    	int arrayrow=14-row;
    	int arraycol=col;
    	boolean onBoard=false;
	Tile ofConcern=board2.getTileOfSquare(arrayrow,arraycol+index);
	if(word2.substring(index,index+1).toUpperCase().equals(ofConcern.getLetter())){
	    onBoard=true;
	}
    	return onBoard;
    }
    public boolean vertOnBoardAndInRack(Board board2,int index,String word2,int x2,int y2){
	int col=x2;
	int row=y2;
	int arrayrow=14-row;
	int arraycol=col;
	boolean onBoard=false;
	Tile ofConcern=board2.getTileOfSquare(arrayrow+index,arraycol);
	if(word2.substring(index,index+1).toUpperCase().equals(ofConcern.getLetter())){
	    onBoard=true;
				   
	}
	return onBoard;
    }
    public boolean checkVert(Board board1,String word1,int x1,int y1){
	int col=x1;
	int row=y1;
	int arrayrow=14-row;
	int arraycol=col;
	boolean ret=true;
	for(int rowCheck=0;rowCheck<word1.length();rowCheck++){
	    Tile ofConcern=board1.getTileOfSquare(arrayrow+rowCheck,arraycol);
	    if(!(ofConcern.getLetter().equals(" "))&&(!word1.substring(rowCheck,rowCheck+1).toUpperCase().equals(ofConcern.getLetter()))){
		ret=false;
				   
	    }
	}
	return ret;
    }
    public int topBoundHor(Board board2,int rowLetter,int colLetter){
	int top=rowLetter;
	while(board2.squareOccupied(top-1,colLetter)){
	    top-=1;
	}
	return top;   
    }
    public int bottomBoundHor(Board board2,int rowLetter,int colLetter){
	int bottom=rowLetter;
	while(board2.squareOccupied(bottom+1,colLetter)){
	    bottom+=1;
	}
	return bottom;
    }
    public int leftBoundVert(Board board2,int rowLetter,int colLetter){
	int left=colLetter;
	while(board2.squareOccupied(rowLetter,left-1)){
	    left-=1;
	}
	return left;
    }
    public int rightBoundVert(Board board2,int rowLetter,int colLetter){
	int right=colLetter;
	while(board2.squareOccupied(rowLetter,right+1)){
	    right+=1;
	}
	return right;
    }
    public String wordExaminingVert(String word1,int indexLetter,int rowLetter,int colLetter,Board board2,int left,int right){
	int leftBound=left;
	int rightBound=right;
	String wordRet="";
	String letterToAdd="";
	System.out.println("square "+rowLetter+" "+colLetter);
	for(int col=leftBound;col<rightBound+1;col++){
	    if(board2.squareOccupied(rowLetter,col)){
		System.out.println("col "+col);
		Tile square=board2.getTileOfSquare(rowLetter,col);
		letterToAdd=square.getLetter();
	    }
	    else{
		for(int start=0;start<rack.size();start++){
		    String letterLookingAt=word1.substring(indexLetter,indexLetter+1).toUpperCase();
		    if(letterLookingAt.equals(rack.get(start).getLetter())){
			int tileIndexInRack=start;
			Tile ofInterest=rack.get(tileIndexInRack);
		        letterToAdd=ofInterest.getLetter();
		    }
		}
	    }
	    wordRet+=letterToAdd;
	}
	return wordRet;
    }
    public String wordExaminingHor(String word1,int indexLetter,int rowLetter,int colLetter,Board board2,int top,int bottom){
	int topBound=top;
	int bottomBound=bottom;
	String letterToAdd="";
	String wordRet="";
	System.out.println("square "+rowLetter+" "+colLetter);
	for(int row=topBound;row<bottomBound+1;row++){
	    if(board2.squareOccupied(row,colLetter)){
		System.out.println("row "+row);
		Tile square=board2.getTileOfSquare(row,colLetter);
		letterToAdd=square.getLetter();
	    }
	    else{
		for(int start=0;start<rack.size();start++){
		    String letterLookingAt=word1.substring(indexLetter,indexLetter+1).toUpperCase();
		    if(letterLookingAt.equals(rack.get(start).getLetter())){
			int tileIndexInRack=start;
			Tile ofInterest=rack.get(tileIndexInRack);
		        letterToAdd=ofInterest.getLetter();
		    }
		}
	    }
	    wordRet+=letterToAdd;
	}
	return wordRet;
    }
    public int scoreOfWordHor(Board board2,String word1,int rowLetter,int colLetter){
	int score=0;
	boolean doubleWord=false;
	boolean tripleWord=false;
	for(int q=0;q<word1.length();q++){
	    Tile square2=board2.getTileOfSquare(rowLetter,colLetter+q);
	    String effect2=board2.getEffectOfSquare(rowLetter,colLetter+q);
	    if(effect2.equals("double word")){
		doubleWord=true;
	    }
	    if(effect2.equals("triple word")){
		tripleWord=true;
	    }
	}
	for(int p=0;p<word1.length();p++){
	    int tileIndexInRack=0;
	    int tilePointValue=0;
	    if(board2.squareOccupied(rowLetter,colLetter+p)){
		Tile square=board2.getTileOfSquare(rowLetter,colLetter+p);
		tilePointValue=square.getPoints();
	    }
	    else{
		for(int start=0;start<rack.size();start++){
		    String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		    if(letterLookingAt.equals(rack.get(start).getLetter())){
			tileIndexInRack=start;
			Tile ofInterest=rack.get(tileIndexInRack);
			tilePointValue=ofInterest.getPoints();
			System.out.println("rack value "+tilePointValue);
			String effect=board2.getEffectOfSquare(rowLetter,colLetter+p);
			if(effect.equals("double letter")){
			    tilePointValue=tilePointValue*2;
			}
			else if(effect.equals("triple letter")){
			    tilePointValue=tilePointValue*3;
			}
		    }
		}
		// boolean doubleWord=false;
		// boolean tripleWord=false;
		// for(int q=0;q<word1.length();q++){
		//     Tile square2=board2.getTileOfSquare(rowLetter,colLetter+p);
		//     String effect2=board2.getEffectOfSquare(rowLetter,colLetter+p);
		//     if(effect2.equals("double word")){
		// 	doubleWord=true;
		//     }
		//     if(effect2.equals("triple word")){
		// 	tripleWord=true;
		//     }
		// }
		
		//double word/triple word
		if(doubleWord){
		    tilePointValue=tilePointValue*2;
		}
		if(tripleWord){
		    tilePointValue=tilePointValue*3;
		}
	    }
	    System.out.println("tile value "+tilePointValue);
	    score+=tilePointValue;
	}
	return score;
    }
    public int scoreOfWordVert(Board board2,String word1,int rowLetter,int colLetter){
	int score=0;
	boolean doubleWord=false;
	boolean tripleWord=false;
	for(int q=0;q<word1.length();q++){
	    Tile square2=board2.getTileOfSquare(rowLetter+q,colLetter);
	    String effect2=board2.getEffectOfSquare(rowLetter+q,colLetter);
	    if(effect2.equals("double word")){
		doubleWord=true;
	    }
	    if(effect2.equals("triple word")){
		tripleWord=true;
	    }
	}
	for(int p=0;p<word1.length();p++){
	    int tileIndexInRack=0;
	    int tilePointValue=0;
	    if(board2.squareOccupied(rowLetter+p,colLetter)){
		Tile square=board2.getTileOfSquare(rowLetter+p,colLetter);
		tilePointValue=square.getPoints();
	    }
	    else{
		for(int start=0;start<rack.size();start++){
		    String letterLookingAt=word1.substring(p,p+1).toUpperCase();
		    if(letterLookingAt.equals(rack.get(start).getLetter())){
			tileIndexInRack=start;
			Tile ofInterest=rack.get(tileIndexInRack);
			tilePointValue=ofInterest.getPoints();
			System.out.println("rack point value: "+tilePointValue);
			String effect=board2.getEffectOfSquare(rowLetter+p,colLetter);
			if(effect.equals("double letter")){
			    tilePointValue=tilePointValue*2;
			}
			else if(effect.equals("triple letter")){
			    tilePointValue=tilePointValue*3;
			}
		    }
		}
		// boolean doubleWord=false;
		// boolean tripleWord=false;
		// for(int q=0;q<word1.length();q++){
		//     Tile square2=board2.getTileOfSquare(rowLetter+p,colLetter);
		//     String effect2=board2.getEffectOfSquare(rowLetter+p,colLetter);
		//     if(effect2.equals("double word")){
		// 	doubleWord=true;
		//     }
		//     if(effect2.equals("triple word")){
		// 	tripleWord=true;
		//     }
		// }
		
		//double word/triple word
		if(doubleWord){
		    tilePointValue=tilePointValue*2;
		}
		if(tripleWord){
		    tilePointValue=tilePointValue*3;
		}
	    }
	    System.out.println("tile value "+tilePointValue);
	    score+=tilePointValue;
	}
	return score;
    }
    public String placeWord(Board board, Scrabble game, String word,int x,int y,String direction, boolean emptyBoard){
    	boolean haveTilesOrNotInRack=true;
	boolean empty=emptyBoard;
	int col=x;
	int row=y;
	int arrayrow=14-row;
	int arraycol=col;
	boolean okayToLay=false;
	int wordLength=word.length();
	boolean intersectAtLeastOnce=false;
	int intersectionPoints=0;
	if(!(x>=0&&x<=14)||!(y>=0&&y<=14)){
	    endTurn=false;
	    return ("Position is off the board.  Please try again");
	    //endTurn=false;
	}
	else{
	    if((direction.equals("h")&&!(x+wordLength<=15))||((direction.equals("v")&&!(y-wordLength>=-1)))){
		endTurn=false;
		return ("Position is on the board but can't place word.  Please try again");
		//endTurn=false;
	    }
	    else{
		if(direction.equals("h")){
		    int col2=x;
		    int row2=y;
		    int arrayrow2=14-row2;
		    int arraycol2=col2;
		    int numIntersections=0;
		    for(int colCheck2=0;colCheck2<word.length();colCheck2++){
			Tile ofConcern2=board.getTileOfSquare(arrayrow2,arraycol2+colCheck2);
			System.out.println("occupied? "+board.squareOccupied(arrayrow2,arraycol2+colCheck2)+" same letter? "+word.substring(colCheck2,colCheck2+1).toUpperCase().equals(ofConcern2.getLetter()));
			// if (board.squareOccupied(arrayrow2,arraycol2+colCheck2)&&word.substring(colCheck2,colCheck2+1).toUpperCase().equals(ofConcern2.getLetter())){
			if (board.squareOccupied(arrayrow2,arraycol2+colCheck2)){
			    numIntersections+=1;
			}
		    }
		    if(numIntersections>=1){
			intersectAtLeastOnce=true;
		    }
		}
		else{
		    int col2=x;
		    int row2=y;
		    int arrayrow2=14-row2;
		    int arraycol2=col2;
		    int numIntersections=0;
		    for(int rowCheck2=0;rowCheck2<word.length();rowCheck2++){
			Tile ofConcern2=board.getTileOfSquare(arrayrow2+rowCheck2,arraycol2);
			System.out.println("occupied? "+board.squareOccupied(arrayrow2+rowCheck2,arraycol2)+" same letter? "+word.substring(rowCheck2,rowCheck2+1).toUpperCase().equals(ofConcern2.getLetter()));
			// if(board.squareOccupied(arrayrow2+rowCheck2,arraycol2)&&word.substring(rowCheck2,rowCheck2+1).toUpperCase().equals(ofConcern2.getLetter())){
			if(board.squareOccupied(arrayrow2+rowCheck2,arraycol2)){
			    numIntersections+=1;				   
			}
		    }
		    if(numIntersections>=1){
			intersectAtLeastOnce=true;
		    }
		}
		boolean parallel=false;
		for(int indexWord=0;indexWord<word.length();indexWord++){
		    if(direction.equals("h")){
			int col3=x;
			int row3=y;
			int arrayrow3=14-row3;
			int arraycol3=col3;
			int rowSquare=arrayrow3;
			int colSquare=arraycol3+indexWord;
			if(board.squareOccupied(rowSquare-1,colSquare)||board.squareOccupied(rowSquare,colSquare+1)||board.squareOccupied(rowSquare+1,colSquare)||board.squareOccupied(rowSquare,colSquare-1)){
			    parallel=true;
			}
		    }
		    else{
			int col3=x;
			int row3=y;
			int arrayrow3=14-row3;
			int arraycol3=col3;
			int rowSquare=arrayrow3+indexWord;
			int colSquare=arraycol3;
			if(board.squareOccupied(rowSquare-1,colSquare)||board.squareOccupied(rowSquare,colSquare+1)||board.squareOccupied(rowSquare+1,colSquare)||board.squareOccupied(rowSquare,colSquare-1)){
			    parallel=true;
			}
		    }
		}
		if(parallel==true){
		    if(direction.equals("h")){
			int col4=x;
			int row4=y;
			int arrayrow4=14-row4;
			int arraycol4=col4;
			int rowSquare=arrayrow4;
			int colSquare=0;
			System.out.println("word length "+word.length());
			for(int wordIndex=0;wordIndex<word.length();wordIndex++){
			    //colSquare=arraycol4+wordIndex;
			    //System.out.println("arraycol "+arraycol4);
			    System.out.println("colSquare "+(arraycol4+wordIndex));
			    System.out.println("wordIndex "+wordIndex);
			    System.out.println("word "+wordExaminingHor(word,wordIndex,rowSquare,arraycol4+wordIndex,board,topBoundHor(board,rowSquare,arraycol4+wordIndex),bottomBoundHor(board,rowSquare,arraycol4+wordIndex)));
			    System.out.println("top "+topBoundHor(board,rowSquare,arraycol4+wordIndex));
			    System.out.println("bottom "+bottomBoundHor(board,rowSquare,arraycol4+wordIndex));
			    if(wordIndex==0){
				String horWordAcross=wordExaminingVert(word,wordIndex,rowSquare,arraycol4,board,leftBoundVert(board,rowSquare,arraycol4),rightBoundVert(board,rowSquare,arraycol4));
				int addStart=1;
				int j=1;
				while(j<word.length()){
				    if(board.squareOccupied(rowSquare,arraycol4+j)){
					addStart+=1;
					j+=1;
				    }
				    else{
					j=word.length()+1;
				    }
				}
				System.out.println("addstart "+addStart);
				// for(int j=0;j<word.length();j++){
				//     if(board.squareOccupied(rowSquare,arraycol4+j)){
				// 	addStart+=1;
				//     }
				// }
				for(int i=addStart;i<word.length();i++){
				    horWordAcross=horWordAcross+word.substring(i,i+1);
				}
				if(horWordAcross.length()>1&&(!game.wordValidityCheck(horWordAcross))){
				    endTurn=false;
				    System.out.println(horWordAcross);
				    return ("Invalid words are formed as a result of this word having tiles adjacent to existing word on board.  Please try again");
				}
			    }								   
			    String wordLook=wordExaminingHor(word,wordIndex,rowSquare,arraycol4+wordIndex,board,topBoundHor(board,rowSquare,arraycol4+wordIndex),bottomBoundHor(board,rowSquare,arraycol4+wordIndex));
			    if(wordLook.length()>1&&(!game.wordValidityCheck(wordExaminingHor(word,wordIndex,rowSquare,arraycol4+wordIndex,board,topBoundHor(board,rowSquare,arraycol4+wordIndex),bottomBoundHor(board,rowSquare,arraycol4+wordIndex))))){
				endTurn=false;
				return("Invalid words are formed as a result of this word being parallel to existing word on board.  Please try again");
			    }
			    else if(wordLook.length()>1&&game.wordValidityCheck(wordExaminingHor(word,wordIndex,rowSquare,arraycol4+wordIndex,board,topBoundHor(board,rowSquare,arraycol4+wordIndex),bottomBoundHor(board,rowSquare,arraycol4+wordIndex)))){
				System.out.println("word "+wordLook+" intersectionPoints "+scoreOfWordHor(board,wordLook,rowSquare,arraycol4+wordIndex));
				//intersectionPoints+=scoreOfWordHor(board,wordLook,rowSquare,arraycol4+wordIndex);
				// intersectionPoints+=scoreOfWordHor(board,wordLook,topBoundHor(board,rowSquare,arraycol4+wordIndex),bottomBoundHor(board,rowSquare,arraycol4+wordIndex));
				intersectionPoints+=scoreOfWordVert(board,wordLook,topBoundHor(board,rowSquare,arraycol4+wordIndex),arraycol4+wordIndex);
			    }
			    // if(game.wordValidityCheck(wordExaminingHor(word,wordIndex,rowSquare,colSquare,board,topBoundHor(board,rowSquare,colSquare),bottomBoundHor(board,rowSquare,colSquare)))&&word.length()>1){
			    // 	intersectionPoints+=scoreOfWordHor(board,word,rowSquare,colSquare);
			    // }
			    // else if(word.length()>1&&(!game.wordValidityCheck(wordExaminingHor(word,wordIndex,rowSquare,colSquare,board,topBoundHor(board,rowSquare,colSquare),bottomBoundHor(board,rowSquare,colSquare))))){
			    // 	endTurn=false;
			    // 	return("Invalid words are formed as a result of this word being parallel to existing word on board.  Please try again");
			    // }
			}
		    }
		    else{
			int col4=x;
			int row4=y;
			int arrayrow4=14-row4;
			int arraycol4=col4;
			int rowSquare=0;
			int colSquare=arraycol4;
			System.out.println("word length "+word.length());
			for(int wordIndex=0;wordIndex<word.length();wordIndex++){
			    // int col4=x;
			    // int row4=y;
			    // int arrayrow4=14-row4;
			    // int arraycol4=col4;
			    //Note: rowSquare=arrayrow4+wordIndex;
			    System.out.println("rowSquare "+(arrayrow4+wordIndex));
			    System.out.println("wordIndex "+wordIndex);
			    //int colSquare=arraycol4;
			    System.out.println("word "+wordExaminingVert(word,wordIndex,arrayrow4+wordIndex,colSquare,board,leftBoundVert(board,arrayrow4+wordIndex,colSquare),rightBoundVert(board,arrayrow4+wordIndex,colSquare)));
			    System.out.println("left "+leftBoundVert(board,arrayrow4+wordIndex,colSquare));
			    System.out.println("right "+rightBoundVert(board,arrayrow4+wordIndex,colSquare));
			    if(wordIndex==0){
				String vertWordDown=wordExaminingHor(word,wordIndex,arrayrow4,colSquare,board,topBoundHor(board,arrayrow4,colSquare),bottomBoundHor(board,arrayrow4,colSquare));
				int addStart=1;
				int j=1;
				while (j<word.length()){
				    if(board.squareOccupied(arrayrow4+j,colSquare)){
					addStart+=1;
					j+=1;
				    }
				    else{
					j=word.length()+1;
				    }
				}
				System.out.println("addstart "+addStart);
				// for(int j=0;j<word.length();j++){
				//     if(board.squareOccupied(arrayrow4+j,colSquare)){
				// 	addStart+=1;
				//     }
				// }
				// for(int j=0;j<vertWordDown.length();j++){
				//     if(word.substring(0,1).equals(vertWordDown.substring(j,j+1))){
				// 	addStart=j;
				//     }
				// }
				for(int i=addStart;i<word.length();i++){
				    vertWordDown=vertWordDown+word.substring(i,i+1);
				}
				if(vertWordDown.length()>1&&(!game.wordValidityCheck(vertWordDown))){
				    System.out.println(vertWordDown);
				    endTurn=false;
				    return ("Invalid words are formed as a result of this word having tiles adjacent to existing word on board.  Please try again");
				}
			    }		
			    String wordLook=wordExaminingVert(word,wordIndex,arrayrow4+wordIndex,colSquare,board,leftBoundVert(board,arrayrow4+wordIndex,colSquare),rightBoundVert(board,arrayrow4+wordIndex,colSquare));
			    if(wordLook.length()>1&&(!game.wordValidityCheck(wordExaminingVert(word,wordIndex,arrayrow4+wordIndex,colSquare,board,leftBoundVert(board,arrayrow4+wordIndex,colSquare),rightBoundVert(board,arrayrow4+wordIndex,colSquare))))){
				endTurn=false;
				return("Invalid words are formed as a result of this word being parallel to existing word on board.  Please try again");
			    }
			    else if(wordLook.length()>1&&game.wordValidityCheck(wordLook)){
				System.out.println("word "+wordLook+" intersectionPoints "+scoreOfWordHor(board,wordLook,arrayrow4+wordIndex,colSquare));
				//intersectionPoints+=scoreOfWordVert(board,wordLook,arrayrow4+wordIndex,colSquare);
				// intersectionPoints+=scoreOfWordVert(board,wordLook,leftBoundVert(board,arrayrow4+wordIndex,colSquare),rightBoundVert(board,arrayrow4+wordIndex,colSquare));
				intersectionPoints+=scoreOfWordHor(board,wordLook,arrayrow4+wordIndex,leftBoundVert(board,arrayrow4+wordIndex,colSquare));
			    }
			    // if(game.wordValidityCheck(wordExaminingVert(word,wordIndex,rowSquare,colSquare,board,leftBoundVert(board,rowSquare,colSquare),rightBoundVert(board,rowSquare,colSquare)))&&word.length()>1){
			    // 	intersectionPoints+=scoreOfWordVert(board,word,rowSquare,colSquare);
			    // }
			    // else{
			    // 	endTurn=false;
			    // 	return("Invalid words are formed as a result of this word being parallel to existing word on board.  Please try again");
			    //}
			}
		    }
		}
		if(intersectAtLeastOnce==false&&empty==false&&parallel==false){
		    System.out.println(intersectAtLeastOnce+ " "+empty);
		    endTurn=false;
		    return ("Word must use one of the letters already on the board.   Please try again");
		    //endTurn=false;
		}
		else{
		    boolean startingCenter=false;
		    if(empty){
			if(direction.equals("h")){
			    int col2=x;
			    int row2=y;
			    int arrayrow2=14-row2;
			    int arraycol2=col2;
			    int numIntersections=0;
			    for(int colCheck2=0;colCheck2<word.length();colCheck2++){
				if (arraycol2+colCheck2==7&&arrayrow==7){
				    startingCenter=true;
				}
			    }
			}
			else{
			    int col2=x;
			    int row2=y;
			    int arrayrow2=14-row2;
			    int arraycol2=col2;
			    int numIntersections=0;
			    for(int rowCheck2=0;rowCheck2<word.length();rowCheck2++){		
				if(arrayrow2+rowCheck2==7&&arraycol==7){
				    startingCenter=true;				   
				}
			    }
			}
		    }
		    System.out.println("starting center? "+startingCenter);
		    if(empty&&!startingCenter){
			endTurn=false;
			return ("First word must have a letter on the center square.  Please try again");
			//endTurn=false;
		    }
		    else{
			if(!(x>=0&&x<=14)||!(y>=0&&y<=14)){
			    endTurn=false;
			    return ("Position is off the board.  Please try again");
			    //endTurn=false;
			}
			else{
			    if((direction.equals("h")&&!(x+wordLength<=15))||((direction.equals("v")&&!(y-wordLength>=-1)))){
				endTurn=false;
				return ("Position is on the board but can't place word.  Please try again");
				//endTurn=false;
			    }
			    else{
				haveTilesOrNotInRack=lettersInRack(word);
				System.out.println("in rack? "+haveTilesOrNotInRack);
				if(!lettersInRack(word)){
				    if(direction.equals("v")){
					if(checkVert(board,word,x,y)){
					    okayToLay=true;
					}
					else{
					    endTurn=false;
					    return ("Cannot lay such a word at such a position.  Some tiles that are not in rack are also not on the board at appropriate positions (when word is laid out).   Please try again.");
					    //endTurn=false;
					}
				    }
				    else if(direction.equals("h")){
					if(checkHor(board,word,x,y)){
					    okayToLay=true;
					}
					else{
					    endTurn=false;
					    return ("Cannot lay such a word at such a position.  Some tiles that are not in rack are also not on the board at appropriate positions (when word is laid out).   Please try again.");
					    //endTurn=false;
					}
				    }
				}//also condition when square is occupied and tile is not in rack.  but maybe already checked in checkHor
				else{
				    if(direction.equals("v")){
					if(checkVert(board,word,x,y)){
					    okayToLay=true;
					}
					else{
					    endTurn=false;
					    return ("Tiles are in rack but some squares on board are already occupied (when word is laid out).  Please try again.");
					    //endTurn=false;
					}
				    }
				    else if(direction.equals("h")){
					if(checkHor(board,word,x,y)){
					    okayToLay=true;
					}
					else{
					    endTurn=false;
					    return ("Tiles are in rack but some squares on board are already occupied (when word is laid out).  Please try again.");
					    //endTurn=false;
					}
				    }
				    //okayToLay=true;
				}
				if(okayToLay){
				    boolean validOrNot=game.wordValidityCheck(word);
				    System.out.println("valid word? "+word+" "+validOrNot);
				    //int wordLength=word.length();
				    if(validOrNot&&(x>=0&&x<=14)&&(y>=0&&y<=14)){
					System.out.println("x and y between 0 and 14, so on board");
					if((direction.equals("h")&&x+wordLength<=15)||(direction.equals("v")&&y-wordLength>=-1)){
					    if(haveTilesOrNotInRack){
						setWordRackTiles(board,word,x,y,direction,intersectionPoints);
					    }
					    else if(direction.equals("h")){
						setWordHor(board,word,x,y,intersectionPoints);
					    }
					    else if(direction.equals("v")){
						setWordVert(board,word,x,y,intersectionPoints);
					    }
					    // System.out.println("point for letter: "+pointForTile);
					    // totalPointValue+=pointForTile;
					    // //removeFromRack(ofInterest);			  		
					    // System.out.println("total points: "+totalPointValue);
					    // endTurn=true;
					}
					else{
					    endTurn=false;
					    return ("Position is on the board but cannot place word in position.  Please try again");
					    //endTurn=false;
		    
					}
				    }
				    else if(validOrNot==false){
					endTurn=true;
					return ("Invalid word.  Next player's turn.");
					//endTurn=true;
				    }
				    else if(!(x>=0&&x<=14)||!(y>=0&&y<=14)){
					endTurn=false;
					return ("Position is off the board so cannot place word on board. Please try again");
					//endTurn=false;
				    }
				}
			    }
			}
		    }
		}
	    }
	}
	// else{
	// 	System.out.println("Word contains letters not in your tile rack.  Please try again with only valid letters");
	// 	endTurn=false;
	// }
	return "";
    }

    public Tile tileAtRackIndex(int indexx){
	return rack.get(indexx);
    }
    public void addToRack(Tile fromBag){
	rack.add(fromBag);
    }
    public void addToRack(int pos, Tile fromBag){
	rack.add(pos,fromBag);
    }
    public void removeFromRack(Tile toRemove){
	rack.remove(toRemove);
    }
    public int getRackSize(){
	return rack.size();
    }
    public String requestExchange(TileBag tilebag,int indexToExchange){
	tilebag.exchange(this,indexToExchange);;
	roundScore=0;
	endTurn=true;
	return "";
    }
    public String requestDifferentiate(int index,String letter){
	endTurn = false;
	System.out.println("index: "+index);
	System.out.println("letter: "+letter);
	if(tileAtRackIndex(index).getLetter().equals("?")){
	    int ascii=(int)letter.charAt(0);
	    if((ascii>='a'&&ascii<='z')||(ascii>='A'&&ascii<='Z')){
		Tile inRack=this.tileAtRackIndex(index);
		this.removeFromRack(inRack);
		Tile rackTile=new Tile("?");
		rackTile.differentiate(letter);
		this.addToRack(index,rackTile);
	    }
	    else{
		return ("Invalid letter. Letter must be a letter in the alphabet.");
	    }
	}
	else{
	    return ("Tile at that index in your rack is not a blank tile");
	}
	//endTurn=false;
	return "";
    }
    public void pass(){
	roundScore=0;
	endTurn=true;
    }
    public String toString(){
	String retStr="Your tiles: ";
	for(int i=0;i<rack.size();i++){
	    Tile displayTile=rack.get(i);
	    String letterCaps=displayTile.getLetter();
	    letterCaps=letterCaps.toUpperCase();
	   retStr=retStr+letterCaps+" ";
	}
	return retStr;
    }
    public static void main(String[] args){
    }

}
