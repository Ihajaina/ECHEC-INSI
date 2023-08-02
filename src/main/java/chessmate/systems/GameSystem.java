package chessmate.systems;

import chessmate.entities.Fou;
import chessmate.entities.Roi;
import chessmate.entities.Cavalier;
import chessmate.entities.Pion;
import chessmate.entities.Piece;
import chessmate.components.PieceColor;
import chessmate.entities.Reine;
import chessmate.entities.Tour;
import chessmate.components.Position;

import java.util.List;

public class GameSystem {
    private final RenderSystem renderSystem;
    private final Piece[][] board = new Piece[8][8];
    private Position selectedPiece;
    private List<Position> possibleMoves;
    private PieceColor turn = PieceColor.WHITE;

    public GameSystem(RenderSystem renderSystem) {
        this.renderSystem = renderSystem;
    }

    public void initialize() {
        // Réinitialiser le plateau de jeu
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0 || j == 7) {
                    PieceColor color = j == 0 ? PieceColor.BLACK : PieceColor.WHITE;
                    if (i == 0 || i == 7)
                        board[i][j] = new Tour(color);
                    else if (i == 1 || i == 6)
                        board[i][j] = new Cavalier(color);
                    else if (i == 2 || i == 5)
                        board[i][j] = new Fou(color);
                    else if (i == 3)
                        board[i][j] = new Reine(color);
                    else board[i][j] = new Roi(color);
                } else if (j == 1 || j == 6) {
                    PieceColor color = j == 1 ? PieceColor.BLACK : PieceColor.WHITE;
                    board[i][j] = new Pion(color);
                }
            }
        }

        // Réinitialiser la liste des mouvements possibles à null
        possibleMoves = null;

        // Mettre à jour le rendu du plateau de jeu
        update();
    }
    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0 || j == 7) {
                    PieceColor color = j == 0 ? PieceColor.BLACK : PieceColor.WHITE;
                    if (i == 0 || i == 7)
                        board[i][j] = new Tour(color);
                    else if (i == 1 || i == 6)
                        board[i][j] = new Cavalier(color);
                    else if (i == 2 || i == 5)
                        board[i][j] = new Fou(color);
                    else if (i == 3)
                        board[i][j] = new Reine(color);
                    else
                        board[i][j] = new Roi(color);
                } else if (j == 1 || j == 6) {
                    PieceColor color = j == 1 ? PieceColor.BLACK : PieceColor.WHITE;
                    board[i][j] = new Pion(color);
                } else {
                    board[i][j] = null;
                }
            }
        }
        // Réinitialiser la liste des mouvements possibles à null
        possibleMoves = null;

        // Réinitialiser le tour au joueur blanc
        turn = PieceColor.WHITE;
    }


    public void onBoardClick(int i, int j) {
        if (hasFriendlyPieceAt(i, j)) {
            selectedPiece = new Position(i, j);
            possibleMoves = board[i][j].getPossibleMoves(new Position(i, j), this);
            update();
        } else if (selectedPiece != null && possibleMoves.contains(new Position(i, j))) {
            movePiece(selectedPiece, new Position(i, j));
            selectedPiece = null;
        } else if (!hasPieceAt(i, j))
            selectedPiece = null;
    }

    public boolean hasPieceAt(int x, int y) {
        if (isInBoard(x, y))
            return board[x][y] != null;
        else return false;
    }
    public boolean hasPieceAt(Position position) {
        return hasPieceAt(position.getX(), position.getY());
    }

    public boolean hasEnnemyPieceAt(int x, int y) {
        return hasPieceAt(x, y) && board[x][y].getColor() != turn;
    }

    public boolean hasFriendlyPieceAt(int x, int y) {
        return hasPieceAt(x, y) && board[x][y].getColor() == turn;
    }
    public Piece getPieceAt(int i, int j) {
        if (hasPieceAt(i, j))
            return board[i][j];
        else return null;
    }

    public boolean isInBoard(Position position) {
        return position.getX() >= 0 && position.getX() < 8 && position.getY() >= 0 && position.getY() < 8;
    }

    public boolean isInBoard(int x, int y) {
        return isInBoard(new Position(x, y));
    }

    private void movePiece(Position from, Position to) {
        board[to.getX()][to.getY()] = board[from.getX()][from.getY()];
        board[from.getX()][from.getY()] = null;
        board[to.getX()][to.getY()].moved();
        update();
        renderSystem.clearPossibleMoves();
        turn = turn == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
    }
    private boolean isKingTaken(PieceColor color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece instanceof Roi && piece.getColor() == color) {
                    return false; // Le roi de la couleur spécifiée est encore présent sur le plateau.
                }
            }
        }
        return true; // Le roi de la couleur spécifiée n'a pas été trouvé sur le plateau, donc il est pris.
    }

    private void update() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null)
                    renderSystem.setPiece(i, j, board[i][j].getImageSignature());
                else renderSystem.clearPiece(i, j);
            }
        }
        if (possibleMoves != null) {
            renderSystem.clearPossibleMoves();
            for (Position p : possibleMoves) {
                renderSystem.setPossibleMove(p, hasPieceAt(p));
            }
        }
        if (isKingTaken(PieceColor.WHITE)) {
            renderSystem.displayGameOverMessage("Noir"); // Afficher le message "Game Over", le joueur Noir a gagné.
        }

        // Vérifier si le roi noir a été pris
        if (isKingTaken(PieceColor.BLACK)) {
            renderSystem.displayGameOverMessage("Blanc"); // Afficher le message "Game Over", le joueur Blanc a gagné.
        }
    }
}
