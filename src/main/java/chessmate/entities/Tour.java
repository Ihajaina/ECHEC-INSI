package chessmate.entities;

import chessmate.components.PieceColor;
import chessmate.systems.GameSystem;
import chessmate.components.Position;
import java.util.ArrayList;
import java.util.List;

public class Tour extends Piece {

    public Tour(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position from, GameSystem game) {
        List<Position> moves = new ArrayList<>();
        int x = from.getX();
        int y = from.getY();
        for (int i = x + 1; i < 8; i++) {
            if (!game.hasFriendlyPieceAt(i, y))
                moves.add(new Position(i, y));
            if (game.hasPieceAt(i, y))
                break;
        }
        for (int i = x - 1; i >= 0; i--) {
            if (!game.hasFriendlyPieceAt(i, y))
                moves.add(new Position(i, y));
            if (game.hasPieceAt(i, y))
                break;
        }
        for (int j = y + 1; j < 8; j++) {
            if (!game.hasFriendlyPieceAt(x, j))
                moves.add(new Position(x, j));
            if (game.hasPieceAt(x, j))
                break;
        }
        for (int j = y - 1; j >= 0; j--) {
            if (!game.hasFriendlyPieceAt(x, j))
                moves.add(new Position(x, j));
            if (game.hasPieceAt(x, j))
                break;
        }
        return moves;
    }

}
