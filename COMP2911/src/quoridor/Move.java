package quoridor;
import java.util.Queue;
import java.util.LinkedList;
public class Move {
	int x;
	int y; 
	int dir = -1;
	Board board;
	//test

	public Move(int x, int y, Board board) {
		this.board = board;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.board = board;
	}

	public Move(int x, int y, int dir, Board board) {
		this.board = board;
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.board = board;
	}

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
			else 
				this.dir = -2;
		}
		if (this.dir == -1 && move.length() == 3 || 
				x < 1 || x > 9 || 
				y < 1 || y > 9 || 
				move.length() > 3) {
		}
	}

	private boolean checkPawnMove(){
		boolean legal = false;
		int px, py;
		Tile pawn = board.getPawnPosition(board.whoseTurn());
		px = pawn.getX();
		py = pawn.getY();

		//no movement
		if (px == x && py == y) return false;
		//out of bounds
		if (x < 1 || x > 9 || y < 1 || y > 9) return false;
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

	//to fix;
	private boolean checkGoalPath() {
		//breadth search;
		Board testPath = (Board)board.clone();
		int[][] visited = new int[9][9];
		Queue<Move> q = new LinkedList<Move>();
		Move nextMove; 
		for (int i = 0; i<4; i++)
			q.add(testPath.pawnMove(i));
		int path = 0;
		visited[board.getPawnPosition(board.whoseTurn()).getX()][board.getPawnPosition(board.whoseTurn()).getY()] = path++;
		boolean goal = false;
		do {			
			nextMove = q.remove();
			for(int i = 0; i<4; i++){
				testPath = (Board)board.clone();
				q.add(testPath.pawnMove(i));	
				if (nextMove != null && nextMove.isLegalMove()){
					nextMove.performMove();
					
					visited[testPath.getPawnPosition(board.whoseTurn()).getX()][testPath.getPawnPosition(board.whoseTurn()).getY()] = path++;
					q.add(testPath.pawnMove(i));
				}
			}
			if (board.whoseTurn() == Board.BLACK && board.getPawnPosition(board.whoseTurn()).getY() == 1)
				goal = true;
			else if (board.whoseTurn() == Board.WHITE && board.getPawnPosition(board.whoseTurn()).getY() == 9)
				goal = true;
				
		} while(!q.isEmpty() || goal == false);
		
		return goal;
	}

	private boolean checkBlockedWalls(){
		boolean blocked;
		if (dir == Board.V)
			blocked = board.getTile(x,y+1).getBlockWall(Tile.EAST);
		else 
			blocked = board.getTile(x+1,y).getBlockWall(Tile.SOUTH);
		return blocked;
	}

	private boolean checkWallMove(){
		boolean legal = true;
		//invalid direction
		if (dir != Board.V && dir != Board.H) return false;
		//edge walls
		if (x == 9 || y == 9) return false;
		if (dir == Board.V && y > 7) return false;
		if (dir == Board.H && x > 7) return false;
		//overlappingwalls
		if (checkBlockedWalls()) legal = false;
		//goal path;
		//Board testPath = (Board)board.clone();
		//testPath.addWall(x,y,dir);	
		//if (!checkGoalPath()) legal = false;

		return legal;
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
	public Board performMove(){
		if (isLegalMove()){
			if (dir == -1) 
				board.setPawnPosition(board.whoseTurn(), board.getTile(x,y));	
			else 
				board.addWall(x,y, dir);		
		} else 
			System.out.println("Illegal Move");	
		return board;
	} 

	public String toString() {
		String s = null;
		return s;
	}
}