import java.util.*;
public class TileBag{
    private ArrayList<Tile> a=new ArrayList<Tile>();
    private static String[] alphabet=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","?"};
    private static ArrayList<Integer> letterFrequency;
    public TileBag(){
	letterFrequency=new ArrayList<Integer>(Arrays.asList(9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2));
	ArrayList<Integer> ints=new ArrayList<Integer>();
	for(int i=0;i<100;i++){
	    ints.add(new Integer(i));
	}
	Collections.shuffle(ints);
	//System.out.println("Random Sequence of Ints from 0 to 99: "+"\n"+ints);
	//System.out.println("\n");
	//System.out.print("Sequence of randomly arranged tiles:"+"\n"+"[");
	for(int start=0;start<100;start++){
	    int z=0;
	    z=ints.get(start);
	    int j=0;
	    while(j<27){
		int k=j;
		int sumS=0;
		int sumFront=0;
		for(int m=0;m<k+1;m++){
		    sumS+=letterFrequency.get(m);
		}
		for(int n=0;n<k;n++){
		    sumFront+=letterFrequency.get(n);
		}
		if((z<sumS)&&(z>=sumFront)){
		    String letterGenerated=alphabet[j];
		    //System.out.print(letterGenerated+",");
		    Tile aa=new Tile(letterGenerated);
		    a.add(aa);
		    j=100;
		}
		else{
		    j+=1;
		}		    
	    }
	}
	//System.out.println("]");
    }
    public void drawTile(Player currentplayer){
	if(a.size()==0){
	    System.out.println("The tile bag is empty, so no additional tiles were added to your rack");
	}
	else{
	    int randomIndex= (int) (Math.random()*(a.size()-1));
	    //System.out.println("random index: "+randomIndex);
	    currentplayer.addToRack(a.get(randomIndex));
	    a.remove(randomIndex);
	}
    }
    public void drawTile(int position, Player currentplayer){
	if(a.size()==0){
	    System.out.println("The tile bag is empty, so no additional tiles were added to your rack");
	}
	else{
	    int randomIndex= (int) (Math.random()*(a.size()-1));
	    // System.out.println("random index: "+randomIndex);
	    //System.out.println(position+"");
	    currentplayer.addToRack(position, a.get(randomIndex));
	    a.remove(randomIndex);
	}
    }	
    public void refillRack(Player currentplayer){
	if(a.size()==0){
	    System.out.println("The tile bag is empty, so no additional tiles were added to your rack");
	}
	else{
	    int numTilesOnRack=currentplayer.getRackSize();
	    if(numTilesOnRack==7){
		System.out.println("No refills made because already have 7 tiles");
	    }
	    else if(numTilesOnRack<7){
		int numTilesToAdd=7-numTilesOnRack;
		for(int i=0;i<numTilesToAdd;i++){
		    this.drawTile(currentplayer);
		}
	    }
	}
    }
    // public void exchange(Player currentplayer, ArrayList<Tile> tilesToBeExchanged){
    // 	if(a.size()<7){
    // 	    System.out.println("Sorry.  You can't exchange tiles because there are less than 7 tiles left in the tile bag.");
    // 	}
    // 	else{
    // 	    int numRequested=0;
    // 	    numRequested=tilesToBeExchanged.size();
    // 	    for(int start=0;start<numRequested;start++){
    // 		int indexToAddRandomlyBack= (int) (Math.random()*(a.size()-1));
    // 		Tile examining=tilesToBeExchanged.get(start);
    // 		a.add(indexToAddRandomlyBack, examining);
    // 		//	tilesToBeExchanged.remove(start);
    // 		currentplayer.removeFromRack(examining);
    // 	    }
    // 	    for(int i=0;i<numRequested;i++){
    // 		this.drawTile(currentplayer);
    // 		//Tile tileClaimed=a.get(0);
    // 		//currentplayer.addToRack(tileClaimed);
    // 		//a.remove(z);
    // 	    }
    // 	    // for(int j=0;j<numRequested;j++){
    // 	    // 	tilesToBeExchanged.remove(j);
    // 	    // }
    // 	}
    // }
    public void exchange(Player currentplayer, int input){
	if(a.size()<7){
	    System.out.println("Sorry.  You can't exchange tiles because there are less than 7 tiles left in the tile bag.");
	}
	else{
	    int indexToAddRandomlyBack= (int) (Math.random()*(a.size()-1));
	    Tile examining=currentplayer.tileAtRackIndex(input-1);
	    a.add(indexToAddRandomlyBack, examining);
	    currentplayer.removeFromRack(examining);
	    this.drawTile(input-1,currentplayer);
	}
    }
    public int getSize(){
	return a.size();
    }
    public static void main(String[] args){
	/*Player jen=new Player("Jenny");
	TileBag test=new TileBag();
	//test.refillRack(jen);
	Tile f=new Tile("f");
	Tile b=new Tile("b");
	Tile c=new Tile("c");
	Tile d=new Tile("d");
	Tile e=new Tile("e");
	Tile g=new Tile("g");
	Tile h=new Tile("h");
	test.refillRack(jen);
	System.out.println(jen);
	//jen.addToRack(f);
	//jen.addToRack(b);
	//jen.addToRack(c);
	//jen.addToRack(d);
	//jen.addToRack(e);
	//jen.addToRack(g);
	//jen.addToRack(h);
	System.out.println(jen.getRackSize());
	System.out.println(jen);
	//System.out.println(test.a);
	// System.out.println(jen);
	//ArrayList<Tile> testLettersToExchange=new ArrayList<Tile>();
	// Tile c=new Tile("c");
	// Tile d=new Tile("d");
	// Tile e=new Tile("e");
	//testLettersToExchange.add(g);
	//testLettersToExchange.add(h);
	// testLettersToExchange.add(c);
	// testLettersToExchange.add(d);
	// testLettersToExchange.add(e);
	//System.out.println(jen);
	//test.exchange(jen,testLettersToExchange);
	test.exchange(jen,5);
	//System.out.println(jen);
	//	test.refillRack(jen);
	System.out.println(jen);
	*/
    }
}
