package chessmate.components;

import javafx.scene.paint.Color;

public enum PieceColor {
    BLACK,
    WHITE;

    @Override
    public String toString() {
        return this == BLACK ? "B" : "W";
    }

    public Color toColor() {
        return this == BLACK ? new Color(.2, .2, .2, 1) : new Color(.8, .8, .8, 1);
    }
}
