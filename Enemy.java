package application;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy extends ImageView {
    private Player player;
    private double speed;
    private double startX;
    private double startY;

    public Enemy(Player player, double radius, String imagePath, double speed) {
        super(new Image(imagePath));
        this.player = player;
        this.speed = speed;
        setFitWidth(radius * 2); // Set width of the image view
        setFitHeight(radius * 2); // Set height of the image view

    
        startX = 0;
        startY = 0;
    }

    public void initializeStartPosition(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
        setX(startX);
        setY(startY);
    }

    public void update() {
        player.update();

        double playerX = player.getX();
        double playerY = player.getY();
        double enemyX = getX();
        double enemyY = getY();

        double dx = playerX - enemyX - 48;
        double dy = playerY - enemyY - 80;
        double angle = Math.atan2(dy, dx);

        double newX = enemyX + Math.cos(angle) * speed;
        double newY = enemyY + Math.sin(angle) * speed;

        setX(newX);
        setY(newY);

        // Check for collision with player
        if (intersects(player)) {
            player.decreaseLife();
            player.resetPosition();
            resetPosition();
        }
    }

    public void resetPosition() {
        setX(startX);
        setY(startY);
    }

    private boolean intersects(Player player) {
        return getBoundsInParent().intersects(player.getNode().getBoundsInParent());
    }
}

