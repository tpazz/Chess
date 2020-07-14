package assignment2018;

import assignment2018.codeprovided.*;
import sheffield.*;

/*
 * Chess.java  	2.1 26/02/2018 
 */

/**
 * Chess.java
 *
 * Chess class will;
 * Construct a new board, a collection of pieces and two players
 * Main method will;
 * Ask the player for the type of opponent they wish to play against ~
 * [Player vs Player] | [Player vs Random AI] | [Player vs Aggressive AI]
 * Request a valid move from player(s) 
 * Display the current state of the game on the console 
 * Declares a winner when a king piece is taken 
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class Chess {
	
	static Board chess = new Board();
	static Pieces blackPieces = new Pieces(chess, PieceCode.BLACK);
	static Pieces whitePieces = new Pieces(chess, PieceCode.WHITE);
	static EasyWriter screen = new EasyWriter();
	static EasyReader keyboard = new EasyReader();
	static HumanPlayer playerOne = new HumanPlayer("Player 1", whitePieces, chess, null); // you
	
	public static void main(String[] args) {
		
		screen.println();
		screen.println("~~~~~~~~~~~~~~~~~~~~ Chess ~~~~~~~~~~~~~~~~~~~~");
		screen.println();
		screen.println("Player vs. Player ~ 'P' | Random AI ~ 'R' | Aggressive AI ~ 'A'");
		screen.println();
		
		// read input to initiate a game vs selected player
		char o = keyboard.readChar("Please enter an opponent: ");
			
		if (o == 'P' || o == 'p') {
			
			startNewPvP();
		}
		
		else if (o == 'R' || o == 'r') {
			
			startNewRandomAi();
		}
		
		else if (o == 'A' || o == 'a') {
			
			startAggressiveAi();
		}
	}
	
	// method to populate and display the board of all pieces
	public static void setUpBoard() {
		
		new TextDisplay().clearBoard(); // clears and sets the board template
		new TextDisplay().displayBoard(blackPieces); // add black pieces
		new TextDisplay().displayBoard(whitePieces); // add white pieces
		new TextDisplay().getChessBoard(); // retrieve populated board
	}
	
	// method that returns false when a player does not have a king piece
	public static boolean checkKing(Pieces p) {
		
		boolean king = false;
		
		for (int i = 0; i < p.getNumPieces(); i++) {
			if (p.getPiece(i).getValue() == 6) {
				king = true;
			}
		}
		return king;
	}
		
	// method for Player vs Player game
	public static void startNewPvP() {
	
		HumanPlayer playerTwo = new HumanPlayer("Player 2", blackPieces, chess, playerOne);
		playerOne.setOpponent(playerTwo);

		boolean winner = false;
		int player = 1;
		
		do {
			setUpBoard(); // display current state of the board after each player's move
			
			if (player == 1) {
				screen.println();
				screen.println("<--- " + playerOne.toString() + "'s move (TOP)" + " --->");
				// request a move from player one
				if (!playerOne.makeMove()) {
					screen.println();
					screen.println("Please enter a valid move ~");
				}
				else
					// set to player 2's turn if player 1's move was successful
					player = 2;
			} 
			
			else if (player == 2) {
				screen.println();
				screen.println("<--- " + playerTwo.toString() + "'s move (BOTTOM)" + " --->");
				if (!playerTwo.makeMove()) {
					screen.println();
					screen.println("Please enter a valid move ~");
				}
				else 
					// set to player 1's turn if player 2's move was successful
					player = 1;
			} 
			
			// loop until either player 1 or 2 no longer has a king piece 
			if (!checkKing(playerTwo.getPieces())) {
				screen.println();
				screen.println(playerOne.toString() + " wins!");
				winner = true;
			}
			
			if (!checkKing(playerOne.getPieces())) {
				screen.println();
				screen.println(playerTwo.toString() + " wins!");
				winner = true;
			}
			
		}	while (!winner);
		
		setUpBoard(); // display winning move
	}
	
	// method for Player vs Random AI game
	public static void startNewRandomAi() {
		
		RandomPlayer playerTwo = new RandomPlayer("Random AI", blackPieces, chess, playerOne);
		playerOne.setOpponent(playerTwo);
			
		boolean winner = false;
		int player = 1;
		
		do {
			setUpBoard();  // display current state of the board after each player's move
			
			if (player == 1) { 
				screen.println();
				screen.println("<--- " + playerOne.toString() + "'s move (TOP)" + " --->");
				// request a move from player one
				if (!playerOne.makeMove()) {
					screen.println();
					screen.println("Please enter a valid move ~");
				}
				else
					// set to player 2's turn if player 1's move was successful
					player = 2;
			} 
			
			else if (player == 2) {
				screen.println();
				screen.println("<--- " + playerTwo.toString() + "'s move (BOTTOM)" + " --->");
				playerTwo.makeMove(); // will return true
				player = 1;
			} 
			
			// loop until either player 1 or 2 no longer has a king piece 
			if (!checkKing(playerTwo.getPieces())) {
				screen.println();
				screen.println(playerOne.toString() + " wins!");
				winner = true;
			}
			
			if (!checkKing(playerOne.getPieces())) {
				screen.println();
				screen.println(playerTwo.toString() + " wins!");
				winner = true;
			}
			
		}	while (!winner);
		
		setUpBoard(); // display winning move
	}
	
	// method for Player vs Aggressive AI
	public static void startAggressiveAi() {
		
		AggressivePlayer playerTwo = new AggressivePlayer("Aggressive AI", blackPieces, chess, playerOne);
		playerOne.setOpponent(playerTwo);
			
		boolean winner = false;
		int player = 1;
		
		do {
			setUpBoard(); // display current state of the board after each player's move
			
			if (player == 1) { 
				screen.println();
				screen.println("<--- " + playerOne.toString() + "'s move (TOP)" + " --->");
				// request a move from player one
				if (!playerOne.makeMove()) {
					screen.println();
					screen.println("Please enter a valid move ~");
				}
				else
					// set to player 2's turn if player 1's move was successful
					player = 2;
			} 
			
			else if (player == 2) {
				screen.println();
				screen.println("<--- " + playerTwo.toString() + "'s move (BOTTOM)" + " --->");
				playerTwo.makeMove(); // will return true
				player = 1;
			} 
			
			// loop until either player 1 or 2 no longer has a king piece 
			if (!checkKing(playerTwo.getPieces())) {
				screen.println();
				screen.println(playerOne.toString() + " wins!");
				winner = true;
			}
			
			if (!checkKing(playerOne.getPieces())) {
				screen.println();
				screen.println(playerTwo.toString() + " wins!");
				winner = true;
			}
			
		}	while (!winner);
		
		setUpBoard(); // display winning move
	}
		
}