package application;

public class Traps extends Platform{

    public Traps(double x, double y, double width, double height, String spritePath) {
        super(x, y, width, height, spritePath);
    }

    public void checkTrapCollision(Player player) {
        if (player.intersects(getNode())) {
            player.resetPosition();
            player.decreaseLife();
        }
    }
}







