package application;
	
import application.Player.PlayerListener;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 850;
    private static final int GROUND_HEIGHT = 20;

    private Pane root;
    private Player player;
    private Platform ground, platform1, platform2, platform3, platform4, platform5, 
    			platform6, platform7, platform8, platform9, platform10, platform11,
    			platform12, platform13, L2partform, L2partform1, L2partform2, L2partform3, L2partform4,
    			L2partform5, L2partform6, L2partform7, L2partform8, L2partform9, L2partform10, L2partform11,
    			L3partform, L3partform1, L3partform2, L3partform3, L3partform4, L3partform5, L3partform6,
    			L3partform7, L3partform8, L3partform9, end, campfire;
    private Traps trap, trap1, trap2, trap3, trap4, trap5, trap6, trap7, trap8, trap9,
    			trap10, trap11, trap12, trap13;
    
	private Stage primaryStage;
	private Rectangle[] lifeRectangles;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Escape From Hell");

        showHomePage();
    }
    
    private void showHomePage() {
        VBox homeLayout = new VBox();
        homeLayout.setSpacing(20);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setStyle("-fx-background-color: #FFE4B5;");

        Image logoImage = new Image("file:src/application/logo.png");
        ImageView logoImageView = new ImageView(logoImage);

        Button playButton = new Button("PLAY NOW");
        Button exitButton = new Button("EXIT");

        playButton.setOnAction(e -> startGame());
        exitButton.setOnAction(e -> primaryStage.close());
        
        playButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 10px; -fx-cursor: hand;");
        exitButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 10px; -fx-cursor: hand;");

        // Button hover effect
        playButton.setOnMouseEntered(e -> playButton.setScaleX(1.05));
        playButton.setOnMouseExited(e -> playButton.setScaleX(1.0));
        exitButton.setOnMouseEntered(e -> exitButton.setScaleX(1.05));
        exitButton.setOnMouseExited(e -> exitButton.setScaleX(1.0));


        homeLayout.getChildren().addAll(logoImageView, playButton, exitButton);

        Scene homeScene = new Scene(homeLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(homeScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public void startGame() {
        root = new Pane();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        Image backgroundImage = new Image("file:src/application/EscapeHellBackground.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, null, new BackgroundSize(WINDOW_WIDTH, WINDOW_HEIGHT, false, false, false, false));
        root.setBackground(new Background(background));
        
        lifeRectangles = new Rectangle[3];
        for (int i = 0; i < lifeRectangles.length; i++) {
            Rectangle rectangle = new Rectangle(20, 20);
            rectangle.setFill(Color.RED);
          
            rectangle.setStroke(Color.WHITE);
            rectangle.setStrokeWidth(2);
            rectangle.setTranslateX(20 + i * 30); // Adjust the positioning of the rectangles
            rectangle.setTranslateY(20); // Adjust the positioning of the rectangles
            lifeRectangles[i] = rectangle;
   
        }

        player = new Player(scene, lifeRectangles);
        ground = new Platform(200, WINDOW_HEIGHT - GROUND_HEIGHT, 390, GROUND_HEIGHT, "file:src/application/hell_platform1.jpg");
        platform1 = new Platform(670, (WINDOW_HEIGHT - GROUND_HEIGHT), 500, 15, "file:src/application/hell_platform1.jpg");
        platform2 = new Platform(0, (WINDOW_HEIGHT - GROUND_HEIGHT), 130, 15, "file:src/application/hell_platform1.jpg");
        platform3 = new Platform(WINDOW_WIDTH - 50, (WINDOW_HEIGHT - GROUND_HEIGHT) - 50, 200, 15, "file:src/application/hell_platform1.jpg");
        platform4 = new Platform(630, 740, 70, 15, "file:src/application/hell_platform1.jpg");
        platform5 = new Platform(700, (WINDOW_HEIGHT - GROUND_HEIGHT) - 155, 200, 15, "file:src/application/hell_platform1.jpg");
        platform6 = new Platform(430, 710, 130, 15, "file:src/application/hell_platform1.jpg");
        platform7 = new Platform(-30, 790, 70, 15, "file:src/application/hell_platform1.jpg");
        platform8 = new Platform(100, 740, 70, 15, "file:src/application/hell_platform1.jpg");
        platform9 = new Platform(230, 700, 115, 15, "file:src/application/hell_platform1.jpg");
        platform10 = new Platform(300, 640, 115, 15, "file:src/application/hell_platform1.jpg");
        platform11 = new Platform(550, 655, 115, 15, "file:src/application/hell_platform1.jpg");
        platform12 = new Platform(60, 660, 115, 15, "file:src/application/hell_platform1.jpg");
        platform13 = new Platform(0, 620, 50, 15, "file:src/application/hell_platform1.jpg");
        L2partform = new Platform(720, 620, 115, 15, "file:src/application/hell_platform1.jpg");
        L2partform1 = new Platform(550, 570, 115, 15, "file:src/application/CaveTile.png");
        L2partform2 = new Platform(350, 570, 170, 15, "file:src/application/CaveTile.png");
        L2partform3 = new Platform(130, 570, 130, 15, "file:src/application/CaveTile.png");
        L2partform4 = new Platform(0, 520, 80, 15, "file:src/application/CaveTile.png");
        L2partform5 = new Platform(150, 470, 170, 15, "file:src/application/CaveTile.png");
        L2partform6 = new Platform(350, 470, 100, 15, "file:src/application/CaveTile.png");
        L2partform7 = new Platform(550, 470, 100, 15, "file:src/application/CaveTile.png");
        L2partform8 = new Platform(750, 540, 70, 15, "file:src/application/CaveTile.png");
        L2partform9 = new Platform(710, 430, 100, 15, "file:src/application/CaveTile.png");
        L2partform10 = new Platform(540, 380, 100, 15, "file:src/application/CaveTile.png");
        L2partform11 = new Platform(710, 340, 100, 15, "file:src/application/darkCaveTile.jpg");
        L3partform  = new Platform(310, 340, 170, 15, "file:src/application/darkCaveTile.jpg");
        L3partform1 = new Platform(110, 300, 140, 15, "file:src/application/darkCaveTile.jpg");
        L3partform2 = new Platform(530, 270, 140, 15, "file:src/application/darkCaveTile.jpg");
        L3partform3 = new Platform(330, 220, 140, 15, "file:src/application/darkCaveTile.jpg");
        L3partform4 = new Platform(540, 180, 100, 15, "file:src/application/darkCaveTile.jpg");
        L3partform5 = new Platform(110, 300, 140, 15, "file:src/application/darkCaveTile.jpg");
        L3partform6 = new Platform(0, 250, 70, 15, "file:src/application/darkCaveTile.jpg");
        L3partform7 = new Platform(120, 200, 150, 15, "file:src/application/darkCaveTile.jpg");
        L3partform8 = new Platform(680, 130, 150, 15, "file:src/application/darkCaveTile.jpg");
        L3partform9 = new Platform(0, 130, 100, 15, "file:src/application/darkCaveTile.jpg");
        
        end = new Platform(150, 70, 500, 15, "file:src/application/GrassTile.png");
        campfire = new Platform(400, 30, 50, 50, "file:src/application/campFire.png");
        
        trap = new Traps(730, 810, 20, 25, "file:src/application/trap.png");
        trap1 = new Traps(750, 810, 20, 25, "file:src/application/trap.png");
        trap2 = new Traps(700, 800-20, 30, 30, "file:src/application/trap.png");
        trap3 = new Traps(800, 850-20-116, 90, 25, "file:src/application/trap.png");
        trap4 = new Traps(415, 675, 30, 25, "file:src/application/trap.png");
        trap5 = new Traps(335, 675, 30, 25, "file:src/application/trap.png");
        trap6 = new Traps(585, 690, 30, 25, "file:src/application/trap.png");
        trap7 = new Traps(780, 620, 30, 25, "file:src/application/trap.png");
        trap8 = new Traps(20, 760, 30, 25, "file:src/application/trap.png");
        trap9 = new Traps(520, 570, 30, 18, "file:src/application/trap.png");
        trap10 = new Traps(650, 600, 300, 15, "file:src/application/longTrap.png");
        trap11 = new Traps(20, 590, 30, 25, "file:src/application/trap.png");
        trap12 = new Traps(430, 500, 270, 15, "file:src/application/longTrap.png");
        trap13 = new Traps(320, 465, 30, 20, "file:src/application/trap.png");
        
        root.getChildren().addAll(player.getNode(), ground.getNode(), 
        		platform1.getNode(), platform2.getNode(), platform3.getNode(), 
        		platform4.getNode(), platform5.getNode(), platform6.getNode(), 
        		platform7.getNode(), platform8.getNode(), platform9.getNode(),
        		platform10.getNode(), platform11.getNode(), platform12.getNode(),
        		platform13.getNode(),
        		L2partform.getNode(), L2partform1.getNode(), L2partform2.getNode(),
        		L2partform3.getNode(),L2partform4.getNode(), L2partform5.getNode(),
        		L2partform6.getNode(),L2partform7.getNode(), L2partform8.getNode(), 
        		L2partform9.getNode(), L2partform10.getNode(), L2partform11.getNode(),
        		L3partform.getNode(), L3partform1.getNode(), L3partform2.getNode(),
        		L3partform3.getNode(), L3partform4.getNode(), L3partform5.getNode(),
        		L3partform6.getNode(), L3partform7.getNode(), L3partform8.getNode(),
        		L3partform9.getNode(), end.getNode(), campfire.getNode(), 
        		trap.getNode(), trap1.getNode(), trap2.getNode(), trap3.getNode(),
        		trap4.getNode(), trap5.getNode(), trap6.getNode(), trap7.getNode(),
        		trap8.getNode(), trap9.getNode(), trap10.getNode(), trap11.getNode(),
        		trap12.getNode(), trap13.getNode());
        
        root.getChildren().addAll(lifeRectangles);
        
        player.setPlayerListener(() -> showGameOverPage());

        scene.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            if (keyCode == KeyCode.SPACE) {
                player.jump();
            } else if (keyCode == KeyCode.A) {
                player.startMovingLeft();
            } else if (keyCode == KeyCode.D) {
                player.startMovingRight();
            }
        });

        scene.setOnKeyReleased(e -> {
            KeyCode keyCode = e.getCode();
            if (keyCode == KeyCode.A) {
                player.stopMovingLeft();
            } else if (keyCode == KeyCode.D) {
                player.stopMovingRight();
            }
        });
        

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        gameLoop.start();
        PlayerListener resetListener = () -> {
            gameLoop.stop();
            root.getChildren().clear();
            showGameOverPage();
        };
        player.setPlayerListener(resetListener);
        primaryStage.setScene(scene);
    }

    private void update() {
        player.update();
        trap.checkTrapCollision(player);
        trap1.checkTrapCollision(player);
        trap2.checkTrapCollision(player);
        trap2.rotate(180);
        trap3.checkTrapCollision(player);
        trap3.rotate(180);
        trap4.checkTrapCollision(player);
        trap4.rotate(180);
        trap5.checkTrapCollision(player);
        trap5.rotate(180);
        trap6.checkTrapCollision(player);
        trap6.rotate(180);
        trap7.checkTrapCollision(player);
        trap7.rotate(-90);
        trap8.checkTrapCollision(player);
        trap8.rotate(90);
        trap9.checkTrapCollision(player);
        trap10.checkTrapCollision(player);
        trap10.rotate(180);
        trap11.checkTrapCollision(player);
        trap11.rotate(90);
        trap12.checkTrapCollision(player);
        trap12.rotate(180);
        trap13.checkTrapCollision(player);

        
        if(player.getY() > WINDOW_HEIGHT) {
        	player.falldeath();
        	player.decreaseLife();
        }
        
        if (player.intersects(campfire.getNode())) {
        	playerWins();
        }
        
        if (player.intersects(ground.getNode())) {
            player.stopFalling(ground.getY() - player.getHeight());
        } else if (player.intersects(platform1.getNode())) {
            player.stopFalling(platform1.getY() - player.getHeight());
        } else if (player.intersects(platform2.getNode())) {
            player.stopFalling(platform2.getY() - player.getHeight());
        } else if (player.intersects(platform3.getNode())) {
            player.stopFalling(platform3.getY() - player.getHeight());
        } else if (player.intersects(platform4.getNode())) {
            player.stopFalling(platform4.getY() - player.getHeight());
        } else if (player.intersects(platform5.getNode())) {
            player.stopFalling(platform5.getY() - player.getHeight());
        } else if (player.intersects(platform6.getNode())) {
            player.stopFalling(platform6.getY() - player.getHeight());
        } else if (player.intersects(platform7.getNode())) {
            player.stopFalling(platform7.getY() - player.getHeight());
        } else if (player.intersects(platform8.getNode())) {
            player.stopFalling(platform8.getY() - player.getHeight());
        } else if (player.intersects(platform9.getNode())) {
            player.stopFalling(platform9.getY() - player.getHeight());
        } else if (player.intersects(platform10.getNode())) {
            player.stopFalling(platform10.getY() - player.getHeight());
        } else if (player.intersects(platform11.getNode())) {
            player.stopFalling(platform11.getY() - player.getHeight());
        } else if (player.intersects(platform12.getNode())) {
            player.stopFalling(platform12.getY() - player.getHeight());
        } else if (player.intersects(platform13.getNode())) {
            player.stopFalling(platform13.getY() - player.getHeight());
        } else if (player.intersects(L2partform.getNode())) {
            player.stopFalling(L2partform.getY() - player.getHeight());
        } else if (player.intersects(L2partform1.getNode())) {
            player.stopFalling(L2partform1.getY() - player.getHeight());
        } else if (player.intersects(L2partform2.getNode())) {
            player.stopFalling(L2partform2.getY() - player.getHeight());
        } else if (player.intersects(L2partform3.getNode())) {
            player.stopFalling(L2partform3.getY() - player.getHeight());
        } else if (player.intersects(L2partform4.getNode())) {
            player.stopFalling(L2partform4.getY() - player.getHeight());
        } else if (player.intersects(L2partform5.getNode())) {
            player.stopFalling(L2partform5.getY() - player.getHeight());
        } else if (player.intersects(L2partform6.getNode())) {
            player.stopFalling(L2partform6.getY() - player.getHeight());
        } else if (player.intersects(L2partform7.getNode())) {
            player.stopFalling(L2partform7.getY() - player.getHeight());
        } else if (player.intersects(L2partform8.getNode())) {
            player.stopFalling(L2partform8.getY() - player.getHeight());
        } else if (player.intersects(L2partform9.getNode())) {
            player.stopFalling(L2partform9.getY() - player.getHeight());
        } else if (player.intersects(L2partform10.getNode())) {
            player.stopFalling(L2partform10.getY() - player.getHeight());
        } else if (player.intersects(L2partform11.getNode())) {
            player.stopFalling(L2partform11.getY() - player.getHeight());
        } else if (player.intersects(L3partform.getNode())) {
            player.stopFalling(L3partform.getY() - player.getHeight());
        } else if (player.intersects(L3partform1.getNode())) {
            player.stopFalling(L3partform1.getY() - player.getHeight());
        } else if (player.intersects(L3partform2.getNode())) {
            player.stopFalling(L3partform2.getY() - player.getHeight());
        } else if (player.intersects(L3partform3.getNode())) {
            player.stopFalling(L3partform3.getY() - player.getHeight());
        } else if (player.intersects(L3partform4.getNode())) {
            player.stopFalling(L3partform4.getY() - player.getHeight());
        } else if (player.intersects(L3partform5.getNode())) {
            player.stopFalling(L3partform5.getY() - player.getHeight());
        } else if (player.intersects(L3partform6.getNode())) {
            player.stopFalling(L3partform6.getY() - player.getHeight());
        } else if (player.intersects(L3partform7.getNode())) {
            player.stopFalling(L3partform7.getY() - player.getHeight());
        } else if (player.intersects(L3partform8.getNode())) {
            player.stopFalling(L3partform8.getY() - player.getHeight());
        } else if (player.intersects(L3partform9.getNode())) {
            player.stopFalling(L3partform9.getY() - player.getHeight());
        } else if (player.intersects(end.getNode())) {
            player.stopFalling(end.getY() - player.getHeight());
        } 
        
    }
    
    private void showGameOverPage() {
        // Background image
        Image gameOverImage = new Image("file:src/application/gameOverbg.png");
        BackgroundImage background = new BackgroundImage(
                gameOverImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                null, new BackgroundSize(WINDOW_WIDTH, WINDOW_HEIGHT, false, false, false, false)
        );

        // Game over pane with background
        Pane gameOverPane = new Pane();
        gameOverPane.setBackground(new Background(background));

        // Buttons
        Button tryAgainButton = new Button("Try Again");
        Button exitButton = new Button("Exit");

        // Button styles
        tryAgainButton.setStyle("-fx-background-color: yellow; -fx-text-fill: black; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 10px; -fx-cursor: hand;");
        exitButton.setStyle("-fx-background-color: yellow; -fx-text-fill: black; -fx-font-size: 20px; -fx-padding: 10px 20px; -fx-border-radius: 10px; -fx-cursor: hand;");
        
        // Button hover effect
        tryAgainButton.setOnMouseEntered(e ->  tryAgainButton.setScaleX(1.05));
        tryAgainButton.setOnMouseExited(e ->  tryAgainButton.setScaleX(1.0));
        exitButton.setOnMouseEntered(e -> exitButton.setScaleX(1.05));
        exitButton.setOnMouseExited(e -> exitButton.setScaleX(1.0));
        
        // Button actions
        tryAgainButton.setOnAction(e -> startGame());
        exitButton.setOnAction(e -> primaryStage.close());

        // Button layout
        VBox buttonLayout = new VBox(20); // Spacing between buttons
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(tryAgainButton, exitButton);

        // Add button layout to game over pane
        gameOverPane.getChildren().add(buttonLayout);

        // Center the button layout within the window
        buttonLayout.setLayoutX((WINDOW_WIDTH - buttonLayout.getWidth()) / 2);
        buttonLayout.setLayoutY((WINDOW_HEIGHT - buttonLayout.getHeight()) / 2);

        // Set the scene with the game over pane
        primaryStage.setScene(new Scene(gameOverPane, WINDOW_WIDTH, WINDOW_HEIGHT));
    }
    
    private void playerWins() {
    	System.out.println("Player Wins");
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
