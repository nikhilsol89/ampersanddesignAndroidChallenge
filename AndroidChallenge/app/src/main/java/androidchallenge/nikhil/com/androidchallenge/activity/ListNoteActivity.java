package androidchallenge.nikhil.com.androidchallenge.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import androidchallenge.nikhil.com.androidchallenge.R;
import androidchallenge.nikhil.com.androidchallenge.adapters.NotesListAdapter;
import androidchallenge.nikhil.com.androidchallenge.database.NotesDBHelper;
import androidchallenge.nikhil.com.androidchallenge.model.NotesModel;

import static androidchallenge.nikhil.com.androidchallenge.application.MainApplication.notesDBHelper;

public class ListNoteActivity extends AppCompatActivity {

    FloatingActionButton addNoteButton;
    RecyclerView notesList;
    NotesListAdapter listAdapter;


    ArrayList<NotesModel> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteslist);

        initViews();
        setUpClickListeners();

        //getting db helper

        notes = new ArrayList<NotesModel>();

        //setting the adapter to the listview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        notesList.setLayoutManager(layoutManager);
        notesList.setItemAnimator(new DefaultItemAnimator());
        listAdapter = new NotesListAdapter(this, notes);
        notesList.setAdapter(listAdapter);
    }

    private void initViews() {
        this.addNoteButton = (FloatingActionButton) findViewById(R.id.addNoteButton);
        this.notesList = (RecyclerView) findViewById(R.id.notesList);
    }

    private void setUpClickListeners() {
        this.addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListNoteActivity.this, AddNoteActivity.class);
                startActivity(intent);
                //notesDBHelper.insertContact("noteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee ");
            }
        });
    }

    private void fetchDataFromDB() {
        Cursor cursor = notesDBHelper.getData();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                NotesModel notesModel = new NotesModel();
                notesModel.setNote(cursor.getString(1));
                notesModel.setNoteDate(cursor.getString(2));
                notesModel.setNoteColor(cursor.getString(3));

                notes.add(notesModel);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchDataFromDB();
        this.listAdapter.notifyDataSetChanged();
    }
}
