package Quoridor;
public class Board implements Display {
	@Override
	public void display(String moves) {
		//get index;
		int x=0,y=0;

		char[][] pawn = new char[9][9];
		for (int i = 0; i<9; i++){
			for (int j = 0; i<9; i++){
				pawn[i][j]=' ';
			}
		}
		pawn[5][1] = 'w';
		pawn[5][8] = 'b';


		int whitePawnX = 5;
		int whitePawnY = 1;
		int blackPawnX = 5;
		int blackPawnY = 8;
		String[][] wall = new String[17][9];
		for (int a = 0; a<17;a++){
			for (int b = 0; b<9;b++){
				if(a%2 == 0 || a == 0){
					wall[a][b] = "   :";
					if (b == 8)
						wall[a][b] = "    ";
				} else if (a == 16) { 
					wall[a][b] = "   :";
				} else {
					wall[a][b] = "- -+";
					if (b == 8)
						wall[a][8] = "- -";
				}					
			}
		}

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
		
	}	

}
