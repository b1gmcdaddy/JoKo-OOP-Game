package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;

public class Platform {
    private StackPane platform;

    public Platform(double x, double y, double width, double height, String spritePath) {
        platform = new StackPane();
        platform.setTranslateX(x);
        platform.setTranslateY(y);

        ImageView sprite = new ImageView(new Image(spritePath));
        sprite.setFitWidth(width);
        sprite.setFitHeight(height);

        platform.getChildren().add(sprite);
    }

    public StackPane getNode() {
        return platform;
    }

    public double getX() {
        return platform.getTranslateX();
    }

    public double getY() {
        return platform.getTranslateY();
    }
    
    public void rotate(double angle) {
        Rotate rotation = new Rotate(angle);
        platform.getTransforms().setAll(rotation);
    }
}
