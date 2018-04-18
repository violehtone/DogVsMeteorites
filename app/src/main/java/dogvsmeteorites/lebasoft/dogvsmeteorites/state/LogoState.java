package dogvsmeteorites.lebasoft.dogvsmeteorites.state;

import android.view.MotionEvent;

import dogvsmeteorites.lebasoft.dogvsmeteorites.main.Assets;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.Painter;


public class LogoState extends State {

    private float counter;

    @Override
    public void init() {
        counter = 0;
    }

    @Override
    public void update(float delta) {
        if(counter == 45) {
            setCurrentState(new MenuState());
        }else{
            counter++;
        }

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.lebaSoft, 0,0);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_UP) {
            setCurrentState(new MenuState());
        }
        return true;
    }
}
