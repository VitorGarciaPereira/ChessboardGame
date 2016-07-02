import java.util.Scanner;

public class KnightMoves {
	
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * the following array represents the 8 possible moves that a knight can make
	 * it represents a 8 x 2 array
	 */
	private static int[][] moves = {
			{-2, +1},
			{-1, +2},
			{+1, +2},
			{+2, +1},
			{+2, -1},
			{+1, -2},
			{-1, -2},
			{-2, -1}
	};
	
	public static void main(String[] args){
		
		System.out.println("Welcome to the Knight move calculator!");
		do {
			showKnightMoves();
			
		} while(getYorN("Do it again?"));
		
		System.out.print("Thank you for playing!");
	}
	
	
	/**
	 * the first dimension is the file (a, b, c, etc.)
	 * the second dimension is the rank (1, 2, 3, etc.)
	 * thus, board[3][4] is square d5
	 * A value of 0 means the square is empty
	 * 1 means the Knight is in the square
	 * 2 means the Knight could move to the square
	 */
	public static void showKnightMoves(){
		
		int[][] board = new int[8][8];
		
		String KnightSquare; // the Knight's position as a square
		Pos KnightPos; // the knight's position as a Pos
		
		// get the Knight's initial position
		
		do{
			System.out.print("Enter Knight's position: ");
			KnightSquare = scan.nextLine();
			
			KnightPos = convertSquareToPos(KnightSquare);
			
		} while(KnightPos == null);
		
		board[KnightPos.x][KnightPos.y] = 1; //because 1 means that Knight is in this square
		
		System.out.println("The Knight is at square " + convertPosToSquare(KnightPos));
		
		System.out.println("From here the Knight can move to: ");
		
		for(int move = 0; move < moves.length; move++){
			
			int x, y;
			x = moves[move][0]; //the x for this move
			y = moves[move][1]; // the y for this move
			
			Pos p = calculateNewPos(KnightPos, x, y);
			
			if(p != null){
				System.out.println(convertPosToSquare(p));
				board[p.x][p.y] = 2;
			}
		}
		printBoard(board);
	}
	
	
	/**
	 * this method converts squares such as a1 or d5 to x, y coordinates such as [0][0] or [3][4]
	 * @return
	 */
	public static Pos convertSquareToPos(String square){
		
		int x = -1;
		int y = -1;
		char rank, file;
		
		file = square.charAt(0);
		
		if(file == 'a') x = 0;
		if(file == 'b') x = 1;
		if(file == 'c') x = 2;
		if(file == 'd') x = 3;
		if(file == 'e') x = 4;
		if(file == 'f') x = 5;
		if(file == 'g') x = 6;
		if(file == 'h') x = 7;
		
		rank = square.charAt(1);
		
		if(rank == '1') y = 0;
		if(rank == '2') y = 1;
		if(rank == '3') y = 2;
		if(rank == '4') y = 3;
		if(rank == '5') y = 4;
		if(rank == '6') y = 5;
		if(rank == '7') y = 6;
		if(rank == '8') y = 7;
		
		if(x == -1 || y == -1){
			return null;
		}
		else{
			return new Pos(x, y);
		}
	}
	
	
	/**
	 * this method will return intentions of the user in continuing to play
	 * @param prompt
	 * @return boolean
	 */
	public static boolean getYorN(String prompt){
		
		while(true){
			String answer;
			System.out.println(prompt + " (Y/N) ");
			answer = scan.nextLine();
			if(answer.equalsIgnoreCase("Y")){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	
	/**
	 * this method converts coordinates x and y to a string square in the board
	 * @param p
	 * @return a string
	 */
	public static String convertPosToSquare(Pos p){
		
		String file = "";
		
		if(p.x == 0) file = "a";
		if(p.x == 1) file = "b";
		if(p.x == 2) file = "c";
		if(p.x == 3) file = "d";
		if(p.x == 4) file = "e";
		if(p.x == 5) file = "f";
		if(p.x == 6) file = "g";
		if(p.x == 7) file = "h";
		
		return file + (p.y + 1);
	}
	
	/**
	 *this method calculates a new position given a starting one
	 *also an x move and a y move 
	 * @param p
	 * @param x
	 * @param y
	 * @return object Pos
	 */
	public static Pos calculateNewPos(Pos p, int x, int y){
		
		// rules out regular moves
		if(p.x + x < 0)
			return null;
		if(p.x + x > 7)
			return null;
		if(p.y + y < 0)
			return null;
		if(p.y + y > 7)
			return null;
		
		//return new position
		return new Pos(p.x + x, p.y + y);
	}
	
	/**
	 * This method takes a two dimensional array and prints a board with all possible moves
	 * @param b
	 */
	public static void printBoard(int[][] b){
		
		for (int y = 7; y >= 0; y--){
			
			for(int x = 0; x < 8; x++){
				
				if(b[x][y] == 1){
					System.out.print(" X ");
				}
				else if(b[x][y] == 2){
					System.out.print(" ? ");
				}
				else{
					System.out.print(" - ");
				}
				
			}
			System.out.println();
		}
	}
	
	
}
