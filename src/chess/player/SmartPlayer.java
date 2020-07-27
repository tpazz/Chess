package chess.player;

import chess.board.Board;
import chess.board.Move;
import chess.piece.Pieces;

import java.util.ArrayList;

/*
 * SmartPlayer.java 19/07/2018
 */

/**
 * SmartPlayer.java
 *
 * Public class to represent a smart AI player that only takes the highest value piece if:
 *
 * the attacking piece is not in danger after the move
 *
 * the value of the attacking piece is less than or equal to the piece it is taking
 * given the attacking piece is taken during the opponents turn
 */

public class SmartPlayer extends Player {

    public SmartPlayer(String n, Pieces p, Board b, Player o) {
        super(n, p, b, o);
    }

    private boolean worthyMove(Move m) {

        int attackVal = getBoard().getPiece(m.getCurrentX(), m.getCurrentY()).getValue();
        ArrayList<Move> opponentMoves;
        opponentMoves = getOpponent().getAllPotentialMoves();
        for (int i = 0; i < opponentMoves.size(); i++) {
            int ox = opponentMoves.get(i).getNewX();
            int oy = opponentMoves.get(i).getNewY();
            int cx = opponentMoves.get(i).getCurrentX();
            int cy = opponentMoves.get(i).getCurrentY();
            if (ox == m.getNewX() && oy == m.getNewY()) {
                if (getBoard().getPiece(cx,cy).getValue() >= attackVal) {
                    return true;
                }
                else {
                    return false;
                }
            } else {
                return true; // opponent cannot take piece
            }
        }
        return false;
    }

    @Override
    public boolean makeMove() {
        //TODO
        // check if any piece is in danger and either eliminate the threat by checking if any of the
        // aggressive moves contain a worthy move for the take, otherwise move the piece out of danger
        // if no piece is in danger ...
        // create descending list of highest value 'take' moves, followed by a list of moves
        // that do not put that piece in danger, followed by ascending list of piece value
        AggressivePlayer tmp = new AggressivePlayer("tmp", getPieces(), getBoard(), getOpponent());
        ArrayList<Move> aggressiveMoves = getAggressiveMoves();
        if (aggressiveMoves.size() != 0) {
            for (int i = 0; i < aggressiveMoves.size(); i++) {
                // get most aggressive move
                Move mostAggressive = tmp.getMostAggressive(aggressiveMoves);
                if (worthyMove(mostAggressive)) {
                    doMove(mostAggressive, getBoard(), getOpponent());
                    break;
                } else {
                    aggressiveMoves.remove(mostAggressive);
                }
            }
        } else {
            ArrayList<Move> allPotentialMoves = getAllPotentialMoves();
            for (int i = 0; i < allPotentialMoves.size(); i++) {
                if (worthyMove(allPotentialMoves.get(i))) {
                    doMove(allPotentialMoves.get(i), getBoard(), getOpponent());
                    break;
                }
                else {
                    allPotentialMoves.remove(allPotentialMoves.get(i));
                }
            }
        }

        return true;
    }
}
