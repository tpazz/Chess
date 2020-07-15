package chess.player;

import java.util.ArrayList;

import chess.board.Board;
import chess.board.Move;
import chess.piece.*;

/*
 * AggressivePlayer.java  	2.1 26/02/2018 
 */

/**
 * AggressivePlayer.java
 *
 * Concrete class to represent an Aggressive AI player that will:
 * Take the highest value piece from it's opponent, provided it can take a piece
 * Otherwise it will perform a random move from one of it's pieces
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class AggressivePlayer extends Player {
	
	public AggressivePlayer(String n, Pieces p, Board b, Player o) {
		super(n, p, b, o);
	}
	
	// abstract makeMove method
	@Override
	public boolean makeMove() {
		
		// set up ArrayList of all potential moves and moves that involve a take 
		ArrayList<Move> allPotentialMoves = new ArrayList<Move>();
		ArrayList<Move> aggressiveMoves = new ArrayList<Move>();
		
		// populate allPotentialMoves with every piece's available moves 
		for (int i = 0; i < getPieces().getNumPieces(); i++) {
			Piece p = getPieces().getPiece(i);
			// cannot populate null moves
			if (p.availableMoves() != null) 
				allPotentialMoves.addAll(p.availableMoves());
		}
		
		for (int i = 0; i < allPotentialMoves.size(); i++) {
			Move theMove = allPotentialMoves.get(i);
			if (theMove.getTake()) 
				// populate aggressiveMoves with moves from allPotentialMoves that take a piece
				aggressiveMoves.add(theMove);
		}
		
		// execute an aggressive move if aggressiveMoves is not empty
		if (aggressiveMoves.size() != 0) {
			Move highestMove = null;
			 // avoid NullPointerException as for loop iterates to size ArrayList size -1
			if (aggressiveMoves.size() == 1) 
				highestMove = aggressiveMoves.get(0);
			
			else 
				for (int i = 0; i < aggressiveMoves.size() - 1; i++) {
					Move currentMove = aggressiveMoves.get(i);
					Move nextMove = aggressiveMoves.get(i + 1);
					// compare current and next taken piece values in aggressiveMoves ArrayList
					if (getBoard().getPiece(nextMove.getNewX(), nextMove.getNewY()).getValue() >
							getBoard().getPiece(currentMove.getNewX(), 
									currentMove.getNewY()).getValue()) 
						// and assign highestMove to the piece that holds the greatest value
						highestMove = nextMove;
					
					else 
						highestMove = currentMove;
				}
			
			// use HumanPlayer's doMove methods to execute move
			HumanPlayer.doMove(highestMove, getBoard(), getOpponent());
		}
		
		else 
			// use HumanPlayer's do move method with RandomPlayer's generateRandomMove method to 
			// execute a random move
			HumanPlayer.doMove(RandomPlayer.generateRandomMove(getPieces()),
					getBoard(), getOpponent());
		
		return true; // always executes a valid move
	}
	
}
