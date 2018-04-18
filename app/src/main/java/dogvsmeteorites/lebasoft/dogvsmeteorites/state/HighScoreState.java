package dogvsmeteorites.lebasoft.dogvsmeteorites.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import dogvsmeteorites.lebasoft.dogvsmeteorites.main.Assets;
import dogvsmeteorites.lebasoft.dogvsmeteorites.main.MainActivity;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.Painter;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.UIbutton;

public class HighScoreState extends State {

    private String highScore;
    public UIbutton continueButton;

    @Override
    public void init() {
        highScore = MainActivity.getHighScore() + "";
        continueButton = new UIbutton(380,520,580,620, Assets.continueButton, Assets.continueButton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.highscoreState, 0 , 0);
        continueButton.render(g);
        g.setColor(Color.WHITE);
        g.setFont(Typeface.DEFAULT_BOLD, 150);
        g.drawString(highScore,345,400);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            continueButton.onTouchDown(scaledX,scaledY);
        }

        if(e.getAction() == MotionEvent.ACTION_UP) {
            if (continueButton.isPressed(scaledX, scaledY)) {
                setCurrentState(new MenuState());
            } else {
                continueButton.cancel();
            }
        }

        return true;
    }

}
