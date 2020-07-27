package chess.player;

import java.lang.reflect.Array;
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

	public Move getMostAggressive(ArrayList<Move> m) {
		Move highestMove = null;
		// avoid NullPointerException as for loop iterates to size ArrayList size -1
		if (m.size() == 1)
			highestMove = m.get(0);

		else
			for (int i = 0; i < m.size() - 1; i++) {
				Move currentMove = m.get(i);
				Move nextMove = m.get(i + 1);
				// compare current and next taken piece values in aggressiveMoves ArrayList
				if (getNextPosPieceValue(nextMove) > getNextPosPieceValue(currentMove))
					// and assign highestMove to the piece that holds the greatest value
					highestMove = nextMove;

				else
					highestMove = currentMove;
			}
			return highestMove;
	}

	// abstract makeMove method
	@Override
	public boolean makeMove() {
		
		// set up ArrayList of all potential moves and moves that involve a take
		ArrayList<Move> aggressiveMoves = getAggressiveMoves();
		
		// execute an aggressive move if aggressiveMoves is not empty
		if (aggressiveMoves.size() != 0) {
			// use HumanPlayer's doMove methods to execute move
			doMove(getMostAggressive(aggressiveMoves), getBoard(), getOpponent());
		}
		else 
			// execute a random move
			doMove(RandomPlayer.generateRandomMove(getPieces()), getBoard(), getOpponent());
		
		return true; // always executes a valid move
	}
	
}
