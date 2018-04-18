package dogvsmeteorites.lebasoft.dogvsmeteorites.main;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.view.WindowManager;

public class MainActivity extends Activity {

    public static final int GAME_WIDTH = 960, GAME_HEIGHT = 640;
    public static GameView game;
    public static AssetManager assets;
    private static int highScore;
    private static SharedPreferences prefs;
    private static final String highScoreKey = "highScoreKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getPreferences(Activity.MODE_PRIVATE);
        highScore = retrieveHighScore();

        assets = getAssets();
        game = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(game);
    }

    public static void setHighScore(int highScore) {
        MainActivity.highScore = highScore;
        Editor editor = prefs.edit();
        editor.putInt(highScoreKey, highScore);
        editor.commit();
    }

    private int retrieveHighScore() {
        return prefs.getInt(highScoreKey,0);
    }

    public static int getHighScore() {
        return highScore;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Assets.onResume();
        game.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Assets.onPause();
        game.onPause();
    }
}