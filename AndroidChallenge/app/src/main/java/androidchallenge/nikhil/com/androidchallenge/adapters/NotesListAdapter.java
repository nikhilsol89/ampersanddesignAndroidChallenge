package androidchallenge.nikhil.com.androidchallenge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import androidchallenge.nikhil.com.androidchallenge.Listeners.RemoveItemListener;
import androidchallenge.nikhil.com.androidchallenge.R;
import androidchallenge.nikhil.com.androidchallenge.model.NotesModel;

/**
 * Created by Nikhil on 22-06-2016.
 */

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.MyViewHolder> {

    Context context;
    private int lastPosition = -1;
    ArrayList<NotesModel> notes;
    RemoveItemListener listener;

    public NotesListAdapter(Context context, ArrayList<NotesModel> notes, RemoveItemListener listener) {
        this.context = context;
        this.notes = notes;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_noteslist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        setAnimation(holder.cellParentLayout, position);
        holder.position = position;
        holder.notesTextView.setText(this.notes.get(position).getNote());
        if (this.notes.get(position).getNoteColor().equalsIgnoreCase(context.getResources().getString(R.string.color_red)))
            holder.cellParentLayout.setBackgroundColor(context.getResources().getColor(R.color.Red));
        if (this.notes.get(position).getNoteColor().equalsIgnoreCase(context.getResources().getString(R.string.color_blue)))
            holder.cellParentLayout.setBackgroundColor(context.getResources().getColor(R.color.Blue));
        if (this.notes.get(position).getNoteColor().equalsIgnoreCase(context.getResources().getString(R.string.color_green)))
            holder.cellParentLayout.setBackgroundColor(context.getResources().getColor(R.color.Green));
        if (this.notes.get(position).getNoteColor().equalsIgnoreCase(context.getResources().getString(R.string.color_yellow)))
            holder.cellParentLayout.setBackgroundColor(context.getResources().getColor(R.color.Yellow));
        if (this.notes.get(position).getNoteColor().equalsIgnoreCase(context.getResources().getString(R.string.color_purple)))
            holder.cellParentLayout.setBackgroundColor(context.getResources().getColor(R.color.Purple));
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout cellParentLayout;
        TextView notesTextView;
        ImageView cancelNotesImageView;
        int position;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.view = itemView;
            this.cellParentLayout = (LinearLayout) view.findViewById(R.id.cell_parent_layout);
            this.notesTextView = (TextView) view.findViewById(R.id.cell_note_textview);
            this.cancelNotesImageView = (ImageView) view.findViewById(R.id.cell_note_cancel_iamgeview);

            this.cancelNotesImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NotesListAdapter.this.listener.removeFromList(position);
                }
            });
        }
    }

    public void remove(int position) {
        notes.remove(position);
        notifyItemRemoved(position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }
}
