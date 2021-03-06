package co.japo.mindexplotion.util;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by japodeveloper on 9/20/17.
 */

public class AudioPlayer implements Audible{

    private MediaPlayer instance;
    private Context context;

    public AudioPlayer(Context context){
        this.context = context;
    }

    @Override
    public void playAudio(int audioId){
        if(instance != null)
            instance.stop();
        new Thread(() -> {
                instance = MediaPlayer.create(this.context,audioId);
                instance.start();
            }).start();

    }

    @Override
    public void stopAudio(){
        instance.stop();
    }
}
