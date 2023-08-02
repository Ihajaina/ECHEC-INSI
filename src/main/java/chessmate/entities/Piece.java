package chessmate.entities;

import chessmate.components.PieceColor;
import chessmate.systems.GameSystem;
import chessmate.components.Position;
import java.util.List;

public abstract class Piece {
    protected PieceColor color;
    protected boolean hasMoved;

    public Piece(PieceColor color) {
        this.color = color;
        this.hasMoved = false;
    }

    public String getImageSignature() {
        String className = getClass().getSimpleName().toLowerCase();
        return color.toString() + "_" + className;
    }

    public void moved() {
        this.hasMoved = true;
    }
    public boolean hasMoved() {
        return hasMoved;
    }
    public PieceColor getColor() {
        return color;
    }

    public abstract List<Position> getPossibleMoves(Position from, GameSystem game);
}
