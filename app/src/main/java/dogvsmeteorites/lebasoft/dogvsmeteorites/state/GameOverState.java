package dogvsmeteorites.lebasoft.dogvsmeteorites.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import dogvsmeteorites.lebasoft.dogvsmeteorites.main.Assets;
import dogvsmeteorites.lebasoft.dogvsmeteorites.main.MainActivity;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.Painter;
import dogvsmeteorites.lebasoft.dogvsmeteorites.util.UIbutton;


public class GameOverState extends State {

    private String playerScore;
    public UIbutton continueButton;
    private String gameOverMessage;

    public GameOverState(int playerScore) {
        this.playerScore = playerScore + "";

        if (playerScore > MainActivity.getHighScore()) {
                MainActivity.setHighScore(playerScore);
                gameOverMessage = "NEW HIGH SCORE!!!";
        }else{
            gameOverMessage = "";
        }
    }

        @Override
        public void init () {
            continueButton = new UIbutton(380,520,580,620, Assets.continueButton, Assets.continueButton);
        }

        @Override
        public void update ( float delta){

        }

        @Override
        public void render (Painter g) {
            g.drawImage(Assets.gameOverState, 0, 0);
            g.setColor(Color.rgb(252,128, 26));
            g.setFont(Typeface.DEFAULT_BOLD, 45);
            g.drawString("Score: " + playerScore, 380, 150);

            g.setColor(Color.BLACK);
            g.setFont(Typeface.DEFAULT_BOLD, 100);
            g.drawString(gameOverMessage, 40, 300);

            continueButton.render(g);

        }

        @Override
        public boolean onTouch (MotionEvent e,int scaledX, int scaledY){

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
