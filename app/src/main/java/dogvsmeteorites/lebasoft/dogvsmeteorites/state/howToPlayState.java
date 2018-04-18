package dogvsmeteorites.lebasoft.dogvsmeteorites.state;

import android.view.MotionEvent;

import dogvsmeteorites.lebasoft.dogvsmeteorites.main.Assets;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.Painter;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.UIbutton;


public class howToPlayState extends State {

    public UIbutton continueButton;

    @Override
    public void init() {
        continueButton = new UIbutton(100,520,300,620, Assets.continueButton, Assets.continueButton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.howToPlayState, 0, 0);
        continueButton.render(g);

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
