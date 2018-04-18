package dogvsmeteorites.lebasoft.dogvsmeteorites.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;
import java.io.InputStream;

//MUSIC
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class Assets {

    //List the names of all the images / sounds below as variables (Bitmap)
    private static SoundPool soundPool;

    public static Bitmap moveLeftButton, moveRightButton, jumpButton,
                        moveLeftButtonPressed, moveRightButtonPressed, jumpButtonPressed,
                        newGameButton, highScoreButton, continueButton, howToPlayButton,

                        doggie, meteorite,
                        mainState, playState, secretState, gameOverState, howToPlayState, storyState, highscoreState,
                        lebaSoft;

    public static int jumpSound, explodeSound, themeMusic;

    private static MediaPlayer mediaPlayer;


    public static void load() {

        //Buttons
        newGameButton = loadBitmap("newGameButton.png", false);
        highScoreButton = loadBitmap("highscoreGameButton.png", false);
        moveLeftButton = loadBitmap("moveLeftButton.png", false);
        moveRightButton = loadBitmap("moveRightButton.png", false);
        moveLeftButtonPressed = loadBitmap("moveLeftButtonPressed.png", false);
        moveRightButtonPressed = loadBitmap("moveRightButtonPressed.png", false);
        jumpButton = loadBitmap("jumpButton.png", false);
        jumpButtonPressed = loadBitmap("jumpButtonPressed.png", false);
        continueButton = loadBitmap("continueButton.png", false);
        howToPlayButton = loadBitmap("howToPlayButton.png", false);

        //States
        mainState = loadBitmap("MainState.png", false);
        playState = loadBitmap("playstate.png", false);
        secretState = loadBitmap("secretstate.png" , false);
        gameOverState = loadBitmap("gameOverState.png", false);
        howToPlayState = loadBitmap("howtoplayState.png", false);
        storyState = loadBitmap("storyState.png", false);
        highscoreState = loadBitmap("highscoreState.png", false);
        lebaSoft = loadBitmap("LebasoftLogo.png", false);


        //Models
        doggie = loadBitmap("doggie.png", false);
        meteorite = loadBitmap("meteorite.png", false);

        //.WAV
        jumpSound = loadSound("jump.wav");
        explodeSound = loadSound("Explode.wav");

        //.MP3
        themeMusic = loadSound("theme.mp3");

    }

    private static Bitmap loadBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;

        try {
            inputStream = MainActivity.assets.open(filename);
        }catch(IOException e) {
            e.printStackTrace();
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        if(transparency) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }else{
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, new BitmapFactory.Options());
        return bitmap;
    }

    private static int loadSound (String filename) {
        int soundID = 0;

        if(soundPool == null) {
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC,0);
        }

        try {
            soundID = soundPool.load(MainActivity.assets.openFd(filename),1);
        }catch(IOException e) {
            e.printStackTrace();
        }

        return soundID;
    }

    public static void playSound(int soundID) {
        if (soundPool != null) {
            soundPool.play(soundID, 1, 1, 1, 0, 1);
        }
    }

    public static void onResume() {
        jumpSound = loadSound("jump.wav");
        explodeSound = loadSound("Explode.wav");
        playMusic("theme.mp3", true);
    }

    public static void onPause() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }

        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void playMusic (String filename, boolean looping) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        try {
            AssetFileDescriptor afd = MainActivity.assets.openFd(filename);
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(looping);
            mediaPlayer.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
