package Tetris;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioPlayer {
    private String soundsFolder = "tetrisSounds"+ File.separator;
    private String clearLinesPath = soundsFolder+"s1.wav";
    private String gameOverpath = soundsFolder+"s2.wav";
    private Clip clearLineSound, gameOverSound;
    public AudioPlayer() {

        try {
            clearLineSound = AudioSystem.getClip();
            gameOverSound = AudioSystem.getClip();
            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinesPath).getAbsoluteFile()));
            gameOverSound.open(AudioSystem.getAudioInputStream(new File(gameOverpath).getAbsoluteFile()));
        } catch (LineUnavailableException e) {
            //throw new RuntimeException(e);
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE,null,e);
        } catch (UnsupportedAudioFileException e) {
           // throw new RuntimeException(e);
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE,null,e);
        } catch (IOException e) {
            //throw new RuntimeException(e);
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE,null,e);
        }

    }
    public void playClearLine(){
        clearLineSound.start();
        clearLineSound.setFramePosition(0);
    }
    public void playGameOver(){
        gameOverSound.start();
        gameOverSound.setFramePosition(0);
    }
}
