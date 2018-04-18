package dogvsmeteorites.lebasoft.dogvsmeteorites.state;


import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import java.util.ArrayList;

import dogvsmeteorites.lebasoft.dogvsmeteorites.main.Assets;
import dogvsmeteorites.lebasoft.dogvsmeteorites.main.MainActivity;
import dogvsmeteorites.lebasoft.dogvsmeteorites.model.Dog;
import dogvsmeteorites.lebasoft.dogvsmeteorites.model.Meteorite;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.Painter;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.UIbutton;

public class PlayState extends State {

    private Dog dog;
    private ArrayList<Meteorite> meteorites;

    private UIbutton moveLeftButton;
    private UIbutton moveRightButton;
    private UIbutton jumpButton;

    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 60;
    private static final int METEORITE_WIDTH = 70;
    private static final int METEORITE_HEIGHT = 70;
    private float meteoriteSpeed = 225;

    private int playerScore = 0;
    private int currentLevel = 1;

    private boolean gamePaused = false;
    private String pausedString = "Game Paused. Tap to resume.";

    @Override
    public void init() {

        moveLeftButton = new UIbutton(30,540,130,640, Assets.moveLeftButton, Assets.moveLeftButtonPressed);
        moveRightButton = new UIbutton(150,540,250,640, Assets.moveRightButton,Assets.moveRightButtonPressed);
        jumpButton = new UIbutton(800, 540, 900, 640, Assets.jumpButton, Assets.jumpButtonPressed);

        dog = new Dog(MainActivity.GAME_WIDTH/2,MainActivity.GAME_HEIGHT-200, PLAYER_WIDTH, PLAYER_HEIGHT);
        meteorites = new ArrayList<Meteorite>();

        for (int i = 0; i<5; i++) {
            Meteorite m = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteorites.add(m);
        }
    }

    @Override
    public void onPause() {
        gamePaused = true;
    }

    @Override
    public void update(float delta) {
        if(gamePaused) {
            return;
        }

        dog.update(delta);
        updateMeteorites(delta);
        playerScore++;

        if(playerScore == 1000) {
            Meteorite newMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteoriteSpeed += 25;
            currentLevel++;
        }

        if(playerScore == 2000) {
            Meteorite newMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteorites.add(newMeteorite);
            meteoriteSpeed += 25;
            currentLevel++;
        }

        if(playerScore == 3000) {
            Meteorite finalMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteoriteSpeed += 25;
            currentLevel++;
        }

        if(playerScore == 4000) {
            Meteorite finalMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteorites.add(finalMeteorite);
            meteoriteSpeed += 25;
            currentLevel++;
        }

        if(playerScore == 5000) {
            Meteorite finalMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteoriteSpeed += 25;
            currentLevel++;
        }

        if(playerScore == 6000) {
            Meteorite finalMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteoriteSpeed += 25;
            meteorites.add(finalMeteorite);
            currentLevel++;
        }

        if(playerScore == 7000) {
            Meteorite finalMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteoriteSpeed += 25;
            currentLevel++;
        }

        if(playerScore == 8000) {
            Meteorite finalMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteoriteSpeed += 25;
            meteorites.add(finalMeteorite);
            currentLevel++;
        }

        if(playerScore == 9000) {
            Meteorite finalMeteorite = new Meteorite(MainActivity.GAME_WIDTH/2, 0, METEORITE_WIDTH, METEORITE_HEIGHT);
            meteoriteSpeed += 25;
            currentLevel++;
        }

    }

    public void updateMeteorites(float delta) {
        for(int i = 0; i < meteorites.size(); i++) {
            Meteorite m = meteorites.get(i);
            m.update(delta, meteoriteSpeed);

            if(Rect.intersects(m.getRect(),dog.getRect())) {
                Assets.playSound(Assets.explodeSound);
                setCurrentState(new GameOverState(playerScore));
            }
        }
    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.playState, 0, 0, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);

        moveRightButton.render(g);
        moveLeftButton.render(g);
        jumpButton.render(g);

        g.drawImage(Assets.doggie, (int) dog.getX(), (int) dog.getY(), PLAYER_WIDTH, PLAYER_HEIGHT);

        for(Meteorite m : meteorites) {
            g.drawImage(Assets.meteorite, (int) m.getX(), (int) m.getY(), METEORITE_WIDTH, METEORITE_HEIGHT);
        }

        g.setFont(Typeface.DEFAULT_BOLD, 25);
        g.setColor(Color.WHITE);
        g.drawString("Points: " + playerScore,20,40);
        g.drawString("Level: ",20, 75);

        g.setColor(Color.RED);
        g.drawString(""+currentLevel, 100, 75);

        if(gamePaused) {
            g.setColor(Color.argb(153,0,0,0));
            g.fillRect(0,0,MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
            g.setFont(Typeface.DEFAULT_BOLD, 60);
            g.drawString(pausedString, 100, 300);
        }

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            moveLeftButton.onTouchDown(scaledX,scaledY);
            moveRightButton.onTouchDown(scaledX, scaledY);
            jumpButton.onTouchDown(scaledX, scaledY);

            if(moveLeftButton.isPressed(scaledX,scaledY)) {
                dog.accelLeft();
                moveRightButton.cancel();
                jumpButton.cancel();

            }else if(moveRightButton.isPressed(scaledX,scaledY)) {
                dog.accelRight();
                moveLeftButton.cancel();
                jumpButton.cancel();

            }else if(jumpButton.isPressed(scaledX, scaledY)) {
                dog.jump();
                moveRightButton.cancel();
                moveLeftButton.cancel();

            }else{
                moveLeftButton.cancel();
                moveRightButton.cancel();
                jumpButton.cancel();
            }
        }

        if(e.getAction() == MotionEvent.ACTION_UP) {
            if (gamePaused) {
                gamePaused = false;
                return true;
            }

            moveLeftButton.cancel();
            moveRightButton.cancel();
            jumpButton.cancel();

            dog.stop();
        }

        return true;
    }

}

