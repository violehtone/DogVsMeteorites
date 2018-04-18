package dogvsmeteorites.lebasoft.dogvsmeteorites.model;

import android.graphics.Rect;

import dogvsmeteorites.lebasoft.dogvsmeteorites.main.Assets;
import dogvsmeteorites.lebasoft.dogvsmeteorites.main.MainActivity;

public class Dog {

    private float x, y;
    private int width, height;
    private float velX, velY;
    private Rect rect;
    private Rect ground;

    private static final int JUMP_VELOCITY = -800, ACCEL_GRAVITY = 1600, MOVE_SPEED = 10;
    public boolean isGrounded;


    public Dog(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rect = new Rect();
        ground = new Rect(0, 515, MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);

        velX = 0;
        velY = 0;

        isGrounded = true;
    }

    public void update(float delta) {

        //Gravity & jumping:
        if (!isGrounded()) {
            velY += ACCEL_GRAVITY * delta;
        } else {
            y = 516 - height;
            velY = 0;
        }

        //Test player leaving the play-area:
        if(x <= 5) {
            velX = 0;
            x += 5;

        }else if(x + width >= MainActivity.GAME_WIDTH - 5) {
            velX = 0;
            x -= 5;

        }

        //Update player's x and y coordinates:
        x += velX;
        y += velY * delta;
        updateRects();
    }


    public boolean isGrounded() {
        return Rect.intersects(rect, ground);
    }

    public void updateRects() {
        rect.set((int) x , (int) y , (int)(x +  width), (int) (y + height));
    }

    public void jump() {
        if(isGrounded()) {
            Assets.playSound(Assets.jumpSound);
            y -= 10;
            velY = JUMP_VELOCITY;
            updateRects();
        }
    }

    public void stop() {
        velX = 0;
    }

    public void accelLeft() {
        velX = -MOVE_SPEED;
    }

    public void accelRight() {
        velX = MOVE_SPEED;
        }

    public float getX() {return x;}
    public float getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Rect getRect() {return rect;}
    public Rect getGround() {return ground;}



}
