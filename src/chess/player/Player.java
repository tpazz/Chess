package chess.player;

import chess.board.Board;
import chess.board.Move;
import chess.piece.Piece;
import chess.piece.Pieces;

import java.util.ArrayList;

/*
 * Player.java  2.1 26/02/2018
 *
 * Copyright (c) University of Sheffield 2018
 */

/**
 * Player.java
 *
 * Abstract class to represent a chess player
 *
 * @version 2.1 26/02/2018
 *
 * @author Richard Clayton (r.h.clayton@sheffield.ac.uk), Steve Maddock
 *         (s.c.maddock@sheffield.ac.uk)
 */

public abstract class Player {

    private String name;
    private Pieces pieces;
    private Board board;
    private Player opponent;

    public Player(String n, Pieces p, Board b, Player o) {
        name = n;
        pieces = p;
        board = b;
        opponent = o;
    }

    // get and set methods

    public int getCurrentPosPieceValue(Move m) {
        return getBoard().getPiece(m.getCurrentX(), m.getCurrentY()).getValue();
    }

    public int getNextPosPieceValue(Move m) {
        return getBoard().getPiece(m.getNewX(), m.getNewY()).getValue();
    }

    public Board getBoard() {
        return board;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player p) {
        opponent = p;
    }

    public Pieces getPieces() {
        return pieces;
    }

    // method to choose a move returning false if
    // there are no legal moves available
    public abstract boolean makeMove();

    // delete a Piece from the player's collection
    public void deletePiece(Piece p) {
        pieces.delete(p);
    }

    // returns the player name
    public String toString() {
        return name;
    }

    public boolean checkKing() {
        // TODO Auto-generated method stub
        return false;
    }

    public ArrayList<Move> getAllPotentialMoves() {
        ArrayList<Move> allPotentialMoves = new ArrayList<>();
        for (int i = 0; i < getPieces().getNumPieces(); i++) {
            Piece p = getPieces().getPiece(i);
            // cannot populate null moves
            if (p.availableMoves() != null)
                allPotentialMoves.addAll(p.availableMoves());
        }
        return allPotentialMoves;
    }

    public ArrayList<Move> getAggressiveMoves() {
        ArrayList<Move> aggressiveMoves = new ArrayList<Move>();
        for (int i = 0; i < getAllPotentialMoves().size(); i++) {
            Move theMove = getAllPotentialMoves().get(i);
            if (theMove.getTake())
                // populate aggressiveMoves with moves from allPotentialMoves that take a piece
                aggressiveMoves.add(theMove);
        }
        return aggressiveMoves;
    }

    // public method to execute move (set/remove position)
    public void doMove(Move m, Board b, Player o) {
        // delete the piece from the opponents collection if take
        if (m.getTake())
            o.deletePiece(b.getPiece(m.getNewX(), m.getNewY()));

        b.setPosition(m.getNewX(), m.getNewY(), b.getPiece(m.getCurrentX(), m.getCurrentY()));
        b.removePosition(m.getCurrentX(), m.getCurrentY());

    }
}

