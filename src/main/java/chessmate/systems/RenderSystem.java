package chessmate.systems;

import chessmate.components.PieceColor;
import chessmate.components.Position;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.scene.control.ButtonBar.ButtonData;

public class RenderSystem implements Initializable {

    private final GameSystem gameSystem = new GameSystem(this);
    private final Pane[][] board = new Pane[8][8];
    private final RessourceSystem ressourceSystem = new RessourceSystem();

    @FXML
    public GridPane boardPane;
    @FXML
    private void handleButtonAction() {
        // Code pour gérer l'action du bouton "Quitter" ici
        System.out.println("Bouton Quitter cliqué !");
        Platform.exit(); // Cela fermera l'application lorsqu'on clique sur le bouton "Quitter".
    }
    @FXML
    private GridPane whiteCapturedPane;

    @FXML
    private GridPane blackCapturedPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("RenderController initialized");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean isBlack = (i + j) % 2 == 0;
                ImageView imageView = new ImageView();
                Pane pane = new Pane();
                pane.getChildren().add(imageView);
                pane.setBackground(new Background(new BackgroundFill(isBlack ? PieceColor.BLACK.toColor() : PieceColor.WHITE.toColor(), null, null)));
                int finalI = i;
                int finalJ = j;
                pane.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> gameSystem.onBoardClick(finalI, finalJ));
                board[i][j] = pane;
                boardPane.add(pane, i, j);
            }
        }
        gameSystem.initialize();
    }

    public void setPiece(int x, int y, String imageRef) {
        ImageView imageView = (ImageView) board[x][y].getChildren().get(0);
        imageView.setImage(ressourceSystem.getImage(imageRef));
    }

    public void clearPiece(int x, int y) {
        ImageView imageView = (ImageView) board[x][y].getChildren().get(0);
        imageView.setImage(null);
    }
    public void setPossibleMove(Position pos, boolean hasPiece) {
        Pane pane = board[pos.getX()][pos.getY()];
        Color color = hasPiece ? new Color(.96, .431, .431, 1) : new Color(.431, .764, .96, 1);
        pane.setBackground(new Background(new BackgroundFill(color, null, null)));
    }

    public void clearPossibleMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pane pane = board[i][j];
                boolean isBlack = (i + j) % 2 == 0;
                pane.setBackground(new Background(new BackgroundFill(isBlack ? new Color(.2, .2, .2, 1) : new Color(.8, .8, .8, 1), null, null)));
            }
        }
    }
    public void displayGameOverMessage(String winner) {
        // Créer une boîte de dialogue pour afficher le message de fin de partie.
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Partie terminée");
        alert.setContentText("Le joueur " + winner + " a gagné !");

        // Ajouter les boutons "Recommencer" et "Quitter" à la boîte de dialogue.
        ButtonType recommencerButton = new ButtonType("Recommencer");
        ButtonType quitterButton = new ButtonType("Quitter");

        alert.getButtonTypes().addAll(recommencerButton, quitterButton);

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur.
        ButtonType choix = alert.showAndWait().orElse(null);

        // Vérifier la réponse de l'utilisateur.
        if (choix == recommencerButton) {
            // Si l'utilisateur choisit "Recommencer", réinitialiser la partie.
            gameSystem.resetBoard();
            gameSystem.initialize();
        } else if (choix == quitterButton) {
            // Si l'utilisateur choisit "Quitter", fermer l'application.
            Platform.exit();
        } else {
            // Si l'utilisateur ferme la boîte de dialogue sans choisir de bouton,
            // afficher un message d'information.
            Alert infoAlert = new Alert(AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setHeaderText(null);
            infoAlert.setContentText("Vous pouvez choisir de recommencer ou quitter le jeu.");
            infoAlert.showAndWait();
        }
    }
    public void addCapturedPiece(PieceColor color, String imageRef) {
        // Create an ImageView to represent the captured piece
        ImageView imageView = new ImageView();
        imageView.setImage(ressourceSystem.getImage(imageRef));

        // Get the appropriate GridPane for the captured pieces based on the color
        GridPane capturedPane = color == PieceColor.WHITE ? whiteCapturedPane : blackCapturedPane;

        // Add the captured piece to the GridPane
        capturedPane.getChildren().add(imageView);

        // Adjust the size of the captured piece
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }

}
