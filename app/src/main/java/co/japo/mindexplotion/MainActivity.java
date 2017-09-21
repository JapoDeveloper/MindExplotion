package co.japo.mindexplotion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import co.japo.mindexplotion.model.Option;
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
        Button startGame = (Button) findViewById(R.id.startGame);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        startGame.setOnClickListener(this);

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
        }
    }

}
