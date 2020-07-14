package assignment2018;

import assignment2018.codeprovided.*;

/*
 * Board.java  	2.1 26/02/2018 
 */

/**
 * Board.java
 *
 * Concrete class to represent the chess board
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class Board {
	
	// initialise new 2d Piece array
	public Piece[][] theBoard = new Piece[8][8];

	// initialise board
	public Board() {
		for( int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				theBoard[i][j] = null;
			}
		}
	}
	
	// return state of board
	public Piece[][] getBoard() {
		return theBoard;
	}
	
	// returns piece at a location on board
	public Piece getPiece(int x, int y) {
		return theBoard[x][y];
	}

	// return false if coordinates exceed board constraints
	public boolean outOfRange(int newX, int newY) {
		if ((newX < 0) || (newX > 7) || (newY < 0) || (newY > 7))
			return true;
		else
			return false;
	}

	// return false if space is unoccupied 
	public boolean occupied(int x, int y) {
		if (theBoard[x][y] == null) 
			return false;
		
		else 
			return true;
		
	}
	
	// set position of piece, and piece on board
	public void setPosition(int x, int y, Piece thePiece) {
		theBoard[x][y] = thePiece;
		thePiece.setPosition(x, y);
	}	
	
	// nullify position on board 
	public void removePosition(int x, int y) {
		theBoard[x][y] = null;
	}
	
}
