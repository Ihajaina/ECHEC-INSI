package chessmate.entities;

import chessmate.components.PieceColor;
import chessmate.systems.GameSystem;
import chessmate.components.Position;
import java.util.ArrayList;
import java.util.List;

public class Fou extends Piece {
    public Fou(PieceColor color) {
        super(color);
    }

    @Override
    public List<Position> getPossibleMoves(Position from, GameSystem game) {
        List<Position> moves = new ArrayList<>();
        int x = from.getX();
        int y = from.getY();
        boolean[] blocked = {false, false, false, false};
        for (int i = 1; i <= 7; i++) {
            // top left diagonal
            int xx1 = x - i;
            int yy1 = y - i;
            if ((!blocked[0] && game.hasPieceAt(xx1, yy1)) || !game.isInBoard(xx1, yy1)) {
                if (game.hasEnnemyPieceAt(xx1, yy1))
                    moves.add(new Position(xx1, yy1));
                blocked[0] = true;
            } else if (!blocked[0])
                moves.add(new Position(xx1, yy1));

            // top right diagonal
            int xx2 = x + i;
            int yy2 = y - i;
            if ((!blocked[1] && game.hasPieceAt(xx2, yy2)) || !game.isInBoard(xx2, yy2)) {
                if (game.hasEnnemyPieceAt(xx2, yy2))
                    moves.add(new Position(xx2, yy2));
                blocked[1] = true;
            } else if (!blocked[1])
                moves.add(new Position(xx2, yy2));

            // bottom left diagonal
            int xx3 = x - i;
            int yy3 = y + i;
            if ((!blocked[2] && game.hasPieceAt(xx3, yy3)) || !game.isInBoard(xx3, yy3)) {
                if (game.hasEnnemyPieceAt(xx3, yy3))
                    moves.add(new Position(xx3, yy3));
                blocked[2] = true;
            } else if (!blocked[2])
                moves.add(new Position(xx3, yy3));

            // bottom right diagonal
            int xx4 = x + i;
            int yy4 = y + i;
            if ((!blocked[3] && game.hasPieceAt(xx4, yy4)) || !game.isInBoard(xx4, yy4)) {
                if (game.hasEnnemyPieceAt(xx4, yy4))
                    moves.add(new Position(xx4, yy4));
                blocked[3] = true;
            } else if (!blocked[3])
                moves.add(new Position(xx4, yy4));
        }
        return moves;
    }
}
