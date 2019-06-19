package com.example.chrislisa.simplenotetaker;

import android.content.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Chrislisa on 12/6/2017.
 */

public class Utilities {

    public static final String FIlE_EXTENSION = ".bin";

    public static boolean saveNote(Context context, Note note){
        String fileName = String.valueOf(note.getDateTime()) + FIlE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos = context.openFileOutput(fileName,context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(note);
            oos.close();
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static ArrayList<Note> getAllSavedNotes(Context context){
        ArrayList<Note> notes = new ArrayList<>();

        File filesDir = context.getFilesDir();
        ArrayList<String> noteFiles = new ArrayList<>();

        for(String file : filesDir.list()){
            if(file.endsWith(FIlE_EXTENSION)){
                noteFiles.add(file);
            }
        }

        FileInputStream fis;
        ObjectInputStream ois;

        for(int i =0; i < noteFiles.size();i++){
            try{
                fis = context.openFileInput(noteFiles.get(i));
                ois = new ObjectInputStream(fis);

                notes.add((Note)ois.readObject());
                fis.close();
                ois.close();

            }catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
                return null;
            }
        }
        return notes;
    }

    public static Note getNoteByName(Context context, String fileName){
        File file = new File(context.getFilesDir(),fileName);
        Note note;
        if(file.exists()){
            FileInputStream fis;
            ObjectInputStream ois;
        try{
            fis = context.openFileInput(fileName);
            ois = new ObjectInputStream(fis);

            note = (Note) ois.readObject();

            fis.close();
            ois.close();

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
        return note;
        }
        return null;
    }

    public static void deleteNote(Context context, String fileName) {
        File dir = context.getFilesDir();
        File file = new File(dir,fileName);

        if(file.exists()){
            file.delete();
        }

    }
}
