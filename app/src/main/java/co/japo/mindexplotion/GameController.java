package co.japo.mindexplotion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.japo.mindexplotion.model.Game;
import co.japo.mindexplotion.model.Option;
import co.japo.mindexplotion.service.InternalStorageService;
import co.japo.mindexplotion.task.AsyncResponse;
import co.japo.mindexplotion.task.GameChallengeDisplayTask;
import co.japo.mindexplotion.util.AudioPlayer;
import co.japo.mindexplotion.util.NumberGenerationStrategy;

/**
 * Created by japodeveloper on 9/21/17.
 */

public class GameController implements AsyncResponse {

    private final int MIN_VALUE = 1, MAX_VALUE = 4;

    private InternalStorageService internalStorageService;
    private Activity context;
    private Option[] options;
    private AudioPlayer audioPlayer;
    private List<Integer> challenge;
    private Integer evaluationChallengeIndex;
    private GameChallengeDisplayTask challengeDisplayTask;
    private Boolean gameStarted;
    private Integer currentScore;
    private Integer highestScore;

    private List<Game> games;


    public GameController(Activity context){
        this.context = context;
        init();

    }

    private void init(){
        this.internalStorageService = InternalStorageService.getCurrentInstance();
        this.options = new Option[] {
                new Option(this.context.findViewById(R.id.startGame),
                        0,R.color.deepPurpleActive,R.color.deepPurpleInactive,R.raw.sound4),
                new Option(this.context.findViewById(R.id.buttonOne),
                        1,R.color.greenActive,R.color.greenInactive,R.raw.sound4),
                new Option(this.context.findViewById(R.id.buttonTwo),
                        2,R.color.redActive,R.color.redInactive,R.raw.sound1),
                new Option(this.context.findViewById(R.id.buttonThree),
                        3,R.color.yellowActive,R.color.yellowInactive,R.raw.sound3),
                new Option(this.context.findViewById(R.id.buttonFour),
                        4,R.color.blueActive,R.color.blueInactive,R.raw.sound2)
        };

        for(Option option: options){
            option.getView().setBackgroundColor(this.context.getResources().getColor(option.getInactive_color()));
        }

        this.audioPlayer = new AudioPlayer(this.context);
        this.gameStarted = false;
        this.currentScore = 0;

        this.games = this.internalStorageService.readGames(context);
        if(games == null){
            this.highestScore = 0;
        }else{
            this.highestScore = games.get(0).getScore();
            for(int i = 1; i < games.size(); i++){
                if(games.get(i).getScore() > this.highestScore){
                    this.highestScore = games.get(i).getScore();
                }
            }
        }
        enableViews(false);

    }

    public void startNewGame(){
        this.challenge = new ArrayList<>();
        this.evaluationChallengeIndex = 0;
        this.currentScore = 0;
        this.challenge.add(NumberGenerationStrategy.random(MIN_VALUE,MAX_VALUE));//Generate initial challenge value
        enableViews(false);
        displayUserChallenge();
        updateCurrentScore();
        updateHighestScore();
    }

    private void newTurn(){
        this.challenge.add(NumberGenerationStrategy.random(MIN_VALUE,MAX_VALUE));
        this.currentScore++;
        System.out.println("Challenge sequence => "+challenge.toString());
        this.evaluationChallengeIndex = 0;
        enableViews(false);
        Toast.makeText(this.context,R.string.user_correct_response,Toast.LENGTH_SHORT).show();
        displayUserChallenge();
        updateCurrentScore();
    }

    private void displayUserChallenge(){
        challengeDisplayTask = new GameChallengeDisplayTask(this.context, this.options, audioPlayer);
        challengeDisplayTask.setDelegate(this);
        this.challengeDisplayTask.execute(this.challenge.toArray(new Integer[this.challenge.size()]));
    }

    public void processUserSelection(View view){
        Option option = getOptionByView(view);
        if(option != null) {
            animateButton(view, option);
            boolean valid = option.getValue() == challenge.get(this.evaluationChallengeIndex);
            System.out.println("Level "+challenge.size()+" => index "+this.evaluationChallengeIndex+", value expected "+challenge.get(this.evaluationChallengeIndex)+", value getted "+option.getValue());

            if (!valid) {
                gameOver();
            }else if(this.evaluationChallengeIndex+1 == challenge.size()){
                newTurn();
            }else {
                this.evaluationChallengeIndex++;
            }
        }
    }

    private void animateButton(View view, Option option){
        audioPlayer.playAudio(option.getSound());
        view.setBackgroundColor(context.getResources().getColor(option.getActive_color()));
        view.postDelayed(() -> {
            view.setBackgroundColor(context.getResources().getColor(option.getInactive_color()));
        },550);
    }

    private Option getOptionByView(View view){
        for(Option option : options){
            if(option.getView().getId() == view.getId()){
                return option;
            }
        }
        return null;
    }

    private void enableViews(Boolean enabled){
        //To control the enabling property of user game selection buttons
        for(int j = 1; j < this.options.length; j++){
            this.options[j].getView().setEnabled(enabled);
        }

        //To control the visibility of startGame button.
        if(enabled)
            this.options[0].getView().setVisibility(View.INVISIBLE);
        else if(!this.gameStarted)
            this.options[0].getView().setVisibility(View.VISIBLE);

    }

    private void gameOver(){
        int earnedPoints = challenge.size()-1;
        if(earnedPoints > highestScore) {
            this.highestScore = earnedPoints;
        }

        Game gamePlayed = new Game(new Date(),earnedPoints);
        this.internalStorageService.saveGame(gamePlayed, context);

        audioPlayer.playAudio(R.raw.game_over);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setMessage(R.string.game_over);
        builder.setNeutralButton(R.string.new_game,(dialogInterface, i) -> {
            audioPlayer.stopAudio();
            startNewGame();
        });

        builder.create().show();
    }

    private void updateCurrentScore(){
        ((TextView)this.context.findViewById(R.id.currentScore)).setText("Score: "+this.currentScore);
    }

    private void updateHighestScore(){
        ((TextView)this.context.findViewById(R.id.highestScore)).setText("Highest: "+this.highestScore);
    }


    @Override
    public void processFinish(Object result) {
        this.gameStarted = true;
        enableViews(true);
    }

}
