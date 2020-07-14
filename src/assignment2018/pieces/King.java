package assignment2018;

import java.util.*;
import assignment2018.Board;
import assignment2018.Move;
import assignment2018.codeprovided.*;

/*
 * King.java  	2.1 26/02/2018 
 */

/**
 * King.java
 *
 * Concrete class to represent a king
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class King extends Piece {

    public King(int ix, int iy, int c, Board b) {
        super(PieceCode.KING, ix, iy, c, b);
    }

    // method implements abstract availableMoves method in Piece class
    public ArrayList<Move> availableMoves() {
        return king();
    }

    // method to return list of legal moves for a king piece
    private ArrayList<Move> king() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();
        
        // create a new vector to store legal king moves
        ArrayList<Move> kingMoves = new ArrayList<Move>();
        
        // set up m to refer to a Move object
        Move theMove = null;
        
        /* 
        King offsets:
	        {1, 0},
	        {0, 1},
	        {1, 1},
	        {-1, 0},
	        {-1, 1},
	        {0, -1},
	        {1, -1},
	        {-1, -1}
        */
        
        // set up two 1d arrays for the king's offsets
        int[] kX = {1, 0, 1, -1, -1, 0, 1, -1};
        int[] kY = {0, 1, 1, 0, 1, -1, -1, -1};
        
        // increment both arrays concurrently
        for (int i = 0; i < 8; i++) {
        	// take position if occupied by different colour piece (and within the board)
        	if (!getBoard().outOfRange((x + kX[i]), (y + kY[i]))) {
	        	if ((getBoard().occupied((x + kX[i]), (y + kY[i]))) 
	    	            && (getBoard().getPiece((x + kX[i]), (y + kY[i])).getColour() 
	    	            		!= this.getColour())) {
	        		theMove = new Move(this, x, y, (x + kX[i]), (y + kY[i]), true);
	        		kingMoves.add(theMove); 
	        	}
	        	// take position if unoccupied
	        	else if (!getBoard().occupied((x + kX[i]), (y + kY[i]))) {
	        		theMove = new Move(this, x, y, (x + kX[i]), (y + kY[i]), false);
	        		kingMoves.add(theMove);
	        	}
        	}
        }
        
        if (kingMoves.isEmpty())
        	return null;
        return kingMoves;
        		
        }
    
}
