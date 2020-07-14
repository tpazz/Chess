package assignment2018;

import java.util.ArrayList;
import assignment2018.codeprovided.*;

/*
 * RandomPlayer.java  	2.1 26/02/2018 
 */


/**
 * RandomPlayer.java
 *
 * Concrete class to represent a Random AI player that will:
 * Perform a random move from one of it's pieces
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class RandomPlayer extends Player {
	
	public RandomPlayer(String n, Pieces p, Board b, Player o) {
		super(n, p, b, o);
	}
	
	@Override
	public boolean makeMove() {
		
		// use HumanPlayer's do move method with RandomPlayer's generateRandomMove method to 
		// execute a random move
		HumanPlayer.doMove(generateRandomMove(getPieces()), getBoard(), getOpponent());
		
		return true; // always executes a valid move
	}
	
	public static Move generateRandomMove(Pieces p) {
		
		// create an ArrayList of potential random moves
		ArrayList<Move> potentialRandomMoves = new ArrayList<Move>();
		Move randomMove;
		int randomPieceNo;
		Piece randomPiece;
		boolean validRandom = false;
		
		do {
			// generate a random piece from the players collection that is not null
			randomPieceNo = (int)(Math.random() * (p.getNumPieces()));
			randomPiece = p.getPiece(randomPieceNo);
			if (randomPiece.availableMoves() != null) 
				validRandom = true;
			
		
		} while(!validRandom);
		
		// populate the potential random moves ArrayList with the random piece's available moves
		potentialRandomMoves = randomPiece.availableMoves();
		// return a random move from the list
		return randomMove = potentialRandomMoves.get
				((int)(Math.random() * potentialRandomMoves.size()));
	}
}