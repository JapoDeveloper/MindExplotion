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
        instance = MediaPlayer.create(this.context,audioId);
        instance.start();
    }

    @Override
    public void stopAudio(){
        instance.stop();
    }
}
