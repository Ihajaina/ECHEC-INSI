module com.garageisep.wschess {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens chessmate to javafx.fxml;
    opens chessmate.systems to javafx.fxml;
    exports chessmate;
    exports chessmate.systems;
    exports chessmate.entities;
    exports chessmate.components;
}