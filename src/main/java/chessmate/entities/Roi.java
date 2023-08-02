package chessmate.entities;

import chessmate.components.PieceColor;
import chessmate.systems.GameSystem;
import chessmate.components.Position;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece {

    public Roi(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position from, GameSystem game) {
        List<Position> moves = new ArrayList<>();
        int x = from.getX();
        int y = from.getY();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!game.hasFriendlyPieceAt(i, j) &&
                        (((i == x + 1 || i == x - 1) && (j == y + 1 || j == y - 1)) ||  // Diagonal
                                (y == j && (i == x + 1 || i == x - 1)) || // Horizontal
                                (x == i && (j == y + 1 || j == y - 1)))) { // Vertical
                    moves.add(new Position(i, j));
                }
            }
        }
        boolean[] canCastle = canCastle(from, game);
        if (canCastle[0]) {
            moves.add(new Position(2, y));
            moves.add(new Position(0, y));
        }
        if (canCastle[1]) {
            moves.add(new Position(6, y));
            moves.add(new Position(7, y));
        }
        return moves;
    }


    /**
     * Check if the king can castle
     * @return an array of 2 booleans, the first one is the left castle, the second one is the right castle
     */
    private boolean[] canCastle(Position pos, GameSystem game) {
        if (hasMoved() || isChecked())
            return new boolean[] { false, false };
        int j = color == PieceColor.BLACK ? 0 : 7;
        boolean[] canCastle = { true, true };
        if (game.getPieceAt(0, j) instanceof Tour rook && !rook.hasMoved()) {
            // Left castle row check
            for (int i = pos.getX() - 1; i > 0; i--) {
                if (game.hasPieceAt(i, j)) {
                    canCastle[0] = false;
                    break;
                }
            }
        } else canCastle[0] = false;
        if (game.getPieceAt(7, j) instanceof Tour rook && !rook.hasMoved()) {
            // Right castle row check
            for (int i = pos.getX() + 1; i < 7; i++) {
                if (game.hasPieceAt(i, j)) {
                    canCastle[1] = false;
                    break;
                }
            }
        } else canCastle[1] = false;
        return canCastle;
    }


    public boolean isChecked() {
        return false;
    }
}
