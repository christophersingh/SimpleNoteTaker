package com.example.chrislisa.simplenotetaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chrislisa on 12/6/2017.
 */

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, int resource, ArrayList<Note>objects){
        super(context,resource,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //return super.getView(position,convertView,parent);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note,null);
        }

        Note note = getItem(position);

        if(note != null){
            TextView title = (TextView) convertView.findViewById(R.id.list_note_title);
            TextView date = (TextView) convertView.findViewById(R.id.list_note_date);
            TextView content = (TextView) convertView.findViewById(R.id.list_note_content);

            title.setText(note.getTitle());
            date.setText(note.getDateTimeFormatted(getContext()));

            if(note.getContent().length() > 50){
                content.setText(note.getContent().substring(0,50));
            }
            else{
                content.setText(note.getContent());
            }

        }
        return convertView;
    }
}
