package quoridor;

public class Move {
	int x;
	int y; 
	int dir = -1;
	Board board;
	//test
	public Move(String move, Board board) {
		char dir;
		this.board = board;
		x = (int)move.charAt(0) - 96;
		y = Character.getNumericValue(move.charAt(1));
		if (move.length() == 3){
			dir = move.charAt(2);
			if (dir == 'v')
				this.dir = Board.V;
			else if (dir == 'h')
				this.dir = Board.H;
		}
		if (this.dir == -1 && move.length() == 3 || 
				x < 1 || x > 9 || 
				y < 1 || y > 9 || 
				move.length() > 3) {
			System.out.println("Invalid Move");
		}
	}

	private boolean outOfBounds(){
		return true;
	}


	private boolean checkPawnMove(){
		boolean legal = false;
		int px, py;
		Tile pawn = board.getPawnPosition(board.whoseTurn());
		px = pawn.getX();
		py = pawn.getY();
		
		if (px == x && py == y)
			return false;
		//up down & jump
		if (x == px) {
			//System.out.println(py + "-" + y + " " + board.whoseTurn());
			if (py - y == 1 && !pawn.getWall(Tile.NORTH) && board.getTile(x,y).isEmpty()|| 
					y - py == 1 && !pawn.getWall(Tile.SOUTH) && board.getTile(x,y).isEmpty()||
					py - y == 2 && !board.getTile(px,py-1).isEmpty() && !board.getTile(px,py-1).getWall(Tile.NORTH) ||
					y - py == 2 && !board.getTile(px,py+1).isEmpty() && !board.getTile(px,py+1).getWall(Tile.SOUTH) ){
				legal = true;
			}
		
		}
		//left right & jump
		if (y == py){
			if (px - x == 1 && !pawn.getWall(Tile.WEST) && board.getTile(x,y).isEmpty()|| 
					x - px == 1 && !pawn.getWall(Tile.EAST) && board.getTile(x,y).isEmpty()||
					px - x == 2 && !board.getTile(px+1,py).isEmpty() && board.getTile(px+1,py).getWall(Tile.EAST) ||
					x - px == 2 && !board.getTile(px-1,py).isEmpty() && board.getTile(px-1,py).getWall(Tile.WEST) ){
				legal = true;
			}
		}
		//diagonal jump
		if (x != px && y != py){
			if (px - x == 1 && py - y == 1 && !board.getTile(x,y+1).getWall(Tile.EAST)|| 
				x - px == 1 && py - y == 1 && !board.getTile(x,y+1).getWall(Tile.WEST)||
				px - x == 1 && y - py == 1 && !board.getTile(x,y-1).getWall(Tile.EAST)||
				x - px == 1 && y - py == 1 && !board.getTile(x,y+1).getWall(Tile.WEST)){
				legal = true;
			}
		}
	
		return legal;
	}

	private boolean checkWallMove(){
		return true;
	}

	public boolean isLegalMove() {
		boolean legal = false;
		if (dir == -1){
			legal = checkPawnMove();	
		} else { //wall movement
			legal = checkWallMove();
		}
		return legal;
	}
	public Board performMove(Board board){
		if (isLegalMove()){
			if (dir == -1) {
				board.setPawnPosition(board.whoseTurn(), board.getTile(x,y));	
			} else {
				board.addWall(x,y, dir);
			}
		}
		return board;
	} 

	public String toString() {
		String s = null;
		return s;
	}
}