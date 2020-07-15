package chess.pieces;

import java.util.*;

import chess.board.Board;
import chess.board.Move;
import chess.piece.*;

/*
 * Queen.java  	2.1 26/02/2018 
 */

/**
 * Queen.java
 *
 * Concrete class to represent a queen
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class Queen extends Piece {
	
	public Queen(int ix, int iy, int c, Board b) {
		super(PieceCode.QUEEN, ix, iy, c, b);
	}
	 
	// method implements abstract availableMoves method in Piece class
	public ArrayList<Move> availableMoves() {
		return queen();
	}
	
	// method to return list of legal moves for queen
	private ArrayList<Move> queen() {
		// obtain current co-ordinates
		int x = this.getX();
		int y = this.getY();
		
		// create a new vector to store legal queen moves
		ArrayList<Move> queenMoves = new ArrayList<Move>();
		
		// set up m to refer to a Move object
		Move theMove = null;
		
		// all possible moves up the board
		// initialise + 1 to avoid moving to same position 
		for (int i = y + 1; i < 8; i++) {
			// any viable move up to an occupied position
			if(!getBoard().occupied(x, i)) {
				theMove = new Move(this, x, y, x, i, false);
				queenMoves.add(theMove);
			}
			// take occupied position if different coloured piece
			else if(getBoard().occupied(x, i)
						&& getBoard().getPiece(x, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, x, i, true);
				queenMoves.add(theMove);
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
				queenMoves.add(theMove);
			}
			else if(getBoard().occupied(x, i)
						&& getBoard().getPiece(x, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, x, i, true);
				queenMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves left the board 
		for (int i = x - 1; i >= 0; i--) {
			if(!getBoard().occupied(i, y)) {
				theMove = new Move(this, x, y, i, y, false);
				queenMoves.add(theMove);
			}
			else if(getBoard().occupied(i, y)
						&& getBoard().getPiece(i, y).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, i, y, true);
				queenMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves right the board 
		for (int i = x + 1; i < 8; i++) {
			if(!getBoard().occupied(i, y)) {
				theMove = new Move(this, x, y, i, y, false);
				queenMoves.add(theMove);
			}
			else if(getBoard().occupied(i, y)
						&& getBoard().getPiece(i, y).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, i, y, true);
				queenMoves.add(theMove);
				break;
			}
			else
				break;
		}

//---------------DIAGONAL MOVES----------------

		// all possible moves in top left diagonal
		for (int j = x - 1, i = y - 1; j >= 0 && i >= 0; j--, i--) {
			if (!getBoard().occupied(j, i)) {
				theMove = new Move(this, x, y, j, i, false);
				queenMoves.add(theMove);
			}
			else if(getBoard().occupied(j, i)
						&& getBoard().getPiece(j, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, j, i, true);
				queenMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves in the top right diagonal
		for (int j = x - 1, i = y + 1; j >= 0 && i < 8; j--, i++) {
			if (!getBoard().occupied(j, i)) {
				theMove = new Move(this, x, y, j, i, false);
				queenMoves.add(theMove);
			}
			else if(getBoard().occupied(j, i)
						&& getBoard().getPiece(j, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, j, i, true);
				queenMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves in the bottom left diagonal
		for (int j = x + 1, i = y - 1; j < 8 && i >= 0; j++, i--) {
			if (!getBoard().occupied(j, i)) {
				theMove = new Move(this, x, y, j, i, false);
				queenMoves.add(theMove);
			}
			else if(getBoard().occupied(j, i)
						&& getBoard().getPiece(j, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, j, i, true);
				queenMoves.add(theMove);
				break;
			}
			else
				break;
		}
		
		// all possible moves in bottom right diagonal
		for (int j = x + 1, i = y + 1; j < 8 && i < 8; j++, i++) {
			if (!getBoard().occupied(j, i)) {
				theMove = new Move(this, x, y, j, i, false);
				queenMoves.add(theMove);
			}
			else if (getBoard().occupied(j, i)
						&& getBoard().getPiece(j, i).getColour() != this.getColour()) {
				theMove = new Move(this, x, y, j, i, true);
				queenMoves.add(theMove);
				break;
			}
			else
				break;
			}
		
		if (queenMoves.isEmpty())
			return null;
		return queenMoves;
	}
	
}