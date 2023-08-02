package chessmate.entities;

import chessmate.components.PieceColor;
import chessmate.systems.GameSystem;
import chessmate.components.Position;

import java.util.ArrayList;
import java.util.List;

public class Pion extends Piece {
    public Pion(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position from, GameSystem game) {
        List<Position> moves = new ArrayList<>();
        int x = from.getX();
        int y = from.getY();
        int offset = getColor() == PieceColor.WHITE ? -1 : 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean hasEnemyPiece = game.hasEnnemyPieceAt(i, j);
                if ((!game.hasFriendlyPieceAt(i, j)) &&
                        (j == y + offset && ((!hasEnemyPiece && i == x) || (hasEnemyPiece && i == x + 1) || (hasEnemyPiece && i == x - 1))) ||
                        (!hasMoved() && j == y + offset * 2 && i == x && !game.hasEnnemyPieceAt(i, j) && !game.hasEnnemyPieceAt(i, y + offset))) {      // Two case move
                    moves.add(new Position(i, j));
                }
            }
        }
        return moves;
    }
}
