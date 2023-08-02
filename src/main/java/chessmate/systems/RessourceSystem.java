package chessmate.systems;

import javafx.scene.image.Image;

import java.util.HashMap;

public class RessourceSystem {

    private static final HashMap<String, Image> images = new HashMap<>();
    private static final String ROOT_DIR = "file:assets/";

    public Image getImage(String path) {
        if (!images.containsKey(path)) {
            System.out.println("Loading: " + ROOT_DIR + path + ".png");
            images.put(path, new Image(ROOT_DIR + path + ".png", 100, 0, true, true));
        }
        return images.get(path);
    }
}
