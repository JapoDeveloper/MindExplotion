package co.japo.mindexplotion.adapter;

import android.content.Context;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import co.japo.mindexplotion.R;
import co.japo.mindexplotion.model.Game;

/**
 * Created by japodeveloper on 10/5/17.
 */

public class GameAdapter extends ArrayAdapter<Game> {

    private SimpleDateFormat dateFormat;

    public GameAdapter(@NonNull Context context, @NonNull List<Game> objects) {
        super(context, 0, objects);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(
                R.layout.activity_game_item,
                parent,
                false);

        // UI references
        TextView score = (TextView) convertView.findViewById(R.id.score);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        // Current item
        Game game = getItem(position);

        // Setup.
        score.setText(game.getScore().toString());
        date.setText(dateFormat.format(game.getPlayedDate()));

        return convertView;
    }
}
