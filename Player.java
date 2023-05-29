package application;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Player {
    private static final int PLAYER_SIZE = 20;
    private static final int JUMP_HEIGHT = 7;
    private static final double GRAVITY = 1;
    private static final double PLAYER_GRAVITY = 0.5;
    private static final double PLAYER_SPEED = 2.5;
    private int life;
    private ImageView player;
    private Rectangle[] lifeRectangles;
    

    private boolean isJumping;
    private boolean isMovingLeft;
    private boolean isMovingRight;
    private double velocityY;
    private Scene scene;
    private PlayerListener listener;
    
    

    public Player(Scene scene, Rectangle[] lifeRectangles) {
    	this.scene = scene;
    	this.lifeRectangles = lifeRectangles;
    	Image playerImage = new Image("file:src/application/player_sprite.png");
        player = new ImageView(playerImage);
        player.setFitWidth(PLAYER_SIZE);
        player.setFitHeight(PLAYER_SIZE);
        player.relocate(390, 810);
        
        
        isJumping = false;
        isMovingLeft = false;
        isMovingRight = false;
        velocityY = 0;
        life = 3;
    }
    
    public interface PlayerListener {
        void onGameOver();
    }
    
    public void setPlayerListener(PlayerListener listener) {
        this.listener = listener;
    }
    
   
    
    public ImageView getNode() {
        return player;
    }

    public double getWidth() {
        return player.getFitWidth();
    }

    public double getHeight() {
        return player.getFitHeight();
    }

    public double getX() {
        return player.getLayoutX();
    }

    public double getY() {
        return player.getLayoutY();
    }

    public void setY(double y) {
        player.setLayoutY(y);
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            velocityY = -JUMP_HEIGHT;
        }
    }
    
    

    public void startMovingLeft() {
        isMovingLeft = true;
    }

    public void stopMovingLeft() {
        isMovingLeft = false;

    }

    public void startMovingRight() {
        isMovingRight = true;
    }

    public void stopMovingRight() {
        isMovingRight = false;
    }

    public void stopFalling(double groundY) {
        player.setLayoutY(groundY);
        velocityY = 0;
        isJumping = false;
    }
    
    public void resetPosition() {
        player.relocate(390, 810);
        velocityY = 0;
        isJumping = false;
        isMovingLeft = false;
        isMovingRight = false;
    }

    
    public void falldeath() {
    	player.relocate(390, 810);		
    }
    
    public void decreaseLife() {
        life--;
        System.out.println("Remaining Lives: " + life);
        if (life >= 0) {
            lifeRectangles[life].setVisible(false);
        }
       
        if (life < 1) {
            System.out.println("Game Over");
            if (listener != null) {
                listener.onGameOver();
            }
        }
    }
    
    public void update() {
        double newX = player.getLayoutX();
        double newY = player.getLayoutY();

        if (isMovingLeft) {
            newX -= PLAYER_SPEED;
        } else if (isMovingRight) {
            newX += PLAYER_SPEED;
        }

        if (isJumping) {
            velocityY += PLAYER_GRAVITY;
            newY += velocityY;
        } else {
            // Apply gravity when not jumping
            velocityY += GRAVITY;
            newY += velocityY;
        }

        // Restrict the player within the horizontal window bounds
        double minX = 0;
        double maxX = scene.getWidth() - player.getFitWidth();

        // Check if the new position is within the horizontal window bounds
        if (newX < minX) {
            newX = minX;
        } else if (newX > maxX) {
            newX = maxX;
        }

        player.setLayoutX(newX);
        player.setLayoutY(newY);

        // Check if player is below the window height
        if (newY > scene.getHeight()) {
            falldeath();
            decreaseLife();
        }
    }

    public boolean intersects(StackPane other) {
        Bounds playerBounds = player.getBoundsInParent();
        Bounds otherBounds = other.getBoundsInParent();
        return playerBounds.intersects(otherBounds);
    }
}
