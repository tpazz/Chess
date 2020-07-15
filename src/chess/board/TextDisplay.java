package chess.board;

import sheffield.EasyWriter;
import chess.piece.*;

/*
 * TextDisplay.java  	2.1 26/02/2018 
 */

/**
 * TextDisplay.java
 *
 * Concrete class to represent the interface that will be displayed on the console
 *
 * @version 2.1 20/04/2018
 *
 * @author Theo Koorehpaz
 */

public class TextDisplay implements Display {
	
	// declare as static array as displayBoard() will be called twice with different pieces
	static char[][] textDisplay = new char[10][10];
	EasyWriter screen = new EasyWriter();
	
	
	// implemented method populates textDisplay with piece coordinates 
	public void displayBoard(Pieces myPieces) { 
		
		for (int k = 0; k < myPieces.getNumPieces(); k++) {
			
			int x = myPieces.getPiece(k).getX();
			int y = myPieces.getPiece(k).getY();
			char c = myPieces.getPiece(k).getChar();
		
			// add two to each coordinate as top row and left column are boarders
			// so the 8x8 board fills the 10x10 text display
			textDisplay[x+2][y+2] = c;
		}		
		
	}
	
	// method that populates a template for the board, including boarders and empty spaces
	public void clearBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 1)
					textDisplay[i][j] = '|';
				if (j == 1)
					textDisplay[i][j] = '-';
				if (i == 2 && j == 0)
					textDisplay[i][j] = 'A';
				if (i == 3 && j == 0)
					textDisplay[i][j] = 'B';
				if (i == 4 && j == 0)
					textDisplay[i][j] = 'C';
				if (i == 5 && j == 0)
					textDisplay[i][j] = 'D';
				if (i == 6 && j == 0)
					textDisplay[i][j] = 'E';
				if (i == 7 && j == 0)
					textDisplay[i][j] = 'F';
				if (i == 8 && j == 0)
					textDisplay[i][j] = 'G';
				if (i == 9 && j == 0)
					textDisplay[i][j] = 'H';
				if (i == 0 && j == 2) 
					textDisplay[i][j] = '8';
				if (i == 0 && j == 3) 
					textDisplay[i][j] = '7';
				if (i == 0 && j == 4) 
					textDisplay[i][j] = '6';
				if (i == 0 && j == 5) 
					textDisplay[i][j] = '5';
				if (i == 0 && j == 6) 
					textDisplay[i][j] = '4';
				if (i == 0 && j == 7) 
					textDisplay[i][j] = '3';
				if (i == 0 && j == 8) 
					textDisplay[i][j] = '2';
				if (i == 0 && j == 9) 
					textDisplay[i][j] = '1';
				if (i >= 2 && j >=2)
					textDisplay[i][j] = '.';
				}
			}
				
		}
	
	// outputs the fully populated textDisplay array
	public void getChessBoard() {
		screen.println();
		for (int i = 0; i < 10; i++) {
			screen.print("     "); // centre the board on screen
			for (int j = 0; j < 10; j++) {
				screen.print(textDisplay[j][i]);
				screen.print(" "); // space out characters
			}
			screen.println();
		}
	}
}
