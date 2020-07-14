package assignment2018;

import assignment2018.codeprovided.*;

/*
 * Move.java  	2.1 26/02/2018 
 */

/**
 * Move.java
 *
 * Concrete class to represent the chess a move
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class Move {
	
	// instance variables 
	private Piece piece;
	private int currentX;
	private int currentY;
	private int nextX;
	private int nextY;
	private boolean take;
    
	
	// constructor
	public Move(Piece p, int x, int y, int x2, int y2, boolean b) {
		
		piece = p;
		currentX = x;
		currentY = y;
		nextX = x2;
		nextY = y2;
		take = b;
	}

	// accessor methods
	public int getCurrentX() {
		return currentX;
	}
	public int getCurrentY() {
		return currentY;
	}
	public int getNewX() {
		return nextX;
	}
	public int getNewY() {
		return nextY;
	}
	public boolean getTake() {
		return take;
	}
	
}
