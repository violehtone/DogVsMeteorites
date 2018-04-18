package dogvsmeteorites.lebasoft.dogvsmeteorites.model;

import android.graphics.Rect;

import dogvsmeteorites.lebasoft.dogvsmeteorites.main.MainActivity;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.RandomNumberGenerator;

public class Meteorite {

    private float x, y;
    private float velX, velY;
    private int width, height;
    public Rect meteoriteRect;

    public Meteorite(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        velX = RandomNumberGenerator.getRandomIntBetween(-250, 250);
        velY = 3;
        meteoriteRect = new Rect((int) x, (int) y, (int) x+width, (int) y+height);
    }

    public void update(float delta, float meteoriteSpeed) {

        velY = meteoriteSpeed;

        if(x >= MainActivity.GAME_WIDTH || x+width < 0) {
            resetPosition();
        }else if(y + height >= MainActivity.GAME_HEIGHT - 120) {
            resetPosition();
        }

        x += velX * delta;
        y += velY * delta;
        updateRect();

    }

    public void resetPosition() {
        y = 0;
        x = RandomNumberGenerator.getRandomIntBetween(100,MainActivity.GAME_WIDTH-100);
        velY = 3;
        velX = RandomNumberGenerator.getRandomIntBetween(-250,250);
        updateRect();
    }

    public void updateRect() {
        meteoriteRect.set((int) x + 20, (int) y + 20, (int) x + width - 20, (int) y + height - 20);
    }

    public float getX() {return x;}
    public float getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Rect getRect() {return meteoriteRect;}
}
