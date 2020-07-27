package chess.player;

import java.util.ArrayList;
import java.util.Iterator;

import chess.board.Board;
import chess.board.Move;
import sheffield.EasyReader;
import chess.piece.*;

/*
 * HumanPlayer.java  	2.1 26/02/2018 
 */

/**
 * HumanPlayer.java
 *
 * Concrete class to represent a Human Player that will:
 * Request a move from the player in board coordinates (eg. A2 A4) and execute it if valid.
 * 
 * The player will be asked to enter the move again if it is not valid, 
 * i.e. not in [char,int_char,int] format or a legal move from the board.
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class HumanPlayer extends Player {
	
	public HumanPlayer(String n, Pieces p, Board b, Player o) {
		super(n, p, b, o);
	}
	
	// abstract makeMove method
	@Override
	public boolean makeMove() {
		
		EasyReader keyboard = new EasyReader(); 
		String toFrom = keyboard.readString("Enter [FROM TO] positions: "); // read input
		boolean possibleMove = false;
	
			toFrom.trim(); // trim input 
			
			try {
				
				// split up supplied string into individual substrings
				String fL = toFrom.substring(0, 1);
				String fN = toFrom.substring(1, 2);
				String tL = toFrom.substring(3, 4);
				String tN = toFrom.substring(4, 5);
				
				// normalise from and to coordinates 
				int x = getNormalisedCoord(fL);
				int y = getNormalisedCoord(fN);
				int x2 = getNormalisedCoord(tL);
				int y2 = getNormalisedCoord(tN);
				
				// retrieve piece to move
				Piece fromPiece = getBoard().getPiece(x, y);
				
				// set up ArrayList of potential moves
				ArrayList<Move> potentialMoves = new ArrayList<Move>();
				potentialMoves = fromPiece.availableMoves(); // retrieve available moves from selected piece
				Iterator<Move> iterator = potentialMoves.iterator(); 
				
				// condition for the player selecting from their own set of pieces
				for (int i = 0; i < getPieces().getNumPieces(); i++) {
					if (getPieces().getPiece(i) == fromPiece) { 
						// iterator
						while (iterator.hasNext()) {
							Move theMove = iterator.next();
							// if there is a move that exists in available moves
							if (theMove.getNewX() == x2 && theMove.getNewY() == y2) {
								// set possibleMove to true execute move
								possibleMove = true;
								doMove(theMove, getBoard(), getOpponent());
							}
						}
					}
				}
			}
			 //catch exceptions for validation on input 
			catch (NullPointerException | StringIndexOutOfBoundsException | NumberFormatException ex) { 
				possibleMove = false;
			}
		// returns false if any exception was caught 
		return possibleMove;
	}
		
	
	// method for mapping alphanumeric board coordinates to pointer coordinates
	public int getNormalisedCoord(String x) {
		if (!isNumeric(x)) {
			String c = "";
			// translate alphabetic board coordinate into array pointer
			switch (x) {
			
				case "A": case "a": c = "0";
					break;
				case "B": case "b": c = "1";
					break; 
				case "C": case "c": c = "2";
					break;
				case "D": case "d": c = "3";
					break;
				case "E": case "e": c = "4";
					break; 
				case "F": case "f": c = "5";
					break;
				case "G": case "g": c = "6";
					break;
				case "H": case "h": c = "7";
					break;
			}
			return Integer.parseInt(c);
			
		}
		else
			// translate numeric board coordinate into array pointer
			// inverse the value as chess coordinates are in descending order
			return Math.abs(Integer.parseInt(x) - 8);
	}

	// method that returns false if supplied string is not a number
	public boolean isNumeric(String x) {
		try {
			Double.parseDouble(x);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}