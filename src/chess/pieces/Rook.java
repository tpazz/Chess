package chess.pieces;

import java.util.*;

import chess.board.Board;
import chess.board.Move;
import chess.piece.*;

/*
 * Rook.java  	2.1 26/02/2018 
 */

/**
 * Rook.java
 *
 * Concrete class to represent a rook
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class Rook extends Piece {
	
	public Rook(int ix, int iy, int c, Board b) {
		super(PieceCode.ROOK, ix, iy, c, b);
	}
	 
	// method implements abstract availableMoves method in Piece class
	public ArrayList<Move> availableMoves() {
		return rook();
	}
	
	// method to return list of legal moves for rook
	private ArrayList<Move> rook() {
		// obtain current co-ordinates
		int x = this.getX();
		int y = this.getY();
		
		// create a new vector to store legal rook moves
		ArrayList<Move> rookMoves = new ArrayList<Move>();
		
		// set up m to refer to a Move object
		Move theMove = null;
		
		// all possible moves up the board
		// initialise + 1 to avoid moving to same position 
		for (int i = y + 1; i < 8; i++) {
			// any viable move up to an occupied position
			if(!getBoard().occupied(x, i)) {
				theMove = new Move(this, x, y, x, i, false);
				rookMoves.add(theMove);
			}
			// take occupied position if different coloured piece
			else if(getBoard().occupied(x, i)
						&& getBoard().getPiece(x, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, x, i, true);
				rookMoves.add(theMove);
				// no move past this position is viable
				break;
			}
			else
				// only possible condition at this point is a position taken by the same 
				// coloured piece which means no move including or past this position is viable
				break;
		}
		
		// all possible moves down the board 
		for (int i = y - 1; i >= 0; i--) {
			if(!getBoard().occupied(x, i)) {
				theMove = new Move(this, x, y, x, i, false);
				rookMoves.add(theMove);
			}
			else if(getBoard().occupied(x, i)
						&& getBoard().getPiece(x, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, x, i, true);
				rookMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves left the board 
		for (int i = x - 1; i >= 0; i--) {
			if(!getBoard().occupied(i, y)) {
				theMove = new Move(this, x, y, i, y, false);
				rookMoves.add(theMove);
			}
			else if(getBoard().occupied(i, y)
						&& getBoard().getPiece(i, y).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, i, y, true);
				rookMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves right the board 
		for (int i = x + 1; i < 8; i++) {
			if(!getBoard().occupied(i, y)) {
				theMove = new Move(this, x, y, i, y, false);
				rookMoves.add(theMove);
			}
			else if(getBoard().occupied(i, y)
						&& getBoard().getPiece(i, y).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, i, y, true);
				rookMoves.add(theMove);
				break;
			}
			else
				break;
		}

		if (rookMoves.isEmpty())
			return null;
		return rookMoves;
	}
	
}
