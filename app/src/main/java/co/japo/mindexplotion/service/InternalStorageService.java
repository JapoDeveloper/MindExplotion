package co.japo.mindexplotion.service;

import android.content.Context;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.japo.mindexplotion.model.Game;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by japodeveloper on 10/5/17.
 */

public class InternalStorageService {

    private static final String FILE_STORAGE = "mind_explotion_data";
    private static InternalStorageService instance;
    private static Context context;

    private InternalStorageService(){

    }

    public static InternalStorageService getCurrentInstance(Context appContext){
        if(instance == null){
            instance = new InternalStorageService();
            context = appContext;
            init();
        }
        return instance;
    }

    private static void init(){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(FILE_STORAGE, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(new Game(new Date(),0));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveGame(Game game){
        try {
            List<Game> gamesDeserialized = readGames();
            if(gamesDeserialized == null) {
                gamesDeserialized = new ArrayList<>();
            }
            gamesDeserialized.add(0,game);

            FileOutputStream fileOutputStream = this.context.openFileOutput(FILE_STORAGE, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Object objToSave : gamesDeserialized) {
                objectOutputStream.writeObject(objToSave);
            }

            objectOutputStream.reset();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Game> readGames(){
        List<Game> objects = null;

        try {
            FileInputStream fileInputStream = this.context.openFileInput(FILE_STORAGE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Game object = null;
            objects = new ArrayList<>();

            while((object = (Game) objectInputStream.readObject()) != null){
                objects.add(object);
            }

            objectInputStream.reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(EOFException e){
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objects;
    }
}
