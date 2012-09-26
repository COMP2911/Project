package quoridor;

public class Tile {
	public static final int NORTH = 0;
	public static final int EAST  = 1;
	public static final int SOUTH = 2;
	public static final int WEST  = 3;

	private boolean[] wall;
	private int xpos;
	private int ypos;
	private int pawn;

	public Tile (int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		wall = new boolean[4];
		pawn = Board.BLANK;
	}

	public void setWall(int dir) {
			wall[dir] = true;
	}
	public boolean getWall(int dir) {
		return wall[dir];
	}

	public int getX() {
		return xpos;
	}
	public int getY() {
		return ypos;
	}

	public void setPawnColour(int colour) {
		pawn = colour;
	}
	public int getPawnColour() {
		return pawn;
	}
}