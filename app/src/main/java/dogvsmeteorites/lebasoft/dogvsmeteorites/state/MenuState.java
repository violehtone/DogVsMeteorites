package dogvsmeteorites.lebasoft.dogvsmeteorites.state;

import android.view.MotionEvent;

import dogvsmeteorites.lebasoft.dogvsmeteorites.main.Assets;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.Painter;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.UIbutton;

public class MenuState extends State {

    public UIbutton playButton, scoreButton, howToPlayButton;

    @Override
    public void init() {
        playButton = new UIbutton(130,500,330,600, Assets.newGameButton, Assets.newGameButton);
        howToPlayButton = new UIbutton(380, 500, 580, 600, Assets.howToPlayButton, Assets.howToPlayButton);
        scoreButton = new UIbutton(630,500,830,600,Assets.highScoreButton, Assets.highScoreButton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.mainState,0,0);
        playButton.render(g);
        scoreButton.render(g);
        howToPlayButton.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            playButton.onTouchDown(scaledX,scaledY);
            scoreButton.onTouchDown(scaledX,scaledY);
            howToPlayButton.onTouchDown(scaledX, scaledY);
        }

        if(e.getAction() == MotionEvent.ACTION_UP) {
            if(playButton.isPressed(scaledX,scaledY)) {
                scoreButton.cancel();
                howToPlayButton.cancel();
                setCurrentState(new StoryState());

            }else if(scoreButton.isPressed(scaledX,scaledY)) {
                playButton.cancel();
                howToPlayButton.cancel();
                setCurrentState(new HighScoreState());

            }else if(howToPlayButton.isPressed(scaledX, scaledY)) {
                playButton.cancel();
                scoreButton.cancel();
                setCurrentState(new howToPlayState());

            }else{
                playButton.cancel();
                scoreButton.cancel();
                howToPlayButton.cancel();
            }
        }
        return true;
    }

 }
