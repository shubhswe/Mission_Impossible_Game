import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MP3Player{

    private final Map<String, MediaPlayer> audioMap;
    private final JFXPanel fxPanel;
    private boolean pauseMode;

    public MP3Player(){
        audioMap = new ConcurrentHashMap<>();
        fxPanel = new JFXPanel(); // need to create one instance
        pauseMode = false;
    }

    private void setLoop(MediaPlayer mediaPlayer, boolean loop) {
        if (mediaPlayer == null){
            return;
        }
        if (loop){
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        else{
            mediaPlayer.setCycleCount(1);
        }
    }

    public void play(String filepath){
        play(filepath, false);
    }

    public void play(String filepath, boolean repeat){
        if (pauseMode) return;
        try {
            MediaPlayer mediaPlayer;
            if (audioMap.containsKey(filepath)) {
                mediaPlayer = audioMap.get(filepath);
                this.setLoop(mediaPlayer, repeat);
            }
            else{
                mediaPlayer = new MediaPlayer( new Media(new File(filepath).toURI().toString()) );
                this.setLoop(mediaPlayer, repeat);
                audioMap.put(filepath, mediaPlayer);
            }
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        }
        catch ( Exception e){
            e.printStackTrace();
        }
    }

    public void stop(String filepath){
        try {
            if (audioMap.containsKey(filepath)) {
                MediaPlayer mediaPlayer = audioMap.get(filepath);
                mediaPlayer.pause();
                mediaPlayer.seek(Duration.ZERO);
                audioMap.remove(filepath);
            }
        }
        catch ( Exception e){
            e.printStackTrace();
        }
    }

    public void stopAll(){
        Set<String> keys = audioMap.keySet();
        if (keys.size() == 0) return;
        for (String s : keys){
            stop(s);
        }
        pauseMode = false;
    }

    public void pauseAll(){
        Set<String> keys = audioMap.keySet();
        if (keys.size() == 0) return;
        for (String s : keys){
            if (pauseMode)
                resume(s);
            else
                pause(s);
        }
        pauseMode = !pauseMode;
    }

    private void pause(String filepath){
        try {
            if (audioMap.containsKey(filepath)) {
                MediaPlayer mediaPlayer = audioMap.get(filepath);
                mediaPlayer.pause();
            }
        }
        catch ( Exception e){
            e.printStackTrace();
        }
    }

    private void resume(String filepath){
        try {
            if (audioMap.containsKey(filepath)) {
                MediaPlayer mediaPlayer = audioMap.get(filepath);
                mediaPlayer.play();
            }
        }
        catch ( Exception e){
            e.printStackTrace();
        }
    }

    public void playFX(String filepath){
        try {
            MediaPlayer mediaPlayer = new MediaPlayer( new Media(new File(filepath).toURI().toString()) );
            mediaPlayer.play();
        }
        catch ( Exception e){
            e.printStackTrace();
        }
    }
}