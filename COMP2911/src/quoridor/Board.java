package quoridor;
public class Board {
	public static final int H = 0;
	public static final int V = 1;
	public static final int WHITE = 0;
	public static final int BLACK = 1;
	public static final int BLANK = 2;


	public int currentTurn = BLACK;
	private Tile[][] board;
	private boolean gameOver = false;
	public Tile[] pawnPosition;

	public Board (){
		board = new Tile[9][9];	
		pawnPosition = new Tile[2];
		for (int j = 0; j<9; j++){
			for (int i = 0; i<9; i++)
				board[i][j] = new Tile(i+1,j+1);
		}
		pawnPosition[WHITE] = getTile(5,1);
		pawnPosition[BLACK] = getTile(5,9);
		pawnPosition[WHITE].setPawnColour(WHITE);
		pawnPosition[BLACK].setPawnColour(BLACK);
		//getTile(5,8).setPawnColour(BLACK);
	}
	public Tile getTile(int x, int y){
		return board[x-1][y-1];
	}
	
	public Tile getPawnPosition(int colour){
		return pawnPosition[colour];
	}

	public void setPawnPosition(int colour, Tile tile){
		getPawnPosition(whoseTurn()).setPawnColour(BLANK);
		pawnPosition[whoseTurn()] = tile ;
		tile.setPawnColour(whoseTurn());
	}
	
	public void play () {
	}

	private void alternateTurn() {
		currentTurn = (currentTurn == WHITE) ? BLACK : WHITE;
	}

	public int whoseTurn () {
		return currentTurn;
	}
	
	private void start () {
		while (!gameOver){
			play();
			alternateTurn();
		}
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
				if (currTile.getPawnColour() == WHITE)
					pawnTile = 'w';
				else if (currTile.getPawnColour() == BLACK)
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
			if (currTile.getPawnColour() == WHITE)
				pawnTile = 'w';
			else if (currTile.getPawnColour() == BLACK)
				pawnTile = 'b';	
			System.out.print(" "+pawnTile+wall);
		}
	}

	public static void main (String[] args){
		Board newGame = new Board();
		//Move movewall = new Move("d7v", newGame);
		Move move = new Move("e8", newGame);
		move.performMove(newGame);
		Move move1 = new Move("e7", newGame);
		move1.performMove(newGame);
		Move move2 = new Move("f7", newGame);
		move2.performMove(newGame);
		Move move3 = new Move("f6", newGame);
		move3.performMove(newGame);
		//movewall.performMove(newGame);
		
		//Move move1 = new Move("f6", newGame);
		//move1.performMove(newGame);
		newGame.printBoard();
		//newBoard.start();
		
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
