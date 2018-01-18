package com.vudn.contra.model;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundWav extends ISound{
    private Clip clip;

    public SoundWav(String fileName) {
        super(fileName);
        try {
            URL url = getClass().getResource("/res/raw/" + fileName +".wav");
            AudioInputStream input = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(input);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    @Override
    public void play() {
        if (clip.isOpen() && !clip.isRunning()){
            clip.start();
        }
    }

    @Override
    public void stop() {
        if (clip.isRunning()){
            clip.stop();
        }
    }

    @Override
    public void loop(int count) {
        clip.loop(count);
    }

    @Override
    public boolean isRunning() {
        return clip.isRunning();
    }


}
