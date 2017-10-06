package co.japo.mindexplotion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.japo.mindexplotion.adapter.GameAdapter;
import co.japo.mindexplotion.model.Game;
import co.japo.mindexplotion.service.InternalStorageService;

public class GamesActivity extends AppCompatActivity {

    private InternalStorageService internalStorageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        internalStorageService = InternalStorageService.getCurrentInstance();

        List<Game> games = internalStorageService.readGames(this);
        if(games == null){
            games = new ArrayList<>();
        }

        ListView gameList = (ListView) findViewById(R.id.gamesList);
        gameList.setAdapter(new GameAdapter(this,games));
    }
}
