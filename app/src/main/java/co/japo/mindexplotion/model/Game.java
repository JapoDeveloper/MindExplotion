package co.japo.mindexplotion.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by japodeveloper on 10/5/17.
 */

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date playedDate;
    private Integer score;

    public Game(){

    }

    public Game(Date playedDate, Integer score){
        this.playedDate = playedDate;
        this.score = score;
    }

    public Date getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(Date playedDate) {
        this.playedDate = playedDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Game{" +
                "playedDate=" + playedDate +
                ", score=" + score +
                '}';
    }
}
