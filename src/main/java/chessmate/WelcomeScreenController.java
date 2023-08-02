package chessmate;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable {

    @FXML
    private Button startButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Vous pouvez effectuer des initialisations ici si nécessaire
    }

    @FXML
    private void onStartButtonClicked() {
        try {
            // L'action à effectuer lors du clic sur le bouton "Commencer"
            Main.showChessBoard();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception here, e.g., show an error message to the user
        }
    }
}
