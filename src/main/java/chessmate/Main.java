package chessmate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        showWelcomeScreen();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showWelcomeScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("welcome_screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        primaryStage.setTitle("CHESSMATE INSI !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showChessBoard() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        primaryStage.setTitle("CHESSMATE INSI!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
