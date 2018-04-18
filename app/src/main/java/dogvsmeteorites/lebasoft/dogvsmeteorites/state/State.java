package dogvsmeteorites.lebasoft.dogvsmeteorites.state;

import android.view.MotionEvent;
import dogvsmeteorites.lebasoft.dogvsmeteorites.main.MainActivity;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.Painter;


public abstract class State {

    public void setCurrentState (State newState) {
        MainActivity.game.setCurrentState(newState);
    }
    public abstract void init();
    public abstract void update(float delta);
    public abstract void render(Painter g);
    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);

    public void onResume() {}
    public void onPause() {}
}
