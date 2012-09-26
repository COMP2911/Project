package quoridor;

public class Tile {
	public static final int NORTH = 0;
	public static final int EAST  = 1;
	public static final int SOUTH = 2;
	public static final int WEST  = 3;
	
	private Tile[] adjacenttile;
	private boolean[] wall;
	private int xpos;
	private int ypos;
	private int pawn;
	
	public Tile (int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
		adjacenttile = new Tile[4];
		wall = new boolean[4];
		pawn = 0;
	}
	
	public void setAdjacentTile (Tile adjacent, int dir) {
		adjacenttile[dir] = adjacent; 
	}
	public Tile getAdjacentTile (int dir) {
		return adjacenttile[dir];
	}
	public void setWall(int dir) {
		System.out.println(getX() +""+ getY());
		Tile nexttile;
		if (dir == Board.V) {
			wall[EAST] = true;
			getAdjacentTile(EAST).wall[WEST] = true;
			nexttile = getAdjacentTile(SOUTH);
			nexttile.wall[EAST] = true;
			nexttile.getAdjacentTile(EAST).wall[WEST] = true;
		
		} else if (dir == Board.H) {
			getAdjacentTile(EAST).wall[SOUTH] = true;
			getAdjacentTile(EAST).getAdjacentTile(SOUTH).wall[NORTH] = true;
			nexttile = getAdjacentTile(EAST).getAdjacentTile(EAST);
			nexttile.wall[SOUTH] = true;
			nexttile.getAdjacentTile(SOUTH).wall[NORTH] = true;
			//System.out.println(getX() + "" +getY());
		}
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
	
	public boolean isAdjacent(Tile tile){
		boolean result = false;
		int i = 0;
		while (result == false || i++ < 4)
			result = adjacenttile[i] == tile ? true : false;
		return result;
	}
}
