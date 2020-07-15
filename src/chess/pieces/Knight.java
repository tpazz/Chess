package chess.pieces;

import java.util.*;

import chess.board.Board;
import chess.board.Move;
import chess.piece.*;

/*
 * Knight.java  	2.1 26/02/2018 
 */

/**
 * Knight.java
 *
 * Concrete class to represent a knight
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class Knight extends Piece {

    public Knight(int ix, int iy, int c, Board b) {
        super(PieceCode.KNIGHT, ix, iy, c, b);
    }

    // method implements abstract availableMoves method in Piece class
    public ArrayList<Move> availableMoves() {
        return knight();
    }

    // method to return list of legal moves for knight
    private ArrayList<Move> knight() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();
        
        // create a new vector to store legal knight moves
        ArrayList<Move> knightMoves = new ArrayList<Move>();
        
        // set up m to refer to a Move object
        Move theMove = null;
        
        /* 
        Knight offsets:
			{2, 1},
        	{1, 2},
        	{2, -1},
       		{-1, 2},
       		{-2, 1},
       		{1, -2},
       		{-2, -1},
    		{-1, -2}
        */
        
        int[] kX = {2, 1, 2, -1, -2, 1, -2, -1};
        int[] kY = {1, 2, -1, 2, 1, -2, -1, -2};
        
        // increment both arrays concurrently
        for (int i = 0; i < 8; i++) {
        	// take position if occupied by different colour piece (and within the board)
        	if (!getBoard().outOfRange((x + kX[i]), (y + kY[i]))) {
	        	if ((getBoard().occupied((x + kX[i]), (y + kY[i]))) 
	    	            && (getBoard().getPiece((x + kX[i]), (y + kY[i])).getColour() 
	    	            		!= this.getColour())) {
	        		theMove = new Move(this, x, y, (x + kX[i]), (y + kY[i]), true);
	        		knightMoves.add(theMove); 
	        	}
	        	// take position if unoccupied
	        	else if (!getBoard().occupied((x + kX[i]), (y + kY[i]))) {
	        		theMove = new Move(this, x, y, (x + kX[i]), (y + kY[i]), false);
	        		knightMoves.add(theMove);
	        	}
        	}
        }
        
        if (knightMoves.isEmpty())
        	return null;
        return knightMoves;
        		
        }

}
        