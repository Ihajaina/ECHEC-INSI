package chessmate.entities;

import chessmate.components.PieceColor;
import chessmate.systems.GameSystem;
import chessmate.components.Position;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Piece {
    public Cavalier(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position from, GameSystem game) {
        List<Position> moves = new ArrayList<>();
        int x = from.getY();
        int y = from.getX();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!game.hasFriendlyPieceAt(i, j) && (
                            ((j == x + 1 || j == x - 1) && (i == y + 2 || i == y - 2)) ||
                            ((j == x + 2 || j == x - 2) && (i == y + 1 || i == y - 1))
                )) {
                    moves.add(new Position(i, j));
                }
            }
        }
        return moves;
    }
}
