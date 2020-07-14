package assignment2018;

import java.util.*;
import assignment2018.Board;
import assignment2018.Move;
import assignment2018.codeprovided.*;

/*
 * Bishop.java 
 */

/**
 * Bishop.java
 * 
 * Class to represent a bishop
 * 
 * @author Theo Koorehpaz 
 */

public class Bishop extends Piece {
	
	public Bishop(int ix, int iy, int c, Board b) {
		super(PieceCode.BISHOP, ix, iy, c, b);
	}
	
	// method implements abstract availableMoves method in Piece class
	public ArrayList<Move> availableMoves() {
		return bishop();
	}
	
	// method to return list of legal moves for bishop
	private ArrayList<Move> bishop() {
		// obtain current co-ordinates
		int x = this.getX();
		int y = this.getY();
		
		// create a new vector to store legal bishop moves
		ArrayList<Move> bishopMoves = new ArrayList<Move>();
		
		// set up m to refer to a Move object
		Move theMove = null;
		
		// all possible moves in top left diagonal
		// initialise (-)1 to avoid moving to same position 
		for (int j = x - 1, i = y - 1; j >= 0 && i >= 0; j--, i--) {
			// any viable move up to an occupied position
			if (!getBoard().occupied(j, i)) {
				theMove = new Move(this, x, y, j, i, false);
				bishopMoves.add(theMove);
			}
			// take occupied position if different coloured piece
			else if(getBoard().occupied(j, i)
						&& getBoard().getPiece(j, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, j, i, true);
				bishopMoves.add(theMove);
				// no move past this position is viable
				break;
			}
			else
				// only possible condition at this point is a position taken by the same 
				// coloured piece which means no move including or past this position is viable 
				break;
		}
		
		// all possible moves in the top right diagonal
		for (int j = x - 1, i = y + 1; j >= 0 && i < 8; j--, i++) {
			if (!getBoard().occupied(j, i)) {
				theMove = new Move(this, x, y, j, i, false);
				bishopMoves.add(theMove);
			}
			else if(getBoard().occupied(j, i)
						&& getBoard().getPiece(j, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, j, i, true);
				bishopMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves in the bottom left diagonal
		for (int j = x + 1, i = y - 1; j < 8 && i >= 0; j++, i--) {
			if (!getBoard().occupied(j, i)) {
				theMove = new Move(this, x, y, j, i, false);
				bishopMoves.add(theMove);
			}
			else if(getBoard().occupied(j, i)
						&& getBoard().getPiece(j, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, j, i, true);
				bishopMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves in bottom right diagonal
		for (int j = x + 1, i = y + 1; j < 8 && i < 8; j++, i++) {
			
			if (!getBoard().occupied(j, i)) {
				theMove = new Move(this, x, y, j, i, false);
				bishopMoves.add(theMove);
			}
			else if (getBoard().occupied(j, i)
						&& getBoard().getPiece(j, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, j, i, true);
				bishopMoves.add(theMove);
				break;
			}
			else
				break;
			}
		
		if (bishopMoves.isEmpty())
			return null;
		return bishopMoves;
	}
}
