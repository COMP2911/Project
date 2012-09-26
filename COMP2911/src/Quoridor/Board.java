package quoridor;
public class Board {
	public static final int H = 0;
	public static final int V = 1;

	private Tile[][] board;
	int whitepawnposX;
	int whitepawnposY;
	int blackpawnposX;
	int blackpawnposY;
	
	public Board (){
		board = new Tile[9][9];	
		for (int j = 0; j<9; j++){
			for (int i = 0; i<9; i++){	
				board[i][j] = new Tile(i,j);
			}
		}
		getTile(5,1).setPawnColour(1);
		getTile(5,9).setPawnColour(2);
	}
	public Tile getTile(int x, int y){
		return board[x-1][y-1];
	}

	public void addWall(int x, int y, int dir){
		if (dir == V) {
			getTile(x,y+1).setWall(Tile.EAST);
			getTile(x+1,y+1).setWall(Tile.WEST);			
			getTile(x,y+2).setWall(Tile.EAST);
			getTile(x+1,y+2).setWall(Tile.WEST);
		} else if (dir == H) {
			getTile(x+1,y).setWall(Tile.SOUTH);	
			getTile(x+1,y+1).setWall(Tile.NORTH);	
			getTile(x+2,y).setWall(Tile.SOUTH);	
			getTile(x+2,y+1).setWall(Tile.NORTH);	
		}
	}
	
	public void printBoard() {
		Tile currTile;
		char pawnTile;
		String wall;
		for (int j = 1; j<9; j++){
			for (int i = 1; i<9; i++){	
				currTile = getTile(i,j);
				pawnTile = ' ';
				wall = currTile.getWall(Tile.EAST) ? " H"  : " |"; 
				if (currTile.getPawnColour() == 1)
					pawnTile = 'w';
				else if (currTile.getPawnColour() == 2)
					pawnTile = 'b';	
				System.out.print(" "+pawnTile+wall);
			}
			System.out.print("\n");
			for (int i = 1; i<9; i++){
				currTile = getTile(i,j);
				pawnTile = ' ';
				wall = currTile.getWall(Tile.SOUTH) ? "===+"  : "---+"; 
				System.out.print(wall);
			}
			wall = getTile(j,9).getWall(Tile.SOUTH) ? "==="  : "---"; 
			System.out.print(wall);
			System.out.print("\n");
		}
		for (int i = 1; i<9; i++){	
			currTile = getTile(i,9);
			pawnTile = ' ';
			wall = currTile.getWall(Tile.EAST) ? " H"  : " |"; 
			if (currTile.getPawnColour() == 1)
				pawnTile = 'w';
			else if (currTile.getPawnColour() == 2)
				pawnTile = 'b';	
			System.out.print(" "+pawnTile+wall);
		}
	}
	
	public static void main (String[] args){
		Board newBoard = new Board();
		newBoard.addWall(2,4,H);
		newBoard.printBoard();
	}

	public void display(String moves) {
		String[][] wall = new String[17][9];
		for (int a = 0; a<17;a++){
			for (int b = 0; b<9;b++){
				if(a%2 == 0 || a == 0){
					wall[a][b] = "   |";
					if (b == 8)
						wall[a][b] = "    ";
				} else if (a == 16) { 
					wall[a][b] = "   |";
				} else {
					wall[a][b] = "- -+";
					if (b == 8)
						wall[a][8] = "- -";
				}					
			}
		}
		/*

		String[] movelist = moves.split("\\s+");
		//check if pawn move or wall
		char[] index;
		//System.out.println(movelist.length);
		for(int j = 0; j<movelist.length; j++){
			index = movelist[j].toCharArray();
			x = (int)index[0] - 96 - 1;
			y = Character.getNumericValue(index[1]) - 1;	
			if (movelist[j].length() == 2) {

				if (j%2 == 0 || j == 0) { //white
					pawn[whitePawnY][whitePawnX] = ' ';
					wall[whitePawnY*2][whitePawnX] = wall[whitePawnY*2][whitePawnX].substring(0,1) + ' ' + wall[whitePawnY*2][whitePawnX].substring(2,4);
					whitePawnX = x;	
					whitePawnY = y;
					pawn[y][x] = 'w';

				} else {
					pawn[blackPawnY][blackPawnX] = ' ';
					wall[blackPawnY*2][blackPawnX] = wall[blackPawnY*2][blackPawnX].substring(0,1) + ' ' + wall[blackPawnY*2][blackPawnX].substring(2,4);
					blackPawnX = x;	
					blackPawnY = y;
					pawn[y][x] = 'b';
				}

				wall[y*2][x] = wall[y*2][x].substring(0,1) + pawn[y][x] + wall[y*2][x].substring(2,4);
				//System.out.println("x is "+ x + " y is " + y);
			} else if(movelist[j].length() == 3) {
				if (index[2] == 'v'){				
					wall[y*2][x] = "   H";
					wall[y*2+2][x] ="   H";
				} else {       //'h'
					//System.out.println("Wall!");
					//System.out.println(wall[y*2+1][x+1]);
					wall[y*2+1][x+1] = "===+";
					wall[y*2+1][x+2] = "===+";
				}
			}
		}
		 
		int n = 1;
		System.out.println("     a   b   c   d   e   f   g   h   i\n");	
		for (int j =0; j<17; j++ ){
			if (j%2==0)
				System.out.print(n++ +"   ");
			else
				System.out.print("    ");
			for (int k =0; k<9; k++ ){
				System.out.print(wall[j][k]);	
			}
			System.out.print("\n");
		}

`	*/
	}	

}
