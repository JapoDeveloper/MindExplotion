package co.japo.mindexplotion.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import co.japo.mindexplotion.R;
import co.japo.mindexplotion.model.Option;
import co.japo.mindexplotion.util.AudioPlayer;

/**
 * Created by japodeveloper on 9/21/17.
 */

public class GameChallengeDisplayTask extends AsyncTask<Integer[],Integer,Boolean>{

    private AsyncResponse delegate;
    private Context context;
    private Option[] gameOptions;
    private AudioPlayer audioPlayer;

    public GameChallengeDisplayTask(Context context, Option[] options, AudioPlayer audioPlayer){
        this.context = context;
        this.gameOptions = options;
        this.audioPlayer = audioPlayer;
    }

    @Override
    protected Boolean doInBackground(Integer[]... params) {
        //Initial delay
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer[] challenge = params[0];
        for(int i = 0; i < challenge.length; i++){
            publishProgress(challenge[i]);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isCancelled())
                break;
        }
        return true;
    }

    @Override
    protected void onPreExecute(){
        audioPlayer.playAudio(R.raw.start_game);
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        Button button = getButtonByIndex(values[0]);
        animateButton(button,values[0]);
    }

    @Override
    protected void onPostExecute(Boolean result){
        audioPlayer.stopAudio();
        delegate.processFinish(result);
    }

    @Override
    protected void onCancelled(){
        audioPlayer.stopAudio();
    }

    private Button getButtonByIndex(int index){
        return (Button) gameOptions[index].getView();
    }

    private void animateButton(View view, int index){
        view.setBackgroundColor(ContextCompat.getColor(this.context,gameOptions[index].getActive_color()));
        view.postDelayed(() -> {
            view.setBackgroundColor(ContextCompat.getColor(this.context, gameOptions[index].getInactive_color()));
        },550);
    }

    public void setDelegate(AsyncResponse delegate){
        this.delegate = delegate;
    }
}
