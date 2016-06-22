package androidchallenge.nikhil.com.androidchallenge.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidchallenge.nikhil.com.androidchallenge.R;

import static androidchallenge.nikhil.com.androidchallenge.application.MainApplication.notesDBHelper;

/**
 * Created by charurani on 23-06-2016.
 */

public class AddNoteActivity extends AppCompatActivity {

    RelativeLayout parentLayout;
    Button colorRed, colorBlue, colorGreen, colorYellow, colorPurple, selectColor;
    EditText noteTextview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);

        initViews();
        setUpClickListeners();
    }

    private void initViews() {
        this.parentLayout = (RelativeLayout) findViewById(R.id.add_note_parent_layout);
        this.colorRed = (Button) findViewById(R.id.color_one);
        this.colorBlue = (Button) findViewById(R.id.color_two);
        this.colorGreen = (Button) findViewById(R.id.color_three);
        this.colorYellow = (Button) findViewById(R.id.color_four);
        this.colorPurple = (Button) findViewById(R.id.color_five);
        this.selectColor = (Button) findViewById(R.id.select_color_button);
        this.noteTextview = (EditText) findViewById(R.id.add_note_edittext);
    }

    private void setUpClickListeners() {

        this.colorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteTextview.setBackgroundColor(getResources().getColor(R.color.Red));
            }
        });

        this.colorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteTextview.setBackgroundColor(getResources().getColor(R.color.Blue));
            }
        });

        this.colorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteTextview.setBackgroundColor(getResources().getColor(R.color.Green));
            }
        });

        this.colorYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteTextview.setBackgroundColor(getResources().getColor(R.color.Yellow));
            }
        });

        this.colorPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteTextview.setBackgroundColor(getResources().getColor(R.color.Purple));
            }
        });

        this.selectColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = "red";
                ColorDrawable drawable = (ColorDrawable) noteTextview.getBackground();
                if (drawable.getColor() == (int) Color.RED) {
                    color = getResources().getString(R.string.color_red);
                } else if (drawable.getColor() == (int) Color.BLUE) {
                    color = getResources().getString(R.string.color_blue);
                } else if (drawable.getColor() == (int) Color.GREEN) {
                    color = getResources().getString(R.string.color_green);
                } else if (drawable.getColor() == (int) Color.YELLOW) {
                    color = getResources().getString(R.string.color_yellow);
                } else if (drawable.getColor() == (int) Color.MAGENTA) {
                    color = getResources().getString(R.string.color_purple);
                }

                notesDBHelper.insertContact(noteTextview.getText().toString(), color);

                AddNoteActivity.this.finish();
            }
        });
    }
}
