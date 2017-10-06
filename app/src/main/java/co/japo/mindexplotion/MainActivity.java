package co.japo.mindexplotion;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import co.japo.mindexplotion.model.Game;
import co.japo.mindexplotion.model.Option;
import co.japo.mindexplotion.service.InternalStorageService;
import co.japo.mindexplotion.task.AsyncResponse;
import co.japo.mindexplotion.task.GameChallengeDisplayTask;
import co.japo.mindexplotion.util.AudioPlayer;
import co.japo.mindexplotion.util.NumberGenerationStrategy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameController = new GameController(this);

        Button buttonOne = (Button) findViewById(R.id.buttonOne);
        Button buttonTwo = (Button) findViewById(R.id.buttonTwo);
        Button buttonThree = (Button) findViewById(R.id.buttonThree);
        Button buttonFour = (Button) findViewById(R.id.buttonFour);
        ImageButton startGame = (ImageButton) findViewById(R.id.startGame);
        ImageButton gamesList = (ImageButton) findViewById(R.id.gamesListButton);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        startGame.setOnClickListener(this);
        gamesList.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonOne:
                this.gameController.processUserSelection(view);
                break;
            case R.id.buttonTwo:
                this.gameController.processUserSelection(view);
                break;
            case R.id.buttonThree:
                this.gameController.processUserSelection(view);
                break;
            case R.id.buttonFour:
                this.gameController.processUserSelection(view);
                break;
            case R.id.startGame:
                this.gameController.startNewGame();
                break;
            case R.id.gamesListButton:
                Intent intent = new Intent(MainActivity.this,GamesActivity.class);
                startActivity(intent);
                break;
        }
    }

}
