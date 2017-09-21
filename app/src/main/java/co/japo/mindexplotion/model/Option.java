package co.japo.mindexplotion.model;

import android.view.View;

/**
 * Created by japodeveloper on 9/20/17.
 */

public class Option {
    private Integer value;
    private Integer active_color;
    private Integer inactive_color;
    private Integer sound;
    private View view;

    public Option(){

    }

    public Option(View view, Integer value, Integer active_color, Integer inactive_color, Integer sound) {
        this.view = view;
        this.value = value;
        this.active_color = active_color;
        this.inactive_color = inactive_color;
        this.sound = sound;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getActive_color() {
        return active_color;
    }

    public void setActive_color(Integer active_color) {
        this.active_color = active_color;
    }

    public Integer getInactive_color() {
        return inactive_color;
    }

    public void setInactive_color(Integer inactive_color) {
        this.inactive_color = inactive_color;
    }

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
