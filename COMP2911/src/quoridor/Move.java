package quoridor;

public class Move {
	int x;
	int y; 
	int dir = -1;

	public Move(String move) {
		char dir;
		x = (int)move.charAt(0) - 96;
		y = Character.getNumericValue(move.charAt(1));
		if (move.length() == 3){
			dir = move.charAt(2);
			if (dir == 'v')
				this.dir = Board.V;
			else if (dir == 'h')
				this.dir = Board.H;
		}
		if (this.dir == -1 || 
			x < 1 || x > 9 || 
			y < 1 || y > 9 || 
			move.length() > 3) {
			System.out.println("Invalid Move");
		}
	}
	public boolean isLegalMove(Board board) {
		return true;
	}
	public void performMove(Board board){

	} 

	public String toString() {
		String s = null;
		return s;
	}
}