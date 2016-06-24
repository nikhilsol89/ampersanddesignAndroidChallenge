package androidchallenge.nikhil.com.androidchallenge.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;

import androidchallenge.nikhil.com.androidchallenge.Listeners.RemoveItemListener;
import androidchallenge.nikhil.com.androidchallenge.R;
import androidchallenge.nikhil.com.androidchallenge.adapters.NotesListAdapter;
import androidchallenge.nikhil.com.androidchallenge.model.NotesModel;

import static androidchallenge.nikhil.com.androidchallenge.application.MainApplication.notesDBHelper;

public class ListNoteActivity extends AppCompatActivity implements RemoveItemListener {

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
        listAdapter = new NotesListAdapter(this, notes, this);
        notesList.setAdapter(listAdapter);


        ItemTouchHelper.Callback callback = new MovieTouchHelper(listAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(notesList);
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
                overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
            }
        });
    }

    private void fetchDataFromDB() {
        if (notes != null && notes.size() > 0) {
            notes.clear();
        }

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

    @Override
    public void removeFromList(int listItemPosition) {
        notesDBHelper.removeFromDB(getUniqueIdCorrespondingToPosition(listItemPosition));
        fetchDataFromDB();
        this.listAdapter.notifyDataSetChanged();
    }

    public int getUniqueIdCorrespondingToPosition(int listItemPosition) {
        return notesDBHelper.getId(listItemPosition);
    }

    public class MovieTouchHelper extends ItemTouchHelper.SimpleCallback {
        private NotesListAdapter mNotesListAdapter;

        public MovieTouchHelper(NotesListAdapter NotesListAdapter) {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            this.mNotesListAdapter = NotesListAdapter;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //TODO: Not implemented here
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //Remove item
            notesDBHelper.removeFromDB(getUniqueIdCorrespondingToPosition(viewHolder.getAdapterPosition()));
            //notesDBHelper.deleteNote(notes.get(viewHolder.getAdapterPosition()).getNote(), notes.get(viewHolder.getAdapterPosition()).getNoteDate(), notes.get(viewHolder.getAdapterPosition()).getNoteColor());
            mNotesListAdapter.remove(viewHolder.getAdapterPosition());
        }
    }


}
