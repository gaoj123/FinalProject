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
	System.out.println(ints);
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
		    System.out.print(letterGenerated+",");
		    Tile aa=new Tile(letterGenerated);
		    a.add(aa);
		    j=100;
		}
		else{
		    j+=1;
		}		    
	    }
	}
		
	// for(int j=0;j<100;j++){
	//     int z=ints.get(j);
	//     if(z>=0&&z<=8){
	// 	Tile aa=new Tile("a");
	// 	a.add(aa);
	//     }
	//     else if(z==9||z==10){
	// 	Tile bb=new Tile("b");
	// 	a.add(bb);
	//     }
	// }
    }
    // public drawTile(){
    // 	if(a.size()==0){
    // 	    System.out.println("Notification: There are no more tiles left in the bag");
    // 	}
    // 	else{
    // 	}		
    // }
    public void refillRack(Player currentplayer){
	//int numTilesOnRack=;
    }
    public void exchange(int numRequested, Player currentplayer, ArrayList<Tile> tilesToBeExchanged){
	for(int start=0;start<tilesToBeExchanged.size();start++){
	    a.add(tilesToBeExchanged.get(start));
	    tilesToBeExchanged.remove(start);
	}
	if(a.size()>=7){
	    for(int i=0;i<numRequested;i++){
		Tile tileClaimed=a.get(i);
		currentplayer.addToRack(tileClaimed);
		//a.remove(z);
	    }
	}
	else{
	    System.out.println("Sorry.  You can't exchange tiles because there are less than 7 tiles left in the tile bag.");
	}
    }
    public static void main(String[] args){
	TileBag test=new TileBag();
	//System.out.println(test.a);
	
    }
}
